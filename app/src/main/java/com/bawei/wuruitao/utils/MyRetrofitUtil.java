package com.bawei.wuruitao.utils;

import com.bawei.wuruitao.api.Api;

import java.util.IdentityHashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * ProjectName: WuRuitao20200316
 * PackageName: com.bawei.wuruitao.utils
 * ClassName:   MyRetrofitUtil
 * Description: Java类的作用
 * Author: LazyRui
 * CreateDate: 2020/3/16 8:32
 */
public class MyRetrofitUtil {
    //单例模式
    public static MyRetrofitUtil sMyRetrofitUtil;
    private final Retrofit mRetrofit;

    private MyRetrofitUtil() {
        OkHttpClient okhttp = new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .client(okhttp)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

    }

    public static MyRetrofitUtil getInstance() {
        //双重校验锁
        if (sMyRetrofitUtil == null) {
            synchronized (MyRetrofitUtil.class) {
                if (sMyRetrofitUtil == null) {
                    sMyRetrofitUtil = new MyRetrofitUtil();
                }
            }
        }
        return sMyRetrofitUtil;
    }

    //方法
    public <T> T createService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

}
