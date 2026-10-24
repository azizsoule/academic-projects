import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/models/message.dart';
import 'package:asna_chat/models/user.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';
import 'package:get_storage/get_storage.dart';

class MessageController extends GetxController {

  final GetStorage _getStorage = GetStorage();

  final Contact contact = Get.arguments;

  final FirestoreProvider firestoreProvider = FirestoreProvider();

  User get user => User.fromJson(_getStorage.read("user"));

  TextEditingController message = TextEditingController();

  onSendButtonTap() async {
    await firestoreProvider.sendMessage(contact.contact!, Message(content: message.text.trim()));
    message.clear();
  }

}