class User {
  User({
      this.fullName, 
      this.status, 
      this.speciality, 
      this.login, 
      this.password,});

  User.fromJson(dynamic json) {
    fullName = json['fullName'];
    status = json['status'];
    speciality = json['speciality'];
    login = json['login'];
    password = json['password'];
    id = json['id'];
  }

  String? id;
  String? fullName;
  int? status;
  String? speciality;
  String? login;
  String? password;

  Map<String, dynamic> toJson({bool withId = false}) {
    final map = <String, dynamic>{};
    map['fullName'] = fullName;
    map['status'] = status;
    map['speciality'] = speciality;
    map['login'] = login;
    map['password'] = password;
    if (withId) map['id'] = id;
    return map;
  }

}