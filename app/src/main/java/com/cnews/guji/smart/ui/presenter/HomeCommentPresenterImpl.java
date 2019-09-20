package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.ui.contract.HomeCommentContract;
import com.cnews.guji.smart.ui.model.HomeCommentlModel;

import io.reactivex.functions.Consumer;

/**
 * 新闻评论
 * @author JSYL-DCL
 */
public class HomeCommentPresenterImpl extends HomeCommentContract.Presenter {
    private HomeCommentContract.Model mModel;

    public HomeCommentPresenterImpl() {
        this.mModel = new HomeCommentlModel();
    }


    /**
     * 热门评论
     * @param context
     * @param documentid
     */
    @Override
    public void getHotComment(Context context, String documentid) {
        if (!isViewAttached()){return;}
        mModel.getHotComment(context, documentid)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<HomeCommentBean>() {
                               @Override
                               public void accept(HomeCommentBean homeCommentBean) throws Exception {
                                   mView.setHotComment(homeCommentBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );

    }

    /**
     * 最新评论
     * @param context
     * @param documentid
     */
    @Override
    public void getNewComment(Context context, String documentid) {
        if (!isViewAttached()){return;}
        mModel.getNewComment(context, documentid)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<HomeCommentBean>() {
                               @Override
                               public void accept(HomeCommentBean homeCommentBean) throws Exception {
                                   mView.setNewComment(homeCommentBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );
    }

    /**
     * 更多评论
     * @param context
     * @param documentid
     */
    @Override
    public void getMoreNewComment(Context context, String documentid,int page) {
        if (!isViewAttached()){return;}
        mModel.getMoreNewComment(context, documentid,page)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<HomeCommentBean>() {
                               @Override
                               public void accept(HomeCommentBean homeCommentBean) throws Exception {
                                   mView.setMoreNewComment(homeCommentBean);
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
