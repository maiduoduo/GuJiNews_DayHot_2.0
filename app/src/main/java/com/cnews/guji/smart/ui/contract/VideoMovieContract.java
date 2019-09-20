package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBaseSubcribePresenter;
import com.cnews.guji.smart.base.ILoadingView;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
//import rx.Observable;


/**
 * 精选视频
 * @author dingcl
 */
public interface VideoMovieContract {
    /**
     * 业务数据
     */
    interface model{
        Flowable<MovieTypeBean> getMovieTypeList(Context context);
        Flowable<ClassifySearchBean> getClassifySearchList(Context context,int offset, int catId, int sourceId, int yearId, int sortId);
    }

    /**
     * 视图
     */
    interface View extends ILoadingView {
        /**
         * 生活视频
         * @param
         */
        void addMovieType(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMovieNation(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMoviePeriod(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addMoviePoint(List<MovieTypeBean.DataBean.TagListBean> tagList);

        void addClassifySearchData(List<ClassifySearchBean.ListBean> list);

    }
    abstract class Presenter extends IBaseSubcribePresenter<View> {
        public abstract void getMovieTypeList(Context context);
        public abstract void getClassifySearchList(Context context, int offset, int catId, int sourceId, int yearId, int sortId);
    }

}