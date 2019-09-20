package com.cnews.guji.smart.ui.fragment.impnews;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.ColorInt;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.common.bean.FrontNewsBean;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.helper.refresh.ypx_refreshlayout.YPXQQRefreshView;
import com.cnews.guji.smart.ui.adapter.FrontNewsAdapter;
import com.cnews.guji.smart.ui.contract.ImpNewsContract;
import com.cnews.guji.smart.ui.presenter.FrontNewsPresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.TipView;
import com.github.library.BaseQuickAdapter;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * des:T
 * 要闻页-要闻界面
 */
public class ImpTopNewsFragment extends BaseMvpFragment<FrontNewsPresenterimpl> implements ImpNewsContract.View, BaseQuickAdapter.RequestLoadMoreListener {
    private final String TAG1 = ImpTopNewsFragment.this.getClass().getSimpleName();
    private static int SIZE = 20;
    private int mStartPage = 1;
    private static final String EXTRA_IS_TRANSPARENT = "is_transparent";
    private List<FrontNewsBean.Articles> frontNewsData = new ArrayList<>();
    private FrontNewsAdapter adapter;
    private final int SUCCESS = 1;
    private final int FAILED = 0;
    /**
     * 加载要闻样式标记
     */
    private int flag = 1;
    @BindView(R.id.fake_status_bar)
    View mFakeStatusBar;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.refreshableView1)
    YPXQQRefreshView refreshableView;
    @BindView(R.id.recyclerview1)
    RecyclerView recyclerView;
    @BindView(R.id.tipView)
    TipView tipView;


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case SUCCESS:
                    refreshableView.finishRefresh(true);
                    break;
                case FAILED:
                    refreshableView.finishRefresh(false);
                    break;
                default:
                    break;
            }
        }

        ;
    };


    @Override
    public void intBase() {
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_front_news_mix_main;
    }

    @Override
    public void initPresenter() {
        mPresenter = new FrontNewsPresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        iniRecyclerView();
    }


    @Override
    public void bindData() {
        mPresenter.lodeMineChannelsRequest(getActivity());
        mPresenter.getFrontNewsData(getActivity(),flag);
        flag = 0;
    }

    @Override
    public void initListener() {
        StatusBarCompatUtils.setLightMode(getActivity());
        refreshableView.setRefreshListener(new YPXQQRefreshView.RefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.setEnableLoadMore(false);//这里的作用是防止下拉刷新的时候还可以上拉加载
                        mPresenter.getFrontNewsData(getActivity(),flag);
                        if (flag == 0) {
                            flag = 1;
                        } else {
                            flag = 0;
                        }
                    }
                }, 1000);
            }
        });

    }

    @OnClick({
    })
    public void bindViewClick(View view) {
        switch (view.getId()) {
        }
    }

    private void iniRecyclerView() {
        adapter = new FrontNewsAdapter(getActivity(), R.layout.item_fragment_front_news, frontNewsData);
        adapter.setOnLoadMoreListener(this);
        adapter.setEnableLoadMore(true);
        adapter.openLoadAnimation();
        adapter.setLoadMoreView(new CustomLoadMoreView());
//        adapter.closeLoadAnimation();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }

    private void initNewsData() {
//        if (frontNewsData != null)frontNewsData.clear();
//        for (int i = 0; i < 50; i++) {
//            frontNewsData.add(new FrontNewsBean(AppConstant.FRONT_TOP_IMAGE_URL,i+"【竞猜】NBA常规赛：火箭VS森林狼，分差是奇数还是偶数？"));
//        }
//        adapter.notifyDataSetChanged();
    }



    public void setTvTitleBackgroundColor(@ColorInt int color) {
        mTvTitle.setBackgroundColor(color);
        mFakeStatusBar.setBackgroundColor(color);
    }

    /**
     * 要闻初始化数据
     *
     * @param data
     */
    @Override
    public void setFrontNewsData(FrontNewsBean data) {
        if (data == null) {
            handler.sendEmptyMessage(FAILED);
            return;
        }
        tipView.show(String.format(getResources().getString(R.string.tip_text),"要闻",data.getArticles().size()));
        adapter.getData().clear();
        adapter.setNewData(data.getArticles());
        adapter.notifyDataSetChanged();
        intSize = data.getArticles().size();
        handler.sendEmptyMessage(SUCCESS);
    }

    @Override
    public void setFrontNewsWares(FrontNewsBean data) {

    }

    /**
     * 填充列表数据
     *
     * @param find
     */
    private int intSize = 0;
    @Override
    public void setFrontNewsMoreWares(FrontNewsBean data) {
        if (data != null) {
            if (data.getArticles() != null) {
                tipView.show(String.format(getResources().getString(R.string.tip_text),"要闻",data.getArticles().size()));
                adapter.getData().addAll(data.getArticles());
                adapter.loadMoreComplete();
            }
        }
    }

    @Override
    public void setMineChannelsRequest(ClothesBean data) {
        if (data != null) {
            List<List<String>> result = data.getResult();
            List<String> result01 = result.get(0);
            ILog.e(TAG1, "\n要闻-->> data--->:" + new Gson().toJson(data)+
                              "\n要闻-->> result--->:"+ new Gson().toJson(result)+
                              "\n要闻-->> result01--->:" + new Gson().toJson(result01)
            );
//            tvHtml.setText("  "+new Gson().toJson(t1348647909107));
        }
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (adapter.getData().size() >= intSize * 2) {
                    adapter.loadMoreEnd(false);
                } else {
                    mPresenter.getFrontNewsMoreWares(getActivity());
                }
            }
        }, 1000);
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void showSuccess() {

    }


    @Override
    public void showNoNet() {

    }

    @Override
    protected void lazyLoad() {

    }


    @Override
    public void onRetry() {

    }

    @Override
    public void showFaild(Throwable e, String msg) {
        ILog.e(TAG1, "\n要闻-->> throwable--->:" +e);
        super.showFaild(e, msg);
    }
}
