package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.view.widget.FullWindowVideoView;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;


/**
 * Author： JSYL_Dingcl
 * Des  :   小视频条目
 */
public class SmallVideoItemAdapter extends BaseMultiItemQuickAdapter<SmallVideoBean.DataBean, BaseViewHolder> {
    private static final String TAG1 = SmallVideoItemAdapter.class.getSimpleName();
    private String placeurl = "http://uvideo.spriteapp.cn/video/2019/0216/682e25fe31ce11e9bcf4842b2b4c75ab_wpd.mp4";
    private Context mContext;
    String currenturl = "";
    int mCurrentPosition = 0;
    private int index = 0;
    private List<SmallVideoBean.DataBean> dataList = new ArrayList<>();

    public SmallVideoItemAdapter(List<SmallVideoBean.DataBean> data, Context mContext, int position) {
        super(data);
        this.mContext = mContext;
        this.mCurrentPosition = position;
        index = mCurrentPosition;
        dataList.clear();
        this.dataList = data;
        addItemType(SmallVideoBean.DataBean.TYPE_SMALL_VIDEO_VERTICAL, R.layout.item_smallvideo_full_viewpager);
        addItemType(SmallVideoBean.DataBean.TYPE_SMALL_VIDEO_HORIZONTAL, R.layout.item_smallvideo_full_viewpager);
    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, int positions) {
        super.onBindViewHolder(holder, positions);
        switch (holder.getItemViewType()) {
            case SmallVideoBean.DataBean.TYPE_SMALL_VIDEO_VERTICAL:
                bindVideo(holder, dataList.get(index));
                break;
            case SmallVideoBean.DataBean.TYPE_SMALL_VIDEO_HORIZONTAL:
                bindVideo(holder, dataList.get(index));
                break;
            default:
                break;
        }
    }

    @Override
    protected void convert(BaseViewHolder holder, SmallVideoBean.DataBean dataBean) {

    }

    FullWindowVideoView mVideoViews;

    private void bindVideo(BaseViewHolder holder, SmallVideoBean.DataBean dataBean) {
        if (dataList != null) {
            ((ExpandImageView) holder.getView(R.id.img_thumb)).setImageURI(dataBean.getImage0());
            if (TextUtils.isEmpty(dataBean.getVideouri())) {
                currenturl = placeurl;
            } else {
                currenturl = dataBean.getVideouri();
            }
            ((TextView) holder.getView(R.id.videoText)).setText(dataBean.getText());
            ((TextView) holder.getView(R.id.videoName)).setText(dataBean.getName());
            ((TextView) holder.getView(R.id.videoId)).setText("咕唧ID: " + dataBean.getUser_id());
            ((TextView) holder.getView(R.id.videoUpTime)).setText("发布时间: " + dataBean.getCreated_at());
            ((ExpandImageView) holder.getView(R.id.iv_topLogo)).setImageURI(dataBean.getProfile_image());
            ((TextView) holder.getView(R.id.tvLove)).setText(dataBean.getLove());
            ((TextView) holder.getView(R.id.tvComment)).setText(dataBean.getComment());
            ((TextView) holder.getView(R.id.tvShare)).setText(dataBean.getBookmark());
            Uri uri = Uri.parse(currenturl);
            FullWindowVideoView mVerticalVideoView = (FullWindowVideoView) holder.getView(R.id.video_view);
            FullWindowVideoView mHorizontalVideoView = (FullWindowVideoView) holder.getView(R.id.video_view_hori);
            ImageView img_play = holder.getView(R.id.img_play);
            if (Integer.parseInt(dataBean.getHeight()) > Integer.parseInt(dataBean.getWidth())) {
                mVerticalVideoView.setVisibility(View.VISIBLE);
                mHorizontalVideoView.setVisibility(View.GONE);
                mVideoViews = mVerticalVideoView;
            } else if (Integer.parseInt(dataBean.getHeight()) < Integer.parseInt(dataBean.getWidth())) {
                mHorizontalVideoView.setVisibility(View.VISIBLE);
                mVerticalVideoView.setVisibility(View.GONE);
                mVideoViews = mHorizontalVideoView;
            } else {
                mHorizontalVideoView.setVisibility(View.GONE);
                mVerticalVideoView.setVisibility(View.VISIBLE);
                mVideoViews = mHorizontalVideoView;
            }
            mVideoViews.setVideoURI(uri);
            mVideoViews.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mVideoViews.stopPlayback(); //播放异常，则停止播放，防止弹窗使界面阻塞
                    ToastUitl.showShort("当前视频地址异常，下翻看看别的吧!");
                    return true;
                }
            });
            index++;
            if (index > (dataList.size() - 1)) {
                index = 0;
            }


//            img.setAnimation(rotate);
    }
}
}
