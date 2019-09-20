package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;
import com.cnews.guji.smart.ui.contract.ImageAtlasContract;
import com.cnews.guji.smart.ui.contract.SquareHotMoreContract;
import com.cnews.guji.smart.ui.model.ImageAtlasModel;
import com.cnews.guji.smart.ui.model.SquareHotMoreModel;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * 广场-更多热点
 * @author JSYL-DCL
 */
public class SquareHotMorePresenterImpl extends SquareHotMoreContract.Presenter {
    private static final String TAG1 = "SquareMoreHotActivity";
    private SquareHotMoreContract.Model mModel;

    public SquareHotMorePresenterImpl() {
        this.mModel = new SquareHotMoreModel();
    }
    /**
     * 新闻详情
     * @param context
     */

    @Override
    public void hotMore(Context context, String action, int page) {
        if (!isViewAttached()){return;}
        mView.showLoading();
        mModel.hotMore(context,action,page)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<List<SquareHotMoreBean>>() {
                               @Override
                               public void accept(List<SquareHotMoreBean> bean) throws Exception {
                                   mView.setHotMore(bean);
//                                   ILog.e(TAG1,"[setHotMore]----------------------->:\n"+new Gson().toJson(bean));
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
    public void hotMoreLoadMore(Context context, String action, int page) {
        if (!isViewAttached()){return;}
        mView.showLoading();
        mModel.hotMore(context,action,page)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<List<SquareHotMoreBean>>() {
                               @Override
                               public void accept(List<SquareHotMoreBean> bean) throws Exception {
                                   mView.setHotMore(bean);
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
