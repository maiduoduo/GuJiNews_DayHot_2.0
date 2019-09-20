package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ToastUitl;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.List;

/**
 * 横向新闻列表
 */
public class HomeMarqueeHorizontalListAdapter extends BaseQuickAdapter<HomeTopIFengBean.ItemBean.MarqueeListBean, BaseViewHolder> {
    private Context context;

    public HomeMarqueeHorizontalListAdapter(List<HomeTopIFengBean.ItemBean.MarqueeListBean> data, Context context) {
        super(data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HomeTopIFengBean.ItemBean.MarqueeListBean marqueeListBean) {
        int adapterPosition = helper.getAdapterPosition();
        if (marqueeListBean != null) {
            //doc-normal
            if (ApiConstant.MARQUEE_STATUS == 1 || ApiConstant.MARQUEE_STATUS == 0) {
                ((ExpandImageView) helper.getView(R.id.ivTop)).setImageURI(marqueeListBean.thumbnail == null ? "" : marqueeListBean.thumbnail);
                helper.setText(R.id.tvTitle, marqueeListBean.title == null ? "" : marqueeListBean.title);
                helper.setText(R.id.tvSource, marqueeListBean.source == null ? "" : marqueeListBean.source);
                helper.setText(R.id.tvCommentnum, marqueeListBean.commentsall == null ? "" : marqueeListBean.commentsall);
                helper.addOnClickListener(R.id.iv_close);
                helper.getView(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUitl.showShort("屏蔽"+marqueeListBean.title);
                    }
                });
            }else if (ApiConstant.MARQUEE_STATUS == 2) {
                ((ExpandImageView) helper.getView(R.id.eivHeaderIco)).setImageURI(marqueeListBean.thumbnail == null ? "" : marqueeListBean.thumbnail);
                helper.setText(R.id.tvTitle, marqueeListBean.title == null ? "" : marqueeListBean.title);
                helper.setText(R.id.tvContent, marqueeListBean.source == null ? "" : marqueeListBean.source);
                helper.addOnClickListener(R.id.ivGuanzhu);
                helper.getView(R.id.ivGuanzhu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUitl.showShort("关注了"+marqueeListBean.title);
                    }
                });
            }
        }
    }

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        List<HomeTopIFengBean.ItemBean.MarqueeListBean> data = getData();
        if (data.size() > 0) {
            if (NewsSource.NAME_TYPE_DOC.equals(data.get(0).type)
                    &&
                    NewsSource.NAME_VIEW_NORMAL.equals(data.get(0).style.view)) {
                ApiConstant.MARQUEE_STATUS = 1;
                return View.inflate(mContext, R.layout.ifeng_item_marquee_marquee_doc_normal, null);
            } else if (NewsSource.NAME_TYPE_WEBMEDIA.equals(data.get(0).type)
                    &&
                    NewsSource.NAME_VIEW_SUBSCRIBE.equals(data.get(0).style.view)) {
                ApiConstant.MARQUEE_STATUS = 2;
                return View.inflate(mContext, R.layout.ifeng_item_marquee_marquee_wemedia_subscribe, null);
            } else {
                ApiConstant.MARQUEE_STATUS = 0;
                return View.inflate(mContext, R.layout.ifeng_item_marquee_marquee_doc_normal, null);
            }
        } else {
            ApiConstant.MARQUEE_STATUS = 0;
            return View.inflate(mContext, R.layout.ifeng_item_marquee_marquee_doc_normal, null);
        }
//        View view = View.inflate(mContext, R.layout_comment_list_title.item_index_horizontal_list_item,null);
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int) (0.286 * DensityUtil.getScreenWidth(mContext)), LinearLayout.LayoutParams.WRAP_CONTENT);
//        view.setLayoutParams(params);
    }
}
