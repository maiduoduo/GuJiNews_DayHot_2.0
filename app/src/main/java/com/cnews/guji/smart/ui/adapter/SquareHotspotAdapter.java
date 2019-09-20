package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   热点聚焦数据适配器
 */
public class SquareHotspotAdapter extends BaseQuickAdapter<SquareMainBean.ItemBean.RelationBeanX,BaseViewHolder> {
    private Context mContext;
    public SquareHotspotAdapter(int layoutResId, List<SquareMainBean.ItemBean.RelationBeanX> data,Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SquareMainBean.ItemBean.RelationBeanX data) {
        int adapterPosition = holder.getAdapterPosition();
        ExpandImageView docImg = holder.getView(R.id.docImg);
        docImg.setAlpha(210);
        docImg.setImageURI(data.getThumbnail() == null ? "" : data.getThumbnail());
        holder.setText(R.id.tvTime, data.updateTime == null ? "" : data.updateTime);
        holder.setText(R.id.tvComments, data.pv == null ? "" : data.pv+"阅");
        holder.setText(R.id.tvNavigationText, data.title == null ? "" : data.title);
        holder.setText(R.id.tvTitleText, data.relation.get(0).getTitle() == null ? "" : "最新 · "+data.relation.get(0).getTitle());
    }
}
