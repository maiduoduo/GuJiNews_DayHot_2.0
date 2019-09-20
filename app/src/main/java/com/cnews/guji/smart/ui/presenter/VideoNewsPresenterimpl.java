package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.ui.contract.VideoNewsContract;
import com.cnews.guji.smart.ui.model.VideoNewsModel;

/**
 * Created by dingcl
 * 视频数据处理
 */
public class VideoNewsPresenterimpl<S> extends VideoNewsContract.Presenter {
    private VideoNewsContract.Model model;
    public VideoNewsPresenterimpl() {
        model = new VideoNewsModel();

    }

    @Override
    public void getVideoData(Context context, int flag, int videoCurrentType) {
        mView.setVideoData(model.getVideoData(context,flag,videoCurrentType));
    }

    @Override
    public void getVideoWares(Context context, int videoCurrentType) {
        mView.setVideoWares(model.getVideoWares(context,videoCurrentType));
    }

    @Override
    public void getVideoMoreWares(Context context, int videoCurrentType) {
        mView.setVideoMoreWares(model.getVideoMoreWares(context,videoCurrentType));
    }
}
