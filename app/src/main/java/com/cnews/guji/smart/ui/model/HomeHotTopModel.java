package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.XunshiBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.HomeHotTopContract;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;


/**
 * @package: HomeHotTopModel
 * @author： JSYL-DCL
 * @describe： 头条数据处理
 * @email： 11442865
 */
public class HomeHotTopModel<S> implements HomeHotTopContract.model {
    /**
     * 热门刷新数据
     * @param context
     * @param flag
     * @param videoCurrentType
     * @return
     */
    @Override
    public HomeTophotIndexBean getHomeHotTopData(Context context, int flag, int videoCurrentType) {
        Type type = new TypeToken<HomeTophotIndexBean>() {}.getType();
        return (HomeTophotIndexBean) getMultiIndexJsonData
                (context, flag == 1 ? NewsSource.getHomeRefreshTypeSource(videoCurrentType): NewsSource.getHomeRefreshTypeSource(videoCurrentType), type);
    }

    /**
     * 头条默认数据
     * @param context
     * @param pullnum
     * @return
     *  fenghuang.news
     */
    @Override
    public Flowable<List<HomeTopIFengBean>> getIFengHomeTopNewsData(Context context, int pullnum,boolean isup,String currentName) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getTopnewsHomeDefault(
                        NewsSource.getHomeNListIDSource(currentName == null ? "" : currentName),
                        isup ?ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_UP:ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_DOWN,
                        pullnum,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_GV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_AV,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_UID,
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_DEVICEID,
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
    public Flowable<XunshiBean> getXunshiData(Context context, String body) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_XUNSHI)
                .getXunSHI(body);
    }

    /**
     * 解析json
     */
    public S getMultiIndexJsonData(Context context, final String fileName, Type type) {
        String json = FileUtils.getJson(context, fileName);
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }


    public S getMultiIndexData(Context context,final Class<S> clazz , final String fillName) {
        InputStream is = null;
        S s = null;
        try {
            is = context.getAssets().open(fillName);
            String text = FileUtils.readTextFromFile(is);
            Gson gson = new Gson();
            s = gson.fromJson(text, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;

    }
}
