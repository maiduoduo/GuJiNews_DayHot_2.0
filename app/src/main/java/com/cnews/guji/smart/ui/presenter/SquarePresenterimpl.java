package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.contract.SquareMainContract;
import com.cnews.guji.smart.ui.model.HomeNormalPublicModel;
import com.cnews.guji.smart.ui.model.SquareMainModel;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * 广场业务
 *Square
 * @author JSYL-DCL
 */
public class SquarePresenterimpl<S> extends SquareMainContract.Presenter {
    private SquareMainContract.model model;
    private HashMap<Integer, String> typemap;

    public SquarePresenterimpl() {
        model = new SquareMainModel();
    }

    /**
     * 刷新数据
     *
     * @param context
     * @param action
     *                ifeng数据
     */
    @Override
    public void getRefreshData(Context context, String action) {
        if (!isViewAttached()) {
            return;
        }
        typemap = new HashMap<>(20);
        model.getRefreshData(context, action)
                .compose(RxSchedulers.Flo_io_main())
                .map(new Function<List<SquareMainBean>, SquareMainBean>() {
                    @Override
                    public SquareMainBean apply(List<SquareMainBean> bean) throws Exception {
                        if (bean.size() > 0) {
                            for (int i = 0; i < bean.size(); i++) {
                                typemap.put(i, bean.get(i).getType());
                            }
                            //导航菜单
                            if (NewsSource.isExitSquareNavigation(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_SQUARE_NAVIGATION.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setSquareNavigation(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setSquareNavigation(null);
                            }
                            //热议
                            if (NewsSource.isExitSquareHotword(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_SQUARE_HOTWORD.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setSquareHotword(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setSquareHotword(null);
                            }

                            //常规列表
                            if (NewsSource.isExitSquareMapList(typemap)) {
                                for (Map.Entry<Integer, String> amap : typemap.entrySet()) {
                                    if (NewsSource.NAME_TYPE_SQUARE_LIST.equals(amap.getValue())) {
                                        Integer key = amap.getKey();
                                        mView.setRefreshData(bean.get(key));
                                    }
                                }
                            } else {
                                mView.setRefreshData(null);
                            }
                        } else {
                            mView.showEmpty();
                        }
                        return bean.get(0);
                    }
                })
                .compose(mView.bindToLife())
                .subscribe(new Consumer<SquareMainBean>() {
                               @Override
                               public void accept(SquareMainBean bean) throws Exception {
//                                   //ListNews数据
                               }
                           },
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                mView.showFaild(throwable,throwable.getMessage());
                                mView.onFail(throwable);
                            }
                        });

    }

    @Override
    public void getLoadMoreData(Context context, String action) {
        if (!isViewAttached()) {
            return;
        }
        model.getLoadMoreData(context, action)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<List<SquareMainBean>>() {
                               @Override
                               public void accept(List<SquareMainBean> bean) throws Exception {
                                   mView.setLoadMoreData(bean);
                                   ILog.e("SquareMainFragment","\n\nADAPTER getLoadMoreData accept-------->:\n"+new Gson().toJson(bean));
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                                   ILog.e("SquareMainFragment","\n\nADAPTER getLoadMoreData Throwable-------->:\n"+throwable);
                                   ILog.e("SquareMainFragment","\n\nADAPTER getLoadMoreData Throwable-------->:\n"+throwable.getMessage());
                               }
                           }
                );
    }


}
