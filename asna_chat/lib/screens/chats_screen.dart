import 'package:asna_chat/controllers/chats_controller.dart';
import 'package:asna_chat/models/Chat.dart';
import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/widgets/chat_card.dart';
import 'package:flutter/material.dart';

import 'message_screen.dart';

class ChatsScreen extends BaseView<ChatsController> {

  const ChatsScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return FutureBuilder(
      future: controller.firestoreProvider.getContacts(),
      builder: (BuildContext context, AsyncSnapshot<List<Contact>> snapshot) {
        if (snapshot.hasError) {
          return const Center(child: Text("Une erreur est survenue"));
        } else if (snapshot.hasData) {
          return ListView.builder(
            itemCount: snapshot.data!.length,
            itemBuilder: (context, index) => ChatCard(
              chat: snapshot.data![index],
              press: () => controller.onChatTap(snapshot.data![index]),
            ),
          );
        }  else {
          return const Center(
            child: CircularProgressIndicator(),
          );
        }
      },
    );
  }

}