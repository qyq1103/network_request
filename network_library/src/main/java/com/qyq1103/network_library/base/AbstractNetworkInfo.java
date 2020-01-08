package com.qyq1103.network_library.base;

import android.app.Application;

import java.io.File;

/**
 * @author：静·灵
 * @date: 2020/1/3 14:53
 * 描述：需要在软件开始初始化的方法都在这里定义
 * 功能: 各个初始化方法
 */
public interface AbstractNetworkInfo {
    /**
     * 判断是否开启debug模式
     *
     * @return true:开启debug模式，false：不开启debug模式
     */
    boolean isDebug();

    /**
     * 初始化创建缓存文件
     *
     * @return File
     */
    File cacheDir();

    Application getApplication();
}
