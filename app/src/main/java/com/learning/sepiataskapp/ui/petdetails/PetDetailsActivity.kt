package com.learning.sepiataskapp.ui.petdetails

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.learning.sepiataskapp.R
import kotlinx.android.synthetic.main.activity_pet_details.*

class PetDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pet_details)
        initView()
    }

    private fun initView() {
        val contentUrl = intent.getStringExtra("pet_content_url")
        //Load the content URL in the WebView if the content url is not nul
        contentUrl?.let {
            web_view_pet_details.webViewClient = WebViewClient()
            web_view_pet_details.loadUrl(it)
        }
    }
}