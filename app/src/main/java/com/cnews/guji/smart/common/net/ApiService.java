package com.cnews.guji.smart.common.net;

import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.ClassifySearchBean;
import com.cnews.guji.smart.common.bean.ClothesBean;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.MovieTypeBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.common.bean.NewsMainBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.common.bean.XunshiBean;
import com.cnews.guji.smart.common.bean.movie.AwardsListBean;
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
import com.cnews.guji.smart.common.bean.movie.VideoListBean;

import java.util.List;

import io.reactivex.Flowable;
//import rx.Observable;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * @package: ApiService
 * @author： JSYL-DCL
 * @describe： 网络请求地址配置
 * @email： 11442865
 */
public interface ApiService {
    //https://suggest.taobao.com/sug?code=utf-8&q=裤子
    //https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712

    /**
     * 获取淘宝服装样式
     * @param code
     * @param q
     * @return
     */
    @GET("sug")
    Flowable<ClothesBean> getClothes(@Query("code") String code, @Query("q") String q);



    /**
     * 头条
     * @param id
     * @param start
     * @param end
     * @return
     */
    //http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
    @GET("nc/article/headline/{id}/{start}-{end}.html")
    Flowable<NewsMainBean> getHeaderNews(@Path("id") String id,
                                         @Path("start") int start,
                                         @Path("end") int end);

    /**
     * 新闻详情
     * @param articleUrl
     *          @Url 它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
     *           baseUrl 需要符合标准，为空、""、或不合法将会报错
     * @return
     */
    @GET
    Flowable<NewsDetailBean> getNewsDetail(@Url String articleUrl);

    /**
     * 图集
     * @param articleUrl
     * @return
     */
    @GET
    Flowable<ImageAtlasBean> getImageAtlasDetail(@Url String articleUrl);

    //-------------------------------1.5.8.4.19----------------------------

    /**
     * 咕唧界面常规数据
     * @param id
     * @param action
     * @param pullNum
     * @param gv
     * @param av
     * @param uid
     * @param deviceid
     * @param proid
     * @param os
     * @param df
     * @param vt
     * @param screen
     * @param publishid
     * @param nw
     * @return
     *
     *     //https://api.iclient.ifeng.com/ClientNews?id=SYLB10,SYDT10&action=down&pullNum=1&gv=6.5.2&av=6.5.2
     *     // &uid=862860033969399&deviceid=862860033969399&proid=ifengnews&os=android_26
     *     // &df=androidphone&vt=5&screen=1080x1794&publishid=2006&nw=wifi
     *
     *   @Url 它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
     *      *           baseUrl 需要符合标准，为空、""、或不合法将会报错
     */
    @GET("ClientNews")
    Flowable<List<HomeTopIFengBean>> getTopnewsHomeDefault(
            @Query("id") String id,
            @Query("action") String action,
            @Query("pullNum") int pullNum,
            @Query("gv") String gv,
            @Query("av") String av,
            @Query("uid") String uid,
            @Query("deviceid") String deviceid,
            @Query("proid") String proid,
            @Query("os") String os,
            @Query("df") String df,
            @Query("vt") int vt,
            @Query("screen") String screen,
            @Query("publishid") String publishid,
            @Query("nw") String nw
    );

    @GET("api_vampire_article_detail")
    Flowable<NewsDetailNormalBean> getNewsDetailWithSub(@Query("aid") String aid);

    @GET("ipadtestdoc")
    Flowable<NewsDetailNormalBean> getNewsDetailWithCmpp(@Query("aid") String aid);

    /**
     * Home热门评论
     * @param doc_url documentId=ucms_7mixaVwwlBg
     * @param p
     * @param orderby
     * @param pagesize
     * @return
     *  热门： doc_url=ucms_7miOBQE9AiO&p=1&orderby=integral&pagesize=5
     *  最新： doc_url=ucms_7miOBQE9AiO&p=1
     */
    @GET("v3/get/comments")
    Flowable<HomeCommentBean> getHomeHotComment(
            @Query("doc_url") String doc_url,
            @Query("p") int p,
            @Query("orderby") String orderby,
            @Query("pagesize") int pagesize
    );

    /**
     * Home热门评论
     * @param doc_url documentId=ucms_7mixaVwwlBg
     * @param p
     * @return
     *  热门： doc_url=ucms_7miOBQE9AiO&p=1&orderby=integral&pagesize=5
     *  最新： doc_url=ucms_7miOBQE9AiO&p=1
     */
    @GET("v3/get/comments")
    Flowable<HomeCommentBean> getHomeNewComment(
            @Query("doc_url") String doc_url,
            @Query("p") int p
    );


