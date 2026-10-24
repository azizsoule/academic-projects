import 'package:asna_chat/screens/add_contact_screen.dart';
import 'package:get/get.dart';

import 'add_contact_controller.dart';

class HomeController extends GetxController {

  int selectedIndex = 0;

  void onBottomNavBarTap(int value) {
    selectedIndex = value;
    update();
  }

  void onFABTap() {
    if (selectedIndex == 1) {
      Get.put<AddContactController>(AddContactController());
      Get.to(() => const AddContactScreen(),);
    }
  }

}