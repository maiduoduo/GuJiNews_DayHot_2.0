package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.ImageAtlasBean;
import com.cnews.guji.smart.common.bean.SquareHotMoreBean;
import com.cnews.guji.smart.common.net.ApiConstant;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.ImageAtlasContract;
import com.cnews.guji.smart.ui.contract.SquareHotMoreContract;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 广场-更多热点业务处理
 * @author JSYL-DCL
 */
public class SquareHotMoreModel implements SquareHotMoreContract.Model {


    /**
     * 更多热点初始加载
     * @param context
     * @param action default:初始加载  up:加载更多
     * @param page 加载的页数
     * @return
     */
    @Override
    public Flowable<List<SquareHotMoreBean>> hotMore(Context context, String action, int page) {
        return  RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getSquareHotMore(
                        action,
                        page,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_GV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_AV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_UID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DEVICEID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PROID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_OS,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DF,
                        ApiConstant.SQUARE_HOST_PARAM.SQUAREHOST_PARAM_VT,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_SCREEN,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PUBLISHID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_NW
                );
    }


    /**
     * 更多热点加载更多
     * @param context
     * @param action default:初始加载  up:加载更多
     * @param page 加载的页数
     * @return
     */
    @Override
    public Flowable<List<SquareHotMoreBean>> hotMoreLoadMore(Context context, String action, int page) {
        return  RetrofitClient.getInstance(context)
                .getApi(HostType.IFeng_NEWS_TYPE)
                .getSquareHotMore(
                        action,
                        page,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_GV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_AV,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_UID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DEVICEID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PROID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_OS,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_DF,
                        ApiConstant.SQUARE_HOST_PARAM.SQUAREHOST_PARAM_VT,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_SCREEN,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_PUBLISHID,
                        ApiConstant.SQUARE_HOST_PARAM.SQUARE_HOST_PARAM_NW
                );
    }
}
