package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.base.IBaseSubcribePresenter;
import com.cnews.guji.smart.base.ILoadingView;
import com.cnews.guji.smart.common.bean.movie.MovieAwardsBean;
import com.cnews.guji.smart.common.bean.movie.MovieCommentTagBean;
import com.cnews.guji.smart.common.bean.movie.MovieDetailDataBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
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

import java.util.List;

import io.reactivex.Flowable;


/**
 * 电影详情
 * @author dingcl
 */
public interface VideoMovieDetailContract {
    /**
     * 业务数据
     */
    interface model{
        //获取电影资料
        Flowable<MovieDetailDataBean> getMovieBasicData(Context context,int movieId);
        Flowable<MovieTipsBean> getMovieTips(Context context,int movieId);
        //影星列表
        Flowable<MovieStarBean> getMovieStarList(Context context,int movieId);
        //获取票房
        Flowable<MovieMoneyBoxBean> getMovieBox(Context context,int movieId);
        //获取获奖情况
        Flowable<MovieAwardsBean> getMovieAwards(Context context,int movieId);
        //获取电影资料
        Flowable<MovieResourceBean> getMovieResource(Context context,int movieId);
        //评论标签
        Flowable<MovieCommentTagBean> getMovieCommentTag(Context context,int movieId);
        //热门长评
        Flowable<MovieLongCommentBean> getMovieLongComment(Context context,int movieId);
        //获取专业影评
        Flowable<MovieProCommentBean> getMovieProComment(Context context,int movieId);
        //相关资讯
        Flowable<MovieRelatedInformationBean> getMovieRelatedInformation(Context context,int movieId);
        //相关电影
        Flowable<RelatedMovieBean> getRelatedMovie(Context context,int movieId);
        //相关话题
        Flowable<MovieTopicBean> getMovieTopic(Context context,int movieId);

    }

    /**
     * 视图
     */
    interface View extends ILoadingView {
        /**
         * 电影详情
         * @param
         */
        void addMovieBasicData(MovieDetailDataBean.DataBean.MovieBean movie);

        void addMovieTips(MovieTipsBean.DataBean tips);

        void addMovieStarList(MovieStarBean movieStarBean);

        void addMovieMoneyBox(MovieMoneyBoxBean moneyBoxBean);

        void addMovieAwards(List<MovieAwardsBean.DataBean> movieAwards);

        void addMovieResource(List<MovieResourceBean.DataBean> movieResources);

        void addMovieCommentTag(List<MovieCommentTagBean.DataBean> commentTags);

        void addMovieLongComment(MovieLongCommentBean.DataBean movieLongComments);

        void addMovieRelatedInformation(List<MovieRelatedInformationBean.DataBean.NewsListBean> newsListBean);

        void addRelatedMovie(List<RelatedMovieBean.DataBean> relatedMovies);

        void addMovieTopic(MovieTopicBean.DataBean movieTopicBean);

        void addMovieProComment(MovieProCommentBean data);

    }
    abstract class Presenter extends IBaseSubcribePresenter<View> {
        public abstract void getMovieData(Context context, int movieId);

        public abstract void getMovieSecondData(Context context, int movieId);

        public abstract void getMovieMoreData(Context context, int movieId);
    }

}