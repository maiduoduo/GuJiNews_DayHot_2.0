package com.cnews.guji.smart.ui.fragment.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.helper.dialog.NewsDelPop;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.helper.refresh.loadingview.LoadingView;
import com.cnews.guji.smart.helper.textview.ScrollTextView;
import com.cnews.guji.smart.ui.activity.AdvertSlideimgActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.activity.ImageAtlasActivity;
import com.cnews.guji.smart.ui.activity.NewsAdvertDetailActivity;
import com.cnews.guji.smart.ui.activity.PhvideoNormalActivity;
import com.cnews.guji.smart.ui.adapter.HomeHottopMultipleRecycleAdapter;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.presenter.HomeNormalPublicPresenterimpl;
import com.cnews.guji.smart.util.BannerTransformerUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ScreenUtil;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.cnews.guji.smart.view.widget.PtrWeiXunHeader;
import com.cnews.guji.smart.view.widget.TipView;
import com.flyco.animation.FlipEnter.FlipRightEnter;
import com.flyco.animation.SlideExit.SlideRightExit;
import com.github.library.BaseQuickAdapter;
import com.github.library.listener.OnItemChildClickListener;
import com.github.library.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * HOME 新闻列表
 * @author JSYL-DCL
 */
public class HomeListFragment extends BaseMvpFragment<HomeNormalPublicPresenterimpl> implements HomeNormalPublicContract.View {
    @BindView(R.id.mRecyclerView)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrFrameLayout mPtrFrameLayout;
    @BindView(R.id.tip_view)
    TipView mTvTip;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;

    private static final String TAG1 = HomeListFragment.class.getSimpleName();
    private String channelTitle;
    private String channelCode;
    private List<HomeTopIFengBean.ItemBean> datasList;
    private HomeHottopMultipleRecycleAdapter adapter;
    private int upPullNum = 1;
    private int downPullNum = 1;
    private int gravityFlag = 10;
    private boolean isRemoveHeaderView = false;
    private PtrWeiXunHeader mHeader;
    private PtrFrameLayout mFrame;
    private LoadingView headerView;
    private static TabLayout mTabLayout;
    private NewsDelPop newsDelPop;


