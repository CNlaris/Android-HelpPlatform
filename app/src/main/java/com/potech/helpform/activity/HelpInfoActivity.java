package com.potech.helpform.activity;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;

public class HelpInfoActivity extends AppCompatActivity {
    
    private WebView helpInfoWebView;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_info);
        helpInfoWebView = (WebView) findViewById(R.id.help_info_webView);
        helpInfoWebView.loadUrl("https://www.polaris521.top");
    }
}
