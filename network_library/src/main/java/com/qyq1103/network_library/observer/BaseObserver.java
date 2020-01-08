package com.qyq1103.network_library.observer;

import io.reactivex.Observer;
import io.reactivex.observers.DisposableObserver;

/**
 * @author：静·灵
 * @date: 2020/1/3 11:57
 * 描述：Observer封装，简化使用两个方法，请求成功和失败
 * 功能:请求成功和失败两个方法的实现
 */
public abstract class BaseObserver<T> extends DisposableObserver<T> implements Observer<T> {
    @Override
    public void onNext(T t) {
        onSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e);
    }

    @Override
    public void onComplete() {

    }

    public abstract void onSuccess(T t);

    public abstract void onFailure(Throwable e);
}
