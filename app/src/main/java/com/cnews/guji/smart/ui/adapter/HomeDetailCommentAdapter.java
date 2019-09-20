package com.cnews.guji.smart.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.horizontaldragmoreview.FEle.DZStickyNavLayouts;
import com.cnews.guji.smart.helper.horizontaldragmoreview.HorizontalScrollSlideView.PulToLeftViewGroup;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.activity.HotNewsBestActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.model.Menu9Model;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgTexMixedUtil;
import com.cnews.guji.smart.util.ToastUitl;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;
import com.youth.banner.transformer.AccordionTransformer;
import com.youth.banner.transformer.BackgroundToForegroundTransformer;
import com.youth.banner.transformer.CubeInTransformer;
import com.youth.banner.transformer.CubeOutTransformer;
import com.youth.banner.transformer.DefaultTransformer;
import com.youth.banner.transformer.DepthPageTransformer;
import com.youth.banner.transformer.FlipHorizontalTransformer;
import com.youth.banner.transformer.FlipVerticalTransformer;
import com.youth.banner.transformer.ForegroundToBackgroundTransformer;
import com.youth.banner.transformer.RotateDownTransformer;
import com.youth.banner.transformer.RotateUpTransformer;
import com.youth.banner.transformer.ScaleInOutTransformer;
import com.youth.banner.transformer.StackTransformer;
import com.youth.banner.transformer.TabletTransformer;
import com.youth.banner.transformer.ZoomInTransformer;
import com.youth.banner.transformer.ZoomOutSlideTransformer;
import com.youth.banner.transformer.ZoomOutTranformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import cn.jzvd.JZDataSource;
import cn.jzvd.JzvdStd;


/**
  * Author： JSYL_Dingcl
  * Des  :   新闻详情评论及更多推荐列表
 */
public class HomeDetailCommentAdapter extends BaseMultiItemQuickAdapter<HomeTopIFengBean.ItemBean, BaseViewHolder> {

    private Context mContext;
    private String TAG = "HomeDetailCommentAdapter";
    private Context context;
    private List<View> mPagerList;
    private List<Menu9Model> mDatas;
    private LayoutInflater inflater;
    private List<HomeTopIFengBean> totalData;
    private HomeHottopMultipleRecycleAdapter.OnRemovePositionListener onRemovePositionListener;
    /**
     * 总的页数
     */
    private int pageCount;
    /**
     * 每一页显示的个数
     */
    private int pageSize = 10;
    /**
     * 当前显示的是第几页
     */
    private int curIndex = 0;

    private List<Integer> bannerData;
    private String mChannelCode;

