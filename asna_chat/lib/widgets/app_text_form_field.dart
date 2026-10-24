import 'package:asna_chat/constants/app_colors.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AppTextFormField extends StatelessWidget {

  const AppTextFormField({required TextEditingController controller, required TextInputType textInputType, required String label, required IconData iconData, bool hidden = false}) : _controller = controller, _textInputType = textInputType, _label = label, _iconData = iconData, _hidden = hidden;

  final TextEditingController _controller;

  final TextInputType _textInputType;

  final String _label;

  final IconData _iconData;

  final bool _hidden;

  @override
  Widget build(BuildContext context) {
    return TextFormField(
      obscureText: _hidden,
      controller: _controller,
      decoration: InputDecoration(
        labelText: _label,
        fillColor: AppColors.green,
        focusedBorder: const UnderlineInputBorder(
            borderSide: BorderSide(
                color: Colors.grey
            )
        ),
        labelStyle: const TextStyle(
          color: AppColors.green,
        ),
        suffixIcon: Icon(
          _iconData,
          color: AppColors.green,
        )
      ),
      cursorColor: AppColors.green,
      keyboardType: _textInputType,
    );
  }

}