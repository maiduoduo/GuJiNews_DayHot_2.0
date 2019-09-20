package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.refresh.cyg.utils.CygView;
import com.cnews.guji.smart.ui.holder.SmallVideoViewHolder;

/**
 * 小视频数据装载
 */
public class SmallVideoAdapter_TEMP extends CygBaseRecyclerAdapter<SmallVideoBean.DataBean, SmallVideoViewHolder> {

    public SmallVideoAdapter_TEMP(Context context, OnItemClickListener<SmallVideoViewHolder> listener) {
        super(context, listener);
    }

    @Override
    public SmallVideoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SmallVideoViewHolder(CygView.inflateLayout(getContext(), R.layout.smallvideo_item_nearby, parent, false));
    }
}
