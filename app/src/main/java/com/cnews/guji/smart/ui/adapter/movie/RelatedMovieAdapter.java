package com.cnews.guji.smart.ui.adapter.movie;

import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.RelatedMovieBean;
import com.cnews.guji.smart.ui.activity.MovieDetailActivity;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgSizeUtil;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 相关电影
 */
public class RelatedMovieAdapter extends BaseQuickAdapter<RelatedMovieBean.DataBean.ItemsBean,BaseViewHolder> {
    public RelatedMovieAdapter() {
        super(R.layout.item_related_movie, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RelatedMovieBean.DataBean.ItemsBean item) {
        //255*234
        helper.setText(R.id.tv_movie_name,item.getTitle())
                .setText(R.id.tv_movie_score,String.format("%s",item.getSc()==0?"暂无评分":item.getSc()));
        String imgUrl = ImgSizeUtil.processUrl(item.getImg(),255,345);
        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_img),imgUrl);
        helper.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MovieDetailActivity.start(mContext, Integer.parseInt(item.getDesc()));
            }
        });
    }
}
