package com.qyq1103.network_request;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author：静·灵
 * @date: 2020/1/8 16:50
 * 描述：
 * 功能:
 */
public interface TestApi {
    @GET("app/appClassList")
    Observable<ClassType> getClassList(@Query("agentID") String agentID);
}