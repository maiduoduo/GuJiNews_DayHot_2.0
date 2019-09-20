package com.cnews.guji.smart.ui.fragment.video;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreViewVideo;
import com.cnews.guji.smart.helper.refresh.cyg.PtrRecyclerViewUIComponent;
import com.cnews.guji.smart.helper.refresh.cyg.RecyclerViewWithEV;
import com.cnews.guji.smart.helper.refresh.cyg.header.MyCustomHeader;
import com.cnews.guji.smart.helper.refresh.cyg.loadmore.OnScrollToBottomLoadMoreListener;
import com.cnews.guji.smart.helper.refresh.cyg.refersh.OnPullToRefreshListener;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.activity.SmallVideoDetailActivity;
import com.cnews.guji.smart.ui.adapter.CygBaseRecyclerAdapter;
import com.cnews.guji.smart.ui.adapter.SmallVideoAdapter;
import com.cnews.guji.smart.ui.contract.SmallVideoContract;
import com.cnews.guji.smart.ui.holder.SmallVideoViewHolder;
import com.cnews.guji.smart.ui.holder.WeakDataHolder;
import com.cnews.guji.smart.ui.presenter.SmallVideoPresenterimpl;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NoDoubleClickUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.util.statusbar.StatusBarFontHelper;
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

import static com.cnews.guji.smart.common.net.ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DOWN;
import static com.cnews.guji.smart.common.net.ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_UP;
import static com.cnews.guji.smart.common.net.ApiConstant.SMALL_VIDEO_HOST_PARAM.SMALL_VIDEO_TYPE_ALL_VIDEO;

/**
 * Author： JSYL_Dingcl
 * Des  :   小视频
 */
public class SmallVideoFragment extends BaseMvpFragment<SmallVideoPresenterimpl> implements CygBaseRecyclerAdapter.OnItemClickListener<SmallVideoViewHolder>,SmallVideoContract.View {
    private static final String TAG1 = SmallVideoFragment.class.getSimpleName();
    @BindView(R.id.am_ptr_framelayout)
    PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent;
    @BindView(R.id.ar_empty_view) View emptyView;
    @BindView(R.id.emptyRetry) TextView emptyRetry;
    @BindView(R.id.load_frameLayout)
    LoadFrameLayout loadFrameLayout;
    @BindView(R.id.fbToTop)
    ImageButton mFbToTop;
    Handler mHandler = new Handler();
    TextView mRetry;
    private int upPullNum = 1;
    private SmallVideoAdapter adapter;
    private RecyclerAdapterWithHF mAdapter;
    private List<SmallVideoBean.DataBean> mList = new ArrayList<>();
    private List<SmallVideoBean.DataBean> mAllData = new ArrayList<>();
    private long max_cursor = 0;
    boolean isLoadMore = false;
    boolean douYinDisable = false;
    private MyCustomHeader header;
    private RecyclerViewWithEV recyclerView;

    public static SmallVideoFragment getInstance() {
        SmallVideoFragment fragment = new SmallVideoFragment();
        return fragment;
    }