    /**
     * 查询相关新闻
     * @param id
     * @param page
     * @param size
     * @param title
     * @param gv
     * @param av
     * @param uid
     * @param deviceid
     * @param proid
     * @param os
     * @param df
     * @param vt
     * @param screen
     * @param publishid
     * @param nw
     * @return
     *
     *  //https://api.iclient.ifeng.com/NewRelatedDocs?id=ucms_7nefEwoFtYW&page=1&size=10&title=这位开国上将虽然出身贫寒，却娶了超级大富豪的女儿当老婆
     *     // &gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&vt=5&os=android_26
     *     // &df=androidphone&vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=
     */
    @GET("NewRelatedDocs")
    Flowable<List<HomeTopIFengBean>> relationNews(
            @Query("id") String id,
            @Query("page") int page,
            @Query("size") int size,
            @Query("title") String title,
            @Query("gv") String gv,
            @Query("av") String av,
            @Query("uid") String uid,
            @Query("deviceid") String deviceid,
            @Query("proid") String proid,
            @Query("os") String os,
            @Query("df") String df,
            @Query("vt") int vt,
            @Query("screen") String screen,
            @Query("publishid") String publishid,
            @Query("nw") String nw
    );

    @FormUrlEncoded
    @POST("patrolAction/queryPatrolPlan")
    Flowable<XunshiBean> getXunSHI(@Field("id") String id);
//    Flowable<XunshiBean> getXunSHI(@Body RequestBody body);
//    Flowable<XunshiBean> getXunSHI(@Query("id") String body);


    /**
     * 广场
     * @param action
     * @param gv
     * @param av
     * @param uid
     * @param deviceid
     * @param proid
     * @param os
     * @param df
     * @param vt
     * @param screen
     * @param publishid
     * @return
     *
     *     https://api.iclient.ifeng.com/news_square?
     *     action=default&gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12
     *     &proid=ifengnews&os=android_26&df=androidphone&vt=5&screen=1080x1794&publishid=2899
     *
     *   @Url 它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
     *      *           baseUrl 需要符合标准，为空、""、或不合法将会报错
     */
    @GET("news_square")
    Flowable<List<SquareMainBean>> getSquareDefault(
            @Query("action") String action,
            @Query("gv") String gv,
            @Query("av") String av,
            @Query("uid") String uid,
            @Query("deviceid") String deviceid,
            @Query("proid") String proid,
            @Query("os") String os,
            @Query("df") String df,
            @Query("vt") int vt,
            @Query("screen") String screen,
            @Query("publishid") String publishid
    );



    /**
     *  更多热点
     * @param action
     * @param page
     * @param gv
     * @param av
     * @param uid
     * @param deviceid
     * @param proid
     * @param os
     * @param df
     * @param vt
     * @param screen
     * @param publishid
     * @param nw
     * @return
     *
     * https://api.iclient.ifeng.com/hotSpotPolyList?action=default&page=1&gv=6.5.5&av=6.5.5
     * &uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&os=android_26&df=androidphone
     * &vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=
     */
    @GET("hotSpotPolyList")
    Flowable<List<SquareHotMoreBean>> getSquareHotMore(
            @Query("action") String action,
            @Query("page") int page,
            @Query("gv") String gv,
            @Query("av") String av,
            @Query("uid") String uid,
            @Query("deviceid") String deviceid,
            @Query("proid") String proid,
            @Query("os") String os,
            @Query("df") String df,
            @Query("vt") int vt,
            @Query("screen") String screen,
            @Query("publishid") String publishid,
            @Query("nw") String nw
    );


    /**
     * 更多热点-item详情
     * @param eventName
     * @param page
     * @param gv
     * @param av
     * @param uid
     * @param deviceid
     * @param proid
     * @param os
     * @param df
     * @param vt
     * @param screen
     * @param publishid
     * @param nw
     * @return
     *
     *   https://api.iclient.ifeng.com/hotSpotDetailList?eventName=华泰汽车三大基地全面停产&page=1
     *  &gv=6.5.5&av=6.5.5&uid=6538247eb412db12&deviceid=6538247eb412db12&proid=ifengnews&os=android_26
     *  &df=androidphone&vt=5&screen=1080x1794&publishid=2899&nw=wifi&loginid=
     */
    @GET("hotSpotDetailList")
    Flowable<SquareHotMoreBean> getSquareHotItemDetail( @Query("eventName") String eventName,
                                                        @Query("page") int page,
                                                        @Query("gv") String gv,
                                                        @Query("av") String av,
                                                        @Query("uid") String uid,
                                                        @Query("deviceid") String deviceid,
                                                        @Query("proid") String proid,
                                                        @Query("os") String os,
                                                        @Query("df") String df,
                                                        @Query("vt") int vt,
                                                        @Query("screen") String screen,
                                                        @Query("publishid") String publishid,
                                                        @Query("nw") String nw
    );


    /**
     * 小视频
     * @param type  1：图/视频
     *              2：段子/神评
     *              3：图
     *              4：视频
     *              5：图/文/视频
     * @param page
     * @return
     *
     *  //https://www.apiopen.top/satinApi?type=1&page=30
     */
    @GET("satinApi")
    Flowable<SmallVideoBean> getSVideo(
                                        @Query("type") int type,
                                        @Query("page") int page
    );


