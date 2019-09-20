package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
import com.cnews.guji.smart.common.bean.movie.MovieAwardsBean;
import com.cnews.guji.smart.common.bean.movie.MovieCommentTagBean;
import com.cnews.guji.smart.common.bean.movie.MovieDetailDataBean;
import com.cnews.guji.smart.common.bean.movie.MovieLongCommentBean;
import com.cnews.guji.smart.common.bean.movie.MovieMoneyBoxBean;
import com.cnews.guji.smart.common.bean.movie.MovieProCommentBean;
import com.cnews.guji.smart.common.bean.movie.MovieRelatedInformationBean;
import com.cnews.guji.smart.common.bean.movie.MovieResourceBean;
import com.cnews.guji.smart.common.bean.movie.MovieStarBean;
import com.cnews.guji.smart.common.bean.movie.MovieTipsBean;
import com.cnews.guji.smart.common.bean.movie.MovieTopicBean;
import com.cnews.guji.smart.common.bean.movie.RelatedMovieBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.VideoMovieDetailContract;

import io.reactivex.Flowable;
import rx.Observable;


/**
 * @package: CarefullyChosenVideoModel
 * @author： JSYL-DCL
 * @describe： 电影详情
 */
public class VideoMovieDetailModel implements VideoMovieDetailContract.model {

    /**
     * 获取电影资料
     * @param movieId
     * @return
     */
    public Flowable<MovieDetailDataBean> getMovieBasicData(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieBasicData(movieId);
    }

    public Flowable<MovieTipsBean> getMovieTips(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieTipsBean(movieId);
    }

    /**
     * 影星列表
     * @param movieId
     * @return
     */
    public Flowable<MovieStarBean> getMovieStarList(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieStarList(movieId);

    }

    /**
     * 获取票房
     * @param movieId
     * @return
     */
    public Flowable<MovieMoneyBoxBean> getMovieBox(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieBox(movieId);
    }

    /**
     * 获取获奖情况
     * @param movieId
     * @return
     */
    public Flowable<MovieAwardsBean> getMovieAwards(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieAwards(movieId);
    }

    /**
     * 获取电影资料
     * @param movieId
     * @return
     */
    public Flowable<MovieResourceBean> getMovieResource(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieResource(movieId);
    }

    /**
     * 评论标签
     * @param movieId
     * @return
     */
    public Flowable<MovieCommentTagBean> getMovieCommentTag(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                //20为广州,暂时写死
                .getMovieCommentTag(movieId,20);
    }

    /**
     * 热门长评
     * @param movieId
     * @return
     */
    public Flowable<MovieLongCommentBean> getMovieLongComment(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieLongComment(movieId);
    }

    /**
     * 获取专业影评
     * @param movieId
     * @return
     */
    public Flowable<MovieProCommentBean> getMovieProComment(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieProComment(movieId,0,3);
    }

    /**
     * 相关资讯
     * @param movieId
     * @return
     */
    public Flowable<MovieRelatedInformationBean> getMovieRelatedInformation(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieRelatedInformation(movieId);
    }


    /**
     * 相关电影
     * @param movieId
     * @return
     */
    public Flowable<RelatedMovieBean> getRelatedMovie(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getRelatedMovie(movieId);
    }

    /**
     * 相关话题
     * @param movieId
     * @return
     */
    public Flowable<MovieTopicBean> getMovieTopic(Context context,int movieId){
        return RetrofitClient
                .getInstance(context)
                .getApi(HostType.TYPE_CATEYE_URL)
                .getMovieTopic(movieId);
    }

}
