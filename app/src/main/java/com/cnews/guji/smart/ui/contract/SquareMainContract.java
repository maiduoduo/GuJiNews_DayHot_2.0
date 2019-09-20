package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import java.util.List;
import io.reactivex.Flowable;


/**
 * 广场契约类
 * @author dingcl
 */
public interface SquareMainContract {
    /**
     * 业务数据
     */
    interface model{
        Flowable<List<SquareMainBean>> getRefreshData(Context context, String action);
        Flowable<List<SquareMainBean>> getLoadMoreData(Context context, String action);
    }

    /**
     * 视图
     */
    interface View extends IBaseView {
        /**
         * 下拉刷新
         * @param bean
         */
        void setRefreshData(SquareMainBean bean);

        /**
         * 加载更多
         * @param bean
         */
        void setLoadMoreData(List<SquareMainBean> bean);
        void setSquareNavigation(SquareMainBean bean);
        void setSquareHotword(SquareMainBean bean);
//        void setFinanceHNData(HomeTopIFengBean bean);
//        void setTopData(HomeTopIFengBean bean);
        void showEmpty();
        void onFail(Throwable throwable);

    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void getRefreshData(Context context,String action);
        public abstract void getLoadMoreData(Context context, String action);
    }

}