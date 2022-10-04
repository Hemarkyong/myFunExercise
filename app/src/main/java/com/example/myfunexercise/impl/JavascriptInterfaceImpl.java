package com.example.myfunexercise.impl;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

import com.example.myfunexercise.kotlin.DrawMainAcivity;

public class JavascriptInterfaceImpl {
    private Context mContext;
    private WebView myWebView;
    private Handler mHandler;
    public JavascriptInterfaceImpl(Context c, WebView w) {
        mContext = c;
        myWebView = w;
        mHandler=new Handler();
    }

    //js同步调用方法
    @JavascriptInterface
    public void syncExecIntent(String val){
        Log.i("syncExecIntent",val);
        Intent intent = new Intent(mContext, DrawMainAcivity.class);
        intent.putExtra("pingying",val);
        mContext.startActivity(intent);
    }

    /**
     * js同步调用方法
     * 获取文字
     * **/
    @JavascriptInterface
    public void syncExecWenzi(String val){
        System.out.println(val);
    }

    /**
           * 异步方法
           * @param msg
           * @param callbackId
     */
    @JavascriptInterface
    public void nosyncExec(final String msg, final String callbackId) {
         new Thread() {
              @Override
              public void run() {
                     SystemClock.sleep(1 * 1000);
                     mHandler.post(new Runnable() {
                     @Override
                     public void run() {
                         String url = "javascript:" + callbackId + "('" + msg
                                                 + " from android " + "')";
                         myWebView.loadUrl(url);
                     }
                 });
              }
        }.start();
    }
}
