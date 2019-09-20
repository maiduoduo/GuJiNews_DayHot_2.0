package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;

import java.util.List;

import io.reactivex.Flowable;


/**
 * 小视频
 * @author dingcl
 */
public interface SmallVideoContract {
    /**
     * 业务数据
     */
    interface model{
        Flowable<SmallVideoBean> smallVideoData(Context context, String action, int type, int page);
    }

    /**
     * 视图
     */
    interface View extends IBaseView {
        /**
         * 小视频
         * @param bean
         */
        void setSmallVideoData(SmallVideoBean bean);
        void setSmallVideoLoadMoreData(SmallVideoBean bean);
        void showEmpty();

    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void smallVideoData(Context context,String action,int type, int page);
    }

}