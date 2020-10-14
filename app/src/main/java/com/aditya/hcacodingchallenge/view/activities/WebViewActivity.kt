package com.aditya.hcacodingchallenge.view.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.aditya.hcacodingchallenge.R
import kotlinx.android.synthetic.main.activity_web_view.*

class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webUrl = intent.getStringExtra("WEB_URL")
        if (webUrl != null) {
            loadWebView(webUrl)
        }
    }
    private fun loadWebView(webUrl: String){
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        webUrl.let { webView.loadUrl(it) }
    }
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}