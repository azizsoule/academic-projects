import 'package:asna_chat/controllers/contact_controller.dart';
import 'package:asna_chat/models/contact.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/widgets/app_button.dart';
import 'package:flutter/material.dart';

class ContactScreen extends BaseView<ContactController> {

  const ContactScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return FutureBuilder(
      future: controller.firestoreProvider.getContacts(),
      builder: (BuildContext context, AsyncSnapshot<List<Contact>> snapshot) {
        if (snapshot.hasError) {
          return const Center(child: Text("Une erreur est survenue"));
        } else if (snapshot.hasData) {
          return Column(
            children: [
              Expanded(
                child: ListView.builder(
                  itemCount: snapshot.data!.length,
                  itemBuilder: (context, index) => Row(
                    children: [
                      Checkbox(value: controller.selectedContact.firstWhere((element) => element.id == snapshot.data![index].id, orElse:() => Contact()).id != null, onChanged: (val) => controller.onCheckContact(snapshot.data![index])),
                      Expanded(
                        child: ListTile(
                          leading: CircleAvatar(
                            child: Text(snapshot.data![index].fullName![0]),
                          ),
                          title: Text(snapshot.data![index].fullName!),
                          subtitle: Text(snapshot.data![index].contact!),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
              TextFormField(
                controller: controller.messageController,
              ),
              Row(
                children: [
                  Expanded(
                    child: AppButton(
                      onTap: controller.onSendButtonPress,
                      child: const Text("Envoyer"),
                    ),
                  ),
                ],
              ),
            ],
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