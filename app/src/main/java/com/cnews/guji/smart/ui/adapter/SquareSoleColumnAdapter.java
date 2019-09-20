package com.cnews.guji.smart.ui.adapter;


import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

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
  * Des  :   横划堆叠数据适配器
 */
public class SquareSoleColumnAdapter extends BaseQuickAdapter<SquareMainBean.ItemBean.NewslistBean,BaseViewHolder> {
    private Context mContext;
    private List<String> imageUrls = new ArrayList<>();
    private List<String> imageBottomUrls = new ArrayList<>();


    public SquareSoleColumnAdapter(int layoutResId, List<SquareMainBean.ItemBean.NewslistBean> data, Context context) {
        super(layoutResId, data);
        mContext = context;
        imageUrls.clear();
        imageBottomUrls.clear();
        for (SquareMainBean.ItemBean.NewslistBean b : data) {
            imageUrls.add(b.getWordThumbnail());
            imageBottomUrls.add(b.getThumbnail());
        }
    }

    @Override
    protected void convert(BaseViewHolder holder, SquareMainBean.ItemBean.NewslistBean bean) {
        int adapterPosition = holder.getAdapterPosition();
        RoundedImageView cover = holder.getView(R.id.cover);
        RoundedImageView bottomcover = holder.getView(R.id.bottomcover);
        Glide.with(mContext).load(imageUrls.get(adapterPosition)).into(cover);
        Glide.with(mContext).load(imageBottomUrls.get(adapterPosition)).into(bottomcover);
    }
}
