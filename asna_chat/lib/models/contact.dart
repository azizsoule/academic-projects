class Contact {

  Contact({
      this.fullName, 
      this.contact, 
      this.personStatus, 
      this.speciality, 
      this.owner,});

  Contact.fromJson(dynamic json) {
    fullName = json['fullName'];
    contact = json['contact'];
    personStatus = json['personStatus'];
    speciality = json['speciality'];
    owner = json['owner'];
  }

  String? id;
  String? fullName;
  String? contact;
  String? personStatus;
  String? speciality;
  String? owner;

  Map<String, dynamic> toJson() {
    final map = <String, dynamic>{};
    map['fullName'] = fullName;
    map['contact'] = contact;
    map['personStatus'] = personStatus;
    map['speciality'] = speciality;
    map['owner'] = owner;
    return map;
  }

}