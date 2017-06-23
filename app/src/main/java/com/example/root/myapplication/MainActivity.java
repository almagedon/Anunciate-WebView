package com.example.root.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.Browser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

    private WebView wv1;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(R.layout.activity_main);

        WebView browser = (WebView) findViewById(R.id.webView);
        browser.loadUrl("http://www.anunciatee.com");


        wv1=(WebView)findViewById(R.id.webView);
        bar=(ProgressBar) findViewById(R.id.progressBar2);
        wv1.getSettings().setJavaScriptEnabled(true);
        wv1.getSettings().setSupportZoom(true);

        wv1.setWebViewClient(new MyBrowser());


    }

    public void onBackPressed() {
        if (wv1.canGoBack())
            wv1.goBack();
        else
            super.onBackPressed();

    }

    private class MyBrowser extends WebViewClient {
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bar.setVisibility(View.GONE);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (Uri.parse(url).getHost().contains("example.com")) {
                return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            intent.putExtra(Browser.EXTRA_APPLICATION_ID, "com.android.browser");
            startActivity(intent);
            return true;
        }

    }
}
