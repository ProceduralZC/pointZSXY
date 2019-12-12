package com.android.commonapp.activity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.util.SharedPreferencesUtilDb;
import com.android.commonapp.views.AuthImageDownloader;
import com.android.commonapp.views.DialogUtils;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 基类
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected P presenter;
    protected Toast toast;
    protected PermissionListener permissionListener;
    public DialogUtils dialogUtils;
    public static SharedPreferencesUtilDb preferencesUtil;
    public static Context context;
    private File cacheDir;
    protected ImageLoader mImagerLoader = ImageLoader.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesUtil = new SharedPreferencesUtilDb(this);
        presenter = initPresenter();
        // 创建等待对话框
        dialogUtils = new DialogUtils(this);
        // 创建Toast提示
        toast = Toast.makeText(this, null, Toast.LENGTH_SHORT);
        context = this.getBaseContext();
        cacheDir = StorageUtils.getOwnCacheDirectory(context, "student/imgCache");

        ImageLoaderConfiguration config = new ImageLoaderConfiguration
                .Builder(context)
                .memoryCacheExtraOptions(480, 800) // max width, max height，即保存的每个缓存文件的最大长宽
                .threadPoolSize(3)//线程池内加载的数量
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/你可以通过自己的内存缓存实现
                .memoryCacheSize(2 * 1024 * 1024)
                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
                .discCacheFileCount(1000) //缓存的文件数量
                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
                .imageDownloader(new AuthImageDownloader(this))
                .writeDebugLogs() // Remove for release app
                .build();//开始构建

        mImagerLoader.init(config);
    }

    /**
     * 在子类中初始化对应的presenter
     *
     * @return 相应的presenter
     */
    public abstract P initPresenter();

    public void showProgressDialog(String msg,boolean ifcancel,boolean showloading) {
        dialogUtils.showProgress(msg,ifcancel,showloading);
    }

    public void hideProgressDialog() {
        dialogUtils.dismissProgress();
    }

    public void showToastMessage(String message) {
        toast.setText(message);
        toast.show();
    }

    public void hideToastMessage() {
        toast.cancel();
    }

    /**
     * 申请权限
     *
     * @param permissions 申请的权限名
     * @param listener    权限授权的回调
     */
    public void requestRuntimePermission(String[] permissions, PermissionListener listener) {
        permissionListener = listener;
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }

        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(this, permissionList.toArray(new String[permissionList.size()]), 1);
        } else {
            permissionListener.onGranted(); // 表示全都授权了
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i]; // 判断是否授权
                        String permission = permissions[i]; // 请求权限的名字
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        permissionListener.onGranted();
                    } else {
                        permissionListener.onDenied(deniedPermissions);
                    }
                }
                break;
            default:
                break;
        }
    }

    /**
     * 接口获取失败的提示
     * @param isNetWorkError
     * @param msg
     * @param defaults
     */
    public void showFailureMessage(boolean isNetWorkError, String msg, String defaults) {
        if (isNetWorkError) {
            showToastMessage("请检查您的网络!");
        } else if (!TextUtils.isEmpty(msg)) {
            showToastMessage(msg);
        } else {
            showToastMessage(defaults);
        }
    }


    @Override
    protected void onDestroy() {
        if (presenter != null) {
            presenter.detach(); // 在presenter中解绑释放view
            presenter = null;
        }
        if (dialogUtils != null) {
            dialogUtils = null;
        }
        super.onDestroy();
    }

}
