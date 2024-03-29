package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.common.bean.HotNewsBestBean;
import com.cnews.guji.smart.ui.contract.HotNewsBestContract;
import com.cnews.guji.smart.ui.model.HotNewsBestModel;

/**
 * Created by dingcl
 * 头条数据收发
 */

public class HotNewsBestPresenterimpl<S> extends IBasePresenter<HotNewsBestContract.View> implements HotNewsBestContract.Presenter {
    private HotNewsBestContract.model model;
    public HotNewsBestPresenterimpl() {
        model = new HotNewsBestModel<>();

    }

    @Override
    public void getHotNewsBestData(Context context) {
        if (!isViewAttached())return;
        mView.showLoading();
        HotNewsBestBean hotNewsBestData = model.getHotNewsBestData(context);
        if (hotNewsBestData != null){
            mView.setHotNewsBestData(hotNewsBestData);
        }
    }

    @Override
    public void getHotNewsBestWares(Context context) {
        if (!isViewAttached())return;
        mView.showLoading();
        HotNewsBestBean hotNewsBestData = model.getHotNewsBestWares(context);
        if (hotNewsBestData != null){
            mView.setHotNewsBestWares(hotNewsBestData);
        }
    }

    @Override
    public void getHotNewsBestMoreWares(Context context) {
        if (!isViewAttached())return;
        mView.showLoading();
        HotNewsBestBean hotNewsBestData = model.getHotNewsBestMoreWares(context);
        if (hotNewsBestData != null){
            mView.setHotNewsBestMoreWares(hotNewsBestData);
        }
    }




}
