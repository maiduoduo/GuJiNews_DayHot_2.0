package com.cnews.guji.smart.ui.adapter.movie;

import android.graphics.drawable.Drawable;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MovieLongCommentBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 长影评
 */
public class MovieLongCommentAdapter extends BaseQuickAdapter<MovieLongCommentBean.DataBean.FilmReviewsBean,BaseViewHolder> {
    public MovieLongCommentAdapter() {
        super(R.layout.item_long_comment,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieLongCommentBean.DataBean.FilmReviewsBean item) {
        helper.setText(R.id.tv_author_name,item.getAuthor().getNickName())
                .setText(R.id.tv_comment_title,item.getTitle())
                .setText(R.id.tv_comment_content,item.getText())
                .setText(R.id.tv_view_count,String.format("%s",item.getViewCount()))
                .setText(R.id.tv_comment_count,String.format("%s",item.getCommentCount()))
                .setText(R.id.tv_pub_time, DateTimeUtils.dateMD(item.getCreated()));

        Drawable icon = null;
        switch (item.getAuthor().getUserLevel()){
            case 1:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv1);
                break;
            case 2:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv2);
                break;
            case 3:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv3);
                break;
            case 4:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv4);
                break;
            case 5:
                icon = mContext.getResources().getDrawable(R.drawable.ic_lv5);
                break;
        }
        helper.setImageDrawable(R.id.iv_user_level,icon);
        ((ExpandImageView) helper.getView(R.id.civ_author)).setImageURI(item.getAuthor().getAvatarurl());
    }
}
