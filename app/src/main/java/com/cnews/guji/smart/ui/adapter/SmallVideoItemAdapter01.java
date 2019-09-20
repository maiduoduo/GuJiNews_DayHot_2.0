package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.view.widget.FullWindowVideoView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   小视频条目
 */
public class SmallVideoItemAdapter01 extends BaseQuickAdapter<SmallVideoBean.DataBean,BaseViewHolder> {
    private String placeurl = "http://uvideo.spriteapp.cn/video/2019/0216/682e25fe31ce11e9bcf4842b2b4c75ab_wpd.mp4";
    private Context mContext;
    View view;
    String currenturl = "";
    public SmallVideoItemAdapter01(int layoutResId, List<SmallVideoBean.DataBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SmallVideoBean.DataBean data) {
        ((ExpandImageView)holder.getView(R.id.img_thumb)).setImageURI(data.getImage0());
        if (TextUtils.isEmpty(data.getVideouri())) {
            currenturl = placeurl;
        } else {
            currenturl = data.getVideouri();
        }
        ((TextView)holder.getView(R.id.videoText)).setText(data.getText());
        ((TextView)holder.getView(R.id.videoName)).setText(data.getName());
        ((TextView)holder.getView(R.id.videoId)).setText("咕唧ID: "+data.getUser_id());
        ((TextView)holder.getView(R.id.videoUpTime)).setText("发布时间: " +data.getCreated_at());
        ((ExpandImageView)holder.getView(R.id.iv_topLogo)).setImageURI(data.getProfile_image());
        ((TextView)holder.getView(R.id.tvLove)).setText(data.getLove());
        ((TextView)holder.getView(R.id.tvComment)).setText(data.getComment());
        ((TextView)holder.getView(R.id.tvShare)).setText(data.getBookmark());
        Uri uri = Uri.parse(currenturl);
        ((FullWindowVideoView)holder.getView(R.id.video_view)).setVideoURI(uri);

//        holder.videoText.setText(data.getText());
//        holder.videoName.setText(data.getName());
//        holder.videoId.setText("咕唧ID: " + data.getUser_id());
//        holder.videoUpTime.setText("发布时间: " + data.getCreated_at());
//        holder.iv_topLogo.setImageURI(data.getProfile_image());
//        holder.tvLove.setText(data.getLove());
//        holder.tvComment.setText(data.getComment());
//        holder.tvShare.setText(data.getBookmark());
//        Uri uri = Uri.parse(currenturl);
//        holder.videoView.setVideoURI(uri);
//        index++;
//        if (index > (mList.size() - 1)) {
//            index = 0;
//        }












//        int adapterPosition = holder.getAdapterPosition();
//        ExpandImageView docImg = holder.getView(R.id.docImg);
//        docImg.setAlpha(210);
//        docImg.setImageURI(data.thumbnail == null ? "" : data.thumbnail);
//        holder.setText(R.id.tvTime, data.updateTime == null ? "" : data.updateTime);
//        holder.setText(R.id.tvComments, data.comments == null ? "" : data.comments.length()+" 阅");
//        holder.setText(R.id.tvNavigationText, data.source == null ? "" : data.source);
//        holder.setText(R.id.tvTitleText, data.title == null ? "" : "最新 · "+data.title);
    }
}
