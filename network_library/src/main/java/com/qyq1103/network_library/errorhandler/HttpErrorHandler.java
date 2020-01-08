package com.qyq1103.network_library.errorhandler;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * @author：静·灵
 * @date: 2020/1/6 9:23
 * 描述：处理如下两种网络请求错误
 * 功能:1、http请求相关错误，例如：404，403，socket，timeout等；
 * 2、应用数据错误会抛出RunTimeException，通过这个方法统一处理
 */
public class HttpErrorHandler<T> implements Function<Throwable, Observable<T>> {
    @Override
    public Observable<T> apply(Throwable throwable) throws Exception {
        return io.reactivex.Observable.error(ExceptionHandler.handleException(throwable));
    }
}
