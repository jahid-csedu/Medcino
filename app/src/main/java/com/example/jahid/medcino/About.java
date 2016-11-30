package com.example.jahid.medcino;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class About extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        WebView webview1= (WebView) findViewById(R.id.webView1);
        WebView webview2= (WebView) findViewById(R.id.webView2);
        WebView webview3= (WebView) findViewById(R.id.webView3);
        WebView webview4= (WebView) findViewById(R.id.webView4);
        String web1="<html><body>"+"<p align=\"justify\">"+getString(R.string.about1)+ "</p> "+ "</body></html>";
        String web2="<html><body>"+"<p align=\"justify\">"+getString(R.string.about2)+ "</p> "+ "</body></html>";
        String web3="<html><body>"+"<p align=\"justify\">"+getString(R.string.about3)+ "</p> "+ "</body></html>";
        String web4="<html><body>"+"<p align=\"justify\">"+getString(R.string.about4)+ "</p> "+ "</body></html>";

        webview1.loadData(web1, "text/html", "utf-8");
        webview2.loadData(web2, "text/html", "utf-8");
        webview3.loadData(web3, "text/html", "utf-8");
        webview4.loadData(web4, "text/html", "utf-8");
    }
}
