package com.cnews.guji.smart.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.DrawableImageViewTarget;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpActivity;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.contract.ImageAtlasContract;
import com.cnews.guji.smart.ui.presenter.ImageAtlasPresenterImpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.view.MyScrollView;
import com.cnews.guji.smart.view.TextViewStyleView;
import com.cnews.guji.smart.view.widget.HackyViewPager;
import com.cnews.guji.smart.view.widget.SwipeBackLayout;
import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * desc: 图片新闻浏览页面.
 *
 * @author JSYL-DCL
 */
public class ImageAtlasActivity extends BaseMvpActivity<ImageAtlasPresenterImpl> implements ImageAtlasContract.View {
    private static final String AID = "aid";
    private static final String LinkUrl = "linkurl";
    private static final String ISCMPP = "isCmpp";
    private static final String ItemTitle = "itemtitle";
    private boolean isShow = true;
    private static final String TAG = ImageAtlasActivity.class.getSimpleName();
    private List<ImageAtlasBean> imageAtlasDatas = new ArrayList<>();
    private String itemTitle = "";
    @BindView(R.id.btn_titlebar_left)
    ImageView mBtnTitlebarLeft;
    @BindView(R.id.tv_titlebar_name)
    TextView mTvTitlebarName;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.tv_info)
    TextView mTvInfo;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.scrollview)
    MyScrollView mScrollview;
    @BindView(R.id.relativeLayout)
    RelativeLayout mRelativeLayout;
    @BindView(R.id.view_pager)
    HackyViewPager mViewPager;
    @BindView(R.id.swipe_layout)
    SwipeBackLayout mSwipeBackLayout;
    @BindView(R.id.tvStyleUname)
    TextViewStyleView tvStyleUname;
    @BindView(R.id.titlebar_ico)
    ExpandImageView tvTitlebarIco;

    public static void launch(Activity context, HomeTopIFengBean.ItemBean bodyBean) {
        Intent intent = new Intent(context, ImageAtlasActivity.class);
//        if (bodyBean.getId().contains(ApiConstants.sGetNewsArticleCmppApi)
//                || bodyBean.getDocumentId().startsWith("cmpp")) {
//            intent.putExtra(ISCMPP, true);
//        } else {
//            intent.putExtra(ISCMPP, false);
//        }
        intent.putExtra(AID, bodyBean.documentId);
        intent.putExtra(LinkUrl, bodyBean.link.url);
        intent.putExtra(ItemTitle, bodyBean.title);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_imageatlas;
    }

    @Override
    public void initPresenter() {
        mPresenter = new ImageAtlasPresenterImpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(this, android.R.color.black));
        if (getIntent().getExtras() == null) return;
        String aid = getIntent().getStringExtra(AID);
        boolean isCmpp = getIntent().getBooleanExtra(ISCMPP, false);
        String linkurl = getIntent().getStringExtra(LinkUrl);
        itemTitle = getIntent().getStringExtra(ItemTitle);
        mPresenter.imageAtlasDetail(mContext, linkurl);
        mRelativeLayout.getBackground().setAlpha(255);
        mSwipeBackHelper.setSwipeBackEnable(true);
        mSwipeBackLayout.setDragDirectMode(SwipeBackLayout.DragDirectMode.VERTICAL);
        mScrollview.getBackground().mutate().setAlpha(100);
        mRlTop.getBackground().mutate().setAlpha(100);
    }

    @Override
    public void bindData() {
    }

    @OnClick(R.id.btn_titlebar_left)
    public void onViewClicked() {
        finish();
    }


    @Override
    public void initListener() {
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (imageAtlasDatas != null && imageAtlasDatas.size() > 0) {
                    ImageAtlasBean imageAtlasBean = imageAtlasDatas.get(0);
//                    mTvInfo.setText((position + 1) + " / " + imageAtlasBean.getBody().getSlides().size() + "  " + imageAtlasBean.getBody().getSlides().get(position).getDescription());
                    mTvInfo.setText(imageAtlasBean.getBody().getSlides().get(position).getDescription()+"");
                    if (0 == position) {
                        mTvTitle.setVisibility(View.VISIBLE);
                        mTvTitle.setText(itemTitle + "");
                    }else {
                        mTvTitle.setVisibility(View.GONE);
                    }
                    tvStyleUname.setTextStyle(mContext, position+1 +" / "+ imageAtlasBean.getBody().getSlides().size(),
                            position+1+"", R.style.order_remark_normal1, R.style.order_remark_emphasize1);
                }
                if (position == 0) {
                    mSwipeBackHelper.setSwipeBackEnable(true);
                } else {
                    mSwipeBackHelper.setSwipeBackEnable(false);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mSwipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
            @Override
            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
                mRelativeLayout.getBackground().setAlpha(255 - (int) Math.ceil(255 * fractionAnchor));
                DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
                df.setMaximumFractionDigits(1);
                df.setRoundingMode(RoundingMode.HALF_UP);
                String dd = df.format(fractionAnchor);
                double alpha = 1 - (Float.valueOf(dd) + 0.8);
                if (fractionAnchor == 0 && isShow) {
                    mScrollview.setAlpha(1f);
                    mRlTop.setAlpha(1f);
                    mRlTop.setVisibility(View.VISIBLE);
                    mScrollview.setVisibility(View.VISIBLE);
                } else {
                    if (alpha == 0) {
                        mRlTop.setVisibility(View.GONE);
                        mScrollview.setVisibility(View.GONE);
                        mScrollview.setAlpha(1f);
                        mRlTop.setAlpha(1f);
                    } else {
                        if (mRlTop.getVisibility() != View.GONE) {
                            mRlTop.setAlpha((float) alpha);
                            mScrollview.setAlpha((float) alpha);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }

    @Override
    public void onRetry() {
        bindData();
    }


    /**
     * 图集列表信息
     *
     * @param data
     */
    @Override
    public void setImageAtlasDetail(ImageAtlasBean data) {
        if (imageAtlasDatas != null) imageAtlasDatas.clear();
        if (data != null) {
            imageAtlasDatas.add(data);
            try {
                ImageAtlasBean imageAtlasBean = imageAtlasDatas.get(0);
                mTvInfo.setText(imageAtlasBean.getBody().getSlides().get(0).getDescription()+"");
                mTvTitle.setText(itemTitle+"");
                mViewPager.setAdapter(new ImageAtlasViewPagerAdapter(imageAtlasBean.getBody().getSlides()));
                mTvTitlebarName.setText(imageAtlasBean.getBody().getSource() == null ? "" : imageAtlasBean.getBody().getSource());
                tvTitlebarIco.setImageURI(imageAtlasBean.getBody().getThumbnail() == null ? "" : imageAtlasBean.getBody().getThumbnail());
                tvStyleUname.setTextStyle(mContext, "1 "+" / "+ imageAtlasBean.getBody().getSlides().size(),
                        "1", R.style.order_remark_normal1, R.style.order_remark_emphasize1);
                //showSuccess();
            } catch (Exception e) {
                // showFaild();
                e.printStackTrace();
            }
        }
    }

    private class ImageAtlasViewPagerAdapter extends PagerAdapter {
        private List<ImageAtlasBean.BodyBean.SlidesBean> slidesBeanList;
        private PhotoView mPhotoView;
        private ProgressBar mProgressBar;

        private ImageAtlasViewPagerAdapter(List<ImageAtlasBean.BodyBean.SlidesBean> slidesBeanList) {
            this.slidesBeanList = slidesBeanList;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.imageatlas_loadimage, null);
            mPhotoView = (PhotoView) view.findViewById(R.id.photoview);
            mProgressBar = (ProgressBar) view
                    .findViewById(R.id.loading);
            mPhotoView.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView imageView, float v, float v1) {
                    if (isShow) {
                        isShow = false;
                        setView(mRlTop, false);
                        setView(mScrollview, false);
                    } else {
                        isShow = true;
                        setView(mRlTop, true);
                        setView(mScrollview, true);
                    }
                }
            });
            mProgressBar.setVisibility(View.GONE);
            boolean gifTag = false;
//            String url = "http://img.soogif.com/TQsLiBiRRYhjypt1HmvyheUfjIBI6C0r.gif";
            boolean b = slidesBeanList.get(position).getImage().endsWith(".gif");
//            boolean b = url.endsWith(".gif");
            if (b){
                gifTag = true;
            }else {
                gifTag = false;
            }
            ImageLoaderUtils.LoadImage(mContext, slidesBeanList.get(position).getImage(),
                    new DrawableImageViewTarget(mPhotoView) {
                        @Override
                        public void setDrawable(Drawable drawable) {
                            super.setDrawable(drawable);
                            //mProgressBar.setVisibility(View.VISIBLE);
                        }

                        @Override
                        public void onLoadStarted(@Nullable Drawable placeholder) {
                            super.onLoadStarted(placeholder);
                            // mProgressBar.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onLoadFailed(Exception e, Drawable errorDrawable) {
                            super.onLoadFailed(e, errorDrawable);
                            mProgressBar.setVisibility(View.GONE);
                        }

                        @Override
                        public void onResourceReady(Drawable resource, GlideAnimation<? super Drawable> glideAnimation) {
                            super.onResourceReady(resource, glideAnimation);
                            mProgressBar.setVisibility(View.GONE);
                        }


                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {
                            super.onLoadCleared(placeholder);
                            mProgressBar.setVisibility(View.GONE);
                        }
                    },gifTag);

            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return slidesBeanList == null ? 0 : slidesBeanList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }


    private void setView(final View view, final boolean isShow) {
        AlphaAnimation alphaAnimation;
        if (isShow) {
            alphaAnimation = new AlphaAnimation(0, 1);
        } else {
            alphaAnimation = new AlphaAnimation(1, 0);
        }
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(500);
        view.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(isShow ? View.VISIBLE : View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
