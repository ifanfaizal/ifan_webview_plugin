package com.ifanfaizaladnan.custom_webview_plugin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewManager {
    public CustomWebviewPlugin customWebviewPlugin;

    public WebViewManager(CustomWebviewPlugin customWebviewPlugin) {
        this.customWebviewPlugin = customWebviewPlugin;
    }

    public void showWebViewPage() {
        Intent intent = new Intent(customWebviewPlugin.activity, WebViewAct.class);
        customWebviewPlugin.activity.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void evalJs(String jsString) {
        if (jsString == null) {
            throw new UnsupportedOperationException("JavaScript string cannot be null");
        }
        WebViewAct.mWebView.evaluateJavascript(
            jsString,
            new android.webkit.ValueCallback<String>() {
                    @Override
                    public void onReceiveValue(String value) {
                        Log.d("WebView", value);
                    }
                });
    }
}
