package com.android.commonapp.network;

import android.accounts.NetworkErrorException;

import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.models.CommonCallModel;

import java.net.ConnectException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 公共的Callback
 */

public abstract class CommonCallback<T> implements Callback<CommonCallModel<T>> {

    @Override
    public void onResponse(Call<CommonCallModel<T>> call, Response<CommonCallModel<T>> response) {
        if (response != null && response.body() != null) {
            CommonCallModel<T> commonCallModel = response.body();
            System.out.print("json==="+response.body().toString());
            if (HttpConfig.SUCCESS.equals(commonCallModel.getCode())) {
                try {
                    onSuccess(commonCallModel);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (HttpConfig.FAILED.equals(commonCallModel.getCode())) {
                try {
                    onFailure(null, false, commonCallModel.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    onFailure(null, false, null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                onFailure(null, false, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(Call<CommonCallModel<T>> call, Throwable t) {
        try {
            if (t instanceof ConnectException || t instanceof TimeoutException
                    || t instanceof NetworkErrorException
                    || t instanceof UnknownHostException) {
                onFailure(t, true, null);
            } else {
                onFailure(t, false, null);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }

    /**
     * 返回成功
     *
     * @param t
     * @throws Exception
     */
    protected abstract void onSuccess(CommonCallModel<T> t) throws Exception;

    /**
     * 返回失败
     *
     * @param e
     * @param isNetWorkError 是否是网络错误
     * @param msg            错误提示
     * @throws Exception
     */
    protected abstract void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception;
}
