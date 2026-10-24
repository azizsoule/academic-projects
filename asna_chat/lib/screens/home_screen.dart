import 'package:asna_chat/constants/app_colors.dart';
import 'package:asna_chat/controllers/home_controller.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/screens/chats_screen.dart';
import 'package:asna_chat/screens/contact_screen.dart';
import 'package:flutter/material.dart';

class HomeScreen extends BaseView<HomeController> {

  const HomeScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return Scaffold(
      bottomNavigationBar: buildBottomNavigationBar(),
      appBar: buildAppBar(),
      body: controller.selectedIndex == 0 ? const ChatsScreen() : const ContactScreen(),
      floatingActionButton: FloatingActionButton(
        onPressed: controller.onFABTap,
        backgroundColor: AppColors.green,
        child: Icon(
          controller.selectedIndex == 0 ? Icons.message : Icons.person_add_alt_1,
          color: Colors.white,
        ),
      ),
    );
  }

  AppBar buildAppBar() {
    return AppBar(
      automaticallyImplyLeading: false,
      title: const Text("Asna Chat"),
    );
  }

  BottomNavigationBar buildBottomNavigationBar() {
    return BottomNavigationBar(
      type: BottomNavigationBarType.fixed,
      currentIndex: controller.selectedIndex,
      onTap: controller.onBottomNavBarTap,
      items: const [
        BottomNavigationBarItem(icon: Icon(Icons.messenger), label: "Chats"),
        BottomNavigationBarItem(icon: Icon(Icons.people), label: "Contacts"),
      ],
    );
  }

}