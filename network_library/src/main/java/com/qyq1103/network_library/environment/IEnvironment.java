package com.qyq1103.network_library.environment;

/**
 * @author：静·灵
 * @date: 2020/1/3 14:09
 * 描述：环境切换
 * 功能:获取正式和测试环境的链接
 */
public interface IEnvironment {
    /**
     * 获取测试环境的链接
     *
     * @return testUrl
     */
    String getTestUrl();

    /**
     * 获取正式环境的链接
     *
     * @return formalUrl
     */
    String getFormalUrl();
}
