package com.example.map.mylocation.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebBackForwardList;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.blankj.utilcode.util.NetworkUtils;
import com.example.map.mylocation.R;
import com.example.map.mylocation.view.WebviewToolbar;

/**
 * 没有 URL凭借的webview 666
 */
public class WebOnlyGetUlrActivity extends BaseWebviewActivity {

    WebviewToolbar top_bar;

    WebView webView;
    String adUrl = "";
    String time;
    String m_title = "";

    @Override
    public int getContentViewResId() {
        return R.layout.activity_hide_web;
    }

    public static void startWebActivity(Activity context, String adUrls, String ad_name) {
        Intent intent = new Intent(context, WebOnlyGetUlrActivity.class);
        //设置要回传的数据
        intent.putExtra("adUrls", adUrls);
        intent.putExtra("title", ad_name);
        context.startActivity(intent);
    }

    @Override
    public void initView() {

        top_bar = findViewById(R.id.top_bar);
        webView = findViewById(R.id.web_view);
        m_title = getIntent().getStringExtra("title");
//        top_bar.setMainTitle("积分抽奖");
        top_bar.setLeftTitleClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (webView.canGoBack()) {
                    webView.goBack();
                    top_bar.showClose();
                } else {
                    finish();
                }
            }
        });
        top_bar.setLetfImageCloseClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        adUrl = getIntent().getStringExtra("adUrls");

//        ToastUtil.showTextToast(adUrl);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setUseWideViewPort(true);//适应分辨率
        settings.setLoadWithOverviewMode(true);
        settings.setDomStorageEnabled(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (NetworkUtils.isConnected()) {
            //有网络连接，设置默认缓存模式
            settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        } else {
            //无网络连接，设置本地缓存模式
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

//        webView.addJavascriptInterface(new WebThirdPartyActivity.JsInterface(WebThirdPartyActivity.this), "changshi");
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (title != null) {
                    top_bar.setMainTitle(title);
                } else {
                    top_bar.setMainTitle(m_title);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //设置网页在webview控件中打开,false在浏览器中打开
                WebBackForwardList list = webView.copyBackForwardList();
                if (list.getSize() >= 1) {
                    top_bar.showClose();
                } else {
                    top_bar.hideClose();
                }
                view.loadUrl(url);
                return true;
            }


        });
        webView.loadUrl(adUrl);
    }

    @Override
    public void onDestroy() {
        webView.removeAllViews();
        webView.destroy();
//        EventBusUtil.sendEvent(new Event(Commons.UP_DATA));
        super.onDestroy();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();//返回上个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);//退出整个应用程序

    }


}
