package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
import com.cnews.guji.smart.common.net.ErrorHanding;
import com.cnews.guji.smart.ui.contract.VideoMovieContract;
import com.cnews.guji.smart.ui.model.VideoMovieModel;
import io.reactivex.functions.Consumer;

/**
 * 精选视频
 * @author JSYL-DCL
 */
public class VideoMoviePresenterimpl extends VideoMovieContract.Presenter {
    private VideoMovieContract.model mModel;

    public VideoMoviePresenterimpl() {
        mModel = new VideoMovieModel();
    }

    @Override
    public void getMovieTypeList(Context context) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        mModel.getMovieTypeList(context)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<MovieTypeBean>() {
                               @Override
                               public void accept(MovieTypeBean bean) throws Exception {
                                   if (mView != null) {
                                       mView.addMovieType(bean.getData().get(0).getTagList());
                                       mView.addMovieNation(bean.getData().get(1).getTagList());
                                       mView.addMoviePeriod(bean.getData().get(2).getTagList());
                                       mView.addMoviePoint(bean.getData().get(3).getTagList());
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       mView.showError(ErrorHanding.handleError(throwable));
                                   }
                               }
                           }
                );
    }

    @Override
    public void getClassifySearchList(Context context, int offset, int catId, int sourceId, int yearId, int sortId) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        mModel.getClassifySearchList(context,
                                     offset,
                                     catId,
                                     sourceId,
                                     yearId,
                                     sortId)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<ClassifySearchBean>() {
                               @Override
                               public void accept(ClassifySearchBean bean) throws Exception {
                                   if (mView != null) {
                                       mView.addClassifySearchData(bean.getList());
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       mView.showError(ErrorHanding.handleError(throwable));
                                   }
                               }
                           }
                );
    }
}
