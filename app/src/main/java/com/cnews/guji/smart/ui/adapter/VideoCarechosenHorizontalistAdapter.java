package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   精选视频-横向视频列表
 */
public class VideoCarechosenHorizontalistAdapter extends BaseQuickAdapter<CareChosenVideoBean.DataListBean.ContListBeanX,BaseViewHolder> {
    private Context mContext;
    public VideoCarechosenHorizontalistAdapter(int layoutResId, List<CareChosenVideoBean.DataListBean.ContListBeanX> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, CareChosenVideoBean.DataListBean.ContListBeanX data) {
        int pos = holder.getAdapterPosition();
        ExpandImageView videoHoriImg = holder.getView(R.id.videoHoriImg);
        videoHoriImg.setImageURI(data.getPic() == null ? "" : data.getPic());
        holder.setText(R.id.tvVideoTitle, data.getName() == null ? "" : data.getName());
        holder.setText(R.id.tvLiNickname, data.getUserInfo().getNickname() == null ? "" : data.getUserInfo().getNickname());
        holder.setText(R.id.tvLiVideoTime, data.getDuration() == null ? "" : data.getDuration());

        if (data.getCornerLabel() == null || "null".equals(data.getCornerLabel()) || TextUtils.isEmpty(data.getCornerLabel())){
            holder.getView(R.id.tvDujia).setVisibility(View.GONE);
        }else {
            holder.getView(R.id.tvDujia).setVisibility(View.VISIBLE);
            holder.setText(R.id.tvDujia, data.getCornerLabelDesc() == null ? "dubo" : data.getCornerLabelDesc());
        }
    }
}
