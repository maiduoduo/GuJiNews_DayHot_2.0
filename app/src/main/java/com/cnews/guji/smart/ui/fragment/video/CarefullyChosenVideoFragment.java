package com.cnews.guji.smart.ui.fragment.video;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreViewVideo;
import com.cnews.guji.smart.helper.refresh.cyg.PtrRecyclerViewUIComponent;
import com.cnews.guji.smart.helper.refresh.cyg.RecyclerViewWithEV;
import com.cnews.guji.smart.helper.refresh.cyg.header.MyCustomHeader;
import com.cnews.guji.smart.helper.refresh.cyg.loadmore.OnScrollToBottomLoadMoreListener;
import com.cnews.guji.smart.helper.refresh.cyg.refersh.OnPullToRefreshListener;
import com.cnews.guji.smart.ui.activity.AdvertSlideimgActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.activity.ImageAtlasActivity;
import com.cnews.guji.smart.ui.activity.NewsAdvertDetailActivity;
import com.cnews.guji.smart.ui.activity.PhvideoNormalActivity;
import com.cnews.guji.smart.ui.adapter.CarefullyChosenVideoAdapter;
import com.cnews.guji.smart.ui.contract.CarefullyChosenVideoContract;
import com.cnews.guji.smart.ui.presenter.CarefullyChosenVideoPresenterimpl;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NoDoubleClickUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.view.widget.state.LoadFrameLayout;
import com.github.library.BaseQuickAdapter;
import com.github.library.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.jack.mc.cyg.cygptr.PtrFrameLayout;
import com.jack.mc.cyg.cygptr.recyclerview.RecyclerAdapterWithHF;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnews.guji.smart.common.net.ApiConstant.CARECHOSEN_VIDEO_HOST_PARAM.VIDEO_TYPE_CHOSEN_CARE_ISHOME;
import static com.cnews.guji.smart.common.net.ApiConstant.CARECHOSEN_VIDEO_HOST_PARAM.VIDEO_TYPE_CHOSEN_CARE_ISHOME_CHANNELCODE;


/**
 * Author： JSYL_Dingcl
 * Des  :   推荐视频专区
 */
public class CarefullyChosenVideoFragment extends BaseMvpFragment<CarefullyChosenVideoPresenterimpl> implements CarefullyChosenVideoContract.View {
    private static final String TAG1 = CarefullyChosenVideoFragment.class.getSimpleName();
    @BindView(R.id.refreshFramelayout)
    PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent;
    @BindView(R.id.ar_empty_view)
    View emptyView;
    @BindView(R.id.emptyRetry)
    TextView emptyRetry;
    @BindView(R.id.load_frameLayout)
    LoadFrameLayout loadFrameLayout;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;
    private TextView mRetry;
    private CarefullyChosenVideoAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    private MyCustomHeader header;
    private long max_cursor = 0;
    boolean isLoadMore = false;
    private List<CareChosenVideoBean.DataListBean> mList = new ArrayList<>();
    private String nextUrl = "";
    private TextView loadEndViewTextView;
    private RecyclerViewWithEV recyclerView;

    public static CarefullyChosenVideoFragment getInstance() {
        CarefullyChosenVideoFragment fragment = new CarefullyChosenVideoFragment();
        return fragment;
    }

    @Override
    public void intBase() {
//        StatusBarCompatUtils.setColor(getActivity(), ContextCompat.getColor(getActivity(), R.color.blue_check), 0);
//        StatusBarCompatUtils.setLightMode(getActivity());
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragmnet_carefullychosen_video;
    }

    @Override
    public void initPresenter() {
        mPresenter = new CarefullyChosenVideoPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
        mRetry = loadFrameLayout.findViewById(R.id.tv_retry);
        adapter = new CarefullyChosenVideoAdapter(mContext, mList);
        mAdapter = new RecyclerAdapterWithHF(adapter);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        ptrRecyclerViewUIComponent.setLayoutManager(linearLayoutManager);
        ptrRecyclerViewUIComponent.setAdapter(mAdapter);
        initHeader();
        ptrRecyclerViewUIComponent.delayRefresh(100);
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreViewVideo());
        adapter.openLoadAnimation();
        ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
        ptrRecyclerViewUIComponent.setAutoLoadMoreEnable(false);
    }

    @Override
    public void bindData() {
    }

    @Override
    public void initListener() {
//        recyclerView = ptrRecyclerViewUIComponent.getRecyclerView();
        if (recyclerView != null) {
//            recyclerView.click
            ptrRecyclerViewUIComponent.getRecyclerView().addOnScrollListener(new RecyclerView.OnScrollListener() {
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
        }
        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NoDoubleClickUtils.isDoubleClick()) {
                    mPresenter.careVideoData(mContext, VIDEO_TYPE_CHOSEN_CARE_ISHOME, VIDEO_TYPE_CHOSEN_CARE_ISHOME_CHANNELCODE);
                } else {
                    ToastUitl.showShort("已经努力为你加载视频，请不要频繁点击哦");
                }
            }
        });

        ptrRecyclerViewUIComponent.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                if (mList != null && mList.size() > 0) {
                    mList.clear();
                }
                max_cursor = 0;
                isLoadMore = false;
                nextUrl = "";
                mPresenter.careVideoData(mContext, VIDEO_TYPE_CHOSEN_CARE_ISHOME, VIDEO_TYPE_CHOSEN_CARE_ISHOME_CHANNELCODE);
            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
