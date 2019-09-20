package com.cnews.guji.smart.ui.fragment.home;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpDialogFragment;
import com.cnews.guji.smart.base.BaseMvpFragment;
import com.cnews.guji.smart.common.bean.ChannelBean;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.event.NewChannelEvent;
import com.cnews.guji.smart.common.event.SelectChannelEvent;
import com.cnews.guji.smart.common.listener.ItemDragHelperCallBack;
import com.cnews.guji.smart.common.listener.OnChannelListener;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.ui.adapter.ChannelAdapter;
import com.cnews.guji.smart.ui.adapter.HomeCommentRecycleAdapter;
import com.cnews.guji.smart.ui.contract.HomeCommentContract;
import com.cnews.guji.smart.ui.presenter.HomeCommentPresenterImpl;
import com.cnews.guji.smart.ui.presenter.NewsDetailPresenterImpl;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.util.statusbar.StatusBarUtil_helper;
import com.github.library.BaseQuickAdapter;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleTransformer;

import org.simple.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 公共评论界面
 *
 * @author JSYL-DCL
 */

public class HomeCommentDialogFragment extends BaseMvpDialogFragment<HomeCommentPresenterImpl> implements View.OnClickListener, HomeCommentContract.View {
    private static final String TAG1 = HomeCommentDialogFragment.class.getSimpleName();
    private static List<HomeCommentBean.CommentsBeanX> mDatas = new ArrayList<>();
    private List<HomeCommentBean.CommentsBeanX> hotDatas = new ArrayList<>();
    private List<HomeCommentBean.CommentsBeanX> newDatas = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private ImageView miVDismiss;
    private LinearLayout mLlFriendCircle;
    private LinearLayout mLlWechat;
    private LinearLayout mLlQq;
    private LinearLayout mLlZfb;
    private int upPullNum = 2;
    private HomeCommentRecycleAdapter hotadapter;
    private static String aid = "";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_white);
        hotDatas.clear();
        if (mDatas != null) {
            mDatas.clear();
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Dialog dialog = getDialog();
        if (dialog != null) {
            //添加动画
            dialog.getWindow().setWindowAnimations(R.style.dialogSlideAnim);
        }
        return inflater.inflate(R.layout.dialog_dialogfragment_news_bottom, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = new HomeCommentPresenterImpl();
        mPresenter.attachView(this);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        miVDismiss = view.findViewById(R.id.ivdismiss);
        mLlFriendCircle = view.findViewById(R.id.ll_wechat_friend_circle);
        mLlWechat = view.findViewById(R.id.ll_wechat_friend);
        mLlQq = view.findViewById(R.id.ll_qq);
        mLlZfb = view.findViewById(R.id.ll_sms);
        miVDismiss.setOnClickListener(this);
        mLlFriendCircle.setOnClickListener(this);
        mLlWechat.setOnClickListener(this);
        mLlQq.setOnClickListener(this);
        mLlZfb.setOnClickListener(this);
        init();
    }

    public static HomeCommentDialogFragment newInstance(String documentid) {
        HomeCommentDialogFragment commentFragment = new HomeCommentDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("documentid", documentid);
        commentFragment.setArguments(bundle);
        aid = documentid;
        return commentFragment;
    }


    private void init() {
        ILog.e("hotnews", "init-aid------->:" + aid);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if (!TextUtils.isEmpty(aid)) {
            mPresenter.getHotComment(mActivity, aid);
            mPresenter.getNewComment(mActivity, aid);
        } else {
            showFaild(null, "获取aid失败");
        }
    }


    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivdismiss:
                dismiss();
                break;
            case R.id.ll_wechat_friend_circle:
                ToastUitl.showShort("朋友圈");
                dismiss();
                break;
            case R.id.ll_wechat_friend:
                ToastUitl.showShort("微信");
                dismiss();
                break;
            case R.id.ll_qq:
                ToastUitl.showShort("QQ");
                dismiss();
                break;
            case R.id.ll_sms:
                ToastUitl.showShort("支付宝");
                dismiss();
                break;
        }
    }


    /**
     * 最热评论
     * @param data
     */
    @Override
    public void setHotComment(HomeCommentBean data) {
        hotDatas.clear();
        if (data != null && data.getComments().size() > 0) {
            data.getComments().get(0).setOs("0001");
            hotDatas.addAll(data.getComments());
            mDatas.addAll(hotDatas);
        }
        ILog.e("HotNews", "setHotComment-hotDatas------->:" + new Gson().toJson(hotDatas));
    }

    /**
     * 最新评论
     * @param data
     */
    @Override
    public void setNewComment(HomeCommentBean data) {
        newDatas.clear();
        if (data != null && data.getComments().size() > 0) {
            data.getComments().get(0).setOs("0002");
            newDatas.addAll(data.getComments());
            mDatas.addAll(newDatas);
        }
        ILog.e("HotNews", "setHotComment-newDatas2222------------------------------->");
        ILog.e("HotNews", "setHotComment-newDatas2222------->:" + new Gson().toJson(newDatas));
        mRecyclerView.setAdapter(hotadapter = new HomeCommentRecycleAdapter(getActivity(), R.layout.item_ifeng_home_comment, mDatas));
        hotadapter.setEnableLoadMore(true);
        hotadapter.setLoadMoreView(new CustomLoadMoreView());
        hotadapter.openLoadAnimation();
        hotadapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.getMoreNewComment(mActivity, aid, upPullNum);
            }
        });

    }

    /**
     * 更多评论
     *
     * @param data
     */
    @Override
    public void setMoreNewComment(HomeCommentBean data) {
        ILog.e(TAG1, "loadMoreData: " + data.getComments().toString());
        try {
            if (data == null) {
                hotadapter.loadMoreFail();
            } else {
                if (data.getComments().size() < 1) {
                    hotadapter.loadMoreEnd();
                } else if (data.getComments().size() > 1 && data.getComments().size() < 9){
                    hotadapter.addData(data.getComments());
                    hotadapter.loadMoreEnd();
                }else {
                    upPullNum++;
                    hotadapter.addData(data.getComments());
                    hotadapter.loadMoreComplete();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showFaild(Throwable e, String msg) {
        ILog.e("HotNews", "showFaild--Throwable------>:" + e);
        ILog.e("HotNews", "showFaild--msg------>:" + msg);
        super.showFaild(e, msg);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return null;
    }
}
