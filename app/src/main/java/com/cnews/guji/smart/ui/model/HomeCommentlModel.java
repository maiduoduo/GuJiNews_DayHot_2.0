package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.HomeCommentContract;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;

import io.reactivex.Flowable;

/**
 * 新闻评论业务数据
 * @author JSYL-DCL
 */
public class HomeCommentlModel implements HomeCommentContract.Model {

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
    public Flowable<HomeCommentBean> getMoreNewComment(Context context, String documentid, int page) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_IFeng_NEWS_HOME_COMMENT)
                .getHomeNewComment(documentid,page);
    }

}
