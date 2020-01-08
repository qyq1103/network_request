package com.qyq1103.network_request;

import com.qyq1103.network_library.base.AbstractNetwork;

import io.reactivex.functions.Function;
import okhttp3.Interceptor;

/**
 * @author：静·灵
 * @date: 2020/1/8 16:45
 * 描述：网络请求
 * 功能：网络请求的封装
 * */
public class HttpRequest extends AbstractNetwork {
    private static HttpRequest httpRequest;

    public static HttpRequest getInstance() {
        if (httpRequest == null) {
            synchronized (HttpRequest.class) {
                if (httpRequest == null) {
                    httpRequest = new HttpRequest();
                }
            }
        }
        return httpRequest;
    }

    @Override
    public <T> T getService(Class<T> service) {
        return getInstance().initRetrofit(service).create(service);
    }

    @Override
    public <T> T getCacheService(Class<T> cacheService) {
        return getInstance().initRxCache(cacheService);
    }

    @Override
    protected Interceptor getInterceptor() {
        return null;
    }

    @Override
    protected <T> Function<T, T> getAppErrorHandle() {
        //不可以返回null，返回null会报错，不处理直接返回对象即可
        return new Function<T, T>() {
            @Override
            public T apply(T t) throws Exception {
                return t;
            }
        };
    }

    @Override
    public String getTestUrl() {
        return Constants.TEST_API;
    }

    @Override
    public String getFormalUrl() {
        return Constants.FORMAL_API;
    }
}
