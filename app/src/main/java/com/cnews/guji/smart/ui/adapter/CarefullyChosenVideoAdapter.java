package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatImageView;
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
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.base.AppConstant;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.common.bean.VideoShareBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.helper.glide.GlideImageLoader;
import com.cnews.guji.smart.helper.horizontaldragmoreview.FEle.DZStickyNavLayouts;
import com.cnews.guji.smart.helper.horizontaldragmoreview.HorizontalScrollSlideView.PulToLeftViewGroup;
import com.cnews.guji.smart.helper.imageview.ExpandImageView;
import com.cnews.guji.smart.helper.refresh.cyg.utils.CygView;
import com.cnews.guji.smart.helper.textview.ScrollTextView;
import com.cnews.guji.smart.ui.activity.GJSettingActivity;
import com.cnews.guji.smart.ui.activity.HotNewsBestActivity;
import com.cnews.guji.smart.ui.activity.HotNewsDetailActivity;
import com.cnews.guji.smart.ui.holder.SmallVideoViewHolder;
import com.cnews.guji.smart.ui.model.Menu9Model;
import com.cnews.guji.smart.ui.model.source.NewsQuerySourceHelper;
import com.cnews.guji.smart.ui.model.source.TopNewsDataHelper;
import com.cnews.guji.smart.util.BannerTransformerUtils;
import com.cnews.guji.smart.util.DateTimeUtils;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.ImageLoaderUtils;
import com.cnews.guji.smart.util.ImgTexMixedUtil;
import com.cnews.guji.smart.util.ToastUitl;
import com.cnews.guji.smart.util.statusbar.StatusBarCompatUtils;
import com.cnews.guji.smart.view.TextViewStyleView;
import com.cnews.guji.smart.view.bottomsheets.SpringBackBottomSheetDialog;
import com.cnews.guji.smart.view.widget.PowerfulRecyclerView;
import com.github.library.BaseMultiItemQuickAdapter;
import com.github.library.BaseViewHolder;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
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
 * 精选视频
 */
public class CarefullyChosenVideoAdapter extends BaseMultiItemQuickAdapter<CareChosenVideoBean.DataListBean, BaseViewHolder> {

    private String TAG = "CarefullyChosenVideoAdapter";
    private String normalThumbImgUrl = "https://img.zcool.cn/community/0169bb5cdcc375a801208f8bb46cd9.png@1280w_1l_2o_100sh.png";
    private Context context;
    private List<CareChosenVideoBean> cares = new ArrayList<>();
    private HomeHottopMultipleRecycleAdapter.OnRemovePositionListener onRemovePositionListener;
    private SpringBackBottomSheetDialog bottomSheetDialog;
    private View rootShare;


    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */
    public CarefullyChosenVideoAdapter(Context context, List<CareChosenVideoBean.DataListBean> data) {
        super(data);
        this.context = context;
        bottomSheetDialog = new SpringBackBottomSheetDialog(context);



        addItemType(CareChosenVideoBean.DataListBean.TYPE_TOP_HEADEAR, R.layout.item_video_care_tophot);
        addItemType(CareChosenVideoBean.DataListBean.TYPE_IMAGE_ALBUMLIST, R.layout.video_carechosen_imagealbum_horizontal_list);
        //横向滚动列表
        addItemType(CareChosenVideoBean.DataListBean.TYPE_HORIZONTAL_VIDEOLIST, R.layout.video_carechosen_horizontal_list);
        addItemType(CareChosenVideoBean.DataListBean.TYPE_NONE, R.layout.item_video_carechosen_none);
        addItemType(CareChosenVideoBean.DataListBean.TYPE_VIDEO_BIGIMG, R.layout.video_carechosen_bigimg);
    }

    public void getAll(CareChosenVideoBean bean){
        cares.clear();
        cares.add(bean);
    }

