package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.net.Uri;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.VideoShareBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   分享-好友
 */
public class VideoShareLinkHorizontalistAdapter extends BaseQuickAdapter<VideoShareBean.VideoShareLinkBean,BaseViewHolder> {
    private Context mContext;
    public VideoShareLinkHorizontalistAdapter(int layoutResId, List<VideoShareBean.VideoShareLinkBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, VideoShareBean.VideoShareLinkBean data) {
        int pos = holder.getAdapterPosition();
        ExpandImageView ivFriendAvtar = holder.getView(R.id.ivFriendAvtar);
        TextView ivFriendAvtarName = holder.getView(R.id.ivFriendAvtarName);
        ivFriendAvtar.setImageURI(Uri.parse(AppConstant.RES_SOURCE + data.icoUrl));
        ivFriendAvtarName.setText(data.sharename+"");
    }
}
