package com.qyq1103.network_library;

/**
 * @author：静·灵
 * @date: 2020/1/8 16:10
 * 描述：常量类
 * 功能:常用的错误码和请求头常量
 */
public class Constants {

    public static final String OS = "Android";

    //============================约定的错误常量===================
    /**
     * 未知错误
     */
    public static final int UNKNOWN = 1000;
    /**
     * 解析错误
     */
    public static final int PARSE_ERROR = 1001;
    /**
     * 网络错误
     */
    public static final int NETWORK_ERROR = 1002;
    /**
     * 协议错误
     */
    public static final int HTTP_ERROR = 1003;
    /**
     * 证书错误
     */
    public static final int SSL_ERROR = 1005;
    /**
     * 连接超时
     */
    public static final int TIMEOUT_ERROR = 1006;
}