    @Override
    protected void convert(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {
        int adapterPosition = holder.getAdapterPosition();
        switch (holder.getItemViewType()) {
            case CareChosenVideoBean.DataListBean.TYPE_TOP_HEADEAR:
                bindTopHotData(holder, data);
                break;
            case CareChosenVideoBean.DataListBean.TYPE_HORIZONTAL_VIDEOLIST:
                bindVideoListData(holder, data);
                break;
            case CareChosenVideoBean.DataListBean.TYPE_VIDEO_BIGIMG:
                bindVideoBigImgData(holder, data);
            case CareChosenVideoBean.DataListBean.TYPE_IMAGE_ALBUMLIST:
                bindImgAlbumData(holder, data);
                break;
            case CareChosenVideoBean.DataListBean.TYPE_NONE:
//                bindImgAlbumData(holder, data);
                break;
            default:
                break;
        }
    }

    private void bindImgAlbumData(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {
        ILog.e(TAG,"类型12--data-------------->："+new Gson().toJson(data.getActivityList()));
        TextView titleType = holder.getView(R.id.imgTitle);
        AppCompatImageView videoTitleIcon = holder.getView(R.id.videoTitleIcon);
        RecyclerView mHeadRecyclerView = holder.getView(R.id.head_home_recyclerview);
        if (videoTitleIcon != null) {
            videoTitleIcon.setVisibility(View.GONE);
        }
        if (titleType != null) {
            titleType.setText(data.getNodeName() == null ? "" : "# "+data.getNodeName()+" #");
        }

        if (mHeadRecyclerView != null) {
            LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
            layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
            mHeadRecyclerView.setLayoutManager(layoutManager2);
            VideoCarechosenImageAlbumHorizontalistAdapter mAdapter = new VideoCarechosenImageAlbumHorizontalistAdapter(R.layout.item_video_carechosen_imagealbum_horilist, data.getActivityList(), context);
            mHeadRecyclerView.setAdapter(mAdapter);
        }
    }


    /**
     * 视频
     *
     * @param holder
     * @param data
     */
    private void bindVideoBigImgData(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {
        if (data != null && data.getContList().size() > 0) {
            CareChosenVideoBean.DataListBean.ContListBeanX contListBeanX = data.getContList().get(0);
            CareChosenVideoBean.DataListBean.ContListBeanX.GeoBean geo = contListBeanX.getGeo();
            ExpandImageView headIco = holder.getView(R.id.video_userheader);
            TextView tvShare = holder.getView(R.id.tvShare);
            ExpandImageView videoThumbImg = holder.getView(R.id.videoThumbImg);
            holder.setText(R.id.videoUserName, contListBeanX.getUserInfo().getNickname() == null ? "GUJI用户" : contListBeanX.getUserInfo().getNickname());
            headIco.setImageURI(contListBeanX.getUserInfo().getPic());
            videoThumbImg.setImageURI(contListBeanX.getPic() == null ? "" : contListBeanX.getPic());
            holder.setText(R.id.videoPlaytitle, contListBeanX.getSummary() == null ? "" : "    " + contListBeanX.getSummary());
            holder.setText(R.id.videoIP, contListBeanX.getGeo().getShowName() == null ? "" : contListBeanX.getGeo().getShowName());
            holder.setText(R.id.titleMix, contListBeanX.getName() == null ? "" : contListBeanX.getName());
            holder.setText(R.id.tvZan, contListBeanX.getPraiseTimes() == null ? "" : contListBeanX.getPraiseTimes());
            holder.setText(R.id.tvCommentNum, contListBeanX.getCommentTimes() == null ? "" : contListBeanX.getCommentTimes());
            holder.addOnClickListener(R.id.tvShare);
            holder.addOnClickListener(R.id.tvClose);

            tvShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ToastUitl.showShort("分享："+contListBeanX.getName());
                    ILog.e(TAG,"分享："+contListBeanX.getName());

                    showCustomSheet();
                }
            });
        }
    }


