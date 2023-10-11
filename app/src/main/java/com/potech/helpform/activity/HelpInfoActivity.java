package com.potech.helpform.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.potech.helpform.R;

public class HelpInfoActivity extends AppCompatActivity {
    
    private WebView helpInfoWebView;
    private HelpInfoWebViewClient helpInfoWebViewClient;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_info);
        helpInfoWebView = (WebView) findViewById(R.id.help_info_webView);
        helpInfoWebViewClient = new HelpInfoWebViewClient();
        helpInfoWebView.loadUrl("http://www.polaris521.top/index.php/2021/12/14/%e7%81%be%e5%90%8e%e5%b0%8f%e7%9f%a5%e8%af%86/");
        helpInfoWebView.setWebViewClient(helpInfoWebViewClient);
    }
    private class HelpInfoWebViewClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
