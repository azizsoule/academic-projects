import 'package:asna_chat/constants/app_colors.dart';
import 'package:asna_chat/controllers/message_controller.dart';
import 'package:asna_chat/models/chat_message.dart';
import 'package:asna_chat/models/message.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:flutter/material.dart';

class TextMessage extends BaseView<MessageController> {

  const TextMessage({
    Key? key,
    this.message,
  }) : super(key: key);

  final Message? message;

  @override
  Widget ui(BuildContext context) {
    return Container(
      padding: const EdgeInsets.symmetric(
        horizontal: 20 * 0.75,
        vertical: 20 / 2,
      ),
      decoration: BoxDecoration(
        color: AppColors.green.withOpacity(message!.sender == controller.user.id ? 1 : 0.1),
        borderRadius: BorderRadius.circular(30),
      ),
      child: Text(
        message!.content!,
        style: TextStyle(
          color: message!.sender == controller.user.id
              ? Colors.white
              : Theme.of(context).textTheme.bodyText1!.color,
        ),
      ),
    );
  }
}
