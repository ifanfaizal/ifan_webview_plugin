package com.ifanfaizaladnan.custom_webview_plugin;

import android.app.Activity;
import android.content.Intent;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** CustomWebviewPlugin */
public class CustomWebviewPlugin implements MethodCallHandler {
  private Activity activity;
  static MethodChannel channel;
  private static final String CHANNEL_NAME = "custom_webview_plugin";

  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    channel = new MethodChannel(registrar.messenger(), CHANNEL_NAME);
    final CustomWebviewPlugin instance = new CustomWebviewPlugin(registrar.activity());
    channel.setMethodCallHandler(instance);
  }

  public CustomWebviewPlugin(Activity activity) {
    this.activity = activity;
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if (call.method.equals("startWebView")) {
      Intent intent = new Intent(activity, WebViewAct.class);
      activity.startActivity(intent);
      result.success(null);
    } else {
      result.notImplemented();
    }
  }
}
