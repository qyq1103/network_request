package com.qyq1103.network_library.base;

import com.qyq1103.network_library.environment.EnvironmentActivity;
import com.qyq1103.network_library.environment.IEnvironment;
import com.qyq1103.network_library.errorhandler.HttpErrorHandler;
import com.qyq1103.network_library.interceptors.RequestInterceptor;
import com.qyq1103.network_library.interceptors.ResponseInterceptor;

import java.io.File;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author：静·灵
 * @date: 2020/1/8 16:44
 * 描述：
 * 功能:
 */
public abstract class AbstractNetwork implements IEnvironment {
    private static AbstractNetworkInfo netWorkInfo;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();
    private static HashMap<String, Object> rxCaHashMap = new HashMap<>();
    private String baseUrl;
    private OkHttpClient okHttpClient;
    /**
     * 判断是使用正式站还是测试站的链接
     */
    private static boolean mIsFormal = true;
    private static File cacheDir;

    public AbstractNetwork() {
        if (!mIsFormal) {
            baseUrl = getTestUrl();
        } else {
            baseUrl = getFormalUrl();
        }
    }

    /**
     * 初始化接口，获取是否是debug模式
     *
     * @param abstractNetWorkInfo AbstractNetWorkInfo
     */
    public static void init(AbstractNetworkInfo abstractNetWorkInfo) {
        netWorkInfo = abstractNetWorkInfo;
        mIsFormal = EnvironmentActivity.isOfficialEnvironment(abstractNetWorkInfo.getApplication());
        cacheDir = abstractNetWorkInfo.cacheDir();
    }

    /**
     * 初始化缓存接口对象
     *
     * @param service 缓存接口类
     * @return service
     */
    protected <T> T initRxCache(Class service) {
        if (rxCaHashMap.get(service.getName() + baseUrl) != null) {
            return (T) rxCaHashMap.get(service.getName() + baseUrl);
        }
        //建造者模式创建和初始化RxCache对象
        RxCache.Builder cacheBuilder = new RxCache.Builder();
        cacheBuilder.setMaxMBPersistenceCache(50);
        rxCaHashMap.put(service.getName() + baseUrl, cacheBuilder.persistence(cacheDir, new GsonSpeaker()).using(service));
        return (T) cacheBuilder.persistence(cacheDir, new GsonSpeaker()).using(service);
    }

    /**
     * 初始化Retrofit
     *
     * @return retrofit
     */
    protected Retrofit initRetrofit(Class service) {
        //判断这个接口的retrofit对象是否已经存在
        if (retrofitHashMap.get(service.getName() + baseUrl) != null) {
            //存在则返回Retrofit对象
            return retrofitHashMap.get(service.getName() + baseUrl);
        }
        //建造者模式创建和初始化Retrofit对象
        Retrofit.Builder builder = new Retrofit.Builder();
        //请求的域名，算是接口前部分吧
        builder.baseUrl(baseUrl);
        builder.client(getOkHttpClient());
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = builder.build();
        retrofitHashMap.put(service.getName() + baseUrl, retrofit);
        return retrofit;
    }

    /**
     * OkHttp连接设置
     *
     * @return okHttpClient
     */
    private OkHttpClient getOkHttpClient() {
        if (okHttpClient == null) {
            OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
            //设置拦截器
            if (getInterceptor() != null) {
                httpClientBuilder.addInterceptor(getInterceptor());
            }
            httpClientBuilder.addInterceptor(new RequestInterceptor());
            httpClientBuilder.addInterceptor(new ResponseInterceptor());
            //Debug模式开启时，调试时开启日志打印
            if (netWorkInfo != null && netWorkInfo.isDebug()) {
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClientBuilder.addInterceptor(httpLoggingInterceptor);
            }
            okHttpClient = httpClientBuilder.build();
        }
        return okHttpClient;
    }

    /**
     * 线程切换和订阅
     *
     * @param observer BaseObserver
     * @param <T>      T 数据结构类型
     * @return observable
     */
    public <T> ObservableTransformer<T, T> applySchedulers(final Observer<T> observer) {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                Observable<T> observable = upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
                observable.subscribe(observer);
                observable.map(getAppErrorHandle());
                observable.onErrorResumeNext(new HttpErrorHandler<T>());
                return observable;
            }
        };
    }

    /**
     * 获取retrofit
     * @param service 接口类
     * @param <T> 范型类
     * @return retrofit
     */
    public abstract <T> T getService(Class<T> service);

    /**
     * 获取缓存
     * @param cacheService 缓存的接口类
     * @param <T> 范型类
     * @return 缓存接口的实例对象
     */
    public abstract <T> T getCacheService(Class<T> cacheService);

    /**
     * 拦截器
     *
     * @return 拦截器Interceptor
     */
    protected abstract Interceptor getInterceptor();

    /**
     * 错误处理器
     *
     * @param <T> T
     */
    protected abstract <T> Function<T, T> getAppErrorHandle();
}
