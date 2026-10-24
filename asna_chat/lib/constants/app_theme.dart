import 'package:asna_chat/constants/app_colors.dart';
import 'package:flutter/material.dart';

class AppTheme {

  static final ThemeData theme = ThemeData(
    colorScheme: const ColorScheme(
      primary: AppColors.green,
      primaryVariant: Colors.green,
      secondary: AppColors.orange,
      secondaryVariant: Colors.deepOrange,
      surface: Colors.white,
      background: Colors.white,
      error: Colors.red,
      onPrimary: Colors.white,
      onSecondary: Colors.white,
      onSurface: Colors.black,
      onBackground: Colors.black,
      onError: Colors.white,
      brightness: Brightness.light,
    ),
  );

}
