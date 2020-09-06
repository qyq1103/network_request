package com.qyq1103.network_request;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author：静·灵
 * @date: 2020/1/8 16:50 描述： 网络请求的接口类 功能: 同域名的接口都写在这里，方便请求
 */
public interface TestApi {
  @GET("index")
  Observable<ClassType> getClassList(@Query("type") String type, @Query("key") String key);
}
