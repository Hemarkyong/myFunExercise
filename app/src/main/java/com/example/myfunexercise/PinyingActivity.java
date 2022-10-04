package com.example.myfunexercise;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myfunexercise.impl.JavascriptInterfaceImpl;

public class PinyingActivity extends AppCompatActivity {
    @SuppressLint({"JavascriptInterface", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pinying_h5);

        WebView myWebView = (WebView) findViewById(R.id.myWebView);
        //JavascriptInterfaceImplButton button = (Button) findViewById(R.id.button);
        // 得到设置属性的对象
        WebSettings webSettings = myWebView.getSettings();
        // 使能JavaScript
        webSettings.setJavaScriptEnabled(true);
        // 支持中文，否则页面中中文显示乱码
        webSettings.setDefaultTextEncodingName("UTF-8");
        //传入一个Java对象和一个接口名,在JavaScript代码中用这个接口名代替这个对象,通过接口名调用Android接口的方法
        myWebView.addJavascriptInterface(new JavascriptInterfaceImpl(this,myWebView), "Android");
        myWebView.loadUrl("file:///android_asset/html/pinying.html");

        //为WebView设置WebChromeClient，不设置WebChromeClient的话，JavaScript的弹框消息会无法显示。
        // WebChromeClient主要用来辅助WebView处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
        myWebView.setWebChromeClient(new WebChromeClient() {
              @Override
              public void onReceivedTitle(WebView view, String title) {
              }

                      @Override
              public boolean onJsAlert(WebView view, String url, String message,
                      JsResult result) {
                      return super.onJsAlert(view, url, message, result);
              }
         });
    }
}
