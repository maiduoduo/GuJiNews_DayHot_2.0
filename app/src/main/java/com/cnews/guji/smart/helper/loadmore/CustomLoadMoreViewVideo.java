package com.cnews.guji.smart.helper.loadmore;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.cnews.guji.smart.R;
import com.github.library.loadmore.LoadMoreView;

/**
 * CustomLoadMoreViewVideo
 */

public final class CustomLoadMoreViewVideo extends LoadMoreView {
    @Override public int getLayoutId() {
        return R.layout.ba_view_load_more_video;
    }

    @Override protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
