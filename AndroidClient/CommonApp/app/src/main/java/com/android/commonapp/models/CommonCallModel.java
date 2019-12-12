package com.android.commonapp.models;

import com.google.gson.annotations.SerializedName;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe:
 */

public class CommonCallModel<T> {
    private static int SUCCESS_CODE = 0; //成功的code 此处code可单独抽出来写，以方便其他地方调用比较
    @SerializedName("code")
    private String code;
    @SerializedName("msg")
    private String message;
    @SerializedName("items")
    private T data;

//    public boolean isSuccess() {
//        return getCode() == SUCCESS_CODE;
//    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
