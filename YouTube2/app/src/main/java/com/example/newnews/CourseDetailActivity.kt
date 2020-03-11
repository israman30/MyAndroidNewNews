package com.example.newnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.detail_webview_article.*

class CourseDetailActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_webview_article)

        // Get intent with extras from MainActivity
        val urlLink = intent.getStringExtra(CustomViewHolder.URL_SITE_LINK)
        webview_article_detail.settings.javaScriptEnabled = true
        webview_article_detail.settings.loadWithOverviewMode = true
        webview_article_detail.settings.useWideViewPort = true
        webview_article_detail.loadUrl(urlLink)
    }
}