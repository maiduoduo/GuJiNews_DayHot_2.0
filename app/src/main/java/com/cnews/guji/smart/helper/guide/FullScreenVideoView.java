package com.cnews.guji.smart.helper.guide;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.VideoView;

/**
 * CN:      FullScreenVideoView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/3
 * Des:    全屏界面Video
 */
public class FullScreenVideoView extends VideoView {

    public FullScreenVideoView(Context context) {
        super(context);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FullScreenVideoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
    }
}
