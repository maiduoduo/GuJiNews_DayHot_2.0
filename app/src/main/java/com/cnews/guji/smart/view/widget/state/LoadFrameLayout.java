package com.cnews.guji.smart.view.widget.state;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.cnews.guji.smart.R;

/**
 * 多种结果布局
 */
public class LoadFrameLayout extends FrameLayout {
    @LayoutRes
    private int mEmptyViewLayoutResId;
    @LayoutRes
    private int mErrorViewLayoutResId;
    @LayoutRes
    private int mLoadingViewLayoutResId;

    private int mContentViewId;

    private View emptyView;

    private View errorView;

    private View loadingView;

    private View contentView;

    public LoadFrameLayout(Context context) {
        this(context, null);
    }

    public LoadFrameLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadFrameLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LoadFrameLayout, defStyleAttr, 0);
        try {
            mEmptyViewLayoutResId = a.getResourceId(R.styleable.LoadFrameLayout_loadEmptyView, -1);
            mErrorViewLayoutResId = a.getResourceId(R.styleable.LoadFrameLayout_loadErrorView, -1);
            mLoadingViewLayoutResId = a.getResourceId(R.styleable.LoadFrameLayout_loadLoadingView, -1);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(0);

        if (mEmptyViewLayoutResId != -1) {
            setEmptyView(mEmptyViewLayoutResId);
        }

        if (mErrorViewLayoutResId != -1) {
            setErrorView(mErrorViewLayoutResId);
        }

        if (mLoadingViewLayoutResId != -1) {
            setLoadingView(mLoadingViewLayoutResId);
        }
    }

    public void setEmptyView(View emptyView) {
        if (this.emptyView != emptyView) {
            if (this.emptyView != null) {
                removeView(this.emptyView);
            }
            this.emptyView = emptyView;
            addView(this.emptyView);
        }

    }

    public void setErrorView(View errorView) {
        if (this.errorView != null) {
            removeView(this.errorView);
        }
        if (this.errorView != errorView) {
            this.errorView = errorView;
            addView(errorView);
            this.errorView.setVisibility(GONE);
        }
    }

    public void setLoadingView(View loadingView) {
        if (this.loadingView != null) {
            removeView(this.loadingView);
        }
        if (this.loadingView != loadingView) {
            this.loadingView = loadingView;
            addView(loadingView);
            this.loadingView.setVisibility(GONE);
        }
    }

    public void setContentView(View contentView) {
        if (this.contentView != null) {
            removeView(this.contentView);
        }
        if (this.contentView != contentView) {
            this.contentView = contentView;
            addView(contentView);
        }
    }

    public void setEmptyView(@LayoutRes int emptyViewResId) {
        View view = LayoutInflater.from(getContext()).inflate(emptyViewResId, this, false);
        setEmptyView(view);
    }

    public void setErrorView(@LayoutRes int errorViewResId) {
        View view = LayoutInflater.from(getContext()).inflate(errorViewResId, this, false);
        setErrorView(view);
    }

    public void setLoadingView(@LayoutRes int loadingViewResId) {
        View view = LayoutInflater.from(getContext()).inflate(loadingViewResId, this, false);
        setLoadingView(view);
    }

    public void setContentView(@LayoutRes int contentViewResId) {
        View view = LayoutInflater.from(getContext()).inflate(contentViewResId, this, false);
        setContentView(view);
    }

    public void showEmptyView() {
        showSingleView(this.emptyView);
    }

    public void showErrorView() {
        showSingleView(this.errorView);
    }

    public void showLoadingView() {
        showSingleView(this.loadingView);
    }

    public void showContentView() {
        showSingleView(this.contentView);
    }

    private void showSingleView(View specialView) {
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child == specialView) {
                child.setVisibility(VISIBLE);
            } else {
                child.setVisibility(GONE);
            }
        }
    }
}
