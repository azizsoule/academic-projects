import 'package:flutter/cupertino.dart';

class PersonStatus {

  int? id;

  String? label;

  PersonStatus({this.label, this.id});

  @override
  String toString() => label!;

  static final List<PersonStatus> values = [
    PersonStatus(id: 0, label: "Etudiant"),
    PersonStatus(id: 1, label: "Professeur")
  ];

}