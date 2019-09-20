package com.cnews.guji.smart.ui.adapter;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.helper.horizontaldragmoreview.FEle.DZStickyNavLayouts;
import com.cnews.guji.smart.helper.horizontaldragmoreview.card.Align;
import com.cnews.guji.smart.helper.horizontaldragmoreview.card.Config;
import com.cnews.guji.smart.helper.horizontaldragmoreview.card.StackLayoutManager;
import com.cnews.guji.smart.helper.horizontaldragmoreview.pile_list.FadeTransitionImageView;
import com.cnews.guji.smart.helper.horizontaldragmoreview.pile_list.HorizontalTransitionLayout;
import com.cnews.guji.smart.helper.horizontaldragmoreview.pile_list.PileLayout;
import com.cnews.guji.smart.helper.horizontaldragmoreview.pile_list.VerticalTransitionLayout;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.github.library.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;


/**
 * 头条适配器
 *
 * @author dingcl
 * 数据绑定未进行详细的数据验证，再实际使用中不可取
 */
public class SquareMainRecycleAdapter extends BaseMultiItemQuickAdapter<SquareMainBean.ItemBean, BaseViewHolder> {
    private String TAG = "HomeListFragment";
    private Context context;
    private OnRemovePositionListener onRemovePositionListener;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public SquareMainRecycleAdapter(Context context, List<SquareMainBean.ItemBean> data) {
        super(data);
        this.context = context;
        //DOC-TITLEIMGBACK2
        addItemType(SquareMainBean.ItemBean.SQUARE_TYPE_DOC_TITLEIMGBACK2, R.layout.ifeng_square_item_doc_titleimgback2);
        //视频大图
        addItemType(SquareMainBean.ItemBean.SQUARE_TYPE_PHVIDEO_BIGIMG, R.layout.ifeng_square_item_phvideo_bigimg);
        //热点聚焦（横向滑动）
        addItemType(SquareMainBean.ItemBean.SQUARE_TYPE_HOTSPOT_HOTSPOT2, R.layout.ifeng_square_item_parent_hotspot_hotspot2);
        //原创精品（横向堆叠滑动）
        addItemType(SquareMainBean.ItemBean.SQUARE_TYPE_SOLECOLUMN_SOLECOLUMN, R.layout.ifeng_square_item_parent_solecolumn_solecolumn);

    }

    @Override
    protected void convert(BaseViewHolder holder, SquareMainBean.ItemBean itemBean) {
        int adapterPosition = holder.getAdapterPosition();
        switch (holder.getItemViewType()) {
            case SquareMainBean.ItemBean.SQUARE_TYPE_DOC_TITLEIMGBACK2:
                bindDocTitleBack2Data(holder, itemBean);
                break;
            case SquareMainBean.ItemBean.SQUARE_TYPE_HOTSPOT_HOTSPOT2:
                bindHotspotData(holder, itemBean);
                break;
            case SquareMainBean.ItemBean.SQUARE_TYPE_SOLECOLUMN_SOLECOLUMN:
                bindSolecolumnData(holder, itemBean);
                break;
            case SquareMainBean.ItemBean.SQUARE_TYPE_PHVIDEO_BIGIMG:
                bindPhvideoBigimgData(holder, itemBean);
                break;
            default:
                break;
        }
    }


    /**
     * 文本图
     *
     * @param holder
     * @param data
     */
    private void bindDocTitleBack2Data(BaseViewHolder holder, SquareMainBean.ItemBean data) {
        holder.setText(R.id.tvTitleText, data.intro == null ? "" : data.getType() + "__" + data.style.getView() + "-" + data.intro);
        holder.setText(R.id.titleType, data.getTitle() == null ? "" : data.getTitle());
        if (data.source != null) {
            holder.setText(R.id.tvsource, data.source);
        }
        holder.setText(R.id.tvUpdatetime, data.updateTime);
        ((ExpandImageView) holder.getView(R.id.docImg)).setImageURI(data.getThumbnail() == null ? "" : data.getThumbnail());
        ((ExpandImageView) holder.getView(R.id.docTitleIcon)).setImageURI(data.titleIcon == null ? "" : data.titleIcon);
        holder.addOnClickListener(R.id.rlDel);
    }

