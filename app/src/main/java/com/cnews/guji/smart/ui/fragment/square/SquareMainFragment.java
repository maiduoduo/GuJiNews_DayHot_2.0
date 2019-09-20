package com.cnews.guji.smart.ui.fragment.square;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.ui.activity.AdvertSlideimgActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.activity.ImageAtlasActivity;
import com.cnews.guji.smart.ui.activity.NewsAdvertDetailActivity;
import com.cnews.guji.smart.ui.activity.PhvideoNormalActivity;
import com.cnews.guji.smart.ui.activity.SquareMoreHotActivity;
import com.cnews.guji.smart.ui.adapter.SquareHotwordAdapter;
import com.cnews.guji.smart.ui.adapter.SquareMainRecycleAdapter;
import com.cnews.guji.smart.ui.adapter.SquareNavigationMenuAdapter;
import com.cnews.guji.smart.ui.contract.SquareMainContract;
import com.cnews.guji.smart.ui.presenter.SquarePresenterimpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.github.library.BaseQuickAdapter;
import com.github.library.listener.OnItemClickListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 广场专区
 * @author JSYL-DCL
 */
public class SquareMainFragment extends BaseMvpFragment<SquarePresenterimpl> implements SquareMainContract.View {
    private static final String TAG = SquareMainFragment.class.getSimpleName();
    private List<SquareMainBean.ItemBean> datasList;
    private SquareMainRecycleAdapter adapter;
    private PtrFrameLayout mFrame;
    private boolean isRemoveHeaderView = false;
    @BindView(R.id.mRecyclerView)
    PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.mPtrFrameLayout)
    PtrFrameLayout mPtrFrameLayout;

    public static SquareMainFragment getInstance(String title) {
        SquareMainFragment fragment = new SquareMainFragment();
        return fragment;
    }

    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_main_news_square;
    }

    @Override
    public void initPresenter() {
        mPresenter = new SquarePresenterimpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarCompatUtils.setLightMode(mActivity);
        if (datasList != null){datasList.clear();}
        else {datasList = new ArrayList<>();}
        mPtrFrameLayout.disableWhenHorizontalMove(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(adapter = new SquareMainRecycleAdapter(_mActivity, datasList));
        adapter.setEnableLoadMore(true);
        adapter.setLoadMoreView(new CustomLoadMoreView());
        adapter.openLoadAnimation();
    }

    @Override
    public void bindData() {
        if (!NetWorkUtils.isNetworkAvailable(_mActivity)){
//            showNoNet();
            mPtrFrameLayout.refreshComplete();
        }
        mPtrFrameLayout.autoRefresh(true);
        mPresenter.getRefreshData(_mActivity, ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_DEFAULT);
    }

    @Override
    public void initListener() {
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (!NetWorkUtils.isNetworkAvailable(mActivity)){
//                    showEmpty();
                    mPtrFrameLayout.refreshComplete();
                }
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, mRecyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (!NetWorkUtils.isNetworkAvailable(_mActivity)){
//                    showEmpty();
                    mPtrFrameLayout.refreshComplete();
                }
                mFrame = frame;
                isRemoveHeaderView = true;
                mPresenter.getRefreshData(_mActivity, ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_DEFAULT);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                ToastUitl.showShort("点击"+position+datasList.get(position).getTitle());

                SquareMainBean.ItemBean itemBean = (SquareMainBean.ItemBean) baseQuickAdapter.getItem(position);
                if (checkNull(itemBean)) {
                } else {
                    toLoadDeatil(itemBean);
                }




            }
        });

        adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getLoadMoreData(_mActivity,ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_UP);
            }
        });
    }


    /**
     * 列表常规数据
     * @param bean
     */
    @Override
    public void setRefreshData(SquareMainBean bean) {
        ILog.e(TAG,"列表常规数据 setRefreshData-------->:\n"+new Gson().toJson(bean));
        isRemoveHeaderView = true;
        if (bean == null) {
            showFaild(null,"");
            mPtrFrameLayout.refreshComplete();
        } else {
            datasList.clear();
            datasList.addAll(bean.getItem());
            adapter.setNewData(bean.getItem());
            mPtrFrameLayout.refreshComplete();
            showSuccess();
        }
    }

    /**
     * 加载更多
     * @param bean
     */
    @Override
    public void setLoadMoreData(List<SquareMainBean> bean) {
        ILog.e(TAG,"加载更多 setLoadMoreData-------->:\n"+new Gson().toJson(bean));
        if (bean == null || bean.size() == 0) {
            adapter.loadMoreFail();
        } else {
            datasList.addAll(bean.get(0).getItem());
            adapter.addData(bean.get(0).getItem());
            adapter.loadMoreComplete();
        }
    }

    /**
     * HEADER导航菜单
     * @param bean
     */
    @Override
    public void setSquareNavigation(SquareMainBean bean) {
        ILog.e(TAG,"HEADER导航菜单 setSquareNavigation-------->:\n"+new Gson().toJson(bean));
        if (isRemoveHeaderView) {
            if (adapter.getHeaderLayoutCount() > 0) {
                adapter.removeAllHeaderView();
            }
        }
        if (bean != null){
            List<SquareMainBean.ItemBean> item = bean.getItem();
//            SquareMainBean.ItemBean itemBean = new SquareMainBean.ItemBean();
//            itemBean.setThumbnail("http://p3.ifengimg.com/a/2019/0505/3df6c0276a2e1cfsize14_w150_h150.png");
//            itemBean.setTitle("最新");
//            item.add(itemBean);
//            item.add(itemBean);
//            item.add(itemBean);
            View inflate = getLayoutInflater().from(mContext).inflate(R.layout.ifeng_square_item_parent_navigation_menu, null);
            adapter.addHeaderView(inflate);
            PowerfulRecyclerView navigationRecyclerview = inflate.findViewById(R.id.recyclerview);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 5);
            new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (position < 6 || position > 10) {
                        return 4;
                    }
                    return 3;
                }
            };
            navigationRecyclerview.setLayoutManager(gridLayoutManager);
            navigationRecyclerview.setAdapter(new SquareNavigationMenuAdapter(R.layout.square_item_navigation_menu,item,mContext));
            navigationRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ToastUitl.showShort("menu:"+i+":"+item.get(i).getTitle());
                }
            });
        }

    }

    /**
     * HEADER热议
     * @param bean
     */
    @Override
    public void setSquareHotword(SquareMainBean bean) {
        if (bean != null){
            List<SquareMainBean.ItemBean> item = bean.getItem();
            View inflate = getLayoutInflater().from(mContext).inflate(R.layout.ifeng_square_item_parent_hotword, null);
            adapter.addHeaderView(inflate);
            ExpandImageView docTitleIcon = inflate.findViewById(R.id.docTitleIcon);
            TextView titleType = inflate.findViewById(R.id.titleType);
            LinearLayout llHotMore = inflate.findViewById(R.id.llHotMore);
            PowerfulRecyclerView hotwordRecyclerview = inflate.findViewById(R.id.recyclerview);
            titleType.setText(bean.getChConfig().getTitleIcon() == null ? "" : bean.getChConfig().getDesc());
            docTitleIcon.setImageURI(bean.getChConfig().getTitleIcon() == null ? "" : bean.getChConfig().getTitleIcon());
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
            hotwordRecyclerview.setLayoutManager(gridLayoutManager);
            hotwordRecyclerview.setAdapter(new SquareHotwordAdapter(R.layout.square_item_hotword,item,mContext));
            hotwordRecyclerview.addOnItemTouchListener(new OnItemClickListener() {
                @Override
                public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ToastUitl.showShort("menu:"+i+":"+item.get(i).getTitle());
                }
            });
            llHotMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SquareMoreHotActivity.launch(getActivity(),bean);
                    ToastUitl.showShort("查看更多热文");

                }
            });
        }
    }

    /**
     * 空数据
     */
    @Override
    public void showEmpty() {
        ILog.e(TAG,"showEmpty--------------->:\n");
    }

    @Override
    public void onFail(Throwable throwable) {
        ToastUitl.showShort("网络异常，稍后刷新试试看");
        if (mPtrFrameLayout != null) {
            mPtrFrameLayout.refreshComplete();
            showFaild(throwable,throwable.getMessage());
        }
    }

    private boolean checkNull(SquareMainBean.ItemBean data) {
        if (data == null) {
            return true;
        } else {
           return false;
        }
    }


    private void toLoadDeatil(SquareMainBean.ItemBean itemBean) {
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
                intent.putExtra("title", itemBean.getTitle());
                startActivity(intent);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_TITLEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_SLIDEIMG:
//                ImageAtlasActivity.launch(getActivity(), itemBean);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_TITLEIMAGE:
                Intent intent2 = new Intent(getActivity(), NewsAdvertDetailActivity.class);
                intent2.putExtra("aid", itemBean.documentId);
                startActivity(intent2);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_BIGIMAGE:
                //webview 第三方网页
//                AdvertSlideimgActivity.launch(getActivity(), itemBean.link.weburl);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_BIGIMAGE:
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_TITLEIMAGE:
                PhvideoNormalActivity.launch(getActivity());
                break;
            default:
                break;
        }
    }

    @Override
    protected void lazyLoad() {

    }

//    @Override
//    public void showFaild(Throwable e, String msg) {
//        super.showFaild(e, msg);
//
//    }
}
