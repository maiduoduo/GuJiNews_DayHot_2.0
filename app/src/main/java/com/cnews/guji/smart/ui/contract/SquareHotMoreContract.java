package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 广场-更多热点
 * @author JSYL-DCL
 */
public interface SquareHotMoreContract {
    interface Model {
        Flowable<List<SquareHotMoreBean>> hotMore(Context context, String action, int page);
        Flowable<List<SquareHotMoreBean>> hotMoreLoadMore(Context context, String action,int page);
    }

    interface View extends IBaseView {
        void setHotMore(List<SquareHotMoreBean> data);
        void sethotMoreLoadMore(List<SquareHotMoreBean> data);
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void hotMore(Context context, String action,int page);
        public abstract void hotMoreLoadMore(Context context, String action,int page);
    }
}
