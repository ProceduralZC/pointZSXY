package com.android.commonapp.network;
import com.android.commonapp.config.UserConfig;
import com.android.commonapp.util.Print;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: Request拦截器，添加header--token
 */
public class OkHttpInterceptor implements Interceptor {
    private static final String TAG = "MyInterceptor";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        // 获取、修改请求头
        Request.Builder builder = original.newBuilder();
        String token = UserConfig.getInstance().getString(UserConfig.token, "");
        builder.addHeader("cookie", "ACCESS_TOKEN=" + token);
        Request request = builder.build();

        System.out.print("===请求url==="+request.url());

        Response response = chain.proceed(request);
        ResponseBody body = response.body();
        if (body != null && body.contentLength() == 0) {
            Print.e(TAG, "length = 0");
            System.out.print("json==="+body.toString());
        }
        return response;
    }

}
