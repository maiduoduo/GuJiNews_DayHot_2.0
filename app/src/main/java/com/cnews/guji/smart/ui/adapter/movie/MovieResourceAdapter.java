package com.cnews.guji.smart.ui.adapter.movie;

import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MovieResourceBean;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 影片资料
 */
public class MovieResourceAdapter extends BaseQuickAdapter<MovieResourceBean.DataBean, BaseViewHolder> {
    private IMovieResourceClickListener movieResourceClickListener;
    public MovieResourceAdapter() {
        super(R.layout.item_movie_resource, null);
    }
    @Override
    protected void convert(BaseViewHolder helper, final MovieResourceBean.DataBean item) {
        helper.setText(R.id.tv_movie_resource_title, item.getTitle())
                .setText(R.id.tv_movie_resource_content, item.getContent());

        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_resource),item.getImg());
        helper.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (movieResourceClickListener != null) {
                    if (item.getUrl() == null) {
                        movieResourceClickListener.onClick(item.getName());
                    } else {
                        String url = item.getUrl();
                        String id = url.substring(url.indexOf("id=") + 3, url.indexOf("&"));
                        String realUrl = "http://m.maoyan.com/information/"+id+"?_v_=yes";
//                        BaseWebViewActivity.start(mContext, realUrl);
                    }
                }
            }
        });
    }

    public void setMovieResourceClickListener(IMovieResourceClickListener movieResourceClickListener) {
        this.movieResourceClickListener = movieResourceClickListener;
    }

    public interface IMovieResourceClickListener {
        void onClick(String type);
    }
}
