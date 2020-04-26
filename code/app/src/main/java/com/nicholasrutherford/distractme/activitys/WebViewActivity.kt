package com.nicholasrutherford.distractme.activitys

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.nicholasrutherford.distractme.R

class WebViewActivity: AppCompatActivity() {

    private lateinit var wvNews : WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_webview_layout)
        wvNews = findViewById(R.id.wvNews)
        main()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun main() {
        wvNews.webViewClient = WebViewClient()
        wvNews.settings.javaScriptEnabled = true
        wvNews.settings.domStorageEnabled = true
        wvNews.overScrollMode = WebView.OVER_SCROLL_NEVER
        loadUrl()
    }

    private fun loadUrl() {
        val bundle :Bundle ?=intent.extras
        val url = bundle!!.getString("url")
        wvNews.loadUrl(url)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}