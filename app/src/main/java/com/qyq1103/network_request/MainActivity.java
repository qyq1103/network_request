package com.qyq1103.network_request;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.qyq1103.network_library.base.AbstractNetworkInfo;
import com.qyq1103.network_library.observer.BaseObserver;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //网络请求初始化
        HttpRequest.init(new AbstractNetworkInfo() {
            @Override
            public boolean isDebug() {
                return BuildConfig.DEBUG;
            }

            @Override
            public File cacheDir() {
                return getFilesDir();
            }

            @Override
            public Application getApplication() {
                return MainActivity.this.getApplication();
            }
        });

        //网络请求调用
        HttpRequest.getInstance().getService(TestApi.class).getClassList("9099")
                .compose(HttpRequest.getInstance().applySchedulers(new BaseObserver<ClassType>() {
                    @Override
                    public void onSuccess(ClassType classType) {
                        Log.e("TAG", new Gson().toJson(classType));
                    }

                    @Override
                    public void onFailure(Throwable e) {

                    }
                }));
    }
}
