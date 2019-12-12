package com.android.commonapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class NetworkUtil {

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvaliable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = cm.getActiveNetworkInfo();
        if (null != activeNetInfo && activeNetInfo.isAvailable() && activeNetInfo.isConnected()) {
            // 当前网络是连接的
            if (activeNetInfo.getState() == NetworkInfo.State.CONNECTED) { // 已连接
                // 当前所连接的网络可用
                return true;
            }
        }
        return false;
    }

    /**
     * 判断wifi 的设置 是否是一直 链接
     *
     * @return true一直链接或者当前网络不是wifi，false当前网络是wifi并且设置不是一直链接
     */
    public static boolean isWifiAlwaysConnect(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.getType() == ConnectivityManager.TYPE_WIFI) {
            int wifiSleepPolicy = Settings.System.getInt(context.getContentResolver(), "wifi_sleep_policy", Settings.System.WIFI_SLEEP_POLICY_DEFAULT);
            if (wifiSleepPolicy != Settings.System.WIFI_SLEEP_POLICY_NEVER) {
                return false;
            }
        }
        return true;
    }

    public static String getNetworkName(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        return (info == null) ? "No network" : info.getTypeName();
    }
}
