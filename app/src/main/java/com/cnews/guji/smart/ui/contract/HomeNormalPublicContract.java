package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import java.util.List;
import io.reactivex.Flowable;


/**
 * 公共门类新闻板块
 * @author dingcl
 */
public interface HomeNormalPublicContract {
    /**
     * 业务数据
     */
    interface model{
        Flowable<List<HomeTopIFengBean>> getRefreshData(Context context,String action,int pullnum, String currentType);
        Flowable<List<HomeTopIFengBean>> getLoadMoreData(Context context,int pullnum, String currentType);
    }

    /**
     * 视图
     */
    interface View extends IBaseView {
        /**
         * 下拉刷新
         * @param bean
         */
        void setRefreshData(HomeTopIFengBean bean);

        /**
         * 加载更多
         * @param bean
         */
        void setLoadMoreData(List<HomeTopIFengBean> bean);
        void setBannerData(HomeTopIFengBean bean);
        void setSecondNAVData(HomeTopIFengBean bean);
        void setFinanceHNData(HomeTopIFengBean bean);
        void setTopData(HomeTopIFengBean bean);
        void showEmpty();

    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void getRefreshData(Context context,String action,int pullnum, String currentType);
        public abstract void getLoadMoreData(Context context,int pullnum, String currentType);
    }

}