import 'package:asna_chat/constants/app_theme.dart';
import 'package:asna_chat/controllers/login_controller.dart';
import 'package:asna_chat/screens/login_screen.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:get/get_navigation/src/root/get_material_app.dart';
import 'package:get_storage/get_storage.dart';
import 'firebase_options.dart';

void initServices() async {
  await GetStorage.init();
}

void main() async {
  initServices();
  WidgetsFlutterBinding.ensureInitialized();
  await Firebase.initializeApp(
    options: DefaultFirebaseOptions.currentPlatform,
  );
  runApp(const AsnaChat());
}

class AsnaChat extends StatelessWidget {

  const AsnaChat({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GetMaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Asna Chat',
      theme: AppTheme.theme,
      home: const LoginScreen(),
      initialBinding: BindingsBuilder(
          () {
            Get.put<LoginController>(LoginController());
          }
      ),
    );
  }
}