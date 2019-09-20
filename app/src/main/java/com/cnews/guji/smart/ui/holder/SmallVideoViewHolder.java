package com.cnews.guji.smart.ui.holder;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.app.BaseApplication;
import com.cnews.guji.smart.common.bean.LevideoData;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.helper.player.util.WindowUtil;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.Utils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * SmallVideoViewHolder
 */
public class SmallVideoViewHolder extends CygBaseRecyclerViewHolder<SmallVideoBean.DataBean> {

    @BindView(R.id.nearby_img)
    SimpleDraweeView mNearbyImg;
    @BindView(R.id.tv_video_title)
    TextView mTvTitle;
    @BindView(R.id.tv_play_count)
    TextView mTvPlayCount;
    @BindView(R.id.tv_like_count)
    TextView mTvLikeCount;

    public SmallVideoViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);

    }

    @Override
    protected void onItemDataUpdated(@Nullable SmallVideoBean.DataBean data) {
        if (data != null) {
            ViewGroup.LayoutParams params = mNearbyImg.getLayoutParams();
            params.width = (WindowUtil.getScreenWidth(BaseApplication.getContext()) - DensityUtil.dip2px(BaseApplication.getContext(), 2)) / 2;
            params.height = (params.width) * 8 / 5;
            mNearbyImg.setLayoutParams(params);
            final Uri uri = Uri.parse(data.getImage0());

            if (isNotEqualsUriPath(mNearbyImg, data.getImage0())) {
//                mNearbyImg.setImageURI(uri);
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


//            mNearbyImg.setImageURI(Uri.parse(data.getCoverImgUrl()));

            mTvTitle.setText(data.getText());
            mTvPlayCount.setText(Utils.formatNumber(Long.parseLong(data.getPlaycount())) + "分享");
            mTvLikeCount.setText(Utils.formatNumber(Long.parseLong(data.getLove())) + "赞");


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

}
