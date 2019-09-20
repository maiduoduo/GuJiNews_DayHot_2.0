package com.cnews.guji.smart.helper.refresh.cyg.header;

import android.content.Context;
import android.graphics.Matrix;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.util.DensityUtil;
import com.cnews.guji.smart.util.ILog;
import com.github.library.widget.RefreshView;
import com.jack.mc.cyg.cygptr.PtrFrameLayout;
import com.jack.mc.cyg.cygptr.indicator.PtrTensionIndicator;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * CN:      VideoCustomRotateHeader
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Des:    外环旋转头部
 */
public class VideoCustomRotateHeader extends LinearLayout implements RefreshHeader {
    ImageView iv_refresh;
    ProgressBar progressbar_pull;
    TextView tv_pull_title;
    private Matrix mMatrix = new Matrix();
    private static int delayTime = 1000;
    private static int refreshTime = 2000;
    private PtrFrameLayout mPtrFrameLayout;
    private PtrTensionIndicator mPtrTensionIndicator;
    private Handler mHandler = new Handler();

    private Animation mScale;

    private Runnable mPullRRunnable = new Runnable() {
        @Override
        public void run() {
            progressbar_pull.setVisibility(GONE);
            iv_refresh.setVisibility(GONE);
        }
    };

    public VideoCustomRotateHeader(@NonNull Context context) {
        this(context, null);
        initView();
    }

    public VideoCustomRotateHeader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        initView();
    }

    public VideoCustomRotateHeader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.video_header_layout, this);

        iv_refresh = view.findViewById(R.id.iv_refresh);
        progressbar_pull = view.findViewById(R.id.progressbar_pull);
        tv_pull_title = view.findViewById(R.id.tv_pull_title);
        progressbar_pull.setVisibility(GONE);
        tv_pull_title.setVisibility(GONE);

        mScale = new ScaleAnimation(0.5f, 1f, 0.5f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 1);
        mScale.setDuration(delayTime);


    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {
        mHandler.removeCallbacks(mPullRRunnable);
        tv_pull_title.clearAnimation();
        pullStep0(0.0f);
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {
        final int mOffsetToRefresh = maxDragHeight;//可释放刷新位置
        final int currentPos = offset;//当前位置
        if (currentPos < mOffsetToRefresh) {//调用2
            float scale = currentPos * 5 / 4 / (float) mOffsetToRefresh;
            if (scale > 1.0f) {
                scale = 1.0f;
            }
            pullStep0(scale);
        } else {
            float scale = currentPos / (float) mOffsetToRefresh;
            if (scale > 1.0f) {
                scale = 1.0f;
            }
            pullStep0(scale);

        }
    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        mHandler.removeCallbacks(mPullRRunnable);
        tv_pull_title.clearAnimation();
        progressbar_pull.setVisibility(VISIBLE);
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        tv_pull_title.clearAnimation();
        if (success){
            progressbar_pull.setVisibility(GONE);
        } else {
            progressbar_pull.setVisibility(GONE);
            tv_pull_title.setVisibility(VISIBLE);
            tv_pull_title.setText("刷新失败");
        }
        Log.e("custom","------------onFinish----------success----:"+success);
        return delayTime;//延迟500毫秒之后再弹回
    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHandler.postDelayed(mPullRRunnable, delayTime);
                break;
            case Refreshing:
                iv_refresh.setVisibility(GONE);
                tv_pull_title.setVisibility(GONE);
                progressbar_pull.setVisibility(VISIBLE);
                break;
            case ReleaseToRefresh:
                iv_refresh.setVisibility(GONE);
                progressbar_pull.setVisibility(VISIBLE);
                tv_pull_title.setVisibility(GONE);
                break;
        }
    }


    private void pullStep0(float scale) {
        iv_refresh.setVisibility(VISIBLE);
        progressbar_pull.setVisibility(GONE);
        tv_pull_title.setVisibility(GONE);

        scaleImage(scale);
    }


    private void scaleImage(float scale) {
        mMatrix.setScale(scale, scale, iv_refresh.getWidth() / 2, iv_refresh.getHeight() / 2);
        iv_refresh.setImageMatrix(mMatrix);
    }
    public TextView getTvtitle() {
        return tv_pull_title;
    }

}
