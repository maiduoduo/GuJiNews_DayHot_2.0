package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.ui.contract.CarefullyChosenVideoContract;
import com.cnews.guji.smart.ui.model.CarefullyChosenVideoModel;

import io.reactivex.functions.Consumer;

/**
 * 精选视频
 * @author JSYL-DCL
 */
public class CarefullyChosenVideoPresenterimpl extends CarefullyChosenVideoContract.Presenter {
    private CarefullyChosenVideoContract.model model;

    public CarefullyChosenVideoPresenterimpl() {
        model = new CarefullyChosenVideoModel();
    }

    /**
     * 精选
     * @param context
     * @param isHome
     * @param channelCode
     */
    private int i = 0;
    @Override
    public void careVideoData(Context context, int isHome, String channelCode) {
        if (!isViewAttached()) {
            return;
        }
        model.careVideoData(context, isHome, channelCode)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<CareChosenVideoBean>() {
                               @Override
                               public void accept(CareChosenVideoBean bean) throws Exception {
                                   if (mView != null){
                                       mView.setCareVideoData(bean);
//                                       if (i == 0){
//                                           mView.setCareVideoData(null);
//                                           i++;
//                                       }else {
//                                           mView.setCareVideoData(bean);
//                                       }
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable, throwable.getMessage());
                               }
                           }
                );
    }


    @Override
    public void careVideoMoreData(Context context, String nextUrl) {
        if (!isViewAttached()) {
            return;
        }
        model.careVideoMoreData(context, nextUrl)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<CareChosenVideoBean>() {
                               @Override
                               public void accept(CareChosenVideoBean bean) throws Exception {
                                   if (mView != null){
                                       mView.setCareVideoLoadMoreData(bean);
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable, throwable.getMessage());
                               }
                           }
                );
    }

}
