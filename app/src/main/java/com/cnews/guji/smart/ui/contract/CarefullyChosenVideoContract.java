package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;

import io.reactivex.Flowable;


/**
 * 精选视频
 * @author dingcl
 */
public interface CarefullyChosenVideoContract {
    /**
     * 业务数据
     */
    interface model{
        Flowable<CareChosenVideoBean> careVideoData(Context context, int isHome, String channelCode);
        Flowable<CareChosenVideoBean> careVideoMoreData(Context context,String nextUrl);
    }

    /**
     * 视图
     */
    interface View extends IBaseView {
        /**
         * 精选视频
         * @param bean
         */
        void setCareVideoData(CareChosenVideoBean bean);
        void setCareVideoLoadMoreData(CareChosenVideoBean bean);
        void showEmpty();

    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void careVideoData(Context context,int isHome, String channelCode);
        public abstract void careVideoMoreData(Context context,String nextUrl);
    }

}