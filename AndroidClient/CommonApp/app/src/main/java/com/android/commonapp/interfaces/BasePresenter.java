package com.android.commonapp.interfaces;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public interface BasePresenter {

    /**
     * 关闭Activity后把view对象置为空
     */
    void detach();

    /**
     * 将网络请求的每一个disposable添加进入CompositeDisposable，退出时一并注销
     * @param subscription
     */
    //void addDisposable(Disposable subscription);

    /**
     * 注销所有请求
     */
    void unDisposable();

}