//                isLoadMore = true;
//                if (net)
                if (!TextUtils.isEmpty(nextUrl)) {
                    mPresenter.careVideoMoreData(mContext, nextUrl);
                }
            }
        });

        ptrRecyclerViewUIComponent.setOnScrollToBottomLoadMoreListener(new OnScrollToBottomLoadMoreListener() {
            @Override
            public void onScrollToBottomLoadMore() {
//                ptrRecyclerViewUIComponent.loadMoreComplete(true);
                ptrRecyclerViewUIComponent.setAutoLoadMoreEnable(false);
                ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
            }
        });


//        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
//            @Override
//            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
////                view.findViewById(R.id.)
//                ToastUitl.showShort("点击"+position+mList.get(position).getContList().get(position).getName());
//                ILog.e(TAG1,"点击"+position+mList.get(position).getContList().get(position).getName());
//
//                CareChosenVideoBean.DataListBean  itemBean = (CareChosenVideoBean.DataListBean) baseQuickAdapter.getItem(position);
//                ILog.e(TAG1,"点击itemBean------------->:\n"+new Gson().toJson(itemBean));
////                if (checkNull(itemBean)) {
////
////                } else {
////                    toLoadDeatil(itemBean);
////                }
//            }
//        });

    }


 /*   private void toLoadDeatil(CareChosenVideoBean.DataListBean itemBean) {
        if (itemBean == null) {
            return;
        }
        switch (itemBean.getItemType()) {
            case HomeTopIFengBean.ItemBean.TYPE_DOC_TITLEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_DOC_SLIDEIMG:
                Intent intent = new Intent(mContext, HotNewsDetailActivity.class);
                intent.putExtra("aid", itemBean.documentId);
                intent.putExtra("commentsall", itemBean.commentsall);
                intent.putExtra("comments", itemBean.comments);
                intent.putExtra("title", itemBean.title);
                startActivity(intent);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_TITLEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_SLIDEIMG:
                ImageAtlasActivity.launch(this, itemBean);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_TITLEIMAGE:
                Intent intent2 = new Intent(this, NewsAdvertDetailActivity.class);
                intent2.putExtra("aid", itemBean.documentId);
                startActivity(intent2);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_BIGIMAGE:
                //webview 第三方网页
                AdvertSlideimgActivity.launch(this, itemBean.link.weburl);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_BIGIMAGE:
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_TITLEIMAGE:
                PhvideoNormalActivity.launch(this);
                break;
            default:
                break;
        }
    }*/

    @OnClick({
            R.id.emptyRetry
            , R.id.fbToTop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.emptyRetry://空界面刷新
                isLoadMore = false;
                ptrRecyclerViewUIComponent.autoRefresh();
                break;
            case R.id.fbToTop:
                ptrRecyclerViewUIComponent.autoRefresh();
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

    /**
     * 精选视频
     *
     * @param bean
     */
    @Override
    public void setCareVideoData(CareChosenVideoBean bean) {
        ILog.e(TAG1, "[setCareVideoData]----------------------------------->");
        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
        if (bean == null) {
            emptyView.setVisibility(View.VISIBLE);
            return;
        }
        nextUrl = bean.getNextUrl();
        if (bean.getDataList().size() > 0) {
            emptyView.setVisibility(View.GONE);
            ptrRecyclerViewUIComponent.setLoadMoreEnable(true);
            List<CareChosenVideoBean.DataListBean> data = bean.getDataList();
            mList = data;
            //挑选视频
            loadFrameLayout.showContentView();
            header.getTvtitle().setText(String.format(getResources().getString(R.string.findSmallVideo), data.size() + ""));
            ptrRecyclerViewUIComponent.removeView(header);
            ptrRecyclerViewUIComponent.setHeaderView(header);
            adapter.getAll(bean);
            adapter.setNewData(mList);
            ptrRecyclerViewUIComponent.refreshComplete();
            if (recyclerView != null) {
                recyclerView.scrollToPosition(0);
            }
        } else {
            emptyView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setCareVideoLoadMoreData(CareChosenVideoBean bean) {
        ILog.e(TAG1, "[setCareVideoLoadMoreData]----------------------------------->");
        if (bean == null || bean.getDataList().size() == 0) {
            adapter.loadMoreFail();
        } else {
            adapter.getAll(bean);
            nextUrl = bean.getNextUrl();
            adapter.addData(bean.getDataList());
            adapter.loadMoreComplete();
        }
    }

    @Override
    public void showEmpty() {
        ILog.e(TAG1, "[showEmpty]--------->");
    }

    @Override
    public void showFaild(Throwable e, String msg) {
        ILog.e(TAG1, "[showFaild]--------->:\n" + e);
        ILog.e(TAG1, "[showFaild  msg]--------->:\n" + msg);
        super.showFaild(e, msg);
    }

    private void initHeader() {
        header = new MyCustomHeader(getActivity());
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(PtrFrameLayout.LayoutParams.MATCH_PARENT, PtrFrameLayout.LayoutParams.WRAP_CONTENT));
        header.setPadding(0, DensityUtil.dip2px(BaseApplication.getContext(), 15), 0, DensityUtil.dip2px(BaseApplication.getContext(), 10));
        header.setUp(ptrRecyclerViewUIComponent);
        header.getTvtitle().setText("");
        ptrRecyclerViewUIComponent.setHeaderView(header);
        ptrRecyclerViewUIComponent.setDurationToCloseHeader(600);
        ptrRecyclerViewUIComponent.setLoadingMinTime(1200);
        ptrRecyclerViewUIComponent.addPtrUIHandler(header);
    }
}
