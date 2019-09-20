package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.text.TextUtils;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;

/**
 * CN:      SquareHotMoreAdpter
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/24
 * Des:    广场热点更多列表页数据适配
 */

public class SquareHotMoreAdpter extends BaseQuickAdapter<SquareHotMoreBean.ItemBean,BaseViewHolder> {
    private static final String TAG1 = "SquareMoreHotActivity";
    private Context mContext;
    public SquareHotMoreAdpter(int layoutResId, List<SquareHotMoreBean.ItemBean> data,Context context) {
        super(layoutResId, data);
        mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder holder, SquareHotMoreBean.ItemBean data) {
        int adapterPosition = holder.getAdapterPosition();
        ExpandImageView docImg = holder.getView(R.id.docImg);
//        docImg.setAlpha(200);
        docImg.setImageURI(data.getThumbnail() == null ? "" : data.getThumbnail());
        String updateTime = data.getUpdateTime();
        holder.setText(R.id.tvTime, TextUtils.isEmpty(DateTimeUtils.getShortTime(updateTime == null ? "" : updateTime))
                || DateTimeUtils.getShortTime(updateTime == null ? "" : updateTime).contains("1970") ? ""
                : DateTimeUtils.getShortTime(updateTime));
        holder.setText(R.id.tvComments, data.getPv() == null ? "" : data.getPv()+"阅");
        holder.setText(R.id.tvNavigationText, data.getTitle() == null ? "" : data.getTitle());
        holder.setText(R.id.tvTitleText, data.getRelation().get(0).getTitle() == null ? "" : "最新 · "+data.getRelation().get(0).getTitle());
    }
}

