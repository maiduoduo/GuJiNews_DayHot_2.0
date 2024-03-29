package com.cnews.guji.smart.helper.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.helper.imageview.CustomRoundAngleImageView;
import com.cnews.guji.smart.helper.imageview.CustomRoundAngleImageViewBanner;
import com.youth.banner.loader.ImageLoader;

/**
 * author：JSYL-DCL on 2017/9/21
 */
public class GlideImageLoader extends ImageLoader {
    private boolean isRC = false;
    public GlideImageLoader(boolean isRoundCorner) {
        this.isRC = isRoundCorner;
    }

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        Glide.with(context.getApplicationContext()).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.place_image_default)
                .error(R.mipmap.place_image_default)
                .crossFade()
                .into(imageView);
    }

    @Override
    public ImageView createImageView(Context context) {
        //圆角
        if (isRC) {
            return new CustomRoundAngleImageViewBanner(context);
        }else {
            return new CustomRoundAngleImageView(context);
        }
    }
}