    public static HomeListFragment newInstance(String channelTitle, String channelCode,TabLayout tabLayout) {
        mTabLayout = tabLayout;
        Bundle bundle = new Bundle();
        bundle.putString(ApiConstant.CHANNEL_TITLE, channelTitle);
        bundle.putString(ApiConstant.CHANNEL_CODE, channelCode);
        HomeListFragment fragment = new HomeListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void intBase() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home_list;
    }
    @Override
    public void initPresenter() {
        mPresenter = new HomeNormalPublicPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        if (getArguments() == null) {return;}
        channelTitle = getArguments().getString(ApiConstant.CHANNEL_TITLE);
        channelCode = getArguments().getString(ApiConstant.CHANNEL_CODE);
        if (datasList != null){datasList.clear();}
        else {datasList = new ArrayList<>();}
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter = new HomeHottopMultipleRecycleAdapter(_mActivity, datasList,channelTitle));
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.openLoadAnimation();
        initDelPop();

    }

    @Override
    public void bindData() {
        if (!NetWorkUtils.isNetworkAvailable(_mActivity)){
            showNoNet();
            mPtrFrameLayout.refreshComplete();
        }
        mPresenter.getRefreshData(_mActivity, "图文".equals(channelTitle) ? ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DOWN
               : ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DEFAULT, 1, channelTitle);
    }

    @Override
    public void initListener() {
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (!NetWorkUtils.isNetworkAvailable(mActivity)){
                    showEmpty();
                    mPtrFrameLayout.refreshComplete();
                }
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (!NetWorkUtils.isNetworkAvailable(_mActivity)){
                    showEmpty();
                    mPtrFrameLayout.refreshComplete();
                }
//                headerView.setVisibility(View.VISIBLE);
                ILog.e(TAG1,"downPullNum:" + downPullNum);
                ILog.e(TAG1,"channelCode:" + channelTitle);
                mFrame = frame;
                isRemoveHeaderView = true;
                mPresenter.getRefreshData(_mActivity, "图文".equals(channelTitle) ? ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DOWN
                        : ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DEFAULT, 1, channelTitle);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                ToastUitl.showShort("点击"+position+datasList.get(position).title);
                ILog.e(TAG1,"点击"+position+datasList.get(position).title);
                ILog.e(TAG1,"点击"+position+datasList.get(position).type);

                HomeTopIFengBean.ItemBean itemBean = (HomeTopIFengBean.ItemBean) baseQuickAdapter.getItem(position);
                if (checkNull(itemBean)) {
                } else {
                    toLoadDeatil(itemBean);
                }
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                ILog.e(TAG1,"onLoadMoreRequested upPullNum: " + upPullNum);
                mPresenter.getLoadMoreData(_mActivity, upPullNum, channelTitle);
            }
        });


        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
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

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                HomeTopIFengBean.ItemBean itemBean = (HomeTopIFengBean.ItemBean) baseQuickAdapter.getItem(i);
                try {
                    switch (view.getId()) {
                        case R.id.rlDel:
                            view.getHeight();
                            int[] location = new int[2];
                            view.getLocationInWindow(location);
                            ILog.e(TAG, "点击的item的高度:" + view.getHeight() + "x值:" + location[0] + "y值" + location[1]);
                            if (itemBean.style == null) { return; }
                            if (ScreenUtil.getScreenWidth(mActivity) - 50 - location[1] < ScreenUtil.dip2px(80)) {
                                newsDelPop
                                        .anchorView(view)
                                        .gravity(Gravity.TOP)
                                        .setBackReason(itemBean.style.backreason == null ? null : itemBean.style.backreason, true, i)
                                        .show();
                            } else {
                                newsDelPop
                                        .anchorView(view)
                                        .gravity(Gravity.BOTTOM)
                                        .setBackReason(itemBean.style.backreason == null ? null : itemBean.style.backreason, false, i)
                                        .show();
                            }
                            break;
                    }
                }catch (Exception e){
                    showFaild(e,e.getMessage());
                }

            }
        });

        newsDelPop.setClickListener(new NewsDelPop.onClickListener() {
            @Override
            public void onClick(int position) {
                newsDelPop.dismiss();
                adapter.remove(position);
                mTvTip.show(getResources().getString(R.string.delpopremovetip));
            }
        });
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


    @OnClick({
            R.id.fbToTop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.fbToTop:
                mRecyclerView.scrollToPosition(0);
                break;
        }
    }


    @Override
    public void showFaild(Throwable throwable, String error) {

    }


    @Override
    public void onRetry() {
        bindData();
        super.onRetry();
    }

    @Override
    public void setRefreshData(HomeTopIFengBean bean) {
        if (bean == null) {
            showFaild(null,"");
            mPtrFrameLayout.refreshComplete();
        } else {
            downPullNum++;
            datasList.clear();
            datasList.addAll(bean.item);
            adapter.setNewData(bean.item);
            mTvTip.show(String.format(getResources().getString(R.string.tip_text),channelTitle,bean.item.size()));
//            showTip(String.format(getResources().getString(R.string.tip_text), channelTitle, bean.item.size()), mTvTip, rlTopTip);
            mPtrFrameLayout.refreshComplete();
            showSuccess();
//            ILog.e(TAG1,"loadData datalist: " + datasList.toString());
        }
    }

    @Override
    public void setLoadMoreData(List<HomeTopIFengBean> bean) {
        if (bean == null || bean.size() == 0) {
            adapter.loadMoreFail();
        } else {
            upPullNum++;
            datasList.addAll(bean.get(0).item);
            adapter.addData(bean.get(0).item);
            adapter.loadMoreComplete();
//            ILog.e(TAG1,"loadMoreData: " + bean.get(0).item.toString());
        }
    }


    private boolean isBannerAdd = true;
    private List<String> banners = new ArrayList<>();
    @Override
    public void setBannerData(HomeTopIFengBean bean) {
//        ILog.e(TAG1,"banner---------->:"+new Gson().toJson(bean));
        if (isRemoveHeaderView) {
            adapter.removeAllHeaderView();
        }
        if (bean != null) {
            View bannerView = getBannerView();
            adapter.addHeaderView(bannerView);
            setBannerdataRefresh(bean, bannerView);
        } else {
            if (inflate1 != null) {
                adapter.removeHeaderView(inflate1);
            }
        }
    }

    @Override
    public void setSecondNAVData(HomeTopIFengBean bean) {
        ILog.e(TAG1,"setSecondNAVData快讯---------->:"+new Gson().toJson(bean));
    }

    /**
     * 滚动快讯
     * @param bean
     */
    @Override
    public void setFinanceHNData(HomeTopIFengBean bean) {
        ILog.e(TAG1,"滚动快讯---------->:"+new Gson().toJson(bean));
//        if (isRemoveHeaderView) {
//            adapter.removeAllHeaderView();
//        }
        if (bean != null){
            View financeHNView = getScrolltextView();
            ScrollTextView scrollTextView = financeHNView.findViewById(R.id.scrollTextView);
            adapter.addHeaderView(financeHNView);
            scrollTextView.setText(bean.item.get(0).title == null ? "" : bean.item.get(0).title);
        }
    }

    @Override
    public void setTopData(HomeTopIFengBean bean) {

    }

    @Override
    public void showEmpty() {
        mTvTip.show(getResources().getString(R.string.empty));
        mPtrFrameLayout.refreshComplete();
        showDataEmpty();
    }

    @Override
    public void showNoNet() {
        mTvTip.show(getResources().getString(R.string.no_net));
        super.showNoNet();
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
     * 滚动快讯布局
     *
     * @return
     */
    View inflate2;
    private View getScrolltextView() {
        inflate2 = getLayoutInflater().inflate(R.layout.ifeng_item_financehn_singletitle, null);
        return inflate2;
    }

    /**
     * banner
     *
     * @param bean
     * @param bannerView
     * @return
     */
    private void setBannerdataRefresh(HomeTopIFengBean bean, View bannerView){
//        ILog.e(TAG1, "getHeaderView_h1-----item------>:\n" + new Gson().toJson(bean));
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
                    ILog.e(TAG1, "substring----------->:\n" + substring);
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

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser){//展示，可见
            ILog.e(TAG1,"isVisibleToUser list:"+isVisibleToUser);
        }else {
            ILog.e(TAG1,"[else]isVisibleToUser list:"+isVisibleToUser);
        }
    }

    @Override
    protected void lazyLoad() {

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
