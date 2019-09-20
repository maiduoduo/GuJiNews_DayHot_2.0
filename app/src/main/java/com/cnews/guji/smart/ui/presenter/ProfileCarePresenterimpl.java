package com.cnews.guji.smart.ui.presenter;


import android.content.Context;

import com.cnews.guji.smart.base.baserx.RxSubscriber;
import com.cnews.guji.smart.common.bean.ProfileCareBean;
import com.cnews.guji.smart.ui.contract.MeContract;

/**
 * 个人关注数据收发
 * @author JSYL-DCL
 */
public class ProfileCarePresenterimpl<S> extends MeContract.Presenter {

    @Override
    public void getData(Context context) {
        mView.showLoading();
        mRxManager.add(mModel.getData(context).subscribe(new RxSubscriber<ProfileCareBean>(context, false) {
            @Override
            protected void _onNext(ProfileCareBean profileCareBean) {
                mView.setData(profileCareBean);
                if (profileCareBean != null){
                }
            }

            @Override
            protected void _onError(Throwable e,String message) {
                mView.showFaild(e,message);
            }
        }));
    }

    @Override
    public void getDataWares(Context context) {

    }

    @Override
    public void getDataMoreWares(Context context) {

    }
}
