package com.cnews.guji.smart.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.player.util.WindowUtil;
import com.cnews.guji.smart.helper.refresh.cyg.PtrRecyclerViewUIComponent;
import com.cnews.guji.smart.helper.refresh.cyg.utils.CygView;
import com.cnews.guji.smart.ui.activity.SmallVideoDetailActivity;
import com.cnews.guji.smart.ui.holder.SmallVideoViewHolder;
import com.cnews.guji.smart.ui.holder.WeakDataHolder;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.cnews.guji.smart.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.github.library.BaseQuickAdapter;
import com.github.library.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 小视频数据装载
 */
public class SmallVideoAdapter extends BaseQuickAdapter<SmallVideoBean.DataBean, BaseViewHolder> {
    private static final String TAG1 = SmallVideoAdapter.class.getSimpleName();
    private Context mContext;
    private List<SmallVideoBean.DataBean> mAdapterAllData = new ArrayList<>();
    private PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent2;
    public SmallVideoAdapter(int layoutResId, List<SmallVideoBean.DataBean> data, Context mContext) {
        super(layoutResId, data);
        this.mContext = mContext;
    }

    public void setSmallAll( List<SmallVideoBean.DataBean> bean,PtrRecyclerViewUIComponent ptrRecyclerViewUIComponent){
        mAdapterAllData.clear();
        mAdapterAllData.addAll(bean);
        this.ptrRecyclerViewUIComponent2 = ptrRecyclerViewUIComponent;
    }


    @Override
    protected void convert(BaseViewHolder holder, SmallVideoBean.DataBean data) {
        int pos = holder.getAdapterPosition();
        if (data != null) {
            SimpleDraweeView mNearbyImg = holder.getView(R.id.nearby_img);
            TextView mTvPlayCount = holder.getView(R.id.tv_play_count);
            TextView mTvLikeCount = holder.getView(R.id.tv_like_count);
            TextView mTvTitle = holder.getView(R.id.tv_video_title);
            RelativeLayout smallVideoRoot = holder.getView(R.id.smallVideoRoot);
            ViewGroup.LayoutParams params = mNearbyImg.getLayoutParams();
            params.width = (WindowUtil.getScreenWidth(BaseApplication.getContext()) - DensityUtil.dip2px(BaseApplication.getContext(), 2)) / 2;
            params.height = (params.width) * 8 / 5;
            mNearbyImg.setLayoutParams(params);
            final Uri uri = Uri.parse(data.getImage0());

            if (isNotEqualsUriPath(mNearbyImg, data.getImage0())) {
                DraweeController controller = Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true)//设置为true将循环播放Gif动画
                        .setOldController(mNearbyImg.getController())
                        .setControllerListener(new BaseControllerListener<ImageInfo>() {

                            @Override
                            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                                mNearbyImg.setTag(R.id.nearby_img, uri);
                            }

                        })
                        .build();
                mNearbyImg.setController(controller);
            }
            mTvTitle.setText(data.getText());
            mTvPlayCount.setText(Utils.formatNumber(Long.parseLong(data.getPlaycount())) + "分享");
            mTvLikeCount.setText(Utils.formatNumber(Long.parseLong(data.getLove())) + "赞");
            holder.addOnClickListener(R.id.smallVideoRoot);

            if (smallVideoRoot != null) {
                smallVideoRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ILog.e(TAG1, "点击" + pos + mAdapterAllData.get(pos).getText());

                        if (checkNull(data)) {
                        } else {
                            if (mAdapterAllData != null && ptrRecyclerViewUIComponent2 != null && ptrRecyclerViewUIComponent2.isRefreshing()) {
                                return;
                            }
                            Intent intent = new Intent(mContext, SmallVideoDetailActivity.class);
                            WeakDataHolder.getInstance().saveData("videoUrlList", mAdapterAllData);
                            intent.putExtra("position", pos);
                            mContext.startActivity(intent);
                        }
                    }
                });
            }
        }

    }

    /**
     * 解决fresco 闪屏
     *
     * @param mNearbyImg
     * @param imgUrl
     * @return
     */
    public boolean isNotEqualsUriPath(SimpleDraweeView mNearbyImg, String imgUrl) {
        if (TextUtils.isEmpty(imgUrl) || TextUtils.isEmpty(mNearbyImg.getTag(R.id.nearby_img) + "")) {
            return false;
        }
        return !(mNearbyImg.getTag(R.id.nearby_img) + "").equals(imgUrl);
    }


    private boolean checkNull(SmallVideoBean.DataBean data) {
        if (data == null) {
            return true;
        } else {
            if (TextUtils.isEmpty(data.getText()) && TextUtils.isEmpty(data.getVideouri())) {
                return true;
            } else {
                return false;
            }
        }
    }
}
