package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.contract.SmallVideoContract;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;

/**
 * @package: HomeHotTopModel
 * @author： JSYL-DCL
 * @describe： 小视频
 * @email： 11442865
 */
public class SmallVideoModel implements SmallVideoContract.model {


    /**
     * 小视频
     * @param context
     * @param action
     * @param type
     * @param page
     * @return
     */
    @Override
    public Flowable<SmallVideoBean> smallVideoData(Context context, String action, int type, int page) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_OPENAPI)
                .getSVideo(type, page);
    }
}
