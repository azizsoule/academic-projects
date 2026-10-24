import 'package:asna_chat/controllers/contact_controller.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

import '../models/contact.dart';

class AddContactController extends GetxController {

  final FirestoreProvider _firestoreProvider = FirestoreProvider();

  final TextEditingController nameController = TextEditingController();

  String? contactStatus;

  final TextEditingController contactController = TextEditingController();

  void onContactStatusChanged(String? v) {
    contactStatus = v;
    update();
  }

  final TextEditingController specialityController = TextEditingController();

  void onAddButtonPress() async {
    Contact? c = await _firestoreProvider.addContact(Contact(
      fullName: nameController.text.trim(),
      contact: contactController.text.trim(),
      personStatus: contactStatus,
      speciality: specialityController.text.trim()
    ));

    if (c != null) {
      Get.find<ContactController>().update();
      contactController.clear();
      nameController.clear();
      specialityController.clear();
      Get.back();
    } else {
      print("Erreur");
    }
  }

}