package com.qyq1103.network_request;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author :Net spirit
 * @date：2020/9/6 21:45
 * @desc:
 */
class ResponseInterceptor implements Interceptor {
  public static final String TAG = "ResponseInterceptor";

  @NotNull
  @Override
  public Response intercept(@NotNull Chain chain) throws IOException {
    Response response = chain.proceed(chain.request());
    String res = response.body().string();
    try {
      JSONObject jsonObject = new JSONObject(res);
      int errorCode = jsonObject.getInt("error_code");
      if (errorCode == 0) {
        Log.e(TAG, "数据拦截成功！！！");
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    return response;
  }
}
