package com.ifanfaizaladnan.custom_webview_plugin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class WebViewAct extends Activity {
    public WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webviewact);
        mWebView = findViewById(R.id.main_webview);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new WebViewJavaScriptInterface(this), "Toaster");
        mWebView.loadUrl("https://smart-gps-e22ab.web.app/");
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public void evaluateJavaScript(String jsString) {
        if (jsString == null) {
            throw new UnsupportedOperationException("JavaScript string cannot be null");
        }
        mWebView.evaluateJavascript(
            jsString,
            new android.webkit.ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.d("WebView", value);
                }
            });
    }

    public class WebViewJavaScriptInterface{

        private Context context;

        /*
         * Need a reference to the context in order to sent a post message
         */
        public WebViewJavaScriptInterface(Context context){
            this.context = context;
        }

        /*
         * This method can be called from Android. @JavascriptInterface
         * required after SDK version 17.
         */
        @JavascriptInterface
        public void postMessage(String message){
            finish();
        }
    }
}
