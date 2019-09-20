package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 新闻详情
 * @author JSYL-DCL
 */
public interface NewsDetailContract {
    interface Model {
        Flowable<HomeCommentBean> getHotComment(Context context, String documentid);
        Flowable<HomeCommentBean> getNewComment(Context context, String documentid);
        Flowable<NewsDetailBean> deatailItemRequest(Context context, String articleUrl);
        Flowable<NewsDetailNormalBean> dataRequest(Context context,String documentId);
        Flowable<List<HomeTopIFengBean>> getRelationNews(Context context, String documentid, int page, int size, String title);
        Flowable<List<HomeTopIFengBean>> getMoreRelationNews(Context context, String documentid, int page, int size, String title);
    }

    interface View extends IBaseView {
        void detailItemData(NewsDetailBean data);
        void setData(NewsDetailNormalBean data);
        /**
         * 公共热门新闻评论
         * @param data
         */
        void setHotComment(HomeCommentBean data);
        void setNewComment(HomeCommentBean data);

        /**
         * 相关新闻
         * @param data
         */
        void setRelationNews(HomeTopIFengBean data);

        /**
         * 更多相关
         * @param data
         */
        void setMoreRelationNews(HomeTopIFengBean data);
        void showEmpty();
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void deatailItemRequest(Context context, String articleUrl);
        public abstract void getData(Context context,String documentId);
        public abstract void getHotComment(Context context,String documentid);
        public abstract void getNewComment(Context context, String documentid);

        /**
         * 相关新闻
         * @param context
         * @param documentid
         * @param page
         * @param size
         * @param title
         */
        public abstract void getRelationNews(Context context, String documentid,int page,int size,String title);

        /**
         * 更多相关
         * @param context
         * @param documentid
         * @param page 第几页
         * @param size 一次加载条目
         * @param title 详情新闻的标题
         */
        public abstract void getMoreRelationNews(Context context, String documentid,int page,int size,String title);
    }
}