    public HomeDetailCommentAdapter(List<HomeTopIFengBean.ItemBean> data,Context context) {
        super(data);
        this.mContext = context;
        addItemType(HomeTopIFengBean.ItemBean.TYPE_DOC_TITLEIMG, R.layout.ifeng_doc_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_DOC_SINGLETITLE, R.layout.ifeng_item_doc_singletitle);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_DOC_SLIDEIMG, R.layout.item_item_doc_slideimg);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_TITLEIMAGE, R.layout.ifeng_phvideo_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_BIGIMAGE, R.layout.ifeng_item_phvideo_bigimg);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_ADVERT_SLIDEIMG, R.layout.ifeng_item_advert_slideimg);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_ADVERT_BIGIMAGE, R.layout.ifeng_item_advert_bigimg);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_ADVERT_TITLEIMAGE, R.layout.ifeng_advert_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_TOPIC2_SINGLETITLE, R.layout.ifeng_item_doc_singletitle);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_SLIDE_NORMAL, R.layout.ifeng_item_slide_normal);
        //图集
        addItemType(HomeTopIFengBean.ItemBean.TYPE_SLIDE_SLIDEIMG, R.layout.ifeng_item_slide_slideimg);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_MARQUEE_MARQUEE, R.layout.ifeng_item_marquee_marquee);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_WEB_TITLEIMG, R.layout.ifeng_doc_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_FINANCEHN_SINGLETITLE, R.layout.ifeng_item_financehn_singletitle);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_GREGNEWSLIST_TITLEIMG, R.layout.ifeng_doc_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_TEXTLIVE_TITLEIMG, R.layout.ifeng_doc_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_SURVEY_TITLEIMG, R.layout.ifeng_doc_title_image);
        addItemType(HomeTopIFengBean.ItemBean.TYPE_SLIDE_TITLEIMG, R.layout.ifeng_doc_title_image);
        //财经直播室
        addItemType(HomeTopIFengBean.ItemBean.TYPE_FASTMESSAGESCROLL_FASTMESSAGESCROLL, R.layout.item_index_bullentin);
        //热点横向列表
        addItemType(HomeTopIFengBean.ItemBean.TYPE_HOTSPOT_HOTSPOT, R.layout.ifeng_square_item_parent_hotspot_hotspot2);
    }

    @Override
    protected void convert(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        int adapterPosition = holder.getAdapterPosition();
        switch (holder.getItemViewType()) {
            case HomeTopIFengBean.ItemBean.TYPE_DOC_TITLEIMG:
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_TITLEIMG:
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_DOC_SINGLETITLE:
                bindSingleTitleData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_DOC_SLIDEIMG:
                bindSlideImgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_TITLEIMAGE:
                bindPhvideoTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_PHVIDEO_BIGIMAGE:
                bindPhvideoBigimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_SLIDEIMG:
                bindAdvertSideimgData(holder, data);

                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_BIGIMAGE:
                bindAdvertBigimgData(holder, data);

                break;
            case HomeTopIFengBean.ItemBean.TYPE_ADVERT_TITLEIMAGE:
                bindAdvertTitleimgData(holder, data);

                break;
            case HomeTopIFengBean.ItemBean.TYPE_TOPIC2_SINGLETITLE:
                bindSingleTitleData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_NORMAL:
                bindSlideNormalData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SLIDE_SLIDEIMG:
                bindSlideSlideimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_MARQUEE_MARQUEE:
                bindMarqueemarqueeData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_WEB_TITLEIMG:
//                bindWebTitleimgData(holder, data);
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_GREGNEWSLIST_TITLEIMG:
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_TEXTLIVE_TITLEIMG:
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_SURVEY_TITLEIMG:
                bindDocTitleimgData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_FASTMESSAGESCROLL_FASTMESSAGESCROLL:
                bindUpdownSwitcherData(holder, data);
                break;
            case HomeTopIFengBean.ItemBean.TYPE_HOTSPOT_HOTSPOT:
                bindHotSpotData(holder, data);
                break;
            default:
                break;
        }
    }



    /**
     * 热点聚焦
     *
     * @param holder
     * @param data
     */
    private void bindHotSpotData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        DZStickyNavLayouts headLayout = holder.getView(R.id.head_home_layout);
        TextView titleType = holder.getView(R.id.titleType);
        titleType.setText(data.title == null ? "" : data.title);
        ((ExpandImageView) holder.getView(R.id.docTitleIcon)).setImageURI(data.titleIcon == null ? "" : data.titleIcon);
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
        HomeHotspotAdapter mSquareHotspotAdapter = new HomeHotspotAdapter(R.layout.ifeng_square_item_hotspot_hotspot2, data.relation, context);
        mHeadRecyclerView.setAdapter(mSquareHotspotAdapter);

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


    /**
     * DOC/标题+1图
     *
     * @param holder
     * @param data
     */
    private void bindDocTitleimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        int adapterPosition = holder.getAdapterPosition();
        LinearLayout view = holder.getView(R.id.llDocTitleImg);
        TextView tvDocTitle = holder.getView(R.id.tvDocTitle);
        ExpandImageView ivDocImage = holder.getView(R.id.ivDocImage);
        if (checkNull(data)) {
            tvDocTitle.setText("数据异常");
            if (onRemovePositionListener != null) {
                onRemovePositionListener.removebadData(adapterPosition);
            }
            ivDocImage.setVisibility(View.GONE);
        } else {
            if (data.style.recomreason != null && getString(R.string.fl_hot).equals(data.style.recomreason)) {
//                tvDocTitle.setText(ImgTexMixedUtil.transferPic(data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title, R.drawable.flag_hot, 70, 40));
                tvDocTitle.setText(ImgTexMixedUtil.transferPic(data.title == null ? "" : data.title+"", R.drawable.flag_hot, 70, 40));
            } else {
//                tvDocTitle.setText(data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
                tvDocTitle.setText(data.title == null ? "" : data.title+"");
            }
            holder.setText(R.id.tvSource, data.source == null ? "" : data.source);
            holder.setText(R.id.tvCommentnum,
                    String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
            ((ExpandImageView) holder.getView(R.id.ivDocImage)).setImageURI(data.thumbnail == null ? "" : data.thumbnail);
            holder.addOnClickListener(R.id.rlDel);
        }
    }

    public String getString(int stringId) {
        return context.getResources().getString(stringId);
    }

    /**
     * 置顶/WEB类型数据绑定
     *
     * @param holder
     * @param data
     */
    private void bindSingleTitleData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        LinearLayout view = holder.getView(R.id.llDocTitleImg);
        ILog.e("homehot", "------------bindSingleTitleData-----------" + checkNull(data));
        ILog.e("homehot", "------------bindSingleTitleData-----------" + data.title);
        ILog.e("homehot", "------------bindSingleTitleData-----------" + data.style.view);
        ILog.e("homehot", "------------bindSingleTitleData-----------" + data.errorText);
        if (checkNull(data)) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
