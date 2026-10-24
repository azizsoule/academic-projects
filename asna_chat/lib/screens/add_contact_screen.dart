import 'package:asna_chat/controllers/add_contact_controller.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:asna_chat/widgets/app_button.dart';
import 'package:flutter/material.dart';

import '../constants/app_colors.dart';

class AddContactScreen extends BaseView<AddContactController> {

  const AddContactScreen({Key? key}) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text("Ajouter un nouveau contact"),
      ),
      body: ListView(
        padding: EdgeInsets.all(24),
        children: [

          TextFormField(
            decoration: InputDecoration(
              hintText: "Nom complet",
            ),
            controller: controller.nameController,
          ),

          SizedBox(height: 20,),

          TextFormField(
            decoration: InputDecoration(
              hintText: "Contact",
            ),
            controller: controller.contactController,
          ),

          SizedBox(height: 20,),

          DropdownButton(
            hint: Text("Statut"),
            value: controller.contactStatus,
            items: [
              DropdownMenuItem(child: Text("Professeur"), value: "P",),
              DropdownMenuItem(child: Text("Etudiant"), value: "E",),
            ],
            onChanged: controller.onContactStatusChanged,
          ),

          SizedBox(height: 20,),

          TextFormField(
            decoration: InputDecoration(
                hintText: "Spécialité / Filière"
            ),
            controller: controller.specialityController,
          ),

          SizedBox(height: 20,),

          AppButton(
            height: 50,
            child: const Text("Ajouter", style: TextStyle(
              color: Colors.white,
            ),),
            color: AppColors.green,
            shapeBorder: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(100),
            ),
            onTap: controller.onAddButtonPress,
          )

        ],
      ),
    );
  }

}