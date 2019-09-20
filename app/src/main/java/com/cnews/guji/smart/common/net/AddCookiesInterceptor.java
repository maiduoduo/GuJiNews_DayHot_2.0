package com.cnews.guji.smart.common.net;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 请求添加cookie
 */
public class AddCookiesInterceptor implements Interceptor {
    private static  final String TAG = "AddCookiesInterceptor";
    private static final String COOKIE_PREF = "tcxt_data";
    private Context mContext;

    public AddCookiesInterceptor(Context context) {
        mContext = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (request.url().toString().contains("nc/article/headline")){
            builder.addHeader("Content-Type", "application/json");
//            builder.addHeader("Accept", "application/json");
        }else if (request.url().toString().contains("clt/jsp/")){
            builder.addHeader("Cookie", "PEAR_PLATFORM=2");
            builder.addHeader("Cookie", "PEAR_UUID=862860033969399");
            builder.addHeader("Cookie", "JSESSIONID=93E89DBD2976ACDEDB6C5B9078D6A9CC");
            builder.addHeader("Cookie", " PV_APP=srv-pv-prod-portal2");
            builder.addHeader("Cookie", " __ads_session=Xglh0ll5TwmIdh+aLAA=");
//            PEAR_PLATFORM=2
//            PEAR_UUID=862860033969399
//            JSESSIONID=93E89DBD2976ACDEDB6C5B9078D6A9CC
//                    PV_APP=srv-pv-prod-portal2
//            __ads_session=
//                    Xglh0ll5TwmIdh+aLAA=
        }
        return chain.proceed(builder.build());
    }

    private String getCookie(String url, String domain) {
        SharedPreferences sp = mContext.getSharedPreferences(COOKIE_PREF, Context.MODE_PRIVATE);
        if (!TextUtils.isEmpty(url) && sp.contains(url) && !TextUtils.isEmpty(sp.getString(url, ""))) {
            return sp.getString(url, "");
        }
        if (!TextUtils.isEmpty(domain) && sp.contains(domain) && !TextUtils.isEmpty(sp.getString(domain, ""))) {
            return sp.getString(domain, "");
        }
        return null;
    }
}
