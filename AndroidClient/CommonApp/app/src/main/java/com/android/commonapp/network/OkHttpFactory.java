package com.android.commonapp.network;

import com.android.commonapp.config.HttpConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: OkHttp构建器
 */

public class OkHttpFactory {

    private volatile static OkHttpClient client;

    public static OkHttpClient buildOkHttpClient() {
        synchronized (OkHttpFactory.class) {
            if (client == null) {
                client = new OkHttpClient.Builder()
                    .connectTimeout(HttpConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(HttpConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .readTimeout(HttpConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    // 日志拦截器
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(new OkHttpInterceptor())
                    //失败重连
                    //.retryOnConnectionFailure(true)
                    .build();
            }
        }
        return client;
    }

}
