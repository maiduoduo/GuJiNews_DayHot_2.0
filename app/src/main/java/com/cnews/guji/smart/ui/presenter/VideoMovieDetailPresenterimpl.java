package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.movie.MovieAwardsBean;
import com.cnews.guji.smart.common.bean.movie.MovieCommentTagBean;
import com.cnews.guji.smart.common.bean.movie.MovieDetailDataBean;
import com.cnews.guji.smart.common.bean.movie.MovieLongCommentBean;
import com.cnews.guji.smart.common.bean.movie.MovieMoneyBoxBean;
import com.cnews.guji.smart.common.bean.movie.MovieProCommentBean;
import com.cnews.guji.smart.common.bean.movie.MovieRelatedInformationBean;
import com.cnews.guji.smart.common.bean.movie.MovieResourceBean;
import com.cnews.guji.smart.common.bean.movie.MovieStarBean;
import com.cnews.guji.smart.common.bean.movie.MovieTipsBean;
import com.cnews.guji.smart.common.bean.movie.MovieTopicBean;
import com.cnews.guji.smart.common.bean.movie.RelatedMovieBean;
import com.cnews.guji.smart.common.net.ErrorHanding;
import com.cnews.guji.smart.ui.contract.VideoMovieDetailContract;
import com.cnews.guji.smart.ui.model.VideoMovieDetailModel;
import com.cnews.guji.smart.util.ILog;

import io.reactivex.functions.Consumer;
import io.reactivex.internal.operators.flowable.FlowableObserveOn;

/**
 * 电影详情
 * @author JSYL-DCL
 */
public class VideoMovieDetailPresenterimpl extends VideoMovieDetailContract.Presenter {
    private VideoMovieDetailContract.model mModel;

    public VideoMovieDetailPresenterimpl() {
        mModel = new VideoMovieDetailModel();
    }


    @Override
    public void getMovieData(Context context, int movieId) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        //merge操作只支持9个,所以分开获取
        FlowableObserveOn.merge(
                mModel.getMovieBasicData(context,movieId),
                mModel.getMovieTips(context,movieId),
                mModel.getMovieStarList(context,movieId))
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object bean) throws Exception {
                                   if (mView != null) {
                                       if (bean instanceof MovieDetailDataBean) {
                                           mView.addMovieBasicData(((MovieDetailDataBean) bean).getData().getMovie());
                                       } else if (bean instanceof MovieTipsBean) {
                                           mView.addMovieTips(((MovieTipsBean) bean).getData());
                                       } else if (bean instanceof MovieStarBean) {
                                           mView.addMovieStarList(((MovieStarBean) bean));
                                       }
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       ILog.e("MOVIE","getMovieData----------->:"+throwable);
//                                       mView.showError(ErrorHanding.handleError(throwable));
                                       mView.showError(throwable.getMessage());
                                   }
                               }
                           }
                );
    }

    @Override
    public void getMovieSecondData(Context context, int movieId) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        //merge操作只支持9个,所以分开获取
        FlowableObserveOn.merge(
                mModel.getMovieBox(context,movieId),
                mModel.getMovieAwards(context,movieId),
                mModel.getMovieResource(context,movieId))
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   if (mView != null) {
                                       if (o instanceof MovieMoneyBoxBean) {
                                           mView.addMovieMoneyBox(((MovieMoneyBoxBean) o));
                                       } else if (o instanceof MovieAwardsBean) {
                                           mView.addMovieAwards(((MovieAwardsBean) o).getData());
                                       } else if (o instanceof MovieResourceBean) {
                                           mView.addMovieResource(((MovieResourceBean) o).getData());
                                       }
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       ILog.e("MOVIE","getMovieSecondData----------->:"+throwable);
//                                       mView.showError(ErrorHanding.handleError(throwable));
                                       mView.showError(throwable.getMessage());
                                   }
                               }
                           }
                );
    }

    @Override
    public void getMovieMoreData(Context context, int movieId) {
        if (!isViewAttached()) {
            return;
        }
        mView.showLoading();
        //merge操作只支持9个,所以分开获取
        FlowableObserveOn.merge(
                mModel.getMovieCommentTag(context,movieId),
                mModel.getMovieLongComment(context,movieId),
                mModel.getMovieProComment(context,movieId))
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   if (mView != null) {
                                       if (o instanceof MovieCommentTagBean) {
                                           mView.addMovieCommentTag(((MovieCommentTagBean) o).getData());
                                       } else if (o instanceof MovieProCommentBean) {
                                           mView.addMovieProComment(((MovieProCommentBean) o));
                                       } else if (o instanceof MovieLongCommentBean) {
                                           mView.addMovieLongComment(((MovieLongCommentBean) o).getData());
                                       }
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       ILog.e("MOVIE","getMovieMoreData--------1.--->:"+throwable);
                                       mView.showError(throwable.getMessage());
//                                       mView.showError(ErrorHanding.handleError(throwable));
                                   }
                               }
                           }
                );

        //merge操作只支持9个,所以分开获取
        FlowableObserveOn.merge(
                mModel.getMovieRelatedInformation(context,movieId),
                mModel.getRelatedMovie(context,movieId),
                mModel.getMovieTopic(context,movieId))
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<Object>() {
                               @Override
                               public void accept(Object o) throws Exception {
                                   if (mView != null) {
                                       if (o instanceof MovieRelatedInformationBean) {
                                           mView.addMovieRelatedInformation(((MovieRelatedInformationBean) o).getData().getNewsList());
                                       } else if (o instanceof RelatedMovieBean) {
                                           mView.addRelatedMovie(((RelatedMovieBean) o).getData());
                                       } else if (o instanceof MovieTopicBean) {
                                           mView.addMovieTopic(((MovieTopicBean) o).getData());
                                       }
                                       mView.showContent();
                                   }
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   if (mView != null) {
                                       ILog.e("MOVIE","getMovieMoreData---2.-------->:"+throwable);
                                       mView.showError(throwable.getMessage());
                                   }
                               }
                           }
                );
    }
}
