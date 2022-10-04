package com.example.myfunexercise.kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import com.example.myfunexercise.R
import com.example.myfunexercise.impl.JsKotlinImpl
import kotlinx.android.synthetic.main.draw_main.*
class DrawMainAcivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val s = intent.getStringExtra("pingying")
        setContentView(R.layout.draw_main)

        //val myWebView = findViewById<View>(R.id.myWebView) as WebView
        //JavascriptInterfaceImplButton button = (Button) findViewById(R.id.button);
        // 得到设置属性的对象
        val webSettings = myWebView.settings
        // 使能JavaScript
        webSettings.javaScriptEnabled = true
        // 支持中文，否则页面中中文显示乱码
        webSettings.defaultTextEncodingName = "UTF-8"
        //传入一个Java对象和一个接口名,在JavaScript代码中用这个接口名代替这个对象,通过接口名调用Android接口的方法
        myWebView.addJavascriptInterface(JsKotlinImpl(this, myWebView), "Android")
        myWebView.loadUrl("file:///android_asset/html/wenzi.html?pinying="+s)

        //为WebView设置WebChromeClient，不设置WebChromeClient的话，JavaScript的弹框消息会无法显示。
        // WebChromeClient主要用来辅助WebView处理Javascript的对话框、网站图标、网站标题以及网页加载进度等
        myWebView.webChromeClient = object : WebChromeClient() {
            override fun onReceivedTitle(view: WebView, title: String) {}
            override fun onJsAlert(view: WebView, url: String, message: String,
                                   result: JsResult): Boolean {
                return super.onJsAlert(view, url, message, result)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)
                println("onProgressChanged:"+ newProgress.toString());
                //Log.i("onProgressChanged:", newProgress.toString());
            }
        }

        clear.setOnClickListener(View.OnClickListener {
            drawView.clear()
        });
    }

    fun setF(wenzi: String?){
        drawView.setWenzi(wenzi);
    }
}