    /**
     * 精选视频
     * @param isHome
     * @param channelCode
     * @return
     *
     * source：LIVideo
     * //http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=320100
     * //http://app.pearvideo.com/clt/jsp/v2/home.jsp?lastLikeIds=1063871%2C1063985%2C1064069%2C1064123%2C1064078%2C1064186%2C1062372%2C1064164%2C1064081%2C1064176%2C1064070%2C1064019
     */
    @GET("clt/jsp/v4/home.jsp")
    Flowable<CareChosenVideoBean> getCareChosenVideo(@Query("isHome") int isHome,
                                                     @Query("channelCode") String channelCode
    );

    /**
     * 精选视频更多
     * @param url
     * @return
     *
     * //http://app.pearvideo.com/clt/jsp/v4/home.jsp?isHome=1&channelCode=320100&start=10&isHome=1&channelCode=320100
     */
    @GET()
    Flowable<CareChosenVideoBean> getCareChosenVideoMore(
            @Url String url
    );


    /**
     * 找片-类型
     * @return
     */
    @GET("mmdb/search/movie/tag/types.json")
    Flowable<MovieTypeBean> getMovieTypeList();


    /**
     * 分类查找
     * @param limit
     * @param offset
     * @param catId
     * @param sourceId
     * @param yearId
     * @param sortId
     * @return
     */
    @GET("mmdb/search/movie/tag/list.json")
    Flowable<ClassifySearchBean> getClassifySearchList(@Query("limit") int limit,
                                                            @Query("offset") int offset,
                                                            @Query("catId") int catId,
                                                            @Query("sourceId") int sourceId,
                                                            @Query("yearId") int yearId,
                                                            @Query("sortId") int sortId);


    /**
     * 奖项列表
     * @return
     */
    @GET("mmdb/movie/region/festival/list.json")
    Flowable<AwardsListBean> getAwardsList();


    /**
     * 电影基本信息
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/v5/{movieId}.json")
    Flowable<MovieDetailDataBean> getMovieBasicData(@Path("movieId") int movieId);


    /**
     * 观影贴士
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/tips/{movieId}/list.json")
    Flowable<MovieTipsBean> getMovieTipsBean(@Path("movieId") int movieId);


    /**
     * 演员列表
     * @param movieId
     * @return
     */
    @GET("mmdb/v7/movie/{movieId}/celebrities.json")
    Flowable<MovieStarBean> getMovieStarList(@Path("movieId") int movieId);


    /**
     * 票房
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/v1/mbox.json")
    Flowable<MovieMoneyBoxBean> getMovieBox(@Path("movieId") int movieId);

    //获奖
    @GET("mmdb/movie/{movieId}/feature/awards.json")
    Flowable<MovieAwardsBean> getMovieAwards(@Path("movieId") int movieId);


    /**
     * 影片资料
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/v2/list.json")
    Flowable<MovieResourceBean> getMovieResource(@Path("movieId") int movieId);


    /**
     * 短评标签
     * @param movieId
     * @param ci
     * @return
     *
     * //请求写死了ci=20(广州),会做修改,应该根据cityId
     */
    @GET("mmdb/comment/tag/movie/{movieId}.json")
    Flowable<MovieCommentTagBean> getMovieCommentTag(@Path("movieId") int movieId,
                                                          @Query("ci") int ci);


    /**
     * 热门长评
     * @param movieId
     * @return
     */
    @GET("sns/movie/{movieId}/filmReview/top.json")
    Flowable<MovieLongCommentBean> getMovieLongComment(@Path("movieId") int movieId);


    /**
     * 专业评论
     * @param movieId
     * @param offset
     * @param limit
     * @return
     */
    @GET("mmdb/comments/pro/movie/{movieId}.json")
    Flowable<MovieProCommentBean> getMovieProComment(@Path("movieId") int movieId,
                                                          @Query("offset") int offset,
                                                          @Query("limit") int limit);


    /**
     * 相关资讯
     * @param movieId
     * @return
     */
    @GET("sns/news/v3/type/0/target/{movieId}/top.json")
    Flowable<MovieRelatedInformationBean> getMovieRelatedInformation(@Path("movieId") int movieId);


    /**
     * 相关电影
     * @param movieId
     * @return
     */
    @GET("mmdb/movie/{movieId}/feature/relatedFilm.json")
    Flowable<RelatedMovieBean> getRelatedMovie(@Path("movieId") int movieId);


    /**
     * 相关话题
     * @param movieId
     * @return
     */
    @GET("sns/0/{movieId}/v2/hotTopics.json")
    Flowable<MovieTopicBean> getMovieTopic(@Path("movieId") int movieId);


    /**
     * 视频预告片
     * @param movieId
     * @param limit
     * @param offset
     * @return
     */
    @GET("mmdb/v1/movie/{movieId}/videos.json")
    Flowable<VideoListBean> getVideoList(@Path("movieId") int movieId,
                                         @Query("limit") int limit,
                                         @Query("offset") int offset);

}
