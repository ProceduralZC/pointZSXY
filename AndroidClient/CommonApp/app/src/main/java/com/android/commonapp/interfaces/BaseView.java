package com.android.commonapp.interfaces;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public interface BaseView {

    /**
     * 显示dialog
     * @param msg
     */
    void showLoadingDialog(String msg,boolean ifcancel,boolean showloading);

    /**
     * 隐藏dialog
     */
    void dismissLoadingDialog();

}
