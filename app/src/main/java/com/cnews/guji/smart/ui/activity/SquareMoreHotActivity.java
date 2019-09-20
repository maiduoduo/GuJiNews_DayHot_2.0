package com.cnews.guji.smart.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpActivity;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.ui.adapter.SquareHotMoreAdpter;
import com.cnews.guji.smart.ui.contract.SquareHotMoreContract;
import com.cnews.guji.smart.ui.presenter.SquareHotMorePresenterImpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.cnews.guji.smart.view.widget.SwipeBackLayout;
import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * desc: 图片新闻浏览页面.
 *
 * @author JSYL-DCL
 */
public class SquareMoreHotActivity extends BaseMvpActivity<SquareHotMorePresenterImpl> implements SquareHotMoreContract.View {
    private boolean isShow = true;
    private static final String TAG = SquareMoreHotActivity.class.getSimpleName();
    private List<SquareHotMoreBean.ItemBean> mData = new ArrayList<>();
    private SquareHotMoreAdpter mSquareHotMoreAdpter;
    @BindView(R.id.btnTitlebarBack) ImageView btnTitlebarBack;
    @BindView(R.id.btnTitlebarName) TextView btnTitlebarName;
    @BindView(R.id.btnTitlebarShare) ImageView btnTitlebarShare;
    @BindView(R.id.swipe_layout) SwipeBackLayout mSwipeBackLayout;
    @BindView(R.id.mRecyclerView) PowerfulRecyclerView mRecyclerView;
    @BindView(R.id.relativeLayout) RelativeLayout mRelativeLayout;
    @BindView(R.id.rl_top) RelativeLayout mRlTop;

    public static void launch(Activity context, SquareMainBean bean) {
        Intent intent = new Intent(context, SquareMoreHotActivity.class);
        context.startActivity(intent);
        context.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

    }

    @Override
    protected void onStart() {
        super.onStart();
        StatusBarCompatUtils.setColor(this, ContextCompat.getColor(this, R.color.white), 0);
        StatusBarCompatUtils.setLightMode(this);
    }

    @Override
    public void intBase() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_square_hot_more;
    }

    @Override
    public void initPresenter() {
        mPresenter = new SquareHotMorePresenterImpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        mData.clear();
        mSwipeBackHelper.setSwipeBackEnable(true);
        mSwipeBackLayout.setDragDirectMode(SwipeBackLayout.DragDirectMode.VERTICAL);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemViewCacheSize(10);
        mSquareHotMoreAdpter = new SquareHotMoreAdpter(R.layout.ifeng_square_hotmore_list,mData,this);
        mRecyclerView.setAdapter(mSquareHotMoreAdpter);
        mPresenter.hotMore(mContext, ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_DEFAULT,1);
    }

    @Override
    public void bindData() {
//        if (!NetWorkUtils.isNetworkAvailable(mContext)){
//            ToastUitl.showShort("暂无网络");
//            return;
//        }
//        mPresenter.hotMore(mContext, ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_DEFAULT,1);
    }

    @OnClick({
            R.id.btnTitlebarBack,
            R.id.btnTitlebarShare
    })
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btnTitlebarBack:
                finish();
                break;
            case R.id.btnTitlebarShare:
                ToastUitl.showShort("分享");
                break;
        }
    }


    @Override
    public void initListener() {
//        mSwipeBackLayout.setOnSwipeBackListener(new SwipeBackLayout.SwipeBackListener() {
//            @Override
//            public void onViewPositionChanged(float fractionAnchor, float fractionScreen) {
//                mRelativeLayout.getBackground().setAlpha(255 - (int) Math.ceil(255 * fractionAnchor));
//                DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
//                df.setMaximumFractionDigits(1);
//                df.setRoundingMode(RoundingMode.HALF_UP);
//                String dd = df.format(fractionAnchor);
//                double alpha = 1 - (Float.valueOf(dd) + 0.8);
//                if (fractionAnchor == 0 && isShow) {
//                    mRlTop.setAlpha(1f);
//                    mRlTop.setVisibility(View.VISIBLE);
//                } else {
//                    if (alpha == 0) {
//                        mRlTop.setVisibility(View.GONE);
//                        mRlTop.setAlpha(1f);
//                    } else {
//                        if (mRlTop.getVisibility() != View.GONE) {
//                            mRlTop.setAlpha((float) alpha);
//                        }
//                    }
//                }
//            }
//        });
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





    @Override
    public void finish() {
        super.finish();
//        finishThis();
//        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    @Override
    public void showFaild(Throwable throwable, String error) {
        super.showFaild(throwable, error);
        ILog.e(TAG,"[setHotMore]--------showFaild--------------->:\n"+throwable);
        ILog.e(TAG,"[setHotMore]--------showFaild--------------->:\n"+error);
    }


    /**
     * 热点
     * @param data
     */
    @Override
    public void setHotMore(List<SquareHotMoreBean> data) {
//        ILog.e(TAG,"[setHotMore]----------------------->:\n"+new Gson().toJson(data));
        if (data == null) {
        } else {
            List<SquareHotMoreBean.ItemBean> item = data.get(0).getItem();
            mData.clear();
            mData.addAll(item);
            mSquareHotMoreAdpter.setNewData(mData);
        }
    }

    /**
     * 加载更多
     * @param data
     */

    @Override
    public void sethotMoreLoadMore(List<SquareHotMoreBean> data) {

    }
}
