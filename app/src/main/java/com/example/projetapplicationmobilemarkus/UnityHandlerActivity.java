package com.example.projetapplicationmobilemarkus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class UnityHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_unity_handler);
        WebView myWebView = findViewById(R.id.webViewUnity);

        myWebView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        // Définir Firefox comme navigateur par défaut
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                intent.setPackage("org.mozilla.firefox");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });

        System.out.println("----------------------------------------------");
        System.err.println(myWebView);
        System.out.println("----------------------------------------------");

        myWebView.loadUrl("http://cours.cegep3r.info/H2023/420606RI/GR04/WebGl/");
        System.out.println("----------------------------------------------");
        System.err.println(myWebView);
        System.out.println("----------------------------------------------");
    }
}