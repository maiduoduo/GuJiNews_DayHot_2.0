package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.HomeTopIFengBean;
import com.cnews.guji.smart.common.bean.SquareMainBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.HomeNormalPublicContract;
import com.cnews.guji.smart.ui.contract.SquareMainContract;
import com.cnews.guji.smart.ui.model.source.NewsSource;
import com.cnews.guji.smart.util.FileUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Flowable;

/**
 * @package: SquareMainModel
 * @author： JSYL-DCL
 * @describe： 广场业务数据
 * @email： 11442865
 */
public class SquareMainModel<S> implements SquareMainContract.model {

    /**
     * 刷新数据
     * @param context
     * @param action 刷新（default）/加载更多(up)
     * @return
     *  fenghuang.news
     */
    @Override
    public Flowable<List<SquareMainBean>> getRefreshData(Context context, final String action) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getSquareDefault(
                        action,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_GV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_AV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_UID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DEVICEID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PROID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_OS,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DF,
                        ApiConstant.SQUARE_HOST_PARAM.SQUAREHOST_PARAM_VT,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_SCREEN,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PUBLISHID
                );
    }

    /**
     * 加载更多数据
     * @param context
     * @param action 刷新（default）/加载更多(up)
     * @return
     *  fenghuang.news
     */
    @Override
    public Flowable<List<SquareMainBean>> getLoadMoreData(Context context,String action) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getSquareDefault(
                        action,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_GV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_AV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_UID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DEVICEID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PROID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_OS,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DF,
                        ApiConstant.SQUARE_HOST_PARAM.SQUAREHOST_PARAM_VT,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_SCREEN,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PUBLISHID
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
