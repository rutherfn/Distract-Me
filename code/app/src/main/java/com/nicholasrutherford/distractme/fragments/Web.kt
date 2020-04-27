package com.nicholasrutherford.distractme.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.preference.PreferenceManager
import com.nicholasrutherford.distractme.R

class Web: Fragment() {
    private var webUrl: String? = ""
    private var mView: View? = null
    private var wvNews: WebView? = null
    var tvNoResultsFound: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mView = inflater.inflate(R.layout.fragment_web, container, false)
        wvNews = mView!!.findViewById(R.id.wvNews)
        tvNoResultsFound = mView!!.findViewById(R.id.tvNoResultsFound)
        main()
        return mView
    }

    private fun main() {
        val sharedPreference = PreferenceManager.getDefaultSharedPreferences(context)
        webUrl = sharedPreference.getString("webUrl","")
        checkBundle()
        setUpWebView()
    }

    private fun checkBundle() {
        println(webUrl)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView() {
        wvNews!!.webViewClient = WebViewClient()
        wvNews!!.settings.javaScriptEnabled = true
        wvNews!!.settings.domStorageEnabled = true
        wvNews!!.overScrollMode = WebView.OVER_SCROLL_NEVER
        loadUrlBasedOnArticle()
    }

    private fun loadUrlBasedOnArticle() {
        if(webUrl == "" ) {
            tvNoResultsFound!!.visibility = View.VISIBLE
            wvNews!!.visibility = View.GONE
        } else {
            tvNoResultsFound!!.visibility = View.GONE
            wvNews!!.visibility = View.VISIBLE
            wvNews!!.loadUrl(webUrl)
        }
    }

}