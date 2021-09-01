import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'dart:async';

import './buttons.dart';

void main() {
  runApp(
    MaterialApp(title: '计算器-花生皮编程', home: new ThemeHome()),
  );
}

class ThemeHome extends StatefulWidget {
  ThemeHome({
    Key? key,
  }) : super(key: key);

  @override
  _ThemeHomeState createState() => _ThemeHomeState();
}

class _ThemeHomeState extends State<ThemeHome> {
  static const platform = const MethodChannel('samples.flutter.dev/theme');

  @override
  Widget build(BuildContext context) {
    {
      return Scaffold(
        body: MyHomePage(),
        drawer: Drawer(
          child: ListView(
            children: <Widget>[
              DrawerHeader(
                child: Text('计算器'),
                decoration: BoxDecoration(
                  color: Colors.white,
                ),
              ),
            ],
          ),
        ),
      );
    }
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({
    Key? key,
  }) : super(key: key);

  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  static bool _isLargeScreen = false;

  @override
  Widget build(BuildContext context) {
    return OrientationBuilder(builder: (context, orientation) {
      if (MediaQuery.of(context).size.width > 600) {
        _isLargeScreen = true;
      } else {
        _isLargeScreen = false;
      }
      return BtnWidget(
          isLargeScreen: _isLargeScreen);
    });
  }
}
