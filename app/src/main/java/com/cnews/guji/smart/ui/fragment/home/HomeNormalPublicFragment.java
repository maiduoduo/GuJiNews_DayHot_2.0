package com.cnews.guji.smart.ui.fragment.home;

import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.headerview.HeaderView;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.ui.adapter.HomeHottopMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.presenter.HomeNormalPublicPresenterimpl;
import com.cnews.guji.smart.util.BannerTransformerUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.github.library.BaseQuickAdapter;
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
 * 公共列表
 *
 * @author JSYL-DCL
 */
public class HomeNormalPublicFragment extends BaseMvpFragment<HomeNormalPublicPresenterimpl> implements HeaderView.RefreshDistanceListener, HomeNormalPublicContract.View, OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    private static final String TAG = HomeNormalPublicFragment.class.getSimpleName();
    private HomeHottopMultipleRecycleAdapter adapter;
    private int pageFrType = 0;
    private static boolean isFirstSecondNAVAdd = true;
    private boolean isRemoveHeaderView = false;
    private static int pageFrType1 = 0;
    private int upPullNum = 1;
    private int downPullNum = 1;
    private boolean isFirst = true;
    private static String currenttitle = "";
    private final String HOME_HOST_PARAM_ACTION_DEFAULT = "default";

    /**
     * 改变titlebar中icon颜色时的距离
     */
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    @BindView(R.id.topbar)
    LinearLayout topbar;
    @BindView(R.id.view2)
    TextView view2;
    @BindView(R.id.swipe_target)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToLoadLayout)
    SwipeToLoadLayout mSwipeToLoadLayout;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;
    @BindView(R.id.rlTopTip)
    LinearLayout rlTopTip;
    @BindView(R.id.tvTip)
    TextView mTvTip;
    @BindView(R.id.iv_vector_header)
    AppCompatImageView ivVectorHeader;


    private List<HomeTopIFengBean.ItemBean> datasList;
    private List<HomeTopIFengBean> datas;
