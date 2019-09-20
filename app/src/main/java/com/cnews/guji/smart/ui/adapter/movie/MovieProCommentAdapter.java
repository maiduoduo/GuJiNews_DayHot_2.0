package com.cnews.guji.smart.ui.adapter.movie;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MovieProCommentBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 电影短评
 */
public class MovieProCommentAdapter extends BaseQuickAdapter<MovieProCommentBean.DataBean,BaseViewHolder> {
    public MovieProCommentAdapter() {
        super(R.layout.item_movie_pro_comment, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieProCommentBean.DataBean item) {
        helper.setText(R.id.tv_author_name,item.getNickName())
                .setText(R.id.tv_author_title,item.getAuthInfo())
                .setText(R.id.tv_comment_content,item.getContent())
                .setText(R.id.tv_createDate, DateTimeUtils.dateYMD(item.getCreated()))
                .setText(R.id.tv_score,String.format("%s",(int)(item.getScore()*2)));
        String imgUrl = item.getAvatarurl();
        imgUrl.replace("avatar","180.180/avatar");
        ((ExpandImageView) helper.getView(R.id.civ_author)).setImageURI(imgUrl);
    }
}
