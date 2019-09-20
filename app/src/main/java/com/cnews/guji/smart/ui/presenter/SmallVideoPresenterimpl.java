package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.contract.SmallVideoContract;
import com.cnews.guji.smart.ui.model.HomeNormalPublicModel;
import com.cnews.guji.smart.ui.model.SmallVideoModel;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.ILog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

import static com.cnews.guji.smart.common.net.ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_DOWN;
import static com.cnews.guji.smart.common.net.ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_ACTION_UP;

/**
 * 小视频
 * @author JSYL-DCL
 */
public class SmallVideoPresenterimpl<S> extends SmallVideoContract.Presenter {
    private SmallVideoContract.model model;
    private HashMap<Integer, String> typemap;

    public SmallVideoPresenterimpl() {
        model = new SmallVideoModel();
    }


    @Override
    public void smallVideoData(Context context, String action, int type, int page) {
        if (!isViewAttached()) {
            return;
        }
        model.smallVideoData(context, action, type, page)
                .compose(RxSchedulers.Flo_io_main())
                .compose(mView.bindToLife())
                .subscribe(new Consumer<SmallVideoBean>() {
                               @Override
                               public void accept(SmallVideoBean bean) throws Exception {
                                   if (SQUARE_HOST_PARAM_ACTION_DOWN.equals(action)) {
                                       ILog.e("SmallVideoFragment","------------刷新请求到数据------------");
                                       if (bean != null && bean.getData().size() > 0) {
                                           mView.setSmallVideoData(bean);
                                       }else {
                                           mView.showEmpty();
                                       }
                                   } else if (SQUARE_HOST_PARAM_ACTION_UP.equals(action)) {
                                       ILog.e("SmallVideoFragment","------------加载更多请求到数据------------");
                                       mView.setSmallVideoLoadMoreData(bean);
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
