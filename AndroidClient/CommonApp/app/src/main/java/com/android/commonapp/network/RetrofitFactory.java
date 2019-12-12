package com.android.commonapp.network;
import com.android.commonapp.config.HttpConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: Retrofit构建器
 */

public class RetrofitFactory {
    private static RetrofitFactory mRetrofitFactory;
    private static RetrofitService mRetrofitService;

    private RetrofitFactory() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASE_URL) //设置baseUrl,注意baseUrl必须后缀"/"
                .addConverterFactory(ScalarsConverterFactory.create()) // 支持返回值为String
                .addConverterFactory(GsonConverterFactory.create()) // 支持Gson转换器
                //.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 支持RxJava转换器
                .client(OkHttpFactory.buildOkHttpClient())
                .build();
        mRetrofitService = retrofit.create(RetrofitService.class);
    }

    public static RetrofitFactory getInstance() {
        if (mRetrofitFactory == null) {
            synchronized (RetrofitFactory.class) {
                if (mRetrofitFactory == null) {
                    mRetrofitFactory = new RetrofitFactory();
                }
            }
        }
        return mRetrofitFactory;
    }

    public RetrofitService api() {
        return mRetrofitService;
    }

}
