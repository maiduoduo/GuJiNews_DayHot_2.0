package com.cnews.guji.smart.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.Target;
import com.cnews.guji.smart.R;
import com.cnews.guji.smart.helper.glide.GlideRoundTransformUtil;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;

/**
 * 图片加载工具类 使用glide框架封装
 * @author JSYL-DCL
 */
public class ImageLoaderUtils {

    public static void display(Context context, ImageView imageView, String url, int placeholder, int error) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).placeholder(placeholder)
                .error(error).crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .crossFade().into(imageView);
    }

    public static void display(Context context, ImageView imageView, File url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .crossFade().into(imageView);
    }
    public static void displaySmallPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .thumbnail(0.5f)
                .into(imageView);
    }
    public static void displayBigPhoto(Context context, ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .into(imageView);
    }
    public static void display(Context context, ImageView imageView, int url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .crossFade().into(imageView);
    }
    public static void displayRound(Context context,ImageView imageView, String url) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.default_img_place)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }
    public static void displayRound(Context context,ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context).load(resId)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.default_img_place)
                .centerCrop().transform(new GlideRoundTransformUtil(context)).into(imageView);
    }


    public static void displayGlideGif(Context context,ImageView imageView, int resId) {
        if (imageView == null) {
            throw new IllegalArgumentException("argument error");
        }
        Glide.with(context)
                .load(resId)
                .asGif()//加载动态图片，若现有图片为非gif图片，则直接加载错误占位图。
                .placeholder(R.drawable.default_img_place)
                .error(R.drawable.default_img_place)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }


//    int mFilterColor = ContextCompat.getColor(mContext,R.color.black);
//        int blue = Color.blue(mFilterColor);
//        int green = Color.green(mFilterColor);
//        int red = Color.red(mFilterColor);
//        float[] cm = new float[]{
//                1, 0, 0, 0, red,// 红色值
//                0, 1, 0, 0, green,// 绿色值
//                0, 0, 1, 0, blue,// 蓝色值
//                0, 0, 0, 5, 1 // 透明度
//        };
//        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(cm);
//        imgTop.setColorFilter(filter);//设置图标的颜色

    /**
     * SimpleDraweeView 加载本地GIF
     */
    public static void displayGif(int drawableRes, SimpleDraweeView view) {
        Uri uri = new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(drawableRes))
                .build();
        DraweeController draweeController =
                Fresco.newDraweeControllerBuilder()
                        .setUri(uri)
                        .setAutoPlayAnimations(true) // 设置加载图片完成后是否直接进行播放
                        .build();
        if (view == null)return;
        view.setController(draweeController);
    }


//    /**
//     * 常规使用
//     *
//     * @param context   上下文
//     * @param url       图片链接
//     * @param imageView 目标view
//     */
//    public static void LoadImage(Context context, Object url, ImageView imageView) {
//        Glide.with(context).load(url)
//                .apply(new RequestOptions()
//                        .centerCrop()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL))
//                .transition(new DrawableTransitionOptions().crossFade(800))
//                .into(imageView);
//    }
//
//
//    /**
//     * 自定义RequestOptions使用
//     *
//     * @param context        上下文
//     * @param url            图片链接
//     * @param requestOptions
//     * @param imageView      目标view
//     */
//    public static void LoadImage(Context context, Object url, ImageView imageView, RequestOptions requestOptions) {
//        Glide.with(context).load(url)
//                .apply(requestOptions)
//                .transition(new DrawableTransitionOptions().crossFade(800))
//                .into(imageView);
//    }
//
//    /**
//     * 自定义RequestOptions使用
//     *
//     * @param fragment
//     * @param url            图片链接
//     * @param requestOptions
//     * @param imageView      目标view
//     */
//    public static void LoadImage(android.support.v4.app.Fragment fragment, Object url, ImageView imageView, RequestOptions requestOptions) {
//        Glide.with(fragment).load(url)
//                .apply(requestOptions)
//                .transition(new DrawableTransitionOptions().crossFade(800))
//                .into(imageView);
//    }
//
//
    /**
     * 需要回调时使用
     *
     * @param context         上下文
     * @param url             图片链接
     * @param imageViewTarget 回调需求
     */
    public static void LoadImage(Context context, Object url, ImageViewTarget imageViewTarget,boolean isGif) {
        if (isGif) {
            Glide.with(context).load(url)
                    .asGif()
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_img_place)
                    .error(R.drawable.default_img_place)
                    .crossFade(500)
                    .into(imageViewTarget);
        }else {
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .centerCrop()
                    .placeholder(R.drawable.default_img_place)
                    .error(R.drawable.default_img_place)
                    .into(imageViewTarget);
        }
    }
//
//    /**
//     * 需要回调时使用
//     *
//     * @param context   上下文
//     * @param url       图片链接
//     * @param imageView 回调需求
//     */
//    public static void LoadImage(Context context, Object url, ImageView imageView, RequestListener listener) {
//        Glide.with(context).load(url)
//                //.thumbnail(0.1f)
//                .apply(new RequestOptions()
//                        .diskCacheStrategy(DiskCacheStrategy.ALL))
//                .transition(new DrawableTransitionOptions().crossFade(800))
//                .listener(listener)
//                .into(imageView);
//    }

}