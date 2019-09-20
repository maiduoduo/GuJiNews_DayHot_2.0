package com.cnews.guji.smart.ui.fragment.home;

import android.content.Intent;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.XunshiBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.dialog.NewsDelPop;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.headerview.HeaderView;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.ui.activity.AdvertSlideimgActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.activity.ImageAtlasActivity;
import com.cnews.guji.smart.ui.activity.NewsAdvertDetailActivity;
import com.cnews.guji.smart.ui.activity.PhvideoNormalActivity;
import com.cnews.guji.smart.ui.adapter.HomeHottopMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.model.source.TopNewsDataHelper;
import com.cnews.guji.smart.ui.presenter.HomeHotTopPresenterimpl;
import com.cnews.guji.smart.util.BannerTransformerUtils;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ScreenUtil;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.flyco.animation.FlipEnter.FlipRightEnter;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.github.library.BaseQuickAdapter;
import com.github.library.listener.OnItemChildClickListener;
import com.github.library.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 头条推荐
 *
 * @author JSYL-DCL
 */
public class HomeTopFragment extends BaseMvpFragment<HomeHotTopPresenterimpl> implements HeaderView.RefreshDistanceListener, HomeHotTopContract.View, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = HomeTopFragment.class.getSimpleName();
    private HomeHottopMultipleRecycleAdapter adapter;
    private int pageFrType = 0;
    private static int pageFrType1 = 0;
    private static TabLayout mTabLayout;
    private int upPullNum = 1;
    private int downPullNum = 1;
    private static String currenttitle = "";
    private NewsDelPop newsDelPop;
    /**
     * 改变titlebar中icon颜色时的距离
     */
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.swipe_target)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;
    @BindView(R.id.rlTopTip)
    LinearLayout rlTopTip;
    @BindView(R.id.tvTip)
    TextView mTvTip;
    @BindView(R.id.iv_vector_header)
    ImageView ivVectorHeader;
    @BindView(R.id.tvXunshiPlan)
    TextView tvXunshiPlan;


    private List<HomeTopIFengBean.ItemBean> topDatasList;
    private List<HomeTopIFengBean> totalData;


    public static HomeTopFragment newInstance(int homeHotType, TabLayout tabLayout) {
        pageFrType1 = homeHotType;
        mTabLayout = tabLayout;
        HomeTopFragment homeHotTopFragment = new HomeTopFragment();
        return homeHotTopFragment;
    }


    @Override
    public void intBase() {
        if (topDatasList != null) {
            topDatasList.clear();
        } else {
            topDatasList = new ArrayList<>();
        }
        if (totalData != null) {
            totalData.clear();
        } else {
            totalData = new ArrayList<>();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_topnews;
    }

    @Override
    public void initPresenter() {
        mPresenter = new HomeHotTopPresenterimpl();
        mPresenter.attachView(this);
    }


    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        //初始化列表
        initRecyclerView();
        initDelPop();
//        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "1362");
//        mPresenter.getXunshiData(mActivity,"1362");
    }

    private void initDelPop() {
        newsDelPop = new NewsDelPop(getActivity())
                .alignCenter(false)
                .widthScale(0.95f)
                .showAnim(new FlipRightEnter())
//                .dismissAnim(new FlipHorizontalExit())
//                .showAnim(new SlideRightEnter())
                .dismissAnim(new SlideRightExit())
                .offset(-100, 0)
                .dimEnabled(true);
    }

    private void initRecyclerView() {
        //取消焦点滑动，解决卡顿
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
        adapter = new HomeHottopMultipleRecycleAdapter(mActivity, topDatasList, "1");
        //防止数据错乱
        adapter.setHasStableIds(true);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.openLoadAnimation();
        adapter.setLoadMoreView(new CustomLoadMoreView());
        mRecyclerView.setAdapter(adapter);
        netCheck(getResources().getString(R.string.text_nonet), mTvTip, rlTopTip);
        adapter.addHeaderView(getHeaderView_h1());
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void bindData() {
        netCheck(getResources().getString(R.string.text_nonet), mTvTip, rlTopTip);
        mSwipeToLoadLayout.setRefreshing(true);
        try {
            AnimatedVectorDrawable background = (AnimatedVectorDrawable) ivVectorHeader.getBackground();
            background.start();
        } catch (ClassCastException e) {

        }
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                mPresenter.getIFengHomeTopNewsData(mActivity, 1, false, currenttitle);
            }
        }, 100);

    }


    private int distanceY = 0;
    private int gravityFlag = 10;

    @Override
    public void initListener() {
        mSwipeToLoadLayout.setOnRefreshListener(this);
        StatusBarCompatUtils.setStatusTextColor(false, getActivity());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                ILog.e(TAG, "滑动距离dy--------------->： " + dy);
                ILog.e(TAG, "滑动距离dx--------------->： " + dx);
                distanceY += dy;
                if (distanceY > DensityUtil.dip2px(mActivity, 20)) {
                    topbar.setVisibility(View.VISIBLE);
                    topbar.setBackgroundColor(getResources().getColor(R.color.white));
                    if (mTabLayout != null) {
                        mTabLayout.setTabTextColors(getResources().getColor(R.color.Black_Alpha80), getResources().getColor(R.color.red));
                    }
                    StatusBarCompatUtils.setLightMode(mActivity);
                    if (Build.VERSION.SDK_INT > 10) {
                        topbar.setAlpha(distanceY * 1.0f / DensityUtil.dip2px(mActivity, 100));
                    } else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                } else {
                    setTransluent();
                    if (mTabLayout != null) {
                        if (0 == mTabLayout.getSelectedTabPosition()) {
                            StatusBarCompatUtils.setDarkMode(mActivity);
                        } else {
                            StatusBarCompatUtils.setLightMode(mActivity);
                        }
                    }
                }

                if (distanceY > DensityUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED)) {
                } else if (distanceY <= DensityUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED)) {
                    if (mTabLayout != null) {
                        mTabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.red));
                    }
                    topbar.setBackgroundColor(0);
                    view2.setVisibility(View.GONE);
                    view2.setBackgroundColor(0);
                }
                if (dy == 0) {
                    setTransluent();
                } else {
                    //-1代表顶部,返回true表示没到顶,还可以滑
                    //1代表底部,返回true表示没到底部,还可以滑
                    boolean b = recyclerView.canScrollVertically(-1);
                    boolean b1 = recyclerView.canScrollVertically(1);
                    if (!b) {
                        setTransluent();
                    }
                }
            }

            //设置RecyclerView滑动监听器 addOnScrollListener(),其中setOnScrollListener()方法已过时
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //获得recyclerView的线性布局管理器
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                //获取到第一个item的显示的下标  不等于0表示第一个item处于不可见状态 说明列表没有滑动到顶部 显示回到顶部按钮
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager manager = (LinearLayoutManager) layoutManager;
                    int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                    // 当不滚动时
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        // 判断是否滚动超过一屏
                        if (firstVisibleItemPosition == 0) {
                            mFbToTop.setVisibility(View.GONE);
                        } else {
                            //显示回到顶部按钮
                            mFbToTop.setVisibility(View.VISIBLE);
                        }
                        //获取RecyclerView滑动时候的状态
                    } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                        //拖动中
                        mFbToTop.setVisibility(View.GONE);
                    }
                }
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {

                HomeTopIFengBean.ItemBean itemBean = (HomeTopIFengBean.ItemBean) baseQuickAdapter.getItem(position);
                if (checkNull(itemBean)) {
                } else {
                    toLoadDeatil(itemBean);
                }


            }
        });


        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeTopIFengBean.ItemBean itemBean = (HomeTopIFengBean.ItemBean) baseQuickAdapter.getItem(i);
                try {
                    View viewById = view.findViewById(R.id.rlDel);
                    if (viewById != null) {
                        viewById.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                view.getHeight();
                                int[] location = new int[2];
                                view.getLocationInWindow(location);
                                ILog.e(TAG, "点击的item的高度:" + view.getHeight() + "x值:" + location[0] + "y值" + location[1]);
                                if (itemBean.style == null) {
                                    return;
                                }
                                if (ScreenUtil.getScreenWidth(mActivity) - 50 - location[1] < ScreenUtil.dip2px(80)) {
                                    newsDelPop
                                            .anchorView(view)
                                            .gravity(Gravity.TOP)
                                            .setBackReason(itemBean.style.backreason, true, i)
                                            .show();
                                } else {
                                    newsDelPop
                                            .anchorView(view)
                                            .gravity(Gravity.BOTTOM)
                                            .setBackReason(itemBean.style.backreason == null ? null : itemBean.style.backreason, false, i)
                                            .show();
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    showFaild(e, e.getMessage());
                }
            }
        });

        newsDelPop.setClickListener(new NewsDelPop.onClickListener() {
            @Override
            public void onClick(int position) {
                newsDelPop.dismiss();
                adapter.remove(position);
                showTip(getResources().getString(R.string.delpopremovetip), mTvTip, rlTopTip);
            }
        });
    }

    private void toLoadDeatil(HomeTopIFengBean.ItemBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getItemType()) {
            case HomeTopIFengBean.ItemBean.TYPE_DOC_TITLEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_DOC_SLIDEIMG:
                Intent intent = new Intent(mActivity, HotNewsDetailActivity.class);
                intent.putExtra("aid", itemBean.documentId);
                intent.putExtra("commentsall", itemBean.commentsall);
                intent.putExtra("comments", itemBean.comments);
                intent.putExtra("title", itemBean.title);
                startActivity(intent);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_TITLEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_SLIDEIMG:
                ImageAtlasActivity.launch(getActivity(), itemBean);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_TITLEIMAGE:
                Intent intent2 = new Intent(getActivity(), NewsAdvertDetailActivity.class);
                intent2.putExtra("aid", itemBean.documentId);
                startActivity(intent2);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_BIGIMAGE:
                //webview 第三方网页
                AdvertSlideimgActivity.launch(getActivity(), itemBean.link.weburl);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_BIGIMAGE:
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_TITLEIMAGE:
                PhvideoNormalActivity.launch(getActivity());
                break;
            default:
                break;
        }
    }


    private void setTransluent() {
        topbar.setBackgroundColor(0);
        topbar.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view2.setBackgroundColor(0);
        if (mTabLayout != null) {
            if (0 == mTabLayout.getSelectedTabPosition()) {
                mTabLayout.setTabTextColors(getResources().getColor(R.color.white), getResources().getColor(R.color.red));
            } else {
                mTabLayout.setTabTextColors(getResources().getColor(R.color.Black_Alpha80), getResources().getColor(R.color.red));
            }
        }
    }


    @OnClick({
            R.id.fbToTop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.fbToTop:
                mRecyclerView.scrollToPosition(0);
                topbar.setVisibility(View.GONE);
                view2.setVisibility(View.GONE);
                break;
        }
    }

    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ILog.e(TAG, "setUserVisibleHint22:" + isVisibleToUser);
            // 相当于onResume()方法--获取焦点
            try {
                StatusBarUtil.setDarkMode(mActivity);
                setTransluent();
                mRecyclerView.scrollToPosition(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ILog.e(TAG, "else setUserVisibleHint22:" + isVisibleToUser);
            // 相当于onpause()方法---失去焦点
        }
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        if (hidden) {
            ILog.e(TAG, "hidden22:" + hidden);
            //相当于Fragment的onPause()
        } else {
            ILog.e(TAG, "else hidden22:" + hidden);
            // 相当于Fragment的onResume()
        }
    }

    /**
     * 下拉后刷新数据
     */
    private void updateData() {
//        mPtrFrame.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mPresenter.getHomeHotTopData();
//            }
//        }, 1000);
    }


    @Override
    public void onPositionChange(int currentPosY) {
    }


    @Override
    public void setHomeHotTopData(HomeTophotIndexBean tophotBean) {
    }


    /**
     * 头条下拉数据
     *
     * @param tophotBean 来源：ifeng.news
     */
    @Override
    public void setIFengHomeTopNewsData(List<HomeTopIFengBean> tophotBean, boolean isup) {
        if (isup) {
            //上拉加载更多
            if (tophotBean == null || tophotBean.size() == 0) {
                adapter.loadMoreFail();
            } else {
                upPullNum++;
                List<HomeTopIFengBean.ItemBean> item0 = tophotBean.get(0).item;
                if (tophotBean.size() > 1) {
                    List<HomeTopIFengBean.ItemBean> item1 = tophotBean.get(1).item;
                    if (item0.size() > item1.size()) {
                        adapter.addData(item0);
                        topDatasList.addAll(item0);
                        adapter.loadMoreComplete();
                        if (item0.size() < 3) {
                            adapter.loadMoreEnd(false);
                        }
                    } else {
                        adapter.addData(item1);
                        topDatasList.addAll(item1);
                        adapter.loadMoreComplete();
                        if (item1.size() < 3) {
                            adapter.loadMoreEnd(false);
                        }
                    }
                } else {
                    adapter.addData(item0);
                    topDatasList.addAll(item0);
                    adapter.loadMoreComplete();
                    if (item0.size() < 3) {
                        adapter.loadMoreEnd(false);
                    }
                }
//                adapter.loadMoreComplete();
//                if (item0.size() < 3) {
//                    adapter.loadMoreEnd(false);
//                }
            }
        } else {
            //下拉刷新
            mSwipeToLoadLayout.setRefreshing(false);
            ivVectorHeader.clearAnimation();
            if (tophotBean == null) {
                return;
            }
            topDatasList.clear();
            totalData.addAll(tophotBean);
            List<HomeTopIFengBean.ItemBean> item0 = tophotBean.get(0).item;
            List<HomeTopIFengBean.ItemBean> item1 = tophotBean.get(1).item;
            adapter.getData().clear();
            if (item0.size() > item1.size()) {
                item1.addAll(item0);
                topDatasList.addAll(item1);
                adapter.setNewData(item1);
            } else {
                item0.addAll(item1);
                topDatasList.addAll(item0);
                adapter.setNewData(item0);
            }
            mSwipeToLoadLayout.setRefreshing(false);
            ivVectorHeader.clearAnimation();
            showTip(String.format(getResources().getString(R.string.tip_text), currenttitle, item0.size()), mTvTip, rlTopTip);
        }
    }


    public void setCurrentPageType(int hotType) {
        pageFrType = hotType;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onRefresh() {
        if (!NetWorkUtils.isNetworkAvailable(mActivity)) {
            showTip(getResources().getString(R.string.text_nonet), mTvTip, rlTopTip);
            mSwipeToLoadLayout.setRefreshing(false);
            ivVectorHeader.clearAnimation();
            return;
        }
        mSwipeToLoadLayout.setRefreshing(true);
        try {
            AnimatedVectorDrawable background = (AnimatedVectorDrawable) ivVectorHeader.getBackground();
            background.start();
        } catch (ClassCastException e) {

        }
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                mPresenter.getIFengHomeTopNewsData(mActivity, 1, false, currenttitle);
            }
        }, 200);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }


    @Override
    public void showEmpty() {
        showTip(getResources().getString(R.string.empty), mTvTip, rlTopTip);
        mSwipeToLoadLayout.setRefreshing(false);
        ivVectorHeader.clearAnimation();
        showDataEmpty();
    }

    @Override
    public void showXunshiData(XunshiBean data) {
        ILog.e(TAG, "巡视数据计划----------->:" + new Gson().toJson(data));
        tvXunshiPlan.setText("巡视数据计划---:         " + new Gson().toJson(data));


    }

    @Override
    public void showNoNet() {
        showTip(getResources().getString(R.string.no_net), mTvTip, rlTopTip);
        mSwipeToLoadLayout.setRefreshing(false);
        ivVectorHeader.clearAnimation();
        super.showNoNet();
    }

    @Override
    public void showFaild(Throwable e, String msg) {
        super.showFaild(e, msg);
        try {
            if (mSwipeToLoadLayout != null && ivVectorHeader != null) {
                mSwipeToLoadLayout.setRefreshing(false);
                ivVectorHeader.clearAnimation();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        ILog.e(TAG, "头条推荐：------------------------> " + e.getMessage());
    }


    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }


    private List<Integer> banners = new ArrayList<>();

    private View getHeaderView_h1() {
        View mTop_h1 = getLayoutInflater().inflate(R.layout.item_banner_with_menu_home_top, null);
        Banner mBanner = (Banner) mTop_h1.findViewById(R.id.banner1);
        //设置banner样式
        //默认是NUM_INDICATOR_TITLE
        List<String> titles = new ArrayList<>();
        if (titles != null && titles.size() > 0) titles.clear();
        banners.clear();
        for (int i = 0; i < TopNewsDataHelper.NEWS_BANNER_IMAGES.length; i++) {
            banners.add(TopNewsDataHelper.NEWS_BANNER_IMAGES[i]);
        }
        mBanner.setImages(banners)
//                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImageLoader(new GlideImageLoader(false))
                .setIndicatorGravity(Gravity.CENTER_HORIZONTAL)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.RIGHT)
                .isAutoPlay(true)
                .setBannerAnimation(BannerTransformerUtils.transformersHolder().get(1))
                .setDelayTime(3000)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (banners.size() < 1) {
                    return;
                }
//                bannerToRead(banners.get(position));
            }
        });
        return mTop_h1;
    }

    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getIFengHomeTopNewsData(mActivity, upPullNum, true, currenttitle);
            }
        }, 100);
    }

    public void setCurrentTabName(String tabName) {
        currenttitle = tabName;
    }

    private boolean checkNull(HomeTopIFengBean.ItemBean data) {
        if (data == null) {
            return true;
        } else {
            if (!TextUtils.isEmpty(data.errorText)) {
                return true;
            } else {
                return false;
            }
        }
    }
}
