package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   精选视频-横向图集列表
 */
public class VideoCarechosenImageAlbumHorizontalistAdapter extends BaseQuickAdapter<CareChosenVideoBean.DataListBean.ActivityListBean,BaseViewHolder> {
    private Context mContext;
    public VideoCarechosenImageAlbumHorizontalistAdapter(int layoutResId, List<CareChosenVideoBean.DataListBean.ActivityListBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, CareChosenVideoBean.DataListBean.ActivityListBean data) {
        int pos = holder.getAdapterPosition();
        ExpandImageView videoHoriImg = holder.getView(R.id.videoHoriImg);
        videoHoriImg.setImageURI(data.getBackgroundImg() == null ? "" : data.getBackgroundImg());
        holder.setText(R.id.tvVideoTitle, data.getName() == null ? "" : data.getName());
    }
}
