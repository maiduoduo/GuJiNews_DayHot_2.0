package com.cnews.guji.smart.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpActivity;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.presenter.NewsDetailPresenterImpl;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.view.ObservableScrollView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 通用广告详情界面
 *
 * @author JSYL-DCL
 */
public class NewsAdvertDetailActivity extends BaseMvpActivity<NewsDetailPresenterImpl> implements NewsDetailContract.View {
    private static final String TAG1 = NewsAdvertDetailActivity.class.getSimpleName();
    private static String dataUrl = "";
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_logo)
    ExpandImageView mIvContentLogo;
    @BindView(R.id.iv_topLogo)
    ExpandImageView mIvTopLogo;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_updateTime)
    TextView mTvUpdateTime;
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.ScrollView)
    ObservableScrollView mScrollView;
    @BindView(R.id.ConstraintLayout)
    RelativeLayout mConstraintLayout;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.tv_topname)
    TextView mTvTopName;
//    @BindView(R.id.tv_TopUpdateTime)
//    TextView mTvTopUpdateTime;

    public static void newInstance(Context context, String data) {
        context.startActivity(new Intent(context, NewsAdvertDetailActivity.class));
        dataUrl = data;
    }

    @Override
    public void intBase() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_advert_titleimg;
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsDetailPresenterImpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        setWebViewSetting();
        mScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int scrollY, int oldx, int oldy) {
                if (scrollY > mConstraintLayout.getHeight()) {
                    mRlTop.setVisibility(View.VISIBLE);
                } else {
                    mRlTop.setVisibility(View.GONE);

                }
            }
        });
    }

    @Override
    public void bindData() {
    }


    @Override
    public void initListener() {

    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showFaild(Throwable throwable, String error) {
        ILog.e(TAG1,"【showFaild】------error------------》："+error);
        ILog.e(TAG1,"【showFaild】------throwable------------》："+throwable);
        showSuccess();
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }


    @Override
    public void onRetry() {
        String aid = getIntent().getStringExtra("aid");
        mPresenter.getData(NewsAdvertDetailActivity.this,aid);
    }


    @Override
    public void detailItemData(NewsDetailBean data) {

    }

    @Override
    public void setData(NewsDetailNormalBean data) {
        if (data == null){
            showNoNet();
            return;
        }
        showSuccess();
        ILog.e(TAG1,"【setData】------------------》："+new Gson().toJson(data));
        mTvTitle.setText(data.getBody().getTitle());
        mTvUpdateTime.setText(DateTimeUtils.getTimestampString(DateTimeUtils.string2Date(data.getBody().getUpdateTime(), "yyyy/MM/dd HH:mm:ss")));
        if (data.getBody().getSubscribe() != null) {
            mIvContentLogo.setImageURI(data.getBody().getSubscribe().getLogo());
            mIvTopLogo.setImageURI(data.getBody().getSubscribe().getLogo());
//            ImageLoaderUtils.display(this,mIvLogo,data.getBody().getThumbnail());
//            ImageLoaderUtils.display(this,mIvTopLogo,data.getBody().getThumbnail());
            mTvTopName.setText(data.getBody().getSubscribe().getCateSource());
            mTvName.setText(data.getBody().getSubscribe().getCateSource());
        } else {
            mIvContentLogo.setImageURI(data.getBody() == null ? "" : data.getBody().getThumbnail());
            mIvTopLogo.setImageURI(data.getBody() == null ? "" : data.getBody().getThumbnail());
            mTvTopName.setText(data.getBody().getSource());
            mTvName.setText(data.getBody().getSource());
        }
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                final String content = data.getBody().getText();
                String url = "javascript:show_content(\'" + content + "\')";
                mWebView.loadUrl(url);
                showSuccess();
            }
        });
    }

    @Override
    public void setHotComment(HomeCommentBean data) {

    }

    @Override
    public void setNewComment(HomeCommentBean data) {

    }

    @Override
    public void setRelationNews(HomeTopIFengBean data) {

    }

    @Override
    public void setMoreRelationNews(HomeTopIFengBean data) {

    }

    @Override
    public void showEmpty() {

    }


    private void setWebViewSetting() {
        addjs(mWebView);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.setVerticalScrollBarEnabled(false);
        mWebView.setVerticalScrollbarOverlay(false);
        mWebView.setHorizontalScrollBarEnabled(false);
        mWebView.setHorizontalScrollbarOverlay(false);
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
        mWebView.loadUrl("file:///android_asset/ifeng/post_detail.html");
//        mWebView.loadUrl("https://www.baidu.com/");
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                String aid = getIntent().getStringExtra("aid");
                mPresenter.getData(NewsAdvertDetailActivity.this,aid);
            }
        });
    }


    private void addjs(final WebView webview) {

        class JsObject {
            @JavascriptInterface
            public void jsFunctionimg(final String i) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
//                        Log.i(TAG, "run: " + i);
                    }
                });

            }

        }
        webview.addJavascriptInterface(new JsObject(), "jscontrolimg");

    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finishThis();
    }
}
