package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.ImageAtlasContract;
import io.reactivex.Flowable;

/**
 * 新闻详情业务处理
 * @author JSYL-DCL
 */
public class ImageAtlasModel implements ImageAtlasContract.Model {

    @Override
    public Flowable<ImageAtlasBean> imageAtlasDetail(Context context, String articleUrl) {
        return  RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_IFeng_NEWS_HOME_COMMENT)
                .getImageAtlasDetail(articleUrl);
    }
}
