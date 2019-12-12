package com.android.commonapp.network;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.WindowManager;

import com.android.commonapp.R;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class NetworkChange {
    private static NetworkChange networkChange;

    public static NetworkChange getInstance() {
        if (networkChange == null){
            networkChange = new NetworkChange();
        }
        return networkChange;
    }

    // 判断当前是否有网络连接
    public boolean isConnected;
    // 是否是移动网络
    public boolean isMobile;
    // 是否是WiFi网络
    public boolean isWifi;

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public boolean isMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
    }

    public boolean isWifi() {
        return isWifi;
    }

    public void setWifi(boolean wifi) {
        isWifi = wifi;
    }


    private static AlertDialog mAlertDialog;

    /**
     * 当判断当前手机没有网络时选择是否打开网络设置
     *
     * @param context
     */
    public static void showNoNetWorkDlg(final Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context.getApplicationContext());
        if (mAlertDialog == null) {
            mAlertDialog = builder.setIcon(R.mipmap.ic_launcher)         //
                    .setTitle("网络设置")            //
                    .setMessage("当前无网络").setPositiveButton("设置", new DialogInterface
                            .OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 跳转到系统的网络设置界面
                            Intent intent = null;
                            // 先判断当前系统版本
                            if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                                intent = new Intent(android.provider.Settings
                                        .ACTION_WIRELESS_SETTINGS);
                            } else {
                                intent = new Intent();
                                intent.setClassName("com.android.settings",
                                        android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                            }
                            if ((context instanceof Application)) {
                                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                            }
                            context.startActivity(intent);

                        }
                    }).setNegativeButton("知道了", null).create();
            // 设置为系统的Dialog，这样使用Application的时候不会 报错
            mAlertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        }
        mAlertDialog.show();
    }

    public static void showDlog(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("温馨提示")
                .setMessage("\n请确认是否有网络链接?")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        // 跳转到系统的网络设置界面
                        Intent intent = null;
                        // 先判断当前系统版本
                        if (android.os.Build.VERSION.SDK_INT > 10) {  // 3.0以上
                            intent = new Intent(android.provider.Settings .ACTION_WIRELESS_SETTINGS);
                        } else {
                            intent = new Intent();
                            intent.setClassName("com.android.settings",android.provider.Settings.ACTION_WIRELESS_SETTINGS);
                        }
                        if ((context instanceof Application)) {
                            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                        }
                        context.startActivity(intent);
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog,int which) {
                        dialog.dismiss();
                    }
                }).setCancelable(false).create().show();
    }
}
