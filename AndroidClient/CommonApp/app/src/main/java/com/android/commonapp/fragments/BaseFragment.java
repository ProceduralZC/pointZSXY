package com.android.commonapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
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

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: fragment的基类
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected P presenter;
    public DialogUtils dialogUtils;
    protected Toast toast;
    public static SharedPreferencesUtilDb preferencesUtil;
    public static Context context;
    private File cacheDir;
    protected ImageLoader mImagerLoader = ImageLoader.getInstance();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferencesUtil = new SharedPreferencesUtilDb(getActivity());
        presenter = initPresenter();
        // 创建等待对话框
        dialogUtils = new DialogUtils(getActivity());
        // 创建Toast提示
        toast = Toast.makeText(getActivity(), null, Toast.LENGTH_SHORT);

        context = getActivity().getBaseContext();
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
                .imageDownloader(new AuthImageDownloader(getActivity()))
                .writeDebugLogs() // Remove for release app
                .build();//开始构建

        mImagerLoader.init(config);
    }

    public abstract P initPresenter();

    @Override
    public void onDestroyView() {
        if (presenter != null) {
            presenter.detach();
        }
        super.onDestroyView();
    }


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
}
