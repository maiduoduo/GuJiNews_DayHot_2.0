package com.cnews.guji.smart.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.BaseMvpActivity;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.loadmore.CustomLoadMoreView;
import com.cnews.guji.smart.ui.adapter.HomeDetailCommentAdapter;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.fragment.home.HomeCommentDialogFragment;
import com.cnews.guji.smart.ui.presenter.NewsDetailPresenterImpl;
import com.cnews.guji.smart.ui.view.HomeDetailHeaderView;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.NetWorkUtils;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.TextViewStyleView;
import com.flyco.animation.BaseAnimatorSet;
import com.flyco.animation.BounceEnter.BounceTopEnter;
import com.github.library.BaseQuickAdapter;
import com.github.library.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.cnews.guji.smart.util.DateTimeUtils.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS;

/**
 * 通用新闻详情界面
 *
 * @author JSYL-DCL
 */
public class HotNewsDetailActivity extends BaseMvpActivity<NewsDetailPresenterImpl> implements NewsDetailContract.View {
    private static final String TAG1 = HotNewsDetailActivity.class.getSimpleName();
    private static String dataUrl = "";
    private BaseAnimatorSet mBasIn;
    private List<HomeTopIFengBean.ItemBean> datasList = new ArrayList<>();
    private HomeDetailHeaderView mHeaderView;
    private HomeDetailCommentAdapter mAdapter;
    private int upPullNum = 1;
    @BindView(R.id.iv_topLogo)
    ExpandImageView mIvTopLogo;
    @BindView(R.id.rl_top)
    RelativeLayout mRlTop;
    @BindView(R.id.tv_topname)
    TextView mTvTopName;
    @BindView(R.id.tv_TopUpdateTime)
    TextView mTvTopUpdateTime;
    @BindView(R.id.flComment)
    FrameLayout mflComment;
    @BindView(R.id.llDetailRoot)
    LinearLayout mLlDetailRoot;
    @BindView(R.id.tv_comment_count)
    TextView mTvCommentCount;
    @BindView(R.id.commentRecyclerView)
    RecyclerView mCommentRecyclerView;
    private String aid = "";
    private String comments;
    private String title;
    private String aid1;

    public static void newInstance(Context context, String data) {
        context.startActivity(new Intent(context, HotNewsDetailActivity.class));
        dataUrl = data;
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
        return R.layout.activity_hotnews_detail_normal;
    }

