package com.cnews.guji.smart.helper.horizontaldragmoreview.FEle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;


public class AnimatorView extends RelativeLayout {

    private View mView;
    private LinearLayout mLayout;
    private TextView mTextView;
    private int mMove;
    private Path mPath;
    private Paint mBackPaint;
    private int mHeight;
    private int mLayoutHeight;
    private int mLayoutWidth;

    public AnimatorView(Context context) {
        super(context);
        mPath = new Path();
        mBackPaint = new Paint();
        mBackPaint.setAntiAlias(true);
        mBackPaint.setStyle(Paint.Style.FILL);
        mBackPaint.setColor(0xffF3F3F3);

        mView = View.inflate(context, R.layout.animator_hot_ele, null);
        mLayout = (LinearLayout) mView.findViewById(R.id.animator_ll);
        mTextView = (TextView) mView.findViewById(R.id.animator_text);
        addView(mView);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AnimatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        mHeight = getHeight();
        mLayoutHeight = mLayout.getHeight();
        mLayoutWidth = mLayout.getWidth();
    }

    public void setRefresh(int width){
        mMove += width;
        if(mMove < 0){
            mMove = 0;
        }else if(mMove > DZStickyNavLayouts.maxWidth){
            mMove = DZStickyNavLayouts.maxWidth;
        }
        mView.getLayoutParams().width = mMove;
        mView.getLayoutParams().height = LinearLayout.LayoutParams.MATCH_PARENT;
        if(mMove > DZStickyNavLayouts.maxWidth / 2){
            mTextView.setText("释放查看");
        }else{
            mTextView.setText("查看更多");
        }
        requestLayout();
    }

    public void setRelease(){
        mMove = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        float marginTop = (mHeight - mLayoutHeight) / 2;
        // 右上角x坐标、右上角y坐标
        mPath.moveTo(mMove - mLayoutWidth, marginTop);
        // 左边弧形x坐标、左边弧形y坐标、右下角x坐标、右下角y坐标
        mPath.quadTo(0,  mHeight / 2, mMove - mLayoutWidth, mLayoutHeight + marginTop);
        canvas.drawPath(mPath, mBackPaint);
    }
}
