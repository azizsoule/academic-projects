import 'package:asna_chat/controllers/message_controller.dart';
import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/providers/firestore_provider.dart';
import 'package:asna_chat/screens/message_screen.dart';
import 'package:get/get.dart';

class ChatsController extends GetxController {

  final FirestoreProvider firestoreProvider = FirestoreProvider();

  onChatTap(Contact contact) {
    Get.to(() => const MessagesScreen(), arguments: contact);
    Get.put<MessageController>(MessageController());
  }



}