    @Override
    public void initPresenter() {
        mPresenter = new NewsDetailPresenterImpl();
        mPresenter.attachView(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        // 解决默认位置不是最顶部
        getParentView().setFocusable(true);
        getParentView().setFocusableInTouchMode(true);
        getParentView().requestFocus();
        mBasIn = new BounceTopEnter();
        if (datasList != null){datasList.clear();}
        else {datasList = new ArrayList<>();}
        if (getIntent() != null) {
            aid = getIntent().getStringExtra("aid");
            title = getIntent().getStringExtra("title");
        }
        setWebViewSetting();

    }

    private void setComments() {
    }

    @Override
    public void bindData() {
        String commentsall = getIntent().getStringExtra("commentsall");
        comments = getIntent().getStringExtra("comments");
        if (!TextUtils.isEmpty(commentsall)) {
            mTvCommentCount.setText(comments);
        } else {
            mTvCommentCount.setText("0");
        }
    }


    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                if (!NetWorkUtils.isNetworkAvailable(mContext)) {
                    ToastUitl.showShort("您的网络出现了问题");
                    return;
                }
                mPresenter.getMoreRelationNews(mContext,aid,upPullNum,15,title);
            }
        });
        final LinearLayoutManager layoutManager = (LinearLayoutManager) mCommentRecyclerView.getLayoutManager();
        final int llInfoBottom = mHeaderView.getRlWebTitleRootView().getBottom();
        mCommentRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                int position = layoutManager.findFirstVisibleItemPosition();
                View firstVisiableChildView = layoutManager.findViewByPosition(position);
                int itemHeight = firstVisiableChildView.getHeight();
                int scrollHeight = (position) * itemHeight - firstVisiableChildView.getTop();
                if (!HotNewsDetailActivity.this.isFinishing()) {
                    //如果滚动超过用户信息一栏，显示标题栏中的用户头像和昵称
                    mRlTop.setVisibility(scrollHeight > llInfoBottom ? View.VISIBLE : View.GONE);
                }
            }
        });

        mCommentRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
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
    }

    @Override
    public void showLoading() {

    }


    @Override
    public void showFaild(Throwable throwable, String error) {
        ILog.e(TAG1, "【showFaild】------error------------》：" + error);
        ILog.e(TAG1, "【showFaild】------throwable------------》：" + throwable);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected void getBundleExtras(Intent intent) {

    }


    @Override
    public void onRetry() {
        mPresenter.getData(HotNewsDetailActivity.this, aid);
    }


    @Override
    public void detailItemData(NewsDetailBean data) {

    }

    @Override
    public void setData(NewsDetailNormalBean data) {
        if (data == null) {
            showNoNet();
            return;
        }
        showSuccess();
        mHeaderView.setDetail(data, new HomeDetailHeaderView.LoadWebListener() {
            @Override
            public void onLoadFinished() {
                //加载完成后，显示内容布局
                mRlTop.setVisibility(View.GONE);
                if (data.getBody().getSource() != null) {
                    mIvTopLogo.setImageURI(data.getBody().getThumbnail() == null ? " " :
                            data.getBody().getThumbnail());
                    mTvTopName.setText(data.getBody().getSource() == null ? " " :
                            data.getBody().getSource());
                    String updateTime = data.getBody().getUpdateTime();
                    mTvTopUpdateTime.setText(TextUtils.isEmpty(DateTimeUtils.getShortTime(updateTime == null ? "" : updateTime))? "" : DateTimeUtils.getShortTime(updateTime));
                }
                showSuccess();
            }
        });
    }

    /**
     * 热门评论
     *
     * @param data
     */
    @Override
    public void setHotComment(HomeCommentBean data) {
        if (data != null && data.getComments().size() > 0) {
            makeCommentheader();
            if (data.getComments() != null && data.getComments().size() > 0) {
                if (commentHeader != null) {
                    commentHeader.setVisibility(View.VISIBLE);
                    mAdapter.addHeaderView(commentHeader);
                    setCommentViewData(commentHeader,data,"热门评论");
                }
            }else {
                if (commentHeader != null){
                    commentHeader.setVisibility(View.GONE);
                }
            }
        }else {
            mPresenter.getNewComment(mContext, aid);
        }
        mPresenter.getRelationNews(mContext,aid,upPullNum,15,title);
    }

    /**
     * 最新评论
     * @param data
     */
    @Override
    public void setNewComment(HomeCommentBean data) {
        if (data != null && data.getComments().size() > 0) {
            makeCommentheader();
            if (data.getComments() != null && data.getComments().size() > 0) {
                if (commentHeader != null) {
                    commentHeader.setVisibility(View.VISIBLE);
                    mAdapter.addHeaderView(commentHeader);
                    setCommentViewData(commentHeader,data,"最新评论");
                }
            }else {
                if (commentHeader != null){
                    commentHeader.setVisibility(View.GONE);
                }
            }
        }
    }

    /**
     * 设置评论内容
     * @param commentHeader
     * @param data
     *
     * //这里偷懒写死了控件，导致现在逻辑量增多了很多，这是一个错误的示范
     */
    private void setCommentViewData(View commentHeader, HomeCommentBean data,String titleText) {
        HomeCommentBean.CommentsBeanX commentsBeanX = data.getComments().get(0);
        TextView titleComment = commentHeader.findViewById(R.id.titleComment);
        LinearLayout commentSecond = commentHeader.findViewById(R.id.commentSecond);
        ExpandImageView iv_avatar = commentHeader.findViewById(R.id.iv_avatar);
        TextView tv_name = commentHeader.findViewById(R.id.tv_name);
        TextView tvArea = commentHeader.findViewById(R.id.tvArea);
        TextView tv_content = commentHeader.findViewById(R.id.tv_content);
        TextView tv_time = commentHeader.findViewById(R.id.tv_time);
        TextView tv_like_count = commentHeader.findViewById(R.id.tv_like_count);
        TextView tvAllSee = commentHeader.findViewById(R.id.tvAllSee);


        LinearLayout llChildComment = commentHeader.findViewById(R.id.llChildComment);
        TextViewStyleView tvStyleUname1 = commentHeader.findViewById(R.id.tvStyleUname1);
        TextViewStyleView tvStyleUname2 = commentHeader.findViewById(R.id.tvStyleUname2);
        LinearLayout llChildComment2 = commentHeader.findViewById(R.id.llChildComment2);


        titleComment.setText(titleText);
        tv_name.setText(commentsBeanX.getUname() == null ? "" : commentsBeanX.getUname());
        tvArea.setText(commentsBeanX.getIp_from() == null ? "" : commentsBeanX.getIp_from());
        tv_content.setText(commentsBeanX.getComment_contents() == null ? "" : commentsBeanX.getComment_contents());
        tv_time.setText(commentsBeanX.getAdd_time() == null ? "" :
                DateTimeUtils.stampToTime(Long.parseLong(commentsBeanX.getAdd_time())));
        tv_like_count.setText(commentsBeanX.getUptimes() + "");
        iv_avatar.setImageURI(commentsBeanX.getFaceurl() == null ? "" : commentsBeanX.getFaceurl());

        if (commentsBeanX.children.getComments().size() > 0) {
            llChildComment.setVisibility(View.VISIBLE);
            List<HomeCommentBean.CommentsBeanX.ChildrenBean.CommentsBean> comments = commentsBeanX.children.comments;
            String count = commentsBeanX.children.getCount();
            for (int i = 0; i < comments.size(); i++) {
                String uname = comments.get(i).getUname();
                String reply_uname = comments.get(i).getReply_uname();
                if (0 == i){
                    if (reply_uname != null && TextUtils.isEmpty(reply_uname)) {
                        tvStyleUname1.setTextStyle2(mContext, comments.get(i).getUname() + " 回复 " + reply_uname+":  "+ comments.get(i).getComment_contents(),
                                comments.get(i).getUname(), reply_uname+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                    }else {
                        tvStyleUname1.setTextStyle(mContext, comments.get(i).getUname()+":  "+ comments.get(i).getComment_contents(),
                                comments.get(i).getUname()+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                    }
                }
            }
        }else {
            llChildComment.setVisibility(View.GONE);
        }


        if (data.getComments().size() > 1) {
            commentSecond.setVisibility(View.VISIBLE);
            HomeCommentBean.CommentsBeanX commentsBeanX1 = data.getComments().get(1);
            ExpandImageView iv_avatar1 = commentHeader.findViewById(R.id.iv_avatar1);
            TextView tv_name1 = commentHeader.findViewById(R.id.tv_name1);
            TextView tvArea1 = commentHeader.findViewById(R.id.tvArea1);
            TextView tv_content1 = commentHeader.findViewById(R.id.tv_content1);
            TextView tv_time1 = commentHeader.findViewById(R.id.tv_time1);
            TextView tv_like_count1 = commentHeader.findViewById(R.id.tv_like_count1);
            tv_name1.setText(commentsBeanX1.getUname() == null ? "" : commentsBeanX1.getUname());
            tvArea1.setText(commentsBeanX1.getIp_from() == null ? "" : commentsBeanX1.getIp_from());
            tv_content1.setText(commentsBeanX1.getComment_contents() == null ? "" : commentsBeanX1.getComment_contents());
            tv_time1.setText(commentsBeanX1.getAdd_time() == null ? "" :
                    DateTimeUtils.stampToTime(Long.parseLong(commentsBeanX1.getAdd_time())));
            tv_like_count1.setText(commentsBeanX1.getUptimes() + "");
            iv_avatar1.setImageURI(commentsBeanX1.getFaceurl() == null ? "" : commentsBeanX1.getFaceurl());


            if (commentsBeanX1.children.getComments().size() > 0) {
                llChildComment2.setVisibility(View.VISIBLE);
                List<HomeCommentBean.CommentsBeanX.ChildrenBean.CommentsBean> comments = commentsBeanX1.children.comments;
                String count = commentsBeanX1.children.getCount();
                for (int i = 0; i < comments.size(); i++) {
                    String uname = comments.get(i).getUname();
                    String reply_uname = comments.get(i).getReply_uname();
                    if (0 == i){
                        if (reply_uname != null && TextUtils.isEmpty(reply_uname)) {
                            tvStyleUname2.setTextStyle2(mContext, comments.get(i).getUname() + " 回复 " + reply_uname+":  "+ comments.get(i).getComment_contents(),
                                    comments.get(i).getUname(), reply_uname+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                        }else {
                            tvStyleUname2.setTextStyle(mContext, comments.get(i).getUname()+":  "+ comments.get(i).getComment_contents(),
                                    comments.get(i).getUname()+":", R.style.order_remark_normal, R.style.order_remark_emphasize);
                        }
                    }

                }
            }else {
                llChildComment2.setVisibility(View.GONE);
            }

        }else {
            commentSecond.setVisibility(View.GONE);
        }
        tvAllSee.setText(String.format(getResources().getString(R.string.see_all_comment),comments+""));
    }


    /**
     * 相关新闻
     * @param data
     */
    @Override
    public void setRelationNews(HomeTopIFengBean data) {
        if (data != null){
            datasList.clear();
            datasList.addAll(data.item);
            makerelationheader();
            if (relationHeader != null && data.item.size() > 0){
                mAdapter.addHeaderView(relationHeader);
            }
            List<HomeTopIFengBean.ItemBean> item = data.item;
            mAdapter.setNewData(item);
        }
    }

    /**
     * 更多相关
     * @param data
     */
    @Override
    public void setMoreRelationNews(HomeTopIFengBean data) {
        if (data == null || data.item.size() == 0) {
            mAdapter.loadMoreFail();
        } else {
            List<HomeTopIFengBean.ItemBean> item = data.item;
            if (item != null && item.size() > 0) {
                upPullNum++;
                datasList.addAll(data.item);
                mAdapter.addData(item);
                mAdapter.loadMoreComplete();
            }else {
                mAdapter.loadMoreFail();
            }
        }
    }

    @Override
    public void showEmpty() {

    }

    View commentHeader;
    private View makeCommentheader() {
        commentHeader = getLayoutInflater().inflate(R.layout.home_item_detail_comment, null);
        LinearLayout llListMore = commentHeader.findViewById(R.id.llListMore);
        llListMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aid = getIntent().getStringExtra("aid");
                ILog.e(TAG1, "onViewClicked-aid------->:" + aid);
                HomeCommentDialogFragment homeCommentDialogFragment = HomeCommentDialogFragment.newInstance(aid);
                homeCommentDialogFragment.show(getSupportFragmentManager(), "COMMENT");
            }
        });
        return commentHeader;
    }
    View relationHeader;
    private View makerelationheader() {
        relationHeader = getLayoutInflater().inflate(R.layout.home_item_detail_relation, null);
        return relationHeader;
    }


    /**
     * Webview配置
     */
    private void setWebViewSetting() {
        if (!NetWorkUtils.isNetworkAvailable(mContext)) {
            ToastUitl.showShort("您的网络出现了问题");
            return;
        }
        mPresenter.getHotComment(mContext, aid);
        mHeaderView = new HomeDetailHeaderView(this);
        if (datasList != null) {
            datasList.clear();
        } else {
            datasList = new ArrayList<>();
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mCommentRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new HomeDetailCommentAdapter(datasList, mContext);
        mCommentRecyclerView.setAdapter(mAdapter);
        mAdapter.setEnableLoadMore(true);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation();
        if (mHeaderView != null) {
            mAdapter.addHeaderView(mHeaderView);
        }
        mPresenter.getData(HotNewsDetailActivity.this, aid);
    }


    @OnClick({
            R.id.iv_back
            , R.id.flComment
    })
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finishThis();
                break;
            case R.id.flComment:
                if (comments == null || "0".equals(comments)){
                    ToastUitl.showShort("暂无评论");
                    return;
                }

                //底部评论的图标
                RecyclerView.LayoutManager layoutManager = mCommentRecyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    LinearLayoutManager linearManager = (LinearLayoutManager) layoutManager;
                    int firstPosition = linearManager.findFirstVisibleItemPosition();
                    int last = linearManager.findLastVisibleItemPosition();
                    if (firstPosition == 0 && last == 0) {
                        //处于头部，滚动到第一个条目
                        mCommentRecyclerView.scrollToPosition(1);
                        mRlTop.setVisibility(View.VISIBLE);
                    } else {
                        //不是头部，滚动到头部
                        mCommentRecyclerView.scrollToPosition(0);
                        mRlTop.setVisibility(View.GONE);
                    }
                }
                break;
        }
    }


    private void toLoadDeatil(HomeTopIFengBean.ItemBean itemBean) {
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
