package com.cnews.guji.smart.ui.adapter;


import android.content.Context;

import com.bumptech.glide.Glide;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


/**
  * Author： JSYL_Dingcl
  * Des  :   导航menu数据适配器
 */
public class SquareNavigationMenuAdapter extends BaseQuickAdapter<SquareMainBean.ItemBean,BaseViewHolder> {
    private Context mContext;


    public SquareNavigationMenuAdapter(int layoutResId, List<SquareMainBean.ItemBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SquareMainBean.ItemBean bean) {
        int adapterPosition = holder.getAdapterPosition();
        ((ExpandImageView)holder.getView(R.id.docImg)).setImageURI(bean.getThumbnail() == null ? "" : bean.getThumbnail());
        holder.setText(R.id.menuText,bean.getTitle() == null ? " " : bean.getTitle());
    }
}
