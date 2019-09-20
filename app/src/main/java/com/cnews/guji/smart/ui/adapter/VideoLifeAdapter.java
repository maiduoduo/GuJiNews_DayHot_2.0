package com.cnews.guji.smart.ui.adapter;


import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   生活视频分类
 */
public class VideoLifeAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    private Context mContext;

    public VideoLifeAdapter(int layoutResId, List<String> data, Context mContext) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    @Override
    protected void convert(BaseViewHolder holder, String s) {
        int position = holder.getAdapterPosition();
        holder.setText(android.R.id.text1, mContext.getString(R.string.item_example_number_title, position));
        holder.setText(android.R.id.text2, mContext.getString(R.string.item_example_number_abstract, position));
        holder.setTextColor(android.R.id.text2, R.color.Gray3);
    }
}
