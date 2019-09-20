package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.HomeCommentBean;

import io.reactivex.Flowable;

/**
 * 新闻评论
 * @author JSYL-DCL
 */
public interface HomeCommentContract {
    interface Model {
        Flowable<HomeCommentBean> getHotComment(Context context, String documentid);
        Flowable<HomeCommentBean> getNewComment(Context context, String documentid);

        /**
         * 更多评论
         * @param context
         * @param documentid
         * @param page
         * @return
         */
        Flowable<HomeCommentBean> getMoreNewComment(Context context, String documentid,int page);
    }

    interface View extends IBaseView {
        /**
         * 公共新闻评论
         * @param data
         */
        void setHotComment(HomeCommentBean data);

        /**
         * 最新评论
         * @param data
         */
        void setNewComment(HomeCommentBean data);
        void setMoreNewComment(HomeCommentBean data);
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void getHotComment(Context context,String documentid);
        public abstract void getNewComment(Context context,String documentid);
        public abstract void getMoreNewComment(Context context,String documentid,int page);
    }
}
