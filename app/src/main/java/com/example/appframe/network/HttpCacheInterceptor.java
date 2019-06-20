package com.example.appframe.network;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.NetworkUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * author: eagle
 * created on: 2019-06-20 16:44
 * description:
 */
public class HttpCacheInterceptor  implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        LogUtils.dTag("URL------->", "/*************************请求API******************************/\n" + request.url().toString());
        if (!NetworkUtils.isConnected()) {
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .build();
        }

        Response response = chain.proceed(request);
        ResponseBody body = response.body();
        BufferedSource source = body.source();
        source.request(Long.MAX_VALUE); // Buffer the entire body.
//        Buffer buffer = source.buffer();
//        Charset charset = Charset.defaultCharset();
//        MediaType contentType = body.contentType();
//        if (contentType != null) {
//            charset = contentType.charset(charset);
//        }

        return response.newBuilder().build();
    }
}
