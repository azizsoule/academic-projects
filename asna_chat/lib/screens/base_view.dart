import 'package:flutter/cupertino.dart';
import 'package:get/get.dart';

abstract class BaseView<T extends GetxController> extends GetView<T> {

  const BaseView({Key? key}) : super(key: key);

  Widget ui(BuildContext context);

  @override
  Widget build(BuildContext context) {
    return GetBuilder<T>(builder: (logic) => ui(context));
  }

}