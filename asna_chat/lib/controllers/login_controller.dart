import 'package:asna_chat/controllers/chats_controller.dart';
import 'package:asna_chat/controllers/contact_controller.dart';
import 'package:asna_chat/controllers/home_controller.dart';
import 'package:asna_chat/controllers/registration_controller.dart';
import 'package:asna_chat/models/user.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:asna_chat/screens/home_screen.dart';
import 'package:asna_chat/screens/registration_screen.dart';
import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

class LoginController extends GetxController {

  final FirestoreProvider _firestoreProvider = FirestoreProvider();

  TextEditingController loginController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  void onLoginButtonPress() {
    _login();
  }

  void onRegistrationButtonPress() {
    Get.put<RegistrationController>(RegistrationController());
    Get.to(() => const RegistrationScreen());
  }

  void _login() async {
    User user = User(login: loginController.text, password: passwordController.text);
    User? resUser = await _firestoreProvider.loginUser(user);
    if (resUser == null) {
      Get.defaultDialog(
        title: "Erreur",
        onConfirm: Get.back,
        content: const Text("Login ou mot de passe incorrect")
      );
    } else {
      Get.put<HomeController>(HomeController());
      Get.put<ChatsController>(ChatsController());
      Get.put<ContactController>(ContactController());
      Get.off(() => const HomeScreen());
    }
  }

}