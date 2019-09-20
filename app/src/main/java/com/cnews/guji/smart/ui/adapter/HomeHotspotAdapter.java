package com.cnews.guji.smart.ui.adapter;


import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   热点聚焦数据适配器
 */
public class HomeHotspotAdapter extends BaseQuickAdapter<HomeTopIFengBean.ItemBean.RelationBean,BaseViewHolder> {
    public HomeHotspotAdapter(int layoutResId, List<HomeTopIFengBean.ItemBean.RelationBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeTopIFengBean.ItemBean.RelationBean data) {
        int adapterPosition = holder.getAdapterPosition();
        ExpandImageView docImg = holder.getView(R.id.docImg);
        docImg.setAlpha(210);
        docImg.setImageURI(data.thumbnail == null ? "" : data.thumbnail);
        holder.setText(R.id.tvTime, data.updateTime == null ? "" : data.updateTime);
        holder.setText(R.id.tvComments, data.comments == null ? "" : data.comments.length()+" 阅");
        holder.setText(R.id.tvNavigationText, data.source == null ? "" : data.source);
        holder.setText(R.id.tvTitleText, data.title == null ? "" : "最新 · "+data.title);
    }
}
