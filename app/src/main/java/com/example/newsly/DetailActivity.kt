package com.example.newsly

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailWebView=findViewById<WebView>(R.id.detailWebView)
        val progressBar=findViewById<ProgressBar>(R.id.progressBar)


        val url=intent.getStringExtra("URL")
        if(url!=null){
           detailWebView.settings.javaScriptEnabled=true
            detailWebView.settings.userAgentString="Mozilla/5.0 (Linux; U; Android 2.2; en-us; Nexus One Build/FRF91) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1"
            detailWebView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility= View.GONE
                    detailWebView.visibility=View.VISIBLE
                }

            }
            detailWebView.loadUrl(url)
        }
    }
}