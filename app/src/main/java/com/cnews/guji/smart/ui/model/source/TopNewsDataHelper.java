package com.cnews.guji.smart.ui.model.source;

import android.content.Context;

import com.cnews.guji.smart.R;
import com.cnews.guji.smart.common.bean.CategoryModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @package: TopNewsDataHelper
 * @author： JSYL-DCL
 * @describe： TODO
 * @email： 11442865
 */
public class TopNewsDataHelper {

    /**
     * banner
     * @param
     * @return
     */
    public static List<Integer> getBannerData() {
        List<Integer> bannerList = new ArrayList<>();
        bannerList.add(R.drawable.home_banner_2);
        bannerList.add(R.drawable.home_banner_1);
        bannerList.add(R.drawable.home_banner_3);
        bannerList.add(R.drawable.home_banner_4);
        bannerList.add(R.drawable.home_banner_5);
        bannerList.add(R.drawable.home_banner_6);
        bannerList.add(R.drawable.home_banner_7);
        bannerList.add(R.drawable.home_banner_8);
        bannerList.add(R.drawable.home_banner_9);
        bannerList.add(R.drawable.home_banner_10);
        bannerList.add(R.drawable.home_banner_11);
        bannerList.add(R.drawable.home_banner_12);
        return bannerList;
    }

    public static final int[] BANNER_IMAGES = {
            R.drawable.home_banner_1, R.drawable.home_banner_2, R.drawable.home_banner_3,
            R.drawable.home_banner_4, R.drawable.home_banner_5, R.drawable.home_banner_6,
            R.drawable.home_banner_7, R.drawable.home_banner_8, R.drawable.home_banner_9,
            R.drawable.home_banner_10, R.drawable.home_banner_11, R.drawable.home_banner_12
    };
    public static final int[] NEWS_BANNER_IMAGES = {
            R.drawable.home_top_banner_c, R.drawable.home_top_banner_b, R.drawable.home_top_banner_e,
            R.drawable.home_top_banner_d, R.drawable.home_top_banner_a, R.drawable.home_top_banner_i,
            R.drawable.home_top_banner_k, R.drawable.home_top_banner_n
    };
}
