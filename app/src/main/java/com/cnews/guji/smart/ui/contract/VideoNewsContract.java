package com.cnews.guji.smart.ui.contract;
import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.VideoNewsBean;


/**
 * 视频业务
 * @author dingcl
 */
public interface VideoNewsContract {
    interface Model{
        VideoNewsBean getVideoData(Context context,int flag, int videoCurrentType);
        VideoNewsBean getVideoWares(Context context,int videoCurrentType);
        VideoNewsBean getVideoMoreWares(Context context,int videoCurrentType);
    }
    interface View extends IBaseView {
        void setVideoData(VideoNewsBean data);
        void setVideoWares(VideoNewsBean data);
        void setVideoMoreWares(VideoNewsBean data);
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void getVideoData(Context context,int flag, int videoCurrentType);
        public abstract void getVideoWares(Context context,int videoCurrentType);
        public abstract void getVideoMoreWares(Context context, int videoCurrentType);
    }

}