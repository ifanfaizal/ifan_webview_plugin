package com.ifanfaizaladnan.custom_webview_plugin;

import android.app.Activity;
import android.content.Intent;
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
}
