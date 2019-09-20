package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.VideoMovieContract;

import io.reactivex.Flowable;

//import rx.Observable;


/**
 * @package: CarefullyChosenVideoModel
 * @author： JSYL-DCL
 * @describe： 精选视频
 */
public class VideoMovieModel implements VideoMovieContract.model {


    /**
     * 电影类型
     * @param context
     * @return
     */
    @Override
    public Flowable<MovieTypeBean> getMovieTypeList(Context context) {
        return  RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieTypeList();
    }

    /**
     * 电影分类
     * @param context
     * @param offset
     * @param catId
     * @param sourceId
     * @param yearId
     * @param sortId
     * @return
     */
    @Override
    public Flowable<ClassifySearchBean> getClassifySearchList(Context context,int offset,int catId,int sourceId,int yearId,int sortId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getClassifySearchList(10,offset,catId,sourceId,yearId,sortId);
    }


}
