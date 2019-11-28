package com.ifanfaizaladnan.custom_webview_plugin;

import android.app.Activity;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class WebViewManager {
    public CustomWebviewPlugin customWebviewPlugin;
    private WebViewAct webViewAct;

    public WebViewManager(CustomWebviewPlugin customWebviewPlugin) {
        this.customWebviewPlugin = customWebviewPlugin;
        this.webViewAct = new WebViewAct();
    }

    public void showWebViewPage() {
        Intent intent = new Intent(customWebviewPlugin.activity, webViewAct.getClass());
        customWebviewPlugin.activity.startActivity(intent);
    }

    public void setUpWebView() {
        webViewAct.mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(webViewAct.mWebView, url);
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(webViewAct.getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
