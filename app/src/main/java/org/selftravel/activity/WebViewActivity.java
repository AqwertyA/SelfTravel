package org.selftravel.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.selftravel.R;
import org.selftravel.callback.TitleBarClickListener;
import org.selftravel.http.HttpUrl;
import org.selftravel.view.TitleBar;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

@ContentView(R.layout.activity_web_view)
public class WebViewActivity extends BaseActivity implements TitleBarClickListener {

    @ViewInject(R.id.web_view)
    private WebView webView;
    @ViewInject(R.id.title_bar)
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        setListener();
        initData();
    }

    private void initView() {
        x.view().inject(this);
    }

    private void setListener() {
        titleBar.setTitleBarBackClickListener(this);
    }

    private void initData() {
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String url = HttpUrl.PIC_BASE_URL + intent.getStringExtra("url");
        titleBar.setTitle(title);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            finish();
        }
    }
}
