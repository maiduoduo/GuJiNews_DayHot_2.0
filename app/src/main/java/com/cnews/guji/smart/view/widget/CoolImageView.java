package com.cnews.guji.smart.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.cnews.guji.smart.R;

/**
 * CN:      CoolImageView
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/3
 * Des:    TODO:
 */
public class CoolImageView extends AppCompatImageView {

    private Drawable mDrawable;
    private  int mLeft=0;
    private  int mTop=0;
    private  int mSpeed=2;
    private boolean isSetVerticalMove;
    private boolean isMoveLeft;
    private boolean isMoveUp;
    private Handler mHandler;
    private int mCanvasBgSize;
    private String moveTime;
    private String direction;

    public CoolImageView(Context context) {
        super(context);
    }

    public CoolImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUp(context,attrs);
    }

    public CoolImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUp(context,attrs);
    }

    private void setUp(Context context, AttributeSet attrs) {
        TypedArray a =context.obtainStyledAttributes(attrs, R.styleable.CoolImageView);
        direction = a.getString(R.styleable.CoolImageView_direction);
        moveTime = a.getString(R.styleable.CoolImageView_moveTime);
        if(direction == null) {
            isSetVerticalMove=true;
//            throw new RuntimeException("You don't set direction properties,If you don't want to do that." +
//                    "You can use ordinary ImageView instead");
        } else if(direction.equals("vertical")) {
            isSetVerticalMove=true;
        } else if(direction.equals("horizontal")){
            isSetVerticalMove=false;
        } else {
            throw new RuntimeException("Direction attribute set is not valid,It is only allowed to set to vertical or horizontal");
        }
        if (moveTime == null){
            moveTime = "20";
        }
        mDrawable=getDrawable();
        mHandler=new MoveHandler();
        mHandler.sendEmptyMessageDelayed(1, Long.parseLong(moveTime));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(isSetVerticalMove) {
            canvas.translate(0.0F,mTop);
        } else {
            canvas.translate(mLeft, 0.0F);
        }
        mDrawable.draw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(isSetVerticalMove) {
            mCanvasBgSize=getMeasuredHeight()*3/2;
            mDrawable.setBounds(0,0,getMeasuredWidth(),mCanvasBgSize);
        } else {
            mCanvasBgSize=getMeasuredWidth()*3/2;
            mDrawable.setBounds(0,0,mCanvasBgSize,getMeasuredHeight());
        }
    }

    private class MoveHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isSetVerticalMove) {
                if(isMoveUp) {
                    if(mTop<=getMeasuredHeight()-mCanvasBgSize) {//此时表示移到了最up的位置
                        mTop+=mSpeed;
                        isMoveUp = false;
                    }else {//继续下移
                        mTop-=mSpeed;
                    }
                } else {
                    if(mTop==0) {//此时表示移动到了最down，此时图片的up侧应该与屏幕up侧对齐，即坐标值为0
                        mTop-=mSpeed;
                        isMoveUp = true;//图片已经移动到了最down侧，需要修改其移动方向为up
                    } else {
                        mTop+=mSpeed;//继续下移
                    }
                }
            } else {
                if(isMoveLeft) {//向左移动
                    if(mLeft<=getMeasuredWidth()-mCanvasBgSize) {//此时表示移到了最左侧的位置
                        mLeft+=mSpeed;
                        isMoveLeft = false;
                    }else {//继续左移
                        mLeft-=mSpeed;
                    }
                }else {
                    if (mLeft == 0) {//此时表示移动到了最右侧，此时图片的左侧应该与屏幕左侧对齐，即坐标值为0
                        mLeft-=mSpeed;
                        isMoveLeft = true;//图片已经移动到了最右侧，需要修改其移动方向为向左
                    } else {
                        mLeft+=mSpeed;//继续右移
                    }
                }
            }
            invalidate();
            mHandler.sendEmptyMessageDelayed(1, Long.parseLong(moveTime));
        }
    }
}