    /**
     * 分享
     */
    private void showCustomSheet(){

        rootShare = LayoutInflater.from(context).inflate(R.layout.layout_tuijian_video_share,null,false);
        PowerfulRecyclerView friendRecyclerView = rootShare.findViewById(R.id.friendRecyclerView);
        PowerfulRecyclerView appRecyclerView = rootShare.findViewById(R.id.appRecyclerView);
        PowerfulRecyclerView linkRecyclerview = rootShare.findViewById(R.id.linkRecyclerview);
        LinearLayout l = rootShare.findViewById(R.id.sheetLinear);
//        initListView(l);
        bottomSheetDialog.setContentView(rootShare);
//        l.bindBottomSheetDialog(v);
        bottomSheetDialog.addSpringBackDisLimit(-1);
        bottomSheetDialog.show();

        NewsQuerySourceHelper newsQuerySourceHelper = new NewsQuerySourceHelper();
        List<VideoShareBean.VideoShareFriendBean> friendShareData = newsQuerySourceHelper.getFriendShareData();
        List<VideoShareBean.VideoShareAppBean> appShareData = newsQuerySourceHelper.getAppShareData();
        List<VideoShareBean.VideoShareLinkBean> linkShareData = newsQuerySourceHelper.getLinkShareData();


        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(mContext);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        friendRecyclerView.setLayoutManager(layoutManager);
        VideoShareFriendHorizontalistAdapter mAdapter = new VideoShareFriendHorizontalistAdapter(R.layout.item_video_share_friend,friendShareData , context);
        friendRecyclerView.setAdapter(mAdapter);

        appRecyclerView.setLayoutManager(layoutManager1);
        linkRecyclerview.setLayoutManager(layoutManager2);
        VideoShareAppHorizontalistAdapter mAppAdapter = new VideoShareAppHorizontalistAdapter(R.layout.item_video_share_friend,appShareData, context);
        VideoShareLinkHorizontalistAdapter mLinkAdapter = new VideoShareLinkHorizontalistAdapter(R.layout.item_video_share_friend,linkShareData, context);
        appRecyclerView.setAdapter(mAppAdapter);
        linkRecyclerview.setAdapter(mLinkAdapter);
    }











