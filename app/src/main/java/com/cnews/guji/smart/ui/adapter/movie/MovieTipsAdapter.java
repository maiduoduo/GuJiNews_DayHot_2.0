package com.cnews.guji.smart.ui.adapter.movie;

import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MovieTipsBean;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 影片简介
 */
public class MovieTipsAdapter extends BaseQuickAdapter<MovieTipsBean.DataBean.TipsBean,BaseViewHolder> {
    public MovieTipsAdapter() {
        super(R.layout.item_movie_tips,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieTipsBean.DataBean.TipsBean item) {
        helper.setText(R.id.tv_tips,item.getContent());
        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_tips),item.getTipImg());
    }
}
