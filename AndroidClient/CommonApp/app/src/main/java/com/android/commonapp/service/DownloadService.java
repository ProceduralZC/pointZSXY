package com.android.commonapp.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.widget.Toast;

import com.android.commonapp.R;
import com.android.commonapp.interfaces.DownloadListener;

import java.io.File;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 下载文件的服务
 */

public class DownloadService extends Service implements DownloadListener{
    private DownloadTask downloadTask;

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class DownloadBinder extends Binder {
        /**
         * 开始下载
         *
         * @param url
         */
        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadTask = new DownloadTask(DownloadService.this, getExternalCacheDir()); //getExternalFilesDir("abc")
                downloadTask.execute(url);
                startForeground(1, getNotification("正在下载最新版本，请稍后...", 0)); // 开启前台服务
            } else {
                Toast.makeText(DownloadService.this, "正在下载中...", Toast.LENGTH_SHORT).show();
            }
        }

    }

    @Override
    public void onProgress(int progress) { //  开始下载
        getNotificationManager().notify(1, getNotification("正在下载最新版本，请稍后...", progress));
    }

    @Override
    public void onSuccess(File file) { // 下载成功
        downloadTask = null;
        stopForeground(true); // 前台服务通知关闭
        installApk(file); // 安装下载的apk
        stopSelf(); // 停止当前下载服务
    }

    @Override
    public void onFailed() { // 下载失败
        downloadTask = null;
        stopForeground(true); // 前台服务通知关闭
        stopSelf(); // 停止当前下载服务
        Toast.makeText(DownloadService.this, "下载失败", Toast.LENGTH_LONG).show();
    }

    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotification(String title, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        if (progress > 0) {
            // 当progress大于0时才需显示下载的进度
            builder.setContentText(progress + "%");
            // 通知的最大进度 通知的当前进度 是否使用模糊进度条
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    /**
     * 安装apk
     *
     * @param file
     */
    private void installApk(File file) {
        try {
            Uri fileUri;
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 防止打不开应用
            if (android.os.Build.VERSION.SDK_INT >= 23) {
                fileUri = FileProvider.getUriForFile(this, "com.android.commonapp.fileprovider", file);
                // 对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                fileUri = Uri.fromFile(file);
            }
            intent.setDataAndType(fileUri, "application/vnd.android.package-archive");
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);// 防止打不开应用
            startActivity(intent); // 小心FileUriExposedException
        } catch (Exception e) {
            Toast.makeText(DownloadService.this, "安装失败", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