//    private static SwipeToLoadLayout mSwipeToLoadLayout;
//    private List<HomeTopIFengBean> totalData;


    public static HomeNormalPublicFragment getInstance(int homeHotType) {
        pageFrType1 = homeHotType;
        HomeNormalPublicFragment fragment = new HomeNormalPublicFragment();
        return fragment;
    }


    @Override
    public void intBase() {
        if (datasList != null) {
            datasList.clear();
        } else {
            datasList = new ArrayList<>();
        }
        if (datas != null) {
            datas.clear();
        } else {
            datas = new ArrayList<>();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_public;
    }

    @Override
    public void initPresenter() {
        mPresenter = new HomeNormalPublicPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        //初始化列表
        isBannerAdd = true;
        initRecyclerView();
        StatusBarCompatUtils.setStatusTextColors(false, mActivity);
        topbar.setBackgroundColor(getResources().getColor(R.color.title_bg_red));
    }

    @Override
    public void bindData() {

    }


    private void initRecyclerView() {
        //取消焦点滑动，解决卡顿
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
//        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
        adapter = new HomeHottopMultipleRecycleAdapter(mActivity, datasList,"1");
        //防止数据错乱
        adapter.setHasStableIds(true);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.openLoadAnimation();
        adapter.setLoadMoreView(new CustomLoadMoreView());
        mRecyclerView.setAdapter(adapter);
        netCheck(getResources().getString(R.string.text_nonet), mTvTip, rlTopTip);
    }


//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    public void bindData() {
//    }


    private int distanceY = 0;

    @Override
    public void initListener() {
        mSwipeToLoadLayout.setOnRefreshListener(this);
        StatusBarCompatUtils.setStatusTextColor(false, getActivity());
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                topbar.setBackgroundColor(getResources().getColor(R.color.title_bg_red));
                ILog.e(TAG, "滑动距离dy--------------->： " + dy);
                ILog.e(TAG, "滑动距离dx--------------->： " + dx);
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
        adapter.setOnRemovePositionListener(new HomeHottopMultipleRecycleAdapter.OnRemovePositionListener() {
            @Override
            public void removebadData(int position) {
                if (mRecyclerView.isComputingLayout()) {
                    mRecyclerView.post(new Runnable() {
                        @Override
                        public void run() {
//                            adapter.remove(position);
//                            adapter.notifyItemChanged(position);
                        }
                    });
                }
            }
        });
    }

    private void setTransluent() {
        topbar.setBackgroundColor(0);
        topbar.setVisibility(View.GONE);
        view2.setVisibility(View.GONE);
        view2.setBackgroundColor(0);
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


    @Override
    public void onPositionChange(int currentPosY) {
    }


    /**
     * 下拉刷新
     *
     * @param bean
     */

    @Override
    public void setRefreshData(HomeTopIFengBean bean) {
        //下拉刷新
        if (bean == null) {
            //下拉刷新关闭
            if (mSwipeToLoadLayout != null && ivVectorHeader != null) {
                mSwipeToLoadLayout.setRefreshing(false);
                ivVectorHeader.clearAnimation();
            }
            return;
        }
        adapter.getData().clear();
        adapter.setNewData(bean.item);
        mSwipeToLoadLayout.setRefreshing(false);
        ivVectorHeader.clearAnimation();
        showTip(String.format(getResources().getString(R.string.tip_text), currenttitle, bean.item.size()), mTvTip, rlTopTip);
    }

    /**
     * 加载更多
     *
     * @param bean
     */
    @Override
    public void setLoadMoreData(List<HomeTopIFengBean> bean) {
        ILog.e(TAG,"setLoadMoreData-------:"+new Gson().toJson(bean));
        //上拉加载更多
        if (bean == null || bean.size() == 0) {
            adapter.loadMoreEnd(true);
        } else {
            upPullNum++;
            List<HomeTopIFengBean.ItemBean> item0 = bean.get(0).item;
            adapter.addData(item0);
            adapter.loadMoreComplete();
            if (item0.size() < 2) {
                adapter.loadMoreEnd(false);
            }
        }
    }

    /**
     * banner
     *
     * @param bean
     */
    private boolean isBannerAdd = true;
    @Override
    public void setBannerData(HomeTopIFengBean bean) {
        ILog.e(TAG,"banner---------->:"+new Gson().toJson(bean));
        if (bean != null) {
            View bannerView = getBannerView();
            if (isBannerAdd) {
                isBannerAdd = false;
                adapter.addHeaderView(bannerView);
            }
            setBannerdataRefresh(bean, bannerView);

        } else {
            if (inflate1 != null) {
                isBannerAdd = true;
                adapter.removeHeaderView(inflate1);
            }
        }
        if (mSwipeToLoadLayout != null && ivVectorHeader != null) {
            mSwipeToLoadLayout.setRefreshing(false);
            ivVectorHeader.clearAnimation();
        }
    }



    /**
     * 第二导航菜单
     *
     * @param bean
     */
    @Override
    public void setSecondNAVData(HomeTopIFengBean bean) {
        if (bean == null) {
            isFirstSecondNAVAdd = true;
            if (inflate2 != null) {
                adapter.removeHeaderView(inflate2);
            }
        } else {
            View headerView_h2 = getHeaderView_h2();
            if (isFirstSecondNAVAdd) {
                isFirstSecondNAVAdd = false;
                adapter.addHeaderView(headerView_h2);
            }
            refreshHeaderView_h2(bean, headerView_h2);
        }

    }

    /**
     * 跑马灯播报
     *
     * @param bean
     */
    View financehn;
    private boolean isFinanceHNFirst = true;

    @Override
    public void setFinanceHNData(HomeTopIFengBean bean) {
        if (bean != null) {
            if (isFinanceHNFirst) {
                financehn = getLayoutInflater().inflate(R.layout.ifeng_item_financehn_singletitle, null);
                isFinanceHNFirst = false;
                adapter.addHeaderView(financehn);
                ((TextView) financehn.findViewById(R.id.scrollTextView)).setText(bean.item.get(0).title == null ? "" : bean.item.get(0).title);
            }
        } else {
            isFinanceHNFirst = true;
            adapter.removeHeaderView(financehn);
        }
    }

    /**
     * 置顶
     *
     * @param bean
     */
    @Override
    public void setTopData(HomeTopIFengBean bean) {

    }

    @Override
    public void showEmpty() {
        if (adapter != null) {
            adapter.getData().clear();
            adapter.notifyDataSetChanged();
//            adapter.setEmptyView();
            //下拉刷新关闭
            if (mSwipeToLoadLayout != null && ivVectorHeader != null) {
                mSwipeToLoadLayout.setRefreshing(false);
                ivVectorHeader.clearAnimation();
            }
        }
    }


    public void setCurrentPageType(int hotType) {
        pageFrType = hotType;
    }

    public void setCurrentTabName(String tabName) {
        currenttitle = tabName;
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
        try {
            AnimatedVectorDrawable background = (AnimatedVectorDrawable) ivVectorHeader.getBackground();
            background.start();
        } catch (ClassCastException e) {

        }
        mSwipeToLoadLayout.postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                isRemoveHeaderView = true;
                mPresenter.getRefreshData(mActivity, ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DEFAULT, 1, currenttitle);
            }
        }, 100);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }



    @Override
    public void onRetry() {

    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }


    @Override
    public void onLoadMoreRequested() {
        mRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getLoadMoreData(mActivity, upPullNum, currenttitle);
            }
        }, 100);
    }

    private List<String> banners = new ArrayList<>();

    /**
     * banner
     *
     * @param bean
     * @param bannerView
     * @return
     */
    private void setBannerdataRefresh(HomeTopIFengBean bean, View bannerView){
        ILog.e("fragment", "getHeaderView_h1-----item------>:\n" + new Gson().toJson(bean));
        List<HomeTopIFengBean.ItemBean> item = bean.item;
        Banner mBanner = (Banner) bannerView.findViewById(R.id.banner1);
        //设置banner样式
        //默认是NUM_INDICATOR_TITLE
        List<String> titles = new ArrayList<>();
        if (titles != null && titles.size() > 0) {
            titles.clear();
        }
        banners.clear();
        for (HomeTopIFengBean.ItemBean a : item) {
            if (a.title != null || !TextUtils.isEmpty(a.title)) {
                String thumbnail = a.thumbnail;
                if (thumbnail.endsWith(".webp")) {
                    String substring = thumbnail.substring(0, thumbnail.length() - 5);
                    banners.add(substring);
                    ILog.e("fragment", "substring----------->:\n" + substring);
                } else {
                    banners.add(a.thumbnail);
                }
                titles.add(a.title);
            }
        }
        mBanner.setImages(banners)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImageLoader(new GlideImageLoader(false))
                .setIndicatorGravity(Gravity.RIGHT)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.RIGHT)
                .isAutoPlay(true)
                .setBannerAnimation(BannerTransformerUtils.transformersHolder().get(1))
                .setDelayTime(5000)
                .start();
        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (banners.size() < 1) {
                    return;
                }
                ToastUitl.showShort("第" + position + "条");
            }
        });
    }

    /**
     * menu
     *
     * @return
     */
    View inflate1;
    private View getBannerView() {
        inflate1 = getLayoutInflater().inflate(R.layout.item_banner_with_menu_home_top, null);
        return inflate1;
    }


    /**
     * menu
     *
     * @return
     */
    View inflate2;

    private View getHeaderView_h2() {
        inflate2 = getLayoutInflater().inflate(R.layout.item_secondnav_title_image, null);
        return inflate2;
    }

    private void refreshHeaderView_h2(HomeTopIFengBean item1, View headerView_h2) {
        List<HomeTopIFengBean.ItemBean> item = item1.item;
        List<ImageView> images = new ArrayList<>();
        List<TextView> titles = new ArrayList<>();
        List<LinearLayout> linearLayouts = new ArrayList<>();
        images.clear();
        titles.clear();
        for (int i = 0; i < linearLayouts.size(); i++) {
            linearLayouts.get(i).setVisibility(View.GONE);
        }
        linearLayouts.add(headerView_h2.findViewById(R.id.llTop1));
        linearLayouts.add(headerView_h2.findViewById(R.id.llTop2));
        linearLayouts.add(headerView_h2.findViewById(R.id.llTop3));
        linearLayouts.add(headerView_h2.findViewById(R.id.llTop4));
        linearLayouts.add(headerView_h2.findViewById(R.id.llTop5));
        images.add((ImageView) headerView_h2.findViewById(R.id.ivImage1));
        images.add((ImageView) headerView_h2.findViewById(R.id.ivImage2));
        images.add((ImageView) headerView_h2.findViewById(R.id.ivImage3));
        images.add((ImageView) headerView_h2.findViewById(R.id.ivImage4));
        images.add((ImageView) headerView_h2.findViewById(R.id.ivImage5));
        titles.add((TextView) headerView_h2.findViewById(R.id.title1));
        titles.add((TextView) headerView_h2.findViewById(R.id.title2));
        titles.add((TextView) headerView_h2.findViewById(R.id.title3));
        titles.add((TextView) headerView_h2.findViewById(R.id.title4));
        titles.add((TextView) headerView_h2.findViewById(R.id.title5));
        if (images.size() > item.size()) {
            for (int i = 0; i < item.size(); i++) {
                linearLayouts.get(i).setVisibility(View.VISIBLE);
                titles.get(i).setText(item.get(i).title);
                ImageLoaderUtils.display(getActivity(), images.get(i), item.get(i).thumbnail);
            }
        } else {
            for (int i = 0; i < images.size(); i++) {
                linearLayouts.get(i).setVisibility(View.VISIBLE);
                titles.get(i).setText(item.get(i).title);
                ImageLoaderUtils.display(getActivity(), images.get(i), item.get(i).thumbnail);
            }
        }
    }


    private boolean isSplit = false;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            ILog.e(TAG, "setUserVisibleHint22:" + isVisibleToUser);
            ILog.e(TAG, "setUserVisibleHint22 datas:" + new Gson().toJson(datas));
            // 相当于onResume()方法--获取焦点
            try {
                StatusBarUtil.setDarkMode(mActivity);
                isFirstSecondNAVAdd = true;
                isSplit = true;
                if (isFirst) {
                    isFirst = false;
                    if (isRemoveHeaderView) {
                        adapter.removeAllHeaderView();
                    }
                    mSwipeToLoadLayout.setRefreshing(true);
                }
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

    public static void toReLoad() {
//       if (mSwipeToLoadLayout != null){
//           mSwipeToLoadLayout.setRefreshing(true);
//       }
    }

}
