package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeCommentBean;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.basebean.HomeTophotIndexBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;

/**
 * @package: HomeHotTopModel
 * @author： JSYL-DCL
 * @date: 2019/4/12
 * @describe： 热门数据处理
 * @email： 11442865
 */
public class HomeNormalPublicModel<S> implements HomeNormalPublicContract.model {

    /**
     * 公共刷新数据
     * @param context
     * @param pullnum
     * @return
     *  fenghuang.news
     */
    @Override
    public Flowable<List<HomeTopIFengBean>> getRefreshData(Context context, final String action,int pullnum, String currentType) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getTopnewsHomeDefault(
                        NewsSource.getHomeNListIDSource(currentType),
                        action,
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

    /**
     * 公共加载更多数据
     * @param context
     * @param pullnum
     * @param currentType
     * @return
     *  fenghuang.news
     */
    @Override
    public Flowable<List<HomeTopIFengBean>> getLoadMoreData(Context context, int pullnum, String currentType) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getTopnewsHomeDefault(
                        NewsSource.getHomeNListIDSource(currentType == null ? "" : currentType),
                        ApiConstant.HOME_HOST_PARAM.HOME_HOST_PARAM_ACTION_UP,
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
