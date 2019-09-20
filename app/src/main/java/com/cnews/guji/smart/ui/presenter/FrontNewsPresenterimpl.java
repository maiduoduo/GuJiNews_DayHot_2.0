package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.ui.contract.ImpNewsContract;
import com.cnews.guji.smart.ui.model.FrontNewsModel;

import io.reactivex.functions.Consumer;

/**
 * Created by dingcl
 * 要闻业务数据收发
 */
public class FrontNewsPresenterimpl<S> extends IBasePresenter<ImpNewsContract.View> implements ImpNewsContract.Presenter {
    private ImpNewsContract.model model;

    public FrontNewsPresenterimpl() {
        this.model = new FrontNewsModel<>();
    }

    @Override
    public void getFrontNewsData(Context context,int flag) {
        mView.setFrontNewsData(model.getFrontNewsData(context,flag));
    }

    @Override
    public void getFrontNewsWares(Context context) {
        mView.setFrontNewsWares(model.getFrontNewsWares(context));
    }

    @Override
    public void getFrontNewsMoreWares(Context context) {
        mView.setFrontNewsMoreWares(model.getFrontNewsMoreWares(context));
    }

    /**
     * 新闻—网络数据
     */
    @Override
    public void lodeMineChannelsRequest(Context context) {
        if (!isViewAttached())return;
        model.lodeMineChannelsRequest(context).compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<ClothesBean>() {
                               @Override
                               public void accept(ClothesBean newsMainBean) throws Exception {
                                   mView.setMineChannelsRequest(newsMainBean);
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
