import 'package:asna_chat/constants/app_colors.dart';
import 'package:asna_chat/controllers/login_controller.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/widgets/app_button.dart';
import 'package:asna_chat/widgets/app_text_form_field.dart';
import 'package:flutter/material.dart';

class LoginScreen extends BaseView<LoginController> {

  const LoginScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return Scaffold(
      backgroundColor: AppColors.green,
      body: SingleChildScrollView(
        child: Column(
          children: [

            const SizedBox(
              height: 50,
            ),

            Center(
              child: Container(
                padding: const EdgeInsets.all(10),
                width: 200,
                height: 100,
                decoration: const BoxDecoration(
                  shape: BoxShape.circle,
                    color: Colors.white
                ),
                child: const Icon(Icons.person, size: 50, color: AppColors.green,),
              ),
            ),

            const SizedBox(
              height: 50,
            ),

            Container(
              padding: const EdgeInsets.all(24),
              decoration: const BoxDecoration(
                  color: Colors.white,
                  borderRadius: BorderRadius.only(
                      topRight: Radius.circular(24),
                      topLeft: Radius.circular(24)
                  )
              ),
              child: Column(
                children: [

                  const SizedBox(
                    height: 60,
                  ),

                  AppTextFormField(controller: controller.loginController, textInputType: TextInputType.number, label: "Mobile", iconData: Icons.phone,),

                  const SizedBox(height: 40,),

                  AppTextFormField(controller: controller.passwordController, textInputType: TextInputType.text, label: "Mot de passe", iconData: Icons.lock, hidden: true,),

                  const SizedBox(height: 50,),

                  Row(
                    children: [
                      Expanded(
                        child: AppButton(
                          height: 50,
                          color: AppColors.green,
                          child: const Text("Connexion", style: TextStyle(
                            color: Colors.white,
                          ),),
                          shapeBorder: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(100)
                          ),
                          onTap: controller.onLoginButtonPress,
                        ),
                      ),
                    ],
                  ),

                  const SizedBox(height: 40,),

                  Row(
                    children: [
                      Expanded(
                        child: AppButton(
                          height: 50,
                          child: const Text("Inscription", style: TextStyle(
                            color: AppColors.green,
                          ),),
                          shapeBorder: RoundedRectangleBorder(
                              borderRadius: BorderRadius.circular(100),
                              side: const BorderSide(
                                color: AppColors.green,
                              )
                          ),
                          onTap: controller.onRegistrationButtonPress,
                        ),
                      ),
                    ],
                  ),

                  const SizedBox(
                    height: 300,
                  ),

                ],
              ),
            )

          ],
        ),
      ),
    );
  }

}