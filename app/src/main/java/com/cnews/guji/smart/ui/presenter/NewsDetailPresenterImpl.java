package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.model.NewsDetailModel;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 新闻详情数据
 * @author JSYL-DCL
 */
public class NewsDetailPresenterImpl extends NewsDetailContract.Presenter {
    private static final String TAG1 = "NewsDetailPresenterImpl";
    private NewsDetailContract.Model mModel;
    private HashMap<Integer, String> typemap;

    public NewsDetailPresenterImpl() {
        this.mModel = new NewsDetailModel();
    }
    /**
     * 新闻详情
     * @param context
     */
    @Override
    public void deatailItemRequest(Context context,String articleUrl) {
        if (!isViewAttached()){return;}
        mView.showLoading();
        mModel.deatailItemRequest(context,articleUrl)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<NewsDetailBean>() {
                               @Override
                               public void accept(NewsDetailBean newsDetailBean) throws Exception {
                                    mView.detailItemData(newsDetailBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   ILog.e(TAG1,"SHOWFAIL:--------------1.throwable---------->"+throwable);
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );
    }

    @Override
    public void getData(Context context, String documentId) {
        if (documentId == null) {
            mView.showFaild(null,"documentId不存在");
            return;
        }
        mModel.dataRequest(context,documentId)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.<NewsDetailNormalBean>bindToLife())
                .subscribe(new Consumer<NewsDetailNormalBean>() {
                               @Override
                               public void accept(NewsDetailNormalBean newsDetailNormalBean) throws Exception {
                                    mView.setData(newsDetailNormalBean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                                   ILog.e(TAG1,"SHOWFAIL:--------------2.throwable---------->"+throwable);
                               }
                           }
                );
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
                                   ILog.e(TAG1,"SHOWFAIL:--------------3.throwable---------->"+throwable);
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
                                   ILog.e(TAG1,"SHOWFAIL:--------------4.throwable---------->"+throwable);
                               }
                           }
                );
    }

    @Override
    public void getRelationNews(Context context, String documentid, int page, int size, String title) {
        if (!isViewAttached()) {
            return;
        }
        typemap = new HashMap<>(20);
        mModel.getRelationNews(context, documentid, page, size,title)
                .compose(RxSchedulers.Flo_io_main())
                .map(new Function<List<HomeTopIFengBean>, HomeTopIFengBean>() {
                    @Override
                    public HomeTopIFengBean apply(List<HomeTopIFengBean> bean) throws Exception {
                        if (bean.size() > 0) {
                            for (int i = 0; i < bean.size(); i++) {
                                typemap.put(i, bean.get(i).type);
                            }
                        } else {
                            mView.showEmpty();
                        }
                        return bean.get(0);
                    }
                })
//                .as(mView.bindAutoDispose())
//                .compose(mView.<NewsResponse>bindToLife())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<HomeTopIFengBean>() {
                               @Override
                               public void accept(HomeTopIFengBean homeTopIFengBean) throws Exception {
                                   //ListNews数据
                                   if (homeTopIFengBean != null) {
                                       if (NewsSource.isListNews(homeTopIFengBean)) {
                                           mView.setRelationNews(homeTopIFengBean);
                                       } else {
                                           mView.setRelationNews(null);
                                       }
                                   }else {
                                       mView.setRelationNews(null);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFaild(throwable,throwable.getMessage());
                            }
                        });
    }

    /**
     * 更多相关
     * @param context
     * @param documentid
     * @param page 第几页
     * @param size 一次加载条目
     * @param title 详情新闻的标题
     */
    @Override
    public void getMoreRelationNews(Context context, String documentid, int page, int size, String title) {
        if (!isViewAttached()) {
            return;
        }
        typemap = new HashMap<>(20);
        mModel.getRelationNews(context, documentid, page, size,title)
                .compose(RxSchedulers.Flo_io_main())
                .map(new Function<List<HomeTopIFengBean>, HomeTopIFengBean>() {
                    @Override
                    public HomeTopIFengBean apply(List<HomeTopIFengBean> bean) throws Exception {
                        if (bean.size() > 0) {
                            for (int i = 0; i < bean.size(); i++) {
                                typemap.put(i, bean.get(i).type);
                            }
                        } else {
                            mView.showEmpty();
                        }
                        return bean.get(0);
                    }
                })
//                .as(mView.bindAutoDispose())
//                .compose(mView.<NewsResponse>bindToLife())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<HomeTopIFengBean>() {
                               @Override
                               public void accept(HomeTopIFengBean homeTopIFengBean) throws Exception {
                                   //ListNews数据
                                   if (homeTopIFengBean != null) {
                                       if (NewsSource.isListNews(homeTopIFengBean)) {
                                           mView.setMoreRelationNews(homeTopIFengBean);
                                       } else {
                                           mView.setMoreRelationNews(null);
                                       }
                                   }else {
                                       mView.setMoreRelationNews(null);
                                   }
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFaild(throwable,throwable.getMessage());
                                ILog.e(TAG1,"SHOWFAIL:--------------5.throwable---------->"+throwable);
                            }
                        });
    }

}
