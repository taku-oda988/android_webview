package com.example.webview

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.KeyEvent


class MainActivity : AppCompatActivity() {
    companion object {
        const val HOST = "www.google.com"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // リソースから読み込む.BackKeyを押した際にも同じリソースを読み取るため
        val myWebView: WebView = findViewById(R.id.webview)

        // Javascriptの有効可（動画再生のため）
        myWebView.settings.javaScriptEnabled = true
        // リンククリック時に他のアプリを起動させない
        myWebView.webViewClient = MyWebViewClient()
        
        // 起動時に表示するページ
        myWebView.loadUrl("https://$HOST")
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        val myWebView: WebView = findViewById(R.id.webview)
        // Check if the key event was the Back button and if there's history
        if (keyCode == KeyEvent.KEYCODE_BACK && myWebView.canGoBack()) {
            myWebView.goBack()
            return true
        }
        // If it wasn't the Back key or there's no web page history, bubble up to the default
        // system behavior (probably exit the activity)
        return super.onKeyDown(keyCode, event)
    }
}
private class MyWebViewClient : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        if (Uri.parse(url).host == MainActivity.HOST) {
            // This is my web site, so do not override; let my WebView load the page
            return false
        }
        return true
    }
}