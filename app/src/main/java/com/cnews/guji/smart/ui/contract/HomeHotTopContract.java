package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.XunshiBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * 头条
 * @author dingcl
 */
public interface HomeHotTopContract {
    interface model{
        HomeTophotIndexBean getHomeHotTopData(Context context,int flag, int currentType);
        Flowable<List<HomeTopIFengBean>> getIFengHomeTopNewsData(Context context, int pullnum,boolean isup,String currentName);
        Flowable<XunshiBean> getXunshiData(Context context, String body);

    }
    interface View extends IBaseView {
        void setHomeHotTopData(HomeTophotIndexBean tophotBean);
        void setIFengHomeTopNewsData(List<HomeTopIFengBean> tophotBean,boolean isup);
        void showEmpty();
        void showXunshiData(XunshiBean data);
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void getHomeHotTopData(Context context, int flag, int videoCurrentType);
        public abstract void getIFengHomeTopNewsData(Context context, int pullnum,boolean isup,String currentName);
        public abstract void getXunshiData(Context context, String body);
    }

}