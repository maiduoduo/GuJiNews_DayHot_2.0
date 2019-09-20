package com.cnews.guji.smart.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.DateTimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author dingcl
 * @description: 新闻详情内容
 */

public class HomeDetailHeaderView extends FrameLayout {

    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.iv_logo)
    ExpandImageView mIvLogo;
    @BindView(R.id.rl_toproot)
    RelativeLayout mRlToproot;
    @BindView(R.id.tv_name)
    TextView mTvName;
    @BindView(R.id.tv_updateTime)
    TextView mTvUpdateTime;
    @BindView(R.id.bt_like)
    Button mBtLike;
    @BindView(R.id.webview)
    WebView mWebView;
    @BindView(R.id.img_webview)
    ExpandImageView mImgWebview;

    private Context mContext;

    public HomeDetailHeaderView(Context context) {
        this(context, null);
    }

    public HomeDetailHeaderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeDetailHeaderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        inflate(getContext(), R.layout.header_news_detail_webjs, this);
        ButterKnife.bind(this, this);
        initWebSetting();
    }

    private void initWebSetting() {
//        addjs(mWebView);
//        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        mWebView.getSettings().setJavaScriptEnabled(true);
//        mWebView.getSettings().setAppCacheEnabled(true);
//        mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
//        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
//        mWebView.setVerticalScrollBarEnabled(false);
//        mWebView.setVerticalScrollbarOverlay(false);
//        mWebView.setHorizontalScrollBarEnabled(false);
//        mWebView.setHorizontalScrollbarOverlay(false);
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        mWebView.getSettings().setDomStorageEnabled(true);
//        mWebView.getSettings().setTextSize(WebSettings.TextSize.NORMAL);
//        mWebView.loadUrl("file:///android_asset/ifeng/post_detail.html");
    }

    public View getRlWebTitleRootView(){
        return mRlToproot;
    }


    public void setDetail(NewsDetailNormalBean data, LoadWebListener listener) {
        mWebListener = listener;
        mTvTitle.setText(data.getBody().getTitle());
        mTvUpdateTime.setText(DateTimeUtils.getTimestampString(DateTimeUtils.string2Date(data.getBody().getUpdateTime(), "yyyy/MM/dd HH:mm:ss")));
        if (data != null) {
            NewsDetailNormalBean.BodyBean body = data.getBody();
            if (data.getBody().getSubscribe() != null) {
                mIvLogo.setImageURI(data.getBody().getSubscribe().getLogo());
            } else {
                mIvLogo.setImageURI(data.getBody() == null ? "" : data.getBody().getThumbnail());
            }
            mImgWebview.setImageURI(body.getImg().get(0).getUrl() == null ? "" : body.getImg().get(0).getUrl());
            mTvName.setText(data.getBody().getSource() == null ? " " : data.getBody().getSource());
            mWebView.post(new Runnable() {
                @Override
                public void run() {
                    final String content = data.getBody().getText();
                    String url = "javascript:show_content(\'" + content + "\')";
                    mWebView.loadUrl(url);
                }
            });
            if (TextUtils.isEmpty(body.getText()))
                mWebView.setVisibility(GONE);

            mWebView.getSettings().setJavaScriptEnabled(true);//设置JS可用
//        wvContent.addJavascriptInterface(new ShowPicRelation(mContext), NICK);

            String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                    "<head><meta charset=\"utf-8\"/>\n" +
                    "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<style> \n" +
                    "img{width:100%!important;height:auto!important}\n" +
                    " </style>";
            String htmlPart2 = "</body></html>";

            String html = htmlPart1 + body.getText() + htmlPart2;

            mWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageFinished(WebView view, String url) {
                    addjs(view);//添加多JS代码，为图片绑定点击函数
                    if (mWebListener != null) {
                        mWebListener.onLoadFinished();
                    }
                }
            });

        }else {

        }

    }

    /**
     * 添加JS代码，获取所有图片的链接以及为图片设置点击事件
     */
//    private void addJs(WebView wv) {
//        wv.loadUrl("javascript:(function  pic(){" +
//                "var imgList = \"\";" +
//                "var imgs = document.getElementsByTagName(\"img\");" +
//                "for(var i=0;i<imgs.length;i++){" +
//                "var img = imgs[i];" +
//                "imgList = imgList + img.src +\";\";" +
//                "img.onclick = function(){" +
//                "window.chaychan.openImg(this.src);" +
//                "}" +
//                "}" +
//                "window.chaychan.getImgArray(imgList);" +
//                "})()");
//    }
    private void addjs(final WebView webview) {
        class JsObject {
            @JavascriptInterface
            public void jsFunctionimg(final String i) {
            }

        }
        webview.addJavascriptInterface(new JsObject(), "jscontrolimg");

    }

    private LoadWebListener mWebListener;

    /**
     * 页面加载的回调
     */
    public interface LoadWebListener {
        void onLoadFinished();
    }
}
