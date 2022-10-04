package com.example.myfunexercise.impl

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebView
import com.example.myfunexercise.kotlin.DrawMainAcivity

class JsKotlinImpl(drawMainAcivity: DrawMainAcivity, myWebView: WebView) {
    private var mContext: Context? = null
    private var myWebView: WebView? = null
    private var mHandler: Handler? = null
    private var drawMain : DrawMainAcivity?=null
    init {
        mHandler = Handler()
        drawMain = drawMainAcivity
    }

    //js同步调用方法
    @JavascriptInterface
    fun syncExecWenzi(wenzi: String?) {
        drawMain!!.setF(wenzi)
    }
}