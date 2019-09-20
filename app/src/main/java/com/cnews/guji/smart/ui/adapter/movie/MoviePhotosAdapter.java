package com.cnews.guji.smart.ui.adapter.movie;

import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MoviePhotosBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgSizeUtil;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 影片海报
 */
public class MoviePhotosAdapter extends BaseMultiItemQuickAdapter<MoviePhotosBean, BaseViewHolder> {
    public MoviePhotosAdapter() {
        super(null);
        addItemType(ApiConstant.TYPE_MOVIE_DETAIL_VEDIO, R.layout.item_movie_vedio);
        addItemType(ApiConstant.TYPE_MOVIE_DETAIL_PHOTO, R.layout.item_movie_photo);
    }


    @Override
    protected void convert(BaseViewHolder helper, final MoviePhotosBean item) {
        switch (helper.getItemViewType()) {
            case ApiConstant.TYPE_MOVIE_DETAIL_VEDIO:
                if (helper.getAdapterPosition() == 0) {
                    helper.setText(R.id.tv_movie_video, "视频");
                }
                helper.setText(R.id.tv_video_num, String.format("%s",item.getVideoNum()));
                ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_video_img),item.getVideoImg());
                helper.getView(R.id.iv_movie_video_img)
                        .setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                MovieVideoActivity.start(mContext, item.getMovieId(), 0, item .getMovieTitle(), item.getUrl());
                            }
                        });
                break;
            case ApiConstant.TYPE_MOVIE_DETAIL_PHOTO:
                if (helper.getAdapterPosition() == 1) {
                    helper.setText(R.id.tv_photo, "图片");
                }else {
                    helper.setText(R.id.tv_photo, "");
                }
                String imgUrl = ImgSizeUtil.resetPicUrl(item.getUrl(), "@100w_100h_1e_1c");
                ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_photo),imgUrl);
                break;
        }
    }
}
