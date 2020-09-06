package com.qyq1103.network_library.interceptors;

import com.qyq1103.network_library.Constants;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author：静·灵
 * @date: 2020/1/3 14:15
 * 描述：请求拦截器
 * 功能:添加请求的公共参数(只有时间戳和OS两个参数)
 */
public class RequestInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("os", Constants.OS);
        return chain.proceed(builder.build());
    }
}
