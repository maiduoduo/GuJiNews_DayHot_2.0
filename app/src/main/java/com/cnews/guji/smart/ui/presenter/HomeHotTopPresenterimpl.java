package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.XunshiBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.model.HomeHotTopModel;

import java.util.List;

import io.reactivex.functions.Consumer;
import okhttp3.RequestBody;

/**
 * 热门精选数据收发
 * @author JSYL-DCL
 */
public class HomeHotTopPresenterimpl<S> extends HomeHotTopContract.Presenter {
    private HomeHotTopContract.model model;
    public HomeHotTopPresenterimpl() {
        model = new HomeHotTopModel();
    }

    @Override
    public void getHomeHotTopData(Context context,int flag,int videoCurrentType) {
        if (!isViewAttached()){return;}
        HomeTophotIndexBean homeHotTopData = model.getHomeHotTopData(context, flag, videoCurrentType);
        if (homeHotTopData != null){
            mView.setHomeHotTopData(homeHotTopData);
        }
    }

    /**
     * 头条默认数据
     * @param context
     * @param pullnum 加载第几页
     *  ifeng数据
     */
    @Override
    public void getIFengHomeTopNewsData(Context context, int pullnum,boolean isup,String currentName) {
        if (!isViewAttached()){return;}
        model.getIFengHomeTopNewsData(context,pullnum,isup,currentName)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<List<HomeTopIFengBean>>() {
                               @Override
                               public void accept(List<HomeTopIFengBean> bean) throws Exception {
                                   if (bean != null && bean.size() > 0) {
                                       mView.setIFengHomeTopNewsData(bean, isup);
                                   }else {
                                       mView.showEmpty();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );

    }

    @Override
    public void getXunshiData(Context context, String body) {
        if (!isViewAttached()){return;}
        model.getXunshiData(context,body)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<XunshiBean>() {
                               @Override
                               public void accept(XunshiBean xunshiBean) throws Exception {
                                   mView.showXunshiData(xunshiBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );
    }
}
