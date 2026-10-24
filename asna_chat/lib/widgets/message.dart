import 'package:asna_chat/constants/app_colors.dart';
import 'package:asna_chat/controllers/message_controller.dart';
import 'package:asna_chat/models/chat_message.dart';
import 'package:asna_chat/models/message.dart' as modelmes;
import 'package:asna_chat/models/user.dart';
import 'package:asna_chat/screens/base_view.dart';
import 'package:flutter/material.dart';
import 'package:get_storage/get_storage.dart';

import 'audio_message.dart';
import 'text_message.dart';
import 'video_message.dart';

class Message extends BaseView<MessageController> {

  Message({
    Key? key,
    required this.message,
  }) : super(key: key);

  final modelmes.Message message;

  @override
  Widget ui(BuildContext context) {
    Widget messageContaint(modelmes.Message message) {
      return TextMessage(message: message);
    }

    return Padding(
      padding: const EdgeInsets.only(top: 20),
      child: Row(
        mainAxisAlignment: controller.user.id == message.sender ? MainAxisAlignment.end : MainAxisAlignment.start,
        children: [
          if (controller.user.id != message.sender) ...[
            CircleAvatar(
              radius: 12,
              child: Text(controller.contact.fullName![0]),
            ),
            const SizedBox(width: 20 / 2),
          ],
          messageContaint(message),
        ],
      ),
    );
  }
}

class MessageStatusDot extends StatelessWidget {
  final MessageStatus? status;

  const MessageStatusDot({Key? key, this.status}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    Color dotColor(MessageStatus status) {
      switch (status) {
        case MessageStatus.not_sent:
          return AppColors.red;
        case MessageStatus.not_view:
          return Theme.of(context).textTheme.bodyText1!.color!.withOpacity(0.1);
        case MessageStatus.viewed:
          return AppColors.green;
        default:
          return Colors.transparent;
      }
    }

    return Container(
      margin: const EdgeInsets.only(left: 20 / 2),
      height: 12,
      width: 12,
      decoration: BoxDecoration(
        color: dotColor(status!),
        shape: BoxShape.circle,
      ),
      child: Icon(
        status == MessageStatus.not_sent ? Icons.close : Icons.done,
        size: 8,
        color: Theme.of(context).scaffoldBackgroundColor,
      ),
    );
  }
}
