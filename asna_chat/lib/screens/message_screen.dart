import 'package:asna_chat/controllers/message_controller.dart';
import 'package:asna_chat/models/message.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/widgets/chat_input_field.dart';
import 'package:flutter/material.dart';
import 'package:asna_chat/widgets/message.dart' as asmessage;
import '../widgets/body.dart';

class MessagesScreen extends BaseView<MessageController> {

  const MessagesScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return Scaffold(
      appBar: buildAppBar(),
      body: FutureBuilder(
        future: controller.firestoreProvider.getIdByLogin(controller.contact.contact!),
        builder: (context, snap) => StreamBuilder<List<Message>>(
            stream: controller.firestoreProvider.getMessages(controller.contact.contact ?? ""),
            builder: (context, snapshot) {
              if (snapshot.hasError) {
                return const Center(child: Text("Une erreur est survenue"));
              } else if(snapshot.hasData) {
                List<Message> messages = [];
                messages = snapshot.data ?? [];
                messages = messages.where((element) {
                  return (element.sender == controller.user.id && element.recipient == snap.data) ||
                      (element.sender == snap.data && element.recipient == controller.user.id);
                }).toList();
                return Column(
                  children: [
                    Expanded(
                      child: Padding(
                        padding: const EdgeInsets.symmetric(horizontal: 20),
                        child: ListView.builder(
                          reverse: true,
                          itemCount: messages.length,
                          itemBuilder: (context, index) =>
                              asmessage.Message(message: messages[index]),
                        ),
                      ),
                    ),
                    const ChatInputField(),
                  ],
                );
              } else {
                return const Center(child: CircularProgressIndicator());
              }
            }
        ),
      ),
    );
  }

  AppBar buildAppBar() {
    return AppBar(
      automaticallyImplyLeading: false,
      title: Row(
        children: [
          const BackButton(),
          const SizedBox(width: 20 * 0.75),
          Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Text(
                controller.contact.fullName!,
                style: TextStyle(fontSize: 16),
              ),
              const Text(
                "Actif",
                style: TextStyle(fontSize: 12),
              )
            ],
          )
        ],
      ),
    );
  }

}
