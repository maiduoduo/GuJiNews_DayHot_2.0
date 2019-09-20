package com.cnews.guji.smart.view.widget.linearlayoutmanager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * CN:      CustomLayoutManager
 * Author： JSYL-DINGCL (dingcl@jsyl.com.cn)
 * Date:   2019/6/25
 * Des:    自定义拓展LinearLayoutManager
 */
public class CustomLayoutManager extends LinearLayoutManager implements RecyclerView.OnChildAttachStateChangeListener {
    private PagerSnapHelper pagerSpaner = null;
    private OnViewPagerListener viewPagerListener = null;
    int diffY = 0;

    public CustomLayoutManager(Context context) {
        super(context);
    }

    public CustomLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        pagerSpaner = new PagerSnapHelper();
    }
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        view.addOnChildAttachStateChangeListener(this);
        pagerSpaner.attachToRecyclerView(view);
    }



    public void onScrollStateChanged(int state) {
        if (RecyclerView.SCROLL_STATE_IDLE == state) {
            View view = pagerSpaner.findSnapView(this);
            int position = getPosition(view);
            int itemCount = getItemCount();
            viewPagerListener.onPageSelected(position, position == itemCount - 1);
        }
        super.onScrollStateChanged(state);
    }

    public void setOnViewPagerListener(OnViewPagerListener listener) {
        viewPagerListener = listener;
    }

    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        diffY = dy;
        return super.scrollVerticallyBy(dy, recycler, state);
    }

    @Override
    public void onChildViewAttachedToWindow(View view) {
        int position = getPosition(view);
        if (0 == position) {
            viewPagerListener.onPageSelected(position, false);
        }
    }

    @Override
    public void onChildViewDetachedFromWindow(View view) {
        int position = getPosition(view);
        if (0 < diffY) {
            viewPagerListener.onPageRelease(true, position);
        } else {
            viewPagerListener.onPageRelease(false, position);
        }
    }
}
