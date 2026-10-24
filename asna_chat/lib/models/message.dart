import 'package:cloud_firestore/cloud_firestore.dart';

class Message {

  Message({
      this.sender, 
      this.recipient, 
      this.content,});

  Message.fromJson(dynamic json) {
    sender = json['sender'];
    recipient = json['recipient'];
    content = json['content'];
    dateTime = json['date'];
  }

  Timestamp? dateTime;
  String? sender;
  String? recipient;
  String? content;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['sender'] = sender;
    map['recipient'] = recipient;
    map['content'] = content;
    map['date'] = Timestamp.now();
    return map;
  }

}