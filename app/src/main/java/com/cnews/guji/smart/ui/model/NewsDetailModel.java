package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.common.constant.ApiConstants;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.ApiService;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 新闻详情业务处理
 * @author JSYL-DCL
 */
public class NewsDetailModel implements NewsDetailContract.Model {

    /**
     * 热门评论
     * @param context
     * @return
     */
    @Override
    public Flowable<HomeCommentBean> getHotComment(Context context, String documentid) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_IFeng_NEWS_HOME_COMMENT)
                .getHomeHotComment(documentid,1,"integral",5);
    }

    /**
     * 最新评论
     * @param context
     * @return
     */
    @Override
    public Flowable<HomeCommentBean> getNewComment(Context context,String documentid) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_IFeng_NEWS_HOME_COMMENT)
                .getHomeNewComment(documentid,1);
    }

    @Override
    public Flowable<List<HomeTopIFengBean>> getRelationNews(Context context, String documentid, int page, int size, String title) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .relationNews(
                        documentid,
                        page,
                        size,
                        title,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_GV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_AV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_UID_RELATION,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_DEVICEID_RELATION,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_PROID,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_OS,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_DF,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_VT,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_SCREEN,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_PUBLISHID,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_NW

                );
    }

    /**
     * 更多相关
     * @param context
     * @param documentid
     * @param page
     * @param size
     * @param title
     * @return
     */
    @Override
    public Flowable<List<HomeTopIFengBean>> getMoreRelationNews(Context context, String documentid, int page, int size, String title) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .relationNews(
                        documentid,
                        page,
                        size,
                        title,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_GV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_AV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_UID_RELATION,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_DEVICEID_RELATION,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_PROID,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_OS,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_DF,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_VT,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_SCREEN,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_PUBLISHID,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_NW

                );
    }

    @Override
    public Flowable<NewsDetailBean> deatailItemRequest(Context context, String articleUrl) {
        return  RetrofitClient.getInstance(context)
                .getApi(HostType.NORMAL_163_TYPE)
                .getNewsDetail(articleUrl);
    }

    @Override
    public Flowable<NewsDetailNormalBean> dataRequest(Context context,String documentId) {
        if (documentId != null) {
            if (documentId.startsWith("sub")) {
                return RetrofitClient.getInstance(context)
                        .getApi(HostType.TYPE_IFeng_NEWS_DETAIL)
                        .getNewsDetailWithSub(documentId);
            } else {
                return RetrofitClient.getInstance(context)
                        .getApi(HostType.TYPE_IFeng_NEWS_DETAIL)
                        .getNewsDetailWithCmpp(documentId);
            }
        }else {
            return null;
        }
    }

}
