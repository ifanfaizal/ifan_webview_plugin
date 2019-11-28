import 'dart:async';

import 'package:flutter/services.dart';

typedef void PageFinishedCallback(String url);

class CustomWebviewPlugin {
  static const MethodChannel _channel =
      const MethodChannel('custom_webview_plugin');

  static PageFinishedCallback onPageFinished;

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future startWebView(String url) async {
    _channel.setMethodCallHandler(_handler);
    await _channel.invokeMethod('startWebView', url);
  }

  static Future<dynamic> _handler(MethodCall call) {
    switch (call.method) {
      case 'onPageFinished':
        onPageFinished(call.arguments);
        return Future.value("");
      default:
        return Future.value("");
    }
  }
}
