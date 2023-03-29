package com.example.projetapplicationmobilemarkus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class UnityHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unity_handler);
        WebView myWebView = (WebView) findViewById(R.id.webViewUnity);
        myWebView.loadUrl("http://www.google.ca");
    }
}