//            holder.setText(R.id.tvDocTitle, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
            holder.setText(R.id.tvDocTitle, data.title == null ? "" : data.title+"");
            holder.setText(R.id.tvSource, data.source == null ? "" : data.source);
            holder.setText(R.id.tvCommentnum,
                    String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
            ExpandImageView ivDocImage = holder.getView(R.id.ivDocImage);
            if (data.thumbnail != null && !TextUtils.isEmpty(data.thumbnail)) {
                ivDocImage.setVisibility(View.VISIBLE);
                ivDocImage.setImageURI(data.thumbnail == null ? "" : data.thumbnail);
            } else {
                ivDocImage.setVisibility(View.GONE);
            }
        }
    }

    /**
     * DOC/三图
     *
     * @param holder
     * @param data
     */
    private void bindSlideImgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tv_title, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tv_title, data.title == null ? "" : data.title+"");
        holder.setText(R.id.tv_source, data.source == null ? "" : data.source);
        holder.setText(R.id.tv_commnetsize,
                String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
//        holder.setText(R.id.tv_time, "xx天前");
        try {
//            ImageLoaderUtils.display(mContext, (ImageView) holder.getView(R.id.iv_1), data.style.images.get(0));
            ((ExpandImageView) holder.getView(R.id.iv_1)).setImageURI(data.style.images.get(0) == null ? ""
                    : data.style.images.get(0));
            ((ExpandImageView) holder.getView(R.id.iv_2)).setImageURI(data.style.images.get(1) == null ? ""
                    : data.style.images.get(1));
            ((ExpandImageView) holder.getView(R.id.iv_3)).setImageURI(data.style.images.get(2) == null ? ""
                    : data.style.images.get(2));
        } catch (Exception e) {
            e.printStackTrace();
        }
        holder.addOnClickListener(R.id.rlDel);
    }

    /**
     * 视频小图
     *
     * @param holder
     * @param data
     */
    private void bindPhvideoTitleimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tvTitle, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tvTitle, data.title == null ? "" : data.title+"");
        if (data.phvideo != null && data.phvideo.length > 0) {
            holder.setText(R.id.tvTotalText, DateTimeUtils.formatVideoTime(data.phvideo == null ? 0 : data.phvideo.length) + "");
        }
        if (data.source == null) {
            if (data.phvideo != null) {
                holder.setText(R.id.tvSource, data.phvideo.channelName);
            } else {
                holder.setText(R.id.tvSource, "");
            }
        }
        holder.setText(R.id.tvCommentnum,
                String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
        ((ExpandImageView) holder.getView(R.id.ivImage)).setImageURI(data.thumbnail == null ? "" : data.thumbnail);
        holder.addOnClickListener(R.id.rlDel);
    }

    /**
     * 视频大图
     *
     * @param holder
     * @param data
     */
    private void bindPhvideoBigimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tvVideoTitle, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tvVideoTitle, data.title == null ? "" : data.title+"");
        if (data.phvideo != null && data.phvideo.length > 0) {
            holder.setText(R.id.tvTotalText, DateTimeUtils.formatVideoTime(data.phvideo == null ? 0 : data.phvideo.length) + "");
        }
        if (data.source == null) {
            if (data.phvideo != null) {
                holder.setText(R.id.tvsource, data.phvideo.channelName);
            } else {
                holder.setText(R.id.tvsource, "");
            }
        }
        holder.setText(R.id.tvCommentnum,
                String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
        ((ExpandImageView) holder.getView(R.id.img_video)).setImageURI(data.thumbnail == null ? "" : data.thumbnail);
        holder.addOnClickListener(R.id.rlDel);
    }

    /**
     * 广告/三图
     *
     * @param holder
     * @param data
     */
    private void bindAdvertSideimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tv_title, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tv_title, data.title == null ? "" : data.title+"");
        holder.setText(R.id.tv_source, data.source == null ? "" : data.source);
        try {
            ((ExpandImageView) holder.getView(R.id.iv_1)).setImageURI(data.style.images.get(0) == null ? ""
                    : data.style.images.get(0));
            ((ExpandImageView) holder.getView(R.id.iv_2)).setImageURI(data.style.images.get(1) == null ? ""
                    : data.style.images.get(1));
            ((ExpandImageView) holder.getView(R.id.iv_3)).setImageURI(data.style.images.get(2) == null ? ""
                    : data.style.images.get(2));
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.addOnClickListener(R.id.rlDel);
    }


    /**
     * 广告/大图
     *
     * @param holder
     * @param data
     */
    private void bindAdvertBigimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tv_title, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tv_title, data.title == null ? "" : data.title+"");
        holder.setText(R.id.tv_source, data.source == null ? "" : data.source);
        ((ExpandImageView) holder.getView(R.id.ivImage)).setImageURI(data.thumbnail == null ? "" : data.thumbnail);
        holder.addOnClickListener(R.id.rlDel);
    }

    /**
     * 广告/单图
     *
     * @param holder
     * @param data
     */
    private void bindAdvertTitleimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        LinearLayout view = holder.getView(R.id.llDocTitleImg);
        if (checkNull(data)) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
