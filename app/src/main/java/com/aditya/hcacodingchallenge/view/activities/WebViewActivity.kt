package com.aditya.hcacodingchallenge.view.activities

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.aditya.hcacodingchallenge.R
import com.aditya.hcacodingchallenge.util.AppConstants.Companion.WEB_VIEW_ACTIVITY
import kotlinx.android.synthetic.main.activity_web_view.*

/**
 *  Activity to load the link of the question into WebView
 */
class WebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val webUrl = intent.getStringExtra(WEB_VIEW_ACTIVITY)
        if (webUrl != null) {
            loadWebView(webUrl)
        }
    }

    // Method to load url into webview
    private fun loadWebView(webUrl: String) {
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        webUrl.let { webView.loadUrl(it) }
    }

    // Method to enable backbutton action in Actionbar
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}