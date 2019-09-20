package com.cnews.guji.smart.ui.adapter.movie;

import com.cnews.guji.smart.R;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

/**
 * 获奖情况
 */
public class MovieAwardsAdapter extends BaseQuickAdapter<String,BaseViewHolder> {
    public MovieAwardsAdapter() {
        super(R.layout.item_movie_awards,null);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_award_name,item);
    }
}
