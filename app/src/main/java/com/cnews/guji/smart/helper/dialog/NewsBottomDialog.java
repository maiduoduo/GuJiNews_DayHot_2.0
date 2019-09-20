package com.cnews.guji.smart.helper.dialog;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.ui.adapter.HomeCommentRecycleAdapter;
import com.cnews.guji.smart.util.ToastUitl;
import com.flyco.dialog.widget.base.BottomBaseDialog;
import com.trello.rxlifecycle2.LifecycleTransformer;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 底部弹出的评论列表
 * @author JSYL-DCL
 */
public class NewsBottomDialog extends BottomBaseDialog<NewsBottomDialog> {
    @BindView(R.id.ll_wechat_friend_circle)
    LinearLayout mLlWechatFriendCircle;
    @BindView(R.id.ll_wechat_friend)
    LinearLayout mLlWechatFriend;
    @BindView(R.id.ll_qq)
    LinearLayout mLlQq;
    @BindView(R.id.ll_sms)
    LinearLayout mLlSms;
    @BindView(R.id.ivdismiss)
    ImageView ivdismiss;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private HomeCommentRecycleAdapter hotadapter;
    private List<HomeCommentBean.CommentsBeanX> data = new ArrayList<>();

    public NewsBottomDialog(Context context, View animateView, List<HomeCommentBean.CommentsBeanX> data) {
        super(context, animateView);
        this.data = data;
    }



    public NewsBottomDialog(Context context, List<HomeCommentBean.CommentsBeanX> data) {
        super(context);
        this.data = data;
    }

    @Override
    public View onCreateView() {
        showAnim(new FlipVerticalAlphaEnter());
        dismissAnim(new FlipVerticalAlphaOuter());
        View inflate = View.inflate(mContext, R.layout.dialog_custom_news_bottom, null);
        ButterKnife.bind(this, inflate);
        return inflate;
    }

    @Override
    public void setUiBeforShow() {

        mLlWechatFriendCircle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort( "朋友圈");
                dismiss();
            }
        });
        mLlWechatFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort( "微信");
                dismiss();
            }
        });
        mLlQq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort( "QQ");
                dismiss();
            }
        });
        mLlSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort( "支付宝");
                dismiss();
            }
        });

        initData();
    }

    @OnClick({
            R.id.ivdismiss
    })
    public void clickView(View view){
        switch (view.getId()) {
            case R.id.ivdismiss:
                dismiss();
                break;
        }
    }


    private void initData() {
        if (data != null){data.clear();}
//        for (int i = 0; i < 15; i++) {
//            HomeCommentBean.CommentsBeanX commentsBeanX = new HomeCommentBean.CommentsBeanX();
//            commentsBeanX.setUname("wangsanwu0"+i);
//            data.add(commentsBeanX);
//        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(hotadapter = new HomeCommentRecycleAdapter(mContext, R.layout.item_ifeng_home_comment,data));
//        hotadapter.setEnableLoadMore(true);
//        hotadapter.setLoadMoreView(new CustomLoadMoreView());
//        hotadapter.openLoadAnimation();

//        textView.setTextStyle(mContext, "你好吗，哈哈哈哈，我很好", "哈哈哈哈", R.style.order_remark_normal, R.style.order_remark_emphasize);

        getComments();

    }

    private void getComments() {

    }

}
