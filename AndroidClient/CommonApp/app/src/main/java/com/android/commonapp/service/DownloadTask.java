package com.android.commonapp.service;

import android.os.AsyncTask;

import com.android.commonapp.network.NetworkChange;
import com.android.commonapp.interfaces.DownloadListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 下载任务
 */

public class DownloadTask extends AsyncTask<String, Integer, Integer> {
    public static final int TYPE_SUCCESS = 0;

    public static final int TYPE_FAILED = 1;

    private DownloadListener listener;

    private int lastProgress;

    private InputStream is = null;

    private RandomAccessFile saveFile = null;

    private File file = null;

    private File downloadDir; // 下载文件存放的目录

    public DownloadTask(DownloadListener listener, File downloadDir) {
        this.listener = listener;
        this.downloadDir = downloadDir;
    }

    @Override
    protected void onPreExecute() { // 后台任务开始前调用
        super.onPreExecute();
    }

    @Override
    protected Integer doInBackground(String... params) {
        // 处理耗时任务
        try {
            long downloadLength = 0; // 记录已下载文件的长度
            String downloadUrl = params[0];
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            // Environment.DIRECTORY_DOWNLOADS SD卡的Download目录
            //String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            //String directory = downloadDir.getPath();
            file = new File(downloadDir.getPath() + fileName);
            if (file.exists()) {
//                downloadLength = file.length(); // 断点下载
                file.delete(); // 每次都从头开始下载
            }
            long contentLength = getFileLength(downloadUrl);
            // 当下载文件的长度为0时处理
            if (contentLength == 0) {
                return TYPE_FAILED;
            } else if (contentLength == downloadLength) { // 已下载的字节和文件的总字节相等，说明已经下载完成
                return TYPE_SUCCESS;
            }
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 告诉服务器想要从哪个字节下载
                    .addHeader("RANGE", "bytes=" + downloadLength + "-") // 断点下载，指定从哪个字节开始下载
                    .url(downloadUrl)
                    .build();
            Response response = client.newCall(request).execute();
            if (response != null) {
                is = response.body().byteStream();
                saveFile = new RandomAccessFile(file, "rw");
                saveFile.seek(downloadLength); // 跳过已下载的字节
                byte[] b = new byte[1024];
                int total = 0;
                int len;
                while ((len = is.read(b)) != -1) {
                    if (NetworkChange.getInstance().isConnected()) {
                        total += len;
                        saveFile.write(b, 0, len);
                        int progress = (int) ((total + downloadLength) * 100 / contentLength); // 计算已下载的百分比
                        publishProgress(progress); // 反馈当前任务执行进度
                    } else {
                        return TYPE_FAILED;
                    }
                }
                response.body().close();
                return TYPE_SUCCESS;
            } else {
                return TYPE_FAILED;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
                if (saveFile != null) {
                    saveFile.close();
                }
                // 测试时写的如果取消下载就删除掉已下载的文件大小
//                if (isCanceled && file != null) {
//                    file.delete();
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return TYPE_FAILED;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        // 当在后台任务中调用publishProgress(Progress...)后，此方法很快就会被回调
        int progress = values[0];
        if (progress > lastProgress) {
            listener.onProgress(progress);
            lastProgress = progress;
        }
    }

    @Override
    protected void onPostExecute(Integer status) {
        // 后台任务操作返回的结果
        switch (status) {
            case TYPE_SUCCESS:
                if (file != null) {
                    listener.onSuccess(file);
                } else {
                    listener.onFailed();
                }
                break;
            case TYPE_FAILED:
                listener.onFailed();
                break;
            default:
                break;
        }
    }

    /**
     * 获取待下载文件的总长度
     *
     * @param downloadUrl 下载地址
     * @return
     * @throws IOException
     */
    private long getFileLength(String downloadUrl) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(downloadUrl)
                .build();
        Response response = client.newCall(request).execute();
        if (response != null && response.isSuccessful()) {
            long contentLength = response.body().contentLength();
            response.close();
            return contentLength;
        }
        return 0;
    }
}
