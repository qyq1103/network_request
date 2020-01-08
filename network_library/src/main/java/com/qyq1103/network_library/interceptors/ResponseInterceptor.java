package com.qyq1103.network_library.interceptors;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author：静·灵
 * @date: 2020/1/3 14:40
 * 描述：返回拦截器
 * 功能:
 */
public class ResponseInterceptor implements Interceptor {
    private static final String TAG = "ResponseInterceptor";

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        long requestTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        Log.d(TAG, "requestTime=" + (System.currentTimeMillis() - requestTime));
        return response;
    }
}
