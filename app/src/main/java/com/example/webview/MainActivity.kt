package com.example.webview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.view.KeyEvent


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // リソースから読み込む.BackKeyを押した際にも同じリソースを読み取るため
        val myWebView: WebView = findViewById(R.id.webview)

        // Javascriptの有効可（動画再生のため）
        myWebView.settings.javaScriptEnabled = true
        // リンククリック時に他のアプリを起動させない
        myWebView.webViewClient = WebViewClient()
        
        // 起動時に表示するページ
        myWebView.loadUrl("https://www.youtube.com")
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