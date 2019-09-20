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
  * Des  :   热议数据适配器
 */
public class SquareHotwordAdapter extends BaseQuickAdapter<SquareMainBean.ItemBean,BaseViewHolder> {
    private Context mContext;


    public SquareHotwordAdapter(int layoutResId, List<SquareMainBean.ItemBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SquareMainBean.ItemBean bean) {
        int adapterPosition = holder.getAdapterPosition();
        ((ExpandImageView)holder.getView(R.id.hotImg)).setImageURI(bean.titleIcon == null ? "" : bean.titleIcon);
        holder.setText(R.id.menuText,bean.getTitle() == null ? " " : bean.getTitle());
    }
}
