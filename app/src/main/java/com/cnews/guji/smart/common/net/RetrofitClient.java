package com.cnews.guji.smart.common.net;


import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
//import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.cnews.guji.smart.api.HostType.TYPE_LIVIDEO;

/**
 * @package: RetrofitClient
 * @author： JSYL-DCL
 * @describe： 网络请求框架Retrofit的封装工具类
 * @email： 11442865
 */
public class RetrofitClient {

    private static volatile RetrofitClient instance;
    private ApiService apiService;
    private static Context _context;
//    https://suggest.taobao.com/sug?code=utf-8&q=裤子
//    https://ditu.amap.com/service/regeo?longitude=121.04925573429551&latitude=31.315590522490712
//    private String baseUrl = "https://ditu.amap.com/";
    private String baseUrl = "https://suggest.taobao.com/";

    private RetrofitClient() {
    }

    public static RetrofitClient getInstance(Context context) {
        _context = context;
        if (instance == null) {
            synchronized (RetrofitClient.class) {
                if (instance == null) {
                    instance = new RetrofitClient();
                }
            }
        }
        return instance;
    }

    /**
     * 设置Header
     *
     * @return
     */
    Request.Builder requestBuilder;
    private Interceptor getHeaderInterceptor(int hostType) {
        return new Interceptor() {
            @Override
            public Response intercept(@NonNull Chain chain) throws IOException {
                Request original = chain.request();
                if (TYPE_LIVIDEO == hostType) {
                    requestBuilder = original.newBuilder()
                            //添加Token
                            .header("X-Channel-Code", "official")
                            .header("X-Client-Agent", "")//手机品牌
                            .header("X-Client-Hash", "566335a3b7cf696b3cc2e27cd27d1d12")//长度为32的小写字母和数字混合字符串
                            .header("X-Client-ID", "")//长度为的15数字字符串
                            .header("X-Location", "118.920618%2C32.083896%7C%E4%B8%AD%E5%9B%BD%2C%E6%B1%9F%E8%8B%8F%E7%9C%81%2C%E5%8D%97%E4%BA%AC%E5%B8%82%2C%E6%A0%96%E9%9C%9E%E5%8C%BA")
                            .header("X-Long-Token", "")
                            .header("X-Platform-Type", "2")
                            .header("X-Platform-Version", "8.0.0")
                            .header("X-Serial-Num", "1561968020")//当前时间 Unix 时间戳
                            .header("X-Client-Agent", "");
                }else {
                    requestBuilder = original.newBuilder()
                            //添加Token
                            .header("token", "");
                }
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        };

    }

    /**
     * 设置拦截器
     * @return
     */
    private Interceptor getInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        //显示日志
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    public ApiService getApi(int hostType) {
        //忽略证书，证书未过审的问题
        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        //初始化一个client,不然retrofit会自己默认添加一个
        OkHttpClient client = new OkHttpClient().newBuilder()
                //设置Header
                .addInterceptor(getHeaderInterceptor(hostType))
                //设置拦截器
                .addInterceptor(getInterceptor())
                .connectTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .readTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .writeTimeout(BaseUrl.HTTP_TIME, TimeUnit.MILLISECONDS)
                .addInterceptor(new AddCookiesInterceptor(_context))
                .addInterceptor(new SaveCookiesInterceptor(_context))
                .sslSocketFactory(sc.getSocketFactory())
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();

//        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                //设置网络请求的Url地址
                .baseUrl(ApiConstant.configHost(hostType))
                //设置数据解析器,添加gson转换器
                .addConverterFactory(GsonConverterFactory.create())
                //添加rxjava转换器,设置网络请求适配器，使其支持RxJava与RxAndroid
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        //创建—— 网络请求接口—— 实例
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }


}
