package com.android.commonapp.interfaces;

import java.io.File;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 下载文件的监听回调
 */

public interface DownloadListener {

    void onProgress(int progress); // 当前下载进度

    void onSuccess(File file); // 下载成功

    void onFailed(); // 下载失败

}
