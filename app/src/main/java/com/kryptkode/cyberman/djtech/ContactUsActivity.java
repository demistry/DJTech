package com.kryptkode.cyberman.djtech;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import android.widget.Toast;

public class ContactUsActivity extends AppCompatActivity {
    Context context = this;
    private WebView webView;
    private static final String url = "http://djtech.com.ng/invisiblecontactpage/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        webView = (WebView) findViewById(R.id.webview);
        final ProgressDialog progressDialog = new ProgressDialog(webView.getContext());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressDialog.setMessage("Contact Form Loading...");

                progressDialog.setCancelable(true);

                progressDialog.setCancelable(false);

                progressDialog.show();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                if (errorCode == ERROR_HOST_LOOKUP){
                    view.setVisibility(View.INVISIBLE);
                    Toast.makeText(context, "Error Loading Contact Form...\n Check Internet Connection",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(context, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(url);
                return true;
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressDialog.dismiss();
            }
        });

    }
}