    /**
     * 横向滚动资讯列表
     *
     * @param holder
     * @param data
     */
    private void bindVideoListData(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {
        TextView titleType = holder.getView(R.id.imagealbum_title);
        AppCompatImageView videoTitleIcon = holder.getView(R.id.videoTitleIcon);
        videoTitleIcon.setVisibility(View.VISIBLE);
        titleType.setText(data.getNodeName() == null ? "" : "# "+data.getNodeName()+" #");
        RecyclerView mHeadRecyclerView = holder.getView(R.id.head_home_recyclerview);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(context);
        layoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        mHeadRecyclerView.setLayoutManager(layoutManager2);
        VideoCarechosenHorizontalistAdapter mAdapter = new VideoCarechosenHorizontalistAdapter(R.layout.item_video_carechosen_horilist, data.getContList(), context);
        mHeadRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 轮播+上下滚动轮播
     *
     * @param holder
     * @param data
     */
    private List<String> banners = new ArrayList<>();

    private void bindTopHotData(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {
        List<CareChosenVideoBean.DataListBean.ContListBeanX> contList = data.getContList();
        CareChosenVideoBean.DataListBean.DayHotInfoBean dayHotInfox = data.getDayHotInfo();
        ExpandImageView mLeftImg = holder.getView(R.id.videoHot_left);
        TextView mLeftTitle = holder.getView(R.id.videoHot_left_title);
        ExpandImageView mRightTopImg = holder.getView(R.id.videoHot_righttop);
        TextView mRightTopTitle = holder.getView(R.id.videoHot_righttop_title);
        ExpandImageView mRightBottomImg = holder.getView(R.id.videoHot_left_bottom);
        TextView mRightBottomTitle = holder.getView(R.id.videoHot_rightbottom_title);
        TextView tvTitle = holder.getView(R.id.tvVideoTitle);
        TextViewStyleView tvUpdateNumOrTime = holder.getView(R.id.tvUpdateNumOrTime);
        ViewFlipper viewFlipper = holder.getView(R.id.viewFlipper);
        if (contList != null && contList.size() > 0) {
            mLeftImg.setImageURI(contList.get(0) == null ? normalThumbImgUrl : contList.get(0).getPic());
            mRightTopImg.setImageURI(contList.get(1) == null ? normalThumbImgUrl : contList.get(1).getPic());
            mRightBottomImg.setImageURI(contList.get(2) == null ? normalThumbImgUrl : contList.get(2).getPic());
            mLeftTitle.setText(contList.get(0) == null ? "# %%" : "# " + contList.get(0).getName());
            mRightTopTitle.setText(contList.get(1) == null ? "# %%" : "# " + contList.get(1).getName());
            mRightBottomTitle.setText(contList.get(2) == null ? "# %%" : "# " + contList.get(2).getName());
        }
        //未读
        int num = Integer.parseInt(dayHotInfox.getUnreadNum());
        tvTitle.setText(dayHotInfox.getBarName() == null ? "" : dayHotInfox.getBarName());
        if (num > 0) {
            tvUpdateNumOrTime.setTextStyle2(mContext, num + "" + " 条更新",
                    num + "", " ", R.style.order_up_normal, R.style.order_up_red_bold);
//            tvUpdateNumOrTime.setText(String.format(context.getResources().getString(R.string.video_scroll_unread), num + ""));
        } else {
            tvUpdateNumOrTime.setTextStyle2(mContext, dayHotInfox.getLastUpdateTime() + "" + " 更新",
                    dayHotInfox.getLastUpdateTime() + "", " ", R.style.order_up_normal, R.style.order_up_red_bold);
//            tvUpdateNumOrTime.setText(String.format(context.getResources().getString(R.string.video_scroll_timepdate), dayHotInfox.getLastUpdateTime()));
        }
        List<CareChosenVideoBean.DataListBean.DayHotInfoBean.ContListBean> contList1 = dayHotInfox.getContList();
        try {
            if (contList1 != null && contList1.size() > 0) {
                for (CareChosenVideoBean.DataListBean.DayHotInfoBean.ContListBean a : contList1) {
                    int i = contList1.indexOf(a);
                    View item = LayoutInflater.from(context).inflate(R.layout.item_home_buttelin_switch_view, null);
                    LinearLayout parent = (LinearLayout) item.findViewById(R.id.flipper_item);
                    TextView text1 = (TextView) item.findViewById(R.id.updowntext);
                    text1.setText(contList1.get(i).getName() == null ? "" : contList1.get(i).getName());
                    viewFlipper.addView(item);
                }
                viewFlipper.startFlipping();

            }

            TextView videoHot_tx1 = holder.getView(R.id.videoHot_tx1);
            TextView videoHot_tx2 = holder.getView(R.id.videoHot_tx2);
            TextView videoHot_tx3 = holder.getView(R.id.videoHot_tx3);
            TextView videoHot_tx4 = holder.getView(R.id.videoHot_tx4);
            if ((contList.size() - 3) > 0) {
                videoHot_tx1.setVisibility(contList.get(3) == null ? View.GONE : View.VISIBLE);
                videoHot_tx1.setText(contList.get(3) == null ? "" : contList.get(3).getName());

                videoHot_tx3.setVisibility(contList.get(4) == null ? View.GONE : View.VISIBLE);
                videoHot_tx3.setText(contList.get(4) == null ? "" : contList.get(4).getName());

                videoHot_tx2.setVisibility(contList.get(5) == null ? View.GONE : View.VISIBLE);
                videoHot_tx2.setText(contList.get(5) == null ? "" : contList.get(5).getName());

                videoHot_tx4.setVisibility(contList.get(6) == null ? View.GONE : View.VISIBLE);
                videoHot_tx4.setText(contList.get(6) == null ? "" : contList.get(6).getName());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    /**
     * @param holder
     * @param data
     */
    private void bindHotSpotData(BaseViewHolder holder, CareChosenVideoBean.DataListBean data) {


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


    public interface OnRemovePositionListener {
        void removebadData(int position);
    }

    public void setOnRemovePositionListener(HomeHottopMultipleRecycleAdapter.OnRemovePositionListener listener) {
        onRemovePositionListener = listener;
    }
}
