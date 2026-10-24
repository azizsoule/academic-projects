import 'dart:async';
import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/models/message.dart';
import 'package:asna_chat/models/user.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:get_storage/get_storage.dart';

class FirestoreProvider {

  final FirebaseFirestore _firebaseFirestore = FirebaseFirestore.instance;

  final GetStorage _box = GetStorage();

  Future<User?> addUser(User user) async {
    bool existByLogin = await _existByLogin(user.login!);
    if (!existByLogin) {
      DocumentReference<Map<String, dynamic>> ref = await _firebaseFirestore.collection("users").add(user.toJson());
      DocumentSnapshot<Map<String, dynamic>> doc = await ref.get();
      User resUser = User.fromJson(doc.data());
      resUser.id = doc.id;
      return resUser;
    } else {
      return null;
    }
  }

  Future<User?> loginUser(User user) async {
    QuerySnapshot<Map<String, dynamic>> snapshot = await _firebaseFirestore.collection("users").where("login", isEqualTo: user.login!.trim()).where("password", isEqualTo: user.password!.trim()).get();
    if (snapshot.docs.isEmpty) {
      return null;
    } else {
      User resultUser = User.fromJson(snapshot.docs.first.data());
      resultUser.id = snapshot.docs.first.id;
      await _box.write("user", resultUser.toJson(withId: true));
      return resultUser;
    }
  }

  Future<bool> _existByLogin(String login) async {
    QuerySnapshot<Map<String, dynamic>> snapshot = await _firebaseFirestore.collection("users").where("login", isEqualTo: login.trim()).get();
    return snapshot.docs.isNotEmpty;
  }

  Future<String?> getIdByLogin(String login) async {
    QuerySnapshot<Map<String, dynamic>> snapshot = await _firebaseFirestore.collection("users").where("login", isEqualTo: login.trim()).get();
    return snapshot.docs.isNotEmpty ? snapshot.docs.first.id : null;
  }

  Future<Contact?> addContact(Contact contact) async {
    User user = User.fromJson(_box.read("user"));
    contact.owner = user.id;
    DocumentReference<Map<String, dynamic>> ref = await _firebaseFirestore.collection("contacts").add(contact.toJson());
    DocumentSnapshot<Map<String, dynamic>> doc = await ref.get();
    contact = Contact.fromJson(doc.data());
    contact.id = doc.id;
    return contact;
  }

  Future<List<Contact>> getContacts() async {
    User user = User.fromJson(_box.read("user"));
    QuerySnapshot<Map<String, dynamic>> qs = await _firebaseFirestore.collection("contacts").where("owner", isEqualTo: user.id).orderBy("fullName").get();
    List<Contact> contacts = [];
    for (var element in qs.docs) {
      Contact newContact = Contact.fromJson(element.data());
      newContact.id = element.id;
      contacts.add(newContact);
    }
    return contacts;
  }

  Future<void> deleteContact(Contact contact) async {
    return await _firebaseFirestore.collection("contacts").doc(contact.id).delete();
  }

  Future<void> sendMessage(String recipientLogin, Message message) async {
    User user = User.fromJson(_box.read("user"));
    message.sender = user.id;
    message.recipient = await getIdByLogin(recipientLogin);
    await _firebaseFirestore.collection("messages").add(message.toJson());
  }

  Stream<List<Message>> getMessages(String recipientLogin) {
    User user = User.fromJson(_box.read("user"));
    String recipId = "";
    getIdByLogin(recipientLogin).then((value) => recipId = value ?? "");
    print(recipId);
    Stream<QuerySnapshot<Map<String, dynamic>>> stream = _firebaseFirestore.collection("messages").orderBy("date", descending: true).snapshots();
    Stream<List<Message>> streamMessages =  stream.map<List<Message>>((event) => List<Message>.generate(event.docs.length, (index) => Message.fromJson(event.docs[index])));
    return streamMessages;
  }

}