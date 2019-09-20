package com.cnews.guji.smart.ui.adapter.movie;

import android.view.View;
import android.widget.ImageView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.movie.MovieStarBean;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgSizeUtil;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 影星列表
 */
public class MovieStarListAdapter extends BaseQuickAdapter<MovieStarBean.DataBean, BaseViewHolder> {
    public MovieStarListAdapter() {
        super(R.layout.item_movie_star, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MovieStarBean.DataBean item) {
        if (helper.getAdapterPosition() == 0) {
            helper.setText(R.id.tv_role, "导演");
        } else if (helper.getAdapterPosition() == 1) {
            helper.setText(R.id.tv_role, "演员");
        }else {
            helper.setText(R.id.tv_role, "");
        }
        helper.setText(R.id.tv_fake_name, item.getRoles());
        helper.setText(R.id.tv_real_name, item.getCnm());

        String imgUrl = ImgSizeUtil.resetPicUrl(item.getAvatar(), ".webp@210w_285h_1e_1c_1l");
        ImageLoaderUtils.display(mContext,(ImageView) helper.getView(R.id.iv_movie_star),imgUrl);
        helper.convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MovieStarActivity.start(mContext,item.getId());
            }
        });
    }
}
