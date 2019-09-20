package com.cnews.guji.smart.ui.presenter;

import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSchedulers;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.NewsDetailBean;
import com.cnews.guji.smart.common.bean.NewsDetailNormalBean;
import com.cnews.guji.smart.ui.contract.ImageAtlasContract;
import com.cnews.guji.smart.ui.contract.NewsDetailContract;
import com.cnews.guji.smart.ui.model.ImageAtlasModel;
import com.cnews.guji.smart.ui.model.NewsDetailModel;

import io.reactivex.functions.Consumer;

/**
 * 图集
 * @author JSYL-DCL
 */
public class ImageAtlasPresenterImpl extends ImageAtlasContract.Presenter {
    private ImageAtlasContract.Model mModel;

    public ImageAtlasPresenterImpl() {
        this.mModel = new ImageAtlasModel();
    }
    /**
     * 新闻详情
     * @param context
     */
    @Override
    public void imageAtlasDetail(Context context,String articleUrl) {
        if (!isViewAttached()){return;}
        mView.showLoading();
        mModel.imageAtlasDetail(context,articleUrl)
                .compose(RxSchedulers.Flo_io_main())
                .as(mView.bindAutoDispose())
                .subscribe(new Consumer<ImageAtlasBean>() {
                               @Override
                               public void accept(ImageAtlasBean bean) throws Exception {
                                    mView.setImageAtlasDetail(bean);
                               }
                           }, new Consumer<Throwable>() {
                               @Override
                               public void accept(Throwable throwable) throws Exception {
                                   mView.showFaild(throwable,throwable.getMessage());
                               }
                           }
                );
    }

}
