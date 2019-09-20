package com.cnews.guji.smart.util;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.cnews.guji.smart.app.BaseApplication;

/**
 * @package: ImgTexMixedUtil
 * @author： JSYL-DCL
 * @describe： 图文混排工具类
 * @email： 11442865
 */
public class ImgTexMixedUtil {
    private static final String TAG = "ImgTexMixedUtil";

    /**
     * 将富文本转成CharSequence
     *
     * @param commonStr 普通内容
     * @param bqId      图片
     * @return
     */
    public static CharSequence transferPic(String commonStr, int bqId,int right, int bottom) {
        Spanned spanned = Html.fromHtml("<img src=\"" + bqId + "\">" + commonStr, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {
                int id = Integer.parseInt(source);
                // 根据id从资源文件中获取图片对象
                Drawable d = BaseApplication.getAppContext().getResources().getDrawable(id);
                // 以此作为标志位，方便外部取出对应的资源id
                d.setState(new int[]{id});
//                d.setBounds(0, 0, d.getMinimumWidth(), d.getMinimumHeight());
                d.setBounds(0, 0, right, bottom);
                return d;
            }
        }, null);
        if (spanned instanceof SpannableStringBuilder) {
            ImageSpan[] imageSpans = spanned.getSpans(0, spanned.length(), ImageSpan.class);
            for (ImageSpan imageSpan : imageSpans) {
                int start = spanned.getSpanStart(imageSpan);
                int end = spanned.getSpanEnd(imageSpan);
                Drawable d = imageSpan.getDrawable();
                StickerSpan newImageSpan = new StickerSpan(d, ImageSpan.ALIGN_BASELINE);
                ((SpannableStringBuilder) spanned).setSpan(newImageSpan, start, end, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                ((SpannableStringBuilder) spanned).removeSpan(imageSpan);
            }
        }
        return spanned;
    }

    public static class StickerSpan extends ImageSpan {

        public StickerSpan(Drawable b, int verticalAlignment) {
            super(b, verticalAlignment);

        }

        @Override
        public void draw(Canvas canvas, CharSequence text,
                         int start, int end, float x,
                         int top, int y, int bottom, Paint paint) {
            Drawable b = getDrawable();
            canvas.save();
            int transY = bottom - b.getBounds().bottom - ScreenUtil.dip2px( 3);
            if (mVerticalAlignment == ALIGN_BASELINE) {
                int textLength = text.length();
                for (int i = 0; i < textLength; i++) {
                    if (Character.isLetterOrDigit(text.charAt(i))) {
                        transY -= paint.getFontMetricsInt().descent;
                        break;
                    }
                }
            }
            canvas.translate(x, transY);
            b.draw(canvas);
            canvas.restore();
        }
    }

}

