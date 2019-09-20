package com.cnews.guji.smart.ui.model;

import android.content.Context;

import com.cnews.guji.smart.api.HostType;
import com.cnews.guji.smart.common.bean.CareChosenVideoBean;
import com.cnews.guji.smart.common.bean.SmallVideoBean;
import com.cnews.guji.smart.common.net.RetrofitClient;
import com.cnews.guji.smart.ui.contract.CarefullyChosenVideoContract;
import com.cnews.guji.smart.ui.contract.SmallVideoContract;

import io.reactivex.Flowable;

/**
 * @package: CarefullyChosenVideoModel
 * @author： JSYL-DCL
 * @describe： 精选视频
 */
public class CarefullyChosenVideoModel implements CarefullyChosenVideoContract.model {


    /**
     * 精选视频
     * @param context
     * @param isHome
     * @param channelCode
     * @return
     */
    @Override
    public Flowable<CareChosenVideoBean> careVideoData(Context context, int isHome, String channelCode) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_LIVIDEO)
                .getCareChosenVideo(isHome, channelCode);
    }

    /**
     * 加载更多
     * @param context
     * @param nextUrl
     * @return
     */
    @Override
    public Flowable<CareChosenVideoBean> careVideoMoreData(Context context,String nextUrl) {
        return RetrofitClient.getInstance(context)
                .getApi(HostType.TYPE_LIVIDEO)
                .getCareChosenVideoMore(nextUrl);
    }
}
