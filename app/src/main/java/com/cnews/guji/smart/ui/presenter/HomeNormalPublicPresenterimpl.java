package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.common.net.BaseObserver;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.model.HomeNormalPublicModel;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.cnews.guji.smart.ui.model.source.NewsSource.NAME_TYPE_BANNER;

/**
 * 其他门类公共页面数据收发
 * @author JSYL-DCL
 */
public class HomeNormalPublicPresenterimpl<S> extends HomeNormalPublicContract.Presenter {
    private HomeNormalPublicContract.model model;
    private HashMap<Integer, String> typemap;

    public HomeNormalPublicPresenterimpl() {
        model = new HomeNormalPublicModel();
    }

    /**
     * 头条默认数据
     *
     * @param context
     * @param pullnum 加载第几页
     *                ifeng数据
     */
    @Override
    public void getRefreshData(Context context, String action, int pullnum, String currentType) {
        if (!isViewAttached()) {
            return;
        }
        typemap = new HashMap<>(20);
        model.getRefreshData(context, action, pullnum, currentType)
                .compose(RxSchedulers.Flo_io_main())
                .map(new Function<List<HomeTopIFengBean>, HomeTopIFengBean>() {
                    @Override
                    public HomeTopIFengBean apply(List<HomeTopIFengBean> bean) throws Exception {
                        if (bean.size() > 0) {
                            for (int i = 0; i < bean.size(); i++) {
                                typemap.put(i, bean.get(i).type);
                            }
                            //banner数据
                            if (NewsSource.isExitBannerNews(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_BANNER.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setBannerData(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setBannerData(null);
                            }

                            //SECONDNAV数据
                            if (NewsSource.isExitSECONDNAV(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_SECONDNAV.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setSecondNAVData(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setSecondNAVData(null);
                            }

                            //FinanceHN数据
                            if (NewsSource.isFinanceHN(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_FINANCEHN.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setFinanceHNData(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setFinanceHNData(null);
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
                                           mView.setRefreshData(homeTopIFengBean);
                                       } else {
                                           mView.setRefreshData(null);
                                       }
                                   }else {
                                       mView.setRefreshData(null);
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

    @Override
    public void getLoadMoreData(Context context, int pullnum, String currentType) {
        if (!isViewAttached()) {
            return;
        }
        model.getLoadMoreData(context, pullnum, currentType)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<List<HomeTopIFengBean>>() {
                               @Override
                               public void accept(List<HomeTopIFengBean> bean) throws Exception {
                                   mView.setLoadMoreData(bean);
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
