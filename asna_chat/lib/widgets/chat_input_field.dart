import 'package:asna_chat/constants/app_colors.dart';
import 'package:asna_chat/controllers/message_controller.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:flutter/material.dart';


class ChatInputField extends BaseView<MessageController> {
  const ChatInputField({
    Key? key,
  }) : super(key: key);

  @override
  Widget ui(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(
        horizontal: 20,
        vertical: 20 / 2,
      ),
      decoration: BoxDecoration(
        color: Theme.of(context).scaffoldBackgroundColor,
        boxShadow: [
          BoxShadow(
            offset: Offset(0, 4),
            blurRadius: 32,
            color: Color(0xFF087949).withOpacity(0.08),
          ),
        ],
      ),
      child: SafeArea(
        child: Row(
          children: [
            Expanded(
              child: Container(
                padding: const EdgeInsets.symmetric(
                  horizontal: 20 * 0.75,
                ),
                decoration: BoxDecoration(
                  color: AppColors.green.withOpacity(0.05),
                  borderRadius: BorderRadius.circular(40),
                ),
                child: Row(
                  children: [
                    Icon(
                      Icons.message_outlined,
                      color: Theme.of(context)
                          .textTheme
                          .bodyText1!
                          .color!
                          .withOpacity(0.64),
                    ),
                    const SizedBox(width: 20 / 4),
                    Expanded(
                      child: TextField(
                        controller: controller.message,
                        decoration: const InputDecoration(
                          hintText: "Tapez votre message",
                          border: InputBorder.none,
                        ),
                      ),
                    ),
                    const SizedBox(width: 20 / 4),
                    IconButton(
                      icon: Icon(Icons.send,
                        color: Theme.of(context)
                            .textTheme
                            .bodyText1!
                            .color!
                            .withOpacity(0.64),),
                      onPressed: controller.onSendButtonTap,
                    ),
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
