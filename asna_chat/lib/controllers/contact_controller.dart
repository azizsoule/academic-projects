
import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/models/message.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

class ContactController extends GetxController {

  FirestoreProvider firestoreProvider = FirestoreProvider();

  List<Contact> selectedContact = [];

  TextEditingController messageController = TextEditingController();

  onCheckContact(Contact data) {
    if (selectedContact.firstWhere((element) => element.id == data.id, orElse: () => Contact()).id != null) {
      selectedContact.removeWhere((element) => element.id == data.id);
    } else {
      selectedContact.add(data);
    }
    print(selectedContact.length);
    update();
  }

  onSendButtonPress() async {
    if (messageController.text.trim().isEmpty) return;
    if (selectedContact.isEmpty) return;
    for (var element in selectedContact) {
      await firestoreProvider.sendMessage(element.contact!, Message(content: messageController.text.trim()));
    }
    messageController.clear();
    selectedContact.clear();
    update();
    Get.defaultDialog(title: "Info", content: const Text("Messages envoyés"), onConfirm: Get.back);
  }

}