    /**
     * 热点聚焦
     *
     * @param holder
     * @param itemBean
     */
    private void bindHotspotData(BaseViewHolder holder, SquareMainBean.ItemBean itemBean) {
        DZStickyNavLayouts headLayout = holder.getView(R.id.head_home_layout);
        TextView titleType = holder.getView(R.id.titleType);
        titleType.setText(itemBean.getTitle() == null ? "" : itemBean.getTitle());
        ((ExpandImageView) holder.getView(R.id.docTitleIcon)).setImageURI(itemBean.titleIcon == null ? "" : itemBean.titleIcon);
        headLayout.setOnStartActivity(new DZStickyNavLayouts.OnStartActivityListener() {
            @Override
            public void onStart() {
                Intent intent = new Intent(context, GJSettingActivity.class);
                context.startActivity(intent);
            }
        });

        RecyclerView mHeadRecyclerView = holder.getView(R.id.head_home_recyclerview);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHeadRecyclerView.setLayoutManager(layoutManager2);
        SquareHotspotAdapter mSquareHotspotAdapter = new SquareHotspotAdapter(R.layout.ifeng_square_item_hotspot_hotspot2, itemBean.relation, context);
        mHeadRecyclerView.setAdapter(mSquareHotspotAdapter);

    }

    /**
     * 原创精品
     *
     * @param holder
     * @param itemBean
     */
    private void bindSolecolumnData(BaseViewHolder holder, SquareMainBean.ItemBean itemBean) {
        List<SquareMainBean.ItemBean.NewslistBean> newslist = itemBean.newslist;
        PowerfulRecyclerView recyclerview = holder.getView(R.id.recyclerview);
        holder.setText(R.id.titleType, itemBean.getTitle() == null ? " "  : itemBean.getTitle());
        ((ExpandImageView) holder.getView(R.id.docTitleIcon)).setImageURI(itemBean.titleIcon == null ? "" : itemBean.titleIcon);
        Config config = new Config();
        config.secondaryScale = 0.8f;
        config.scaleRatio = 0.4f;
        config.maxStackCount = 4;
        config.initialStackCount = 2;
        config.space = 15;
        config.align = Align.LEFT;
        StackLayoutManager layoutManager = new StackLayoutManager(config);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setAdapter(new SquareSoleColumnAdapter(R.layout.solecolum_item_card,newslist,context));
        layoutManager.scrollToPosition(10);
        recyclerview.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                ToastUitl.showShort("点击："+position+"-"+newslist.get(position).getTitle());
            }
        });
    }


    /**
     * WEB/TITLEIMG
     *
     * @param holder
     * @param data
     */
    private void bindWebTitleimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        List<ExpandImageView> images = new ArrayList<>();
        List<TextView> titles = new ArrayList<>();
        images.clear();
        titles.clear();
    }

    private void bindFinancehnData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        holder.setText(R.id.scrollTextView, data.title == null ? "" : data.title);
    }


    public String getString(int stringId) {
        return context.getResources().getString(stringId);
    }


    /**
     * 视频大图
     *
     * @param holder
     * @param data
     */
    private void bindPhvideoBigimgData(BaseViewHolder holder, SquareMainBean.ItemBean data) {
        holder.setText(R.id.tvtitle, data.getTitle() == null ? "" : data.getTitle());
        LinearLayout navigationTop = holder.getView(R.id.navigationTop);
        String navigationTitle = data.navigationTitle;
        if (navigationTitle != null && !TextUtils.isEmpty(navigationTitle)) {
            navigationTop.setVisibility(View.VISIBLE);
            holder.setText(R.id.titleType, data.navigationTitle);
            ((ExpandImageView) holder.getView(R.id.docTitleIcon)).setImageURI(data.navigationIcon == null ? "" : data.navigationIcon);
        } else {
            navigationTop.setVisibility(View.GONE);
        }
        if (data.phvideo != null) {
            holder.setText(R.id.tvsource, data.phvideo.getChannelName() == null ? "" : data.phvideo.getChannelName());
        }
        holder.setText(R.id.tvcommentNum, data.comments == null ? "" : data.comments + "评");
        ((ExpandImageView) holder.getView(R.id.docImg)).setImageURI(data.getThumbnail() == null ? "" : data.getThumbnail());
        holder.addOnClickListener(R.id.rlDel);
    }


    private boolean checkNull(HomeTopIFengBean.ItemBean data) {
        if (data == null) {
            return true;
        } else {
            if (!TextUtils.isEmpty(data.errorText)) {
                return true;
            } else {
                return false;
            }
        }
    }


    public interface OnRemovePositionListener {
        void removebadData(int position);
    }

    public void setOnRemovePositionListener(OnRemovePositionListener listener) {
        onRemovePositionListener = listener;
    }

    class ViewHolder {
        ImageView imageView;
    }

    /**
     * 属性动画
     */
    public void setTransitionValue(float transitionValue) {
        transitionValue = transitionValue;
//        countryView.duringAnimation(transitionValue);
//        temperatureView.duringAnimation(transitionValue);
//        addressView.duringAnimation(transitionValue);
//        bottomView.duringAnimation(transitionValue);
//        timeView.duringAnimation(transitionValue);
    }

//    public float getTransitionValue() {
//        return transitionValue;
//    }


}