    public static SmallVideoFragment newInstance(Bundle args) {
        SmallVideoFragment fragment = new SmallVideoFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void lazyLoad() {
    }

    @Override
    public void intBase() {
        StatusBarCompatUtils.translucentStatusBar(getActivity(), true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.video_fragment_small_video;
    }

    @Override
    public void initPresenter() {
        mPresenter = new SmallVideoPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        mRetry = loadFrameLayout.findViewById(R.id.tv_retry);
        mList.clear();
        mAllData.clear();
        adapter = new SmallVideoAdapter(R.layout.smallvideo_item_nearby,mList,getActivity());
        mAdapter = new RecyclerAdapterWithHF(adapter);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return (mAdapter.isHeader(position) || mAdapter.isFooter(position)) ? gridLayoutManager.getSpanCount() : 1;
            }
        });
        ptrRecyclerViewUIComponent.setLayoutManager(gridLayoutManager);
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
        mRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NoDoubleClickUtils.isDoubleClick()) {
                    mPresenter.smallVideoData(mContext,HOME_HOST_PARAM_ACTION_DOWN,SMALL_VIDEO_TYPE_ALL_VIDEO,1);
                }else {
                    ToastUitl.showShort("已经努力为你加载视频，请不要频繁点击哦");
                }
            }
        });

        ptrRecyclerViewUIComponent.setOnPullToRefreshListener(new OnPullToRefreshListener() {
            @Override
            public void onPullToRefresh() {
                max_cursor = 0;
                isLoadMore = false;
                mPresenter.smallVideoData(mContext,HOME_HOST_PARAM_ACTION_DOWN,SMALL_VIDEO_TYPE_ALL_VIDEO,1);


            }
        });

        ptrRecyclerViewUIComponent.setOnScrollToBottomLoadMoreListener(new OnScrollToBottomLoadMoreListener() {
            @Override
            public void onScrollToBottomLoadMore() {
                ptrRecyclerViewUIComponent.loadMoreComplete(true);
                ptrRecyclerViewUIComponent.setAutoLoadMoreEnable(false);
                ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
            }
        });

        recyclerView = ptrRecyclerViewUIComponent.getRecyclerView();
        if (recyclerView != null) {
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

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                isLoadMore = false;
                mPresenter.smallVideoData(mContext,HOME_HOST_PARAM_ACTION_UP,SMALL_VIDEO_TYPE_ALL_VIDEO,upPullNum);
            }
        });
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


    @OnClick({
              R.id.emptyRetry
            , R.id.fbToTop
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
            case R.id.emptyRetry://空界面刷新
                isLoadMore = false;
//                mPresenter.smallVideoData(mContext,HOME_HOST_PARAM_ACTION_DOWN,SMALL_VIDEO_TYPE_ALL_VIDEO,1);
                ptrRecyclerViewUIComponent.autoRefresh();
                break;
            case R.id.fbToTop:
                ptrRecyclerViewUIComponent.autoRefresh();
                break;
        }
    }

    /**
     * 小视频数据
     * @param bean
     */
    private int isFirst = 0;
    private List<SmallVideoBean.DataBean> selectData = new ArrayList<>();
    @Override
    public void setSmallVideoData(SmallVideoBean bean) {
        if (mList != null && mList.size() > 0) {
            mList.clear();
        }
        mAllData.clear();
        if (bean == null){
            emptyView.setVisibility(View.VISIBLE);
            ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
            return;
        }
        if (bean.getData().size() > 0){
            upPullNum = 1;
            emptyView.setVisibility(View.GONE);
            ptrRecyclerViewUIComponent.setLoadMoreEnable(true);
            List<SmallVideoBean.DataBean> data = bean.getData();
            mList = data;
//            mAllData.addAll(data);
            adapter.setSmallAll(mList,ptrRecyclerViewUIComponent);
            loadFrameLayout.showContentView();
            header.getTvtitle().setText(String.format(getResources().getString(R.string.findSmallVideo),data.size()+""));
            ptrRecyclerViewUIComponent.removeView(header);
            ptrRecyclerViewUIComponent.setHeaderView(header);
            adapter.setNewData(mList);
            mAdapter.notifyDataSetChanged();
            ptrRecyclerViewUIComponent.refreshComplete();
            if (recyclerView != null) {
                recyclerView.scrollToPosition(0);
            }
        }else {
            emptyView.setVisibility(View.VISIBLE);
            ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
        }
    }

    /**
     * 加载更多
     * @param bean
     */
    @Override
    public void setSmallVideoLoadMoreData(SmallVideoBean bean) {
        if (bean == null || bean.getData().size() == 0) {
            adapter.loadMoreFail();
        } else {
            upPullNum++;
            //挑选视频
            List<SmallVideoBean.DataBean> data = bean.getData();
            mAllData.addAll(data);
            mList.addAll(data);
            adapter.setSmallAll(mList,ptrRecyclerViewUIComponent);
            adapter.addData(bean.getData());
            adapter.loadMoreComplete();
        }
    }

    /**
     * 空数据
     */
    @Override
    public void showEmpty() {
        emptyView.setVisibility(View.VISIBLE);
        ptrRecyclerViewUIComponent.setLoadMoreEnable(false);
    }

    @Override
    public void showFaild(Throwable e, String msg) {
        ptrRecyclerViewUIComponent.loadMoreComplete(false);
        ptrRecyclerViewUIComponent.refreshComplete();
        ToastUitl.showShort("网络连接失败");
        header.getTvtitle().setText("网络连接失败，请重试"+msg);
        ptrRecyclerViewUIComponent.removeView(header);
        ptrRecyclerViewUIComponent.setHeaderView(header);
        loadFrameLayout.showErrorView();
        ILog.e(TAG1,"网络连接失败--"+msg);
        ILog.e(TAG1,"网络连接失败--"+e);
        super.showFaild(e, msg);
    }

    @Override
    public void onItemClick(int position) {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            StatusBarCompatUtils.translucentStatusBar(getActivity(), true);
        } else {
            StatusBarCompatUtils.setStatusBarColor(getActivity(), 0xfffffff);
            StatusBarFontHelper.setStatusBarMode(getActivity(), true);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private boolean checkNull(SmallVideoBean.DataBean data) {
        if (data == null) {
            return true;
        } else {
            if (TextUtils.isEmpty(data.getText()) && TextUtils.isEmpty(data.getVideouri())) {
                return true;
            } else {
                return false;
            }
        }
    }


}
