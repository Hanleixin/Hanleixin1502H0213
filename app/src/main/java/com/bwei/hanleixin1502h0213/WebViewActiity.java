package com.bwei.hanleixin1502h0213;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bwei.hanleixin1502h0213.R;

/**
 * Author ${韩磊鑫} on 2017/2/13 09:48
 * 邮箱：leixinhan@foxmail.com
 * 项目名称：
 * 类描述：
 * 修改人：${Oliver}
 * 修改备注：
 * 修改时间：
 */

public class WebViewActiity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_actiity);

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");

        WebView webView = (WebView) findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);

        WebViewClient client=new WebViewClient();

        webView.setWebViewClient(client);
        webView.loadUrl(url);
    }
}
