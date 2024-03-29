import 'package:flutter/material.dart';
import 'dart:async';

import 'package:flutter/services.dart';
import 'package:custom_webview_plugin/custom_webview_plugin.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  String _platformVersion = 'Unknown';

  @override
  void initState() {
    super.initState();
    initPlatformState();
  }

  // Platform messages are asynchronous, so we initialize in an async method.
  Future<void> initPlatformState() async {
    String platformVersion;
    // Platform messages may fail, so we use a try/catch PlatformException.
    try {
      platformVersion = await CustomWebviewPlugin.platformVersion;
    } on PlatformException {
      platformVersion = 'Failed to get platform version.';
    }

    // If the widget was removed from the tree while the asynchronous platform
    // message was in flight, we want to discard the reply rather than calling
    // setState to update our non-existent appearance.
    if (!mounted) return;

    setState(() {
      _platformVersion = platformVersion;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Plugin example app'),
        ),
        body: Center(
          child: Column(
            children: <Widget>[
              RaisedButton(
                onPressed: () async {
                  CustomWebviewPlugin.onPageFinished = _onPageFinished;
                  CustomWebviewPlugin.onToasterCalled = _onToasterCalled;
                  await CustomWebviewPlugin.startWebView("https://smart-gps-e22ab.web.app/");

                  Future.delayed(Duration(seconds: 10), () async {
                    await CustomWebviewPlugin.evalJs("alert('test');");
                  });
                },
                child: Text("OPEN WEBVIEW"),
              ),
              Text('Running on: $_platformVersion\n')
            ],
          ),
        ),
      ),
    );
  }

  void _onPageFinished(String url) {
    print("Load finish: $url");
  }

  void _onToasterCalled() {
    print("Toaster called");
  }
}
