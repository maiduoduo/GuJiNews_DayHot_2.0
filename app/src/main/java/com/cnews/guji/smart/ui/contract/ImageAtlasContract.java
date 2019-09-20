package com.cnews.guji.smart.ui.contract;

import android.content.Context;

import com.cnews.guji.smart.base.IBasePresenter;
import com.cnews.guji.smart.base.IBaseView;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;

import io.reactivex.Flowable;

/**
 * 图集
 * @author JSYL-DCL
 */
public interface ImageAtlasContract {
    interface Model {
        Flowable<ImageAtlasBean> imageAtlasDetail(Context context, String articleUrl);
    }

    interface View extends IBaseView {
        void setImageAtlasDetail(ImageAtlasBean data);
    }
    abstract class Presenter extends IBasePresenter<View> {
        public abstract void imageAtlasDetail(Context context, String articleUrl);
    }
}
