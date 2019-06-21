package com.example.appframe.network;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: eagle
 * created on: 2019-06-19 13:46
 * description:
 */
public class Apiinterceptor {

    private final long readTimeout = 30;
    private final long connectTimeout = 30;
    private final String BASE_URL = "www.baidu.com";
    private HttpLoggingInterceptor mLogging;
    private Gson mGson;
    private OkHttpClient okHttpClient;
    //APP版本号
    public String appVersion = AppUtils.getAppVersionName();
    public String devicePlatform = "Android";


    public final String TAG = "Request";

    /**
     * 访问的request
     */
    private Interceptor toKenInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            String token = null;//XX.gettoken;
            Request authorisedHeader = null;
            authorisedHeader = request.newBuilder()
                    .addHeader("PlatformId", "AA-BB-CC")    //添加销售平台
                    .addHeader("AppVersion", AppUtils.getAppVersionName())            //添加版本参数
                    .addHeader("DevicePlatform", "Android")    //添加设备平台
//                    .addHeader("DeviceNo", DeviceNo)    //添加设备号
                    .addHeader("Token", token)
                    .build();

            long startTime = System.currentTimeMillis();
            Response response = chain.proceed(authorisedHeader);
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e(TAG, "----------Request Start----------------");
            Log.e(TAG, "| " + request.toString() + request.headers().toString());
            Log.e(TAG, "| Response:" + content);
            Log.e(TAG, "----------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(ResponseBody.create(mediaType, content))
                    .build();
        }
    };


    public void ApiRetrofit() {
        if (mLogging == null) {
            mLogging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    if (isGoodJson(message)) {
                        LogUtils.json(message);
                    }
                }
            });
            mLogging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }
        OkHttpClient okHttpClient = null;

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
//                .sslSocketFactory(sslSocketFactory, trustManager) //配置证书SSL
                .addInterceptor(toKenInterceptor)
                .retryOnConnectionFailure(false)
                .addInterceptor(mLogging)
                .addNetworkInterceptor(new HttpCacheInterceptor())
                .build();


        if (mGson == null) {
            mGson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
        }
        new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(mGson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build().create(ApiService.class);
    }

    public static boolean isGoodJson(String json) {
        if (TextUtils.isEmpty(json)) {
            return false;
        }
        try {
            new JsonParser().parse(json);
            return true;
        } catch (JsonParseException e) {
            return false;
        }
    }

}
