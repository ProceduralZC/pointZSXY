package com.android.commonapp.receiver;
import android.annotation.TargetApi;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;
import java.io.File;
/**
 * 作者：哇牛Aaron
 * 作者简书文章地址: http://www.jianshu.com/users/07a8b5386866/latest_articles
 * 时间: 2016/11/18
 * 功能描述: 运用DownloadManager实现下载 一样通知栏会显示
 *
 */
public class DownloadService2 extends Service {
    public static final String DOWNLOAD_FOLDER_NAME = "collegestudent";
    public static final String DOWNLOAD_FILE_NAME = "collegestudent.apk";

    private static DownloadManager downloadManager;

//    private String DOWN_APK_URL = "http://lxpdk.oss-cn-qingdao.aliyuncs.com/lxpdk-release1.4.11.apk";
    private String DOWN_APK_URL = "";

    private static long downloadId;
    private DownloadFinish downloadFinish;
    public String loadurl = "";

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        loadurl = intent.getStringExtra("url");
        DOWN_APK_URL = loadurl;
        downLoadApk();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public void  downLoadApk(){
        Log.e("TAG", "onCreate() 启动服务");
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        //下载之前先移除上一个 不然会导致 多次下载不成功问题
        long id = (long) SPUtils.get(DownloadService2.this, SPUtils.KEY, (long) 0);
        if (id != 0) {
            downloadManager.remove(id);
        }

        initData();

        downloadFinish = new DownloadFinish();
        //动态注册广播接收器
        registerReceiver(downloadFinish, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }

    private void initData() {
        Log.e("TAG", "initData() 执行了~");
        //判断文件是否存在 不存在则创建
        File folder = new File(DOWNLOAD_FOLDER_NAME);
        if (!folder.exists() || !folder.isDirectory()) {
            folder.mkdirs();
        }
        //设置下载的URL
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(DOWN_APK_URL));
        request.setDestinationInExternalPublicDir(DOWNLOAD_FOLDER_NAME,
                DOWNLOAD_FILE_NAME);
        //设置样式 貌似必须用 getString() 如果不用 下载完毕后会显示 下载的路径
        //request.setTitle(getString(R.string.download_notification_title));
        request.setTitle("下载");
        request.setDescription("知识学院正在下载");

        //判断开关状态
        Boolean isOpen = (Boolean) SPUtils.get(this, SPUtils.WIFI_DOWNLOAD_SWITCH, false);
        //自动下载开关是否打开 如果打开
        if (isOpen) {
            Log.e("TAG", "下载完才显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，直到用户点击该
             * Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION);
        } else {
            Log.e("TAG", "正在下载时显示");
            /**
             * 在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，
             * 直到用户点击该Notification或者消除该Notification。
             */
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        //是否显示下载用户接口
        request.setVisibleInDownloadsUi(false);
        //request.setMimeType("application/cn.trinea.download.file");
        request.setMimeType("application/vnd.android.package-archive");//设置此Type不然点击通知栏无法安装

        downloadId = downloadManager.enqueue(request);
        SPUtils.put(this, SPUtils.KEY, downloadId);

    }

    /**
     * 接受下载完成的广播
     */
    class DownloadFinish extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.e("TAG", "DownloadFinish 广播接受完毕");

            //此ID为下载完成的ID
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            //如果完成的ID 于 我们下载的ID 一致则表示下载完成
            if (downloadId == completeDownloadId) {
                Log.e("TAG", "DownloadFinish downloadId == completeDownloadId");
                //安装apk
                String apkFilePath = new StringBuilder(Environment
                        .getExternalStorageDirectory().getAbsolutePath())
                        .append(File.separator)
                        .append(DOWNLOAD_FOLDER_NAME)
                        .append(File.separator).append(DOWNLOAD_FILE_NAME)
                        .toString();
                Uri downIdUri = getUriForDownloadedFile_HONEYCOMB();
                String apkPath = getFilePathByUri(context, downIdUri);

                install(context, apkFilePath);
            }

        }
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private static Uri getUriForDownloadedFile_HONEYCOMB(){
        Uri downIdUri = downloadManager.getUriForDownloadedFile(downloadId);
        return downIdUri;
    }

    /**
     * URI与路径的转换
     */
    private static String getFilePathByUri(final Context context, final Uri uri) {
        if (null == uri)
            return null;

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    /**
     * 安装APK
     *
     * @param context
     * @param filePath
     */
    private void install(Context context, String filePath) {
        Log.e("TAG", "install() 安装");
        Intent i = new Intent(Intent.ACTION_VIEW);
        File file = new File(filePath);
        if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
            if(Build.VERSION.SDK_INT >= 24) {
//                Uri contentUri = FileProvider.getUriForFile(context, "cn.com.maxtech.game.cdpdk.fileprovider", file);
//                i.setDataAndType(contentUri, "application/vnd.android.package-archive");
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Uri uriForFile = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".fileprovider", file);
                i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                i.setDataAndType(uriForFile, context.getContentResolver().getType(uriForFile));

            }else{
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setDataAndType(Uri.parse("file://" + filePath),"application/vnd.android.package-archive");
            }
            context.startActivity(i);
        }

        //停止服务
        stopSelf();
    }


    //打印看看有没有停止服务 带完善的地方 下载完成以后通知栏应该移除掉啊 还没弄不知道行不行 这里只能用户手动移除
    //而且也没有做用户点击前台服务就启动安装 以前做过貌似报错 记不清了
    @Override
    public void onDestroy() {
        Log.e("TAG", "onDestroy()");
        super.onDestroy();

        if (downloadFinish != null) {
            unregisterReceiver(downloadFinish);
        }
    }

}