//            holder.setText(R.id.tvTitle, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
            holder.setText(R.id.tvTitle, data.title == null ? "" : data.title+"");
            holder.setText(R.id.tvSource, data.source == null ? "" : data.source);
            ((ExpandImageView) holder.getView(R.id.ivImage)).setImageURI(data.thumbnail == null ? "" : data.thumbnail);
            holder.addOnClickListener(R.id.rlDel);
        }
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

    /**
     * slide/多图模式/横向列表
     *
     * @param holder
     * @param data
     */
    private void bindSlideNormalData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
//        holder.setText(R.id.tvTitle, data.title == null ? "" : data.type + "__" + data.style.view + "\n" + data.title);
        holder.setText(R.id.tvTitle, data.title == null ? "" : data.title+"");
        holder.setText(R.id.tv_source, data.source);
        holder.setText(R.id.tv_commnetsize,
                String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
        ImageLoaderUtils.display(mContext, (ImageView) holder.getView(R.id.iv_logo), data.thumbnail);
        holder.addOnClickListener(R.id.rlDel);
    }


    /**
     * slide/三图模式/图集
     *
     * @param holder
     * @param data
     */
    private void bindSlideSlideimgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        try {
//            holder.setText(R.id.tv_title, data.title == null ? "" : data.type + "__" + data.style.view + "  " + data.style.slideCount + "图\n" + data.title);
            holder.setText(R.id.tv_title, data.title == null ? "" : data.title+"");
            try {
                holder.setText(R.id.tvTime, data.updateTime == null ? "" : DateTimeUtils.parseTimeFormat(DateTimeUtils.FORMATT_YD, data.updateTime));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            holder.setText(R.id.tvCommentnum, String.format(context.getString(R.string.news_commentsize), data.comments == null ? "" : data.comments));
            TextView tvimgSize = holder.getView(R.id.tvimgSize);
            if (data.style.slideCount > 0) {
                tvimgSize.setVisibility(View.VISIBLE);
//            holder.setText(R.id.tvimgSize, String.format(mContext.getResources().getString(R.string.slideimg_size), data.style.slidecount==null ? 0 : data.commentsall));
                holder.setText(R.id.tvimgSize, data.style.slideCount + "图");
            } else {
                tvimgSize.setVisibility(View.GONE);
            }

            try {
                ((ExpandImageView) holder.getView(R.id.iv_1)).setImageURI(data.style.images == null ? "" : data.style.images.get(0));
                ((ExpandImageView) holder.getView(R.id.iv_2)).setImageURI(data.style.images == null ? "" : data.style.images.get(1));
                ((ExpandImageView) holder.getView(R.id.iv_3)).setImageURI(data.style.images == null ? "" : data.style.images.get(2));
            } catch (Exception e) {
                e.printStackTrace();
            }

            holder.addOnClickListener(R.id.rlDel);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Marquee/横向列表
     *
     * @param holder
     * @param data
     */
    private void bindMarqueemarqueeData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        if (data != null) {
            holder.setText(R.id.tvTitles, data.title == null ? " " : data.title);
            RecyclerView recyclerView = holder.getView(R.id.recyclerView);
            //Recyclerview
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            recyclerView.setFocusable(false);
            recyclerView.setFocusableInTouchMode(false);
            recyclerView.setLayoutManager(linearLayoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setItemViewCacheSize(10);
            HomeMarqueeHorizontalListAdapter adapter = new HomeMarqueeHorizontalListAdapter(data.marqueeList, context);
            adapter.setEnableLoadMore(true);
            recyclerView.setAdapter(adapter);
        }

    }


    /**
     * 多图
     *
     * @param holder
     * @param data
     */
    private void bindMoreImgData(BaseViewHolder holder, HomeTopIFengBean.ItemBean data) {
        LinearLayout llMoreImg = holder.getView(R.id.llMoreImg);
        if (data.style.images != null) {
            for (int i = 0; i < data.style.images.size(); i++) {
                ExpandImageView imageView = new ExpandImageView(mContext);
                imageView.setImageURI(data.style.images.get(i) == null ? "" : data.style.images.get(i));
                LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                param.leftMargin = 10;
                param.gravity = Gravity.CENTER;  //必须要加上这句，setMargins才会起作用，而且此句还必须在setMargins下面
                imageView.setLayoutParams(param);
                llMoreImg.addView(imageView);
            }
        }
        holder.setText(R.id.tv_title, data.title == null ? "" : data.title);
        holder.setText(R.id.tv_source, data.source == null ? "" : data.source);
        holder.setText(R.id.tv_commnetsize,
                String.format(mContext.getResources().getString(R.string.news_commentsize), data.commentsall));
        holder.addOnClickListener(R.id.rlDel);
    }


    /**
     * 视频-支持直接播放
     *
     * @param helper
     * @param articles
     * @param adapterPosition
     */
    private void bindVideoPlayerNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles articles, int adapterPosition) {
        int position = helper.getAdapterPosition();
        ILog.e(TAG, "构造方法：============videoplayer 11==================");
        JzvdStd mVideoPlayer = helper.getView(R.id.videoplayers);
        if (mVideoPlayer != null) {
            LinearLayout llVideoPlayer = helper.getView(R.id.llVideoPlayer);
            // 将列表中的每个视频设置为默认16:9的比例
            ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
            // 宽度为屏幕宽度
            params.width = llVideoPlayer.getResources().getDisplayMetrics().widthPixels;
            // 高度为宽度的9/16
            params.height = (int) (params.width * 9f / 16f);
            mVideoPlayer.setLayoutParams(params);
            bindData(mVideoPlayer, articles);
            helper.setText(R.id.videoChannel, articles.mediaName == null ? "" : articles.mediaName);
            helper.setText(R.id.tvVideoCommentNum, articles.commentNum + "");
        }
    }

    /**
     * 视频封面大图
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindVideoImageData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        helper.setText(R.id.tvVideoTitle, item.title == null ? "" : item.title);
        helper.setText(R.id.tvSourceName_best, item.mediaName == null ? "" : item.mediaName);
        ((ExpandImageView) helper.getView(R.id.img_video)).setImageURI(item.getPics().get(0) == null ? "" : item.getPics().get(0).url);
        helper.addOnClickListener(R.id.flVideo);
        helper.setOnClickListener(R.id.flVideo, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort(item.title == null ? "" : item.title);
            }
        });
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        if (item.videos.size() > 0) {
            helper.setText(R.id.tvTotalText, DateTimeUtils.formatVideoTime(item.videos.get(0).duration) + "");
        }

        LinearLayout view = helper.getView(R.id.article_left_best);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUitl.showShort("视频：" + adapterPosition + item.title);
            }
        });
    }



    /**
     * 绑定banner数据
     *
     * @param helper
     * @param item
     * @param position
     */
    private List<String> bannerList = new ArrayList<>();
    private List<Integer> banners = new ArrayList<>();

/*    private void bindTopBannerData(BaseViewHolder helper, final HomeTophotIndexBean.Articles item, int position) {
        Banner mBanner = helper.getView(R.id.banner1);
//        mBanner.setBannerStyle()
        List<String> titles = new ArrayList<>();
        if (titles != null && titles.size() > 0) titles.clear();
        bannerList.clear();
        banners.clear();
        initAnim();
        for (int i = 0; i < TopNewsDataHelper.BANNER_IMAGES.length; i++) {
            banners.add(TopNewsDataHelper.BANNER_IMAGES[i]);
        }

        //数据
//        for (HomeTophotIndexBean.Articles.Pics a : item.getPics()) {
//            bannerList.add(a.url);
//            titles.add("");
//        }
        //默认是NUM_INDICATOR_TITLE
        mBanner.setImages(banners)
//                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
                .setIndicatorGravity(BannerConfig.RIGHT)
                .setImageLoader(new GlideImageLoader(false))
                .setOnBannerListener(this)
                .setIndicatorGravity(Gravity.CENTER_HORIZONTAL)
                //设置指示器位置（当banner模式中有指示器时）
                .setIndicatorGravity(BannerConfig.RIGHT)
                .isAutoPlay(true)
                .setBannerAnimation(transformers.get(1))
                .setDelayTime(3000)
                .start();
    }*/

    /**
     * 单图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindSingleNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        LinearLayout view = helper.getView(R.id.llContent);
        ((ExpandImageView) helper.getView(R.id.article_img)).setImageURI(item.getPics().get(0).url);
        helper.setText(R.id.tvSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvCommentnum, item.commentNum + "");
        helper.setText(R.id.tvArticletitle, item.title == null ? "" : item.title);
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("单图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });

    }

    /**
     * 单张大图
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindSingleBigNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        LinearLayout view = helper.getView(R.id.article_left_best);
        helper.setText(R.id.tvSourceName_best, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvCommentnum_best, item.commentNum + "");
        helper.setText(R.id.tvArticletitle_best, item.title == null ? "" : item.title);
        ((ExpandImageView) helper.getView(R.id.img_single_big)).setImageURI(item.getPics().get(0).url);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("单张大图："+adapterPosition+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 通用广告-三图拼接
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindTYAdImageData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.tymenu_a)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_b)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.tymenu_c)).setImageURI(item.getPics().get(2).url);
        }
    }

    /**
     * 三图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindMultiImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        LinearLayout view = helper.getView(R.id.llContent);
        if (item.getPics().size() >= 3) {
            ((ExpandImageView) helper.getView(R.id.article_img1)).setImageURI(item.getPics().get(0).url);
            ((ExpandImageView) helper.getView(R.id.article_img2)).setImageURI(item.getPics().get(1).url);
            ((ExpandImageView) helper.getView(R.id.article_img3)).setImageURI(item.getPics().get(2).url);
        }
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("三图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 无图
     *
     * @param helper
     * @param item
     * @param position
     */
    private void bindEmptyImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int position) {
        helper.setText(R.id.Tarticle_title, item.title == null ? "" : item.title);
        helper.setText(R.id.tvTSourceName, item.mediaName == null ? "" : item.mediaName);
        helper.setText(R.id.tvTcommetnum, item.commentNum + "");
        helper.setText(R.id.tvDate, DateTimeUtils.getLongDateToString(item.virtualTime) + "");
        LinearLayout view = helper.getView(R.id.llSingleRoot);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ToastUitl.showShort("无图："+position+item.title);
                HotNewsDetailActivity.newInstance(context, item.articleUrl == null ? "" : item.articleUrl);
            }
        });
    }

    /**
     * 横向图片新闻列表
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindHorizaontalImgNewsData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        RecyclerView recyclerView = helper.getView(R.id.spike_content_view);
        RelativeLayout rlMore = helper.getView(R.id.rlMore);
        LinearLayout llHorizontalList = helper.getView(R.id.llHorizontalList);
        PulToLeftViewGroup mHorizontalPullGroup = helper.getView(R.id.pull_group);
        //拖拽操作
        initDragMoreLayout(mHorizontalPullGroup);
        List<HomeTophotIndexBean.Articles.HotSpecialNews> hotSpecialNews = item.getHotSpecialNews();
        if (item.getHotSpecialNews() == null || item.getHotSpecialNews().size() <= 0) {
            return;
        }
        ((ImageView) helper.getView(R.id.hotNewsTitleIco)).setImageResource(R.mipmap.icon_title_hotnews);
        helper.setText(R.id.hotnewsMoreText, "查看更多");
//        helper.addOnClickListener(R.id.rlMore);
        rlMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
            }
        });
        //Recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setFocusable(false);
        recyclerView.setFocusableInTouchMode(false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(10);
        SpikeContentAdapter adapter = new SpikeContentAdapter(context, R.layout.item_index_horizontal_list, hotSpecialNews);
        adapter.setEnableLoadMore(true);
        recyclerView.setAdapter(adapter);

    }


    /**
     * 横向分页九宫格菜单
     *
     * @param helper
     * @param item
     * @param adapterPosition
     */
    private void bindHorizaontal9MenuData(BaseViewHolder helper, HomeTophotIndexBean.Articles item, int adapterPosition) {
        ViewPager mPager = helper.getView(R.id.viewpager);
        LinearLayout mLlDot = helper.getView(R.id.ll_dot);
        //填充数据
        addData();
        //设置数据
//        setMixData(mPager, mLlDot);

    }

    /**
     * 上下滚动轮播-财经直播室
     *
     * @param helper
     * @param data
     */
    private void bindUpdownSwitcherData(BaseViewHolder helper, HomeTopIFengBean.ItemBean data) {
        ViewFlipper viewFlipper = helper.getView(R.id.viewFlipper);
        ExpandImageView titleIimg = helper.getView(R.id.title_img);
        titleIimg.setImageURI(data.titleIcon == null ? "" : data.titleIcon);
        for (int i = 0; i < data.marqueeList.size(); i++) {
            View item = LayoutInflater.from(context).inflate(R.layout.item_home_buttelin_switch_view, null);
            LinearLayout parent = (LinearLayout) item.findViewById(R.id.flipper_item);
//            parent.setBackgroundResource(bgs[i]);
            TextView text1 = (TextView) item.findViewById(R.id.updowntext);
            text1.setText(data.marqueeList.get(i).title == null ? "" : data.marqueeList.get(i).title);
            viewFlipper.addView(item);
        }
        viewFlipper.startFlipping();

//        viewSwitcher.setSwitcheNextViewListener(new UpDownViewSwitcher.SwitchNextViewListener() {
//            @Override
//            public void switchTONextView(View nextView, int index) {
//                if (nextView == null) {
//                    return;
//                }
//                try {
//                    final String tag = "热";
//                    final String tag1 = data.marqueeList.get(index).title;
//                    ((TextView) nextView.findViewById(R.id.switch_title_status)).setText(tag);
//                    ((TextView) nextView.findViewById(R.id.switch_title)).setText(tag1);
//                    nextView.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Toast.makeText(v.getContext().getApplicationContext(), tag1, Toast.LENGTH_SHORT).show();
//                            HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
//                        }
//                    });
//                }catch (IndexOutOfBoundsException e){
//
//                }
//            }
//        });
    }


    private void addData() {
        mDatas = new ArrayList<Menu9Model>();
        String[] titles = context.getResources().getStringArray(R.array.menu9_title);
        for (int i = 0; i < titles.length; i++) {
            //动态获取资源ID，第一个参数是资源名，第二个参数是资源类型例如drawable，string等，第三个参数包名
            int imageId = context.getResources().getIdentifier("ic_category_" + i, "mipmap", context.getPackageName());
            mDatas.add(new Menu9Model(titles[i], imageId));
        }
    }


    /**
     * banner动画
     */
    private List<Class<? extends ViewPager.PageTransformer>> transformers = new ArrayList<>();

    private void initAnim() {
        transformers.add(DefaultTransformer.class);
        transformers.add(AccordionTransformer.class);
        transformers.add(BackgroundToForegroundTransformer.class);
        transformers.add(ForegroundToBackgroundTransformer.class);
        transformers.add(CubeInTransformer.class);//兼容问题，慎用
        transformers.add(CubeOutTransformer.class);
        transformers.add(DepthPageTransformer.class);
        transformers.add(FlipHorizontalTransformer.class);//7
        transformers.add(FlipVerticalTransformer.class);
        transformers.add(RotateDownTransformer.class);
        transformers.add(RotateUpTransformer.class);
        transformers.add(ScaleInOutTransformer.class);
        transformers.add(StackTransformer.class);
        transformers.add(TabletTransformer.class);
        transformers.add(ZoomInTransformer.class);
        transformers.add(ZoomOutTranformer.class);
        transformers.add(ZoomOutSlideTransformer.class);
    }

    private void initDragMoreLayout(final PulToLeftViewGroup horizontalDragMoreView) {
        if (horizontalDragMoreView != null) {
            horizontalDragMoreView.setOnPullToLeftListener(new PulToLeftViewGroup.OnPullToLeftListener() {
                @Override
                public void onReleaseFingerToUpload() {
                    //回弹
                    horizontalDragMoreView.completeToUpload();
                    HotNewsBestActivity.newInstance(context, null, AppConstant.HOT_NEWS_PARAMVALUE_TOP);
                }

                @Override
                public void onStartToUpload() {

                }
            });
        }
    }

    void bindData(JzvdStd mVideoPlayer, HomeTophotIndexBean.Articles articles) {
        ILog.e(TAG, "构造方法：============videoplayer 22==================");
        if (mVideoPlayer != null && articles.videos.size() > 0) {
            ILog.e(TAG, "构造方法：============videoplayer 33==================");
            //播放带清晰度的视频
            LinkedHashMap map = new LinkedHashMap();
            map.put("高清", articles.videos.get(0).url);
            map.put("普清", articles.videos.get(0).definitionInfos.size() >= 2 ? articles.videos.get(0).definitionInfos.get(0).url : articles.videos.get(0).url);
            map.put("标清", articles.videos.get(0).definitionInfos.size() >= 2 ? articles.videos.get(0).definitionInfos.get(1).url : articles.videos.get(0).url);
            JZDataSource jzDataSource = new JZDataSource(map, articles.title == null ? "" : articles.title);
            jzDataSource.looping = true;
            jzDataSource.currentUrlIndex = 2;
            jzDataSource.headerMap.put("key", "value");//header
            mVideoPlayer.setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_NORMAL);
//            mVideoPlayer.setUp(articles.videos.get(0).url, articles.title == null ? "" : articles.title,Jzvd.SCREEN_WINDOW_NORMAL);
            TextView titleTextView = mVideoPlayer.titleTextView;
            titleTextView.setTextSize(13f);
            titleTextView.setMaxLines(2);
            titleTextView.setEllipsize(TextUtils.TruncateAt.END);
            //粗体
            titleTextView.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            titleTextView.setPadding(0, 10, 0, 10);
//            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) titleTextView.getLayoutParams();
//            p.setMargins(10,50,10,10);
//            titleTextView.requestLayout();
//            titleTextView.setLayoutParams(p);
        }
        mVideoPlayer.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        // 将列表中的每个视频设置为默认16:9的比例
        ViewGroup.LayoutParams params = mVideoPlayer.getLayoutParams();
        // 宽度为屏幕宽度
        params.width = mContext.getResources().getDisplayMetrics().widthPixels;
        // 高度为宽度的9/16
        params.height = (int) (params.width * 9f / 16f);
        mVideoPlayer.setLayoutParams(params);
        ImageLoaderUtils.display(mContext, mVideoPlayer.thumbImageView, articles.getPics().get(0).url);


    }

    public interface OnRemovePositionListener {
        void removebadData(int position);
    }

    public void setOnRemovePositionListener(HomeHottopMultipleRecycleAdapter.OnRemovePositionListener listener) {
        onRemovePositionListener = listener;
    }
}
