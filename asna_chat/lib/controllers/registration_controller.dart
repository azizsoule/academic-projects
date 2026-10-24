import 'package:asna_chat/models/user.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

class RegistrationController extends GetxController {

  final FirestoreProvider _firestoreProvider = FirestoreProvider();

  TextEditingController fullNameController = TextEditingController();

  TextEditingController mobileController = TextEditingController();

  TextEditingController passwordController = TextEditingController();

  TextEditingController confirmPasswordController = TextEditingController();

  void onLoginButtonPress() {
    Get.back();
  }

  void onRegistrationButtonPress() async {
    if (passwordController.text.trim() != confirmPasswordController.text.trim()) {
      Get.defaultDialog(
          title: "Erreur",
          onConfirm: Get.back,
          content: const Text("Les mots de passe ne concordent pas")
      );
    } else {
      User user = User();
      user.fullName = fullNameController.text.trim();
      user.login = mobileController.text.trim();
      user.password = passwordController.text.trim();
      User? userRes = await _firestoreProvider.addUser(user);
      if (userRes == null) {
        Get.defaultDialog(
            title: "Erreur",
            onConfirm: Get.back,
            content: const Text("Inscription échouée")
        );
      } else {
        Get.defaultDialog(
            title: "Succès",
            onConfirm: () {
              Get.back();
              Get.back();
            },
            content: const Text("Inscription réussie")
        );
      }
    }
  }

}