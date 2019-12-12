package com.android.commonapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.android.commonapp.network.NetworkChange;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 监听网络变化
 */

public class NetworkChangedReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected() && activeNetwork.isAvailable()){
            NetworkChange.getInstance().setConnected(true);
            if (activeNetwork.getType() == ConnectivityManager.TYPE_MOBILE){
                NetworkChange.getInstance().setMobile(true);
                NetworkChange.getInstance().setWifi(false);
            }else if (activeNetwork.getType() == ConnectivityManager.TYPE_WIFI){
                NetworkChange.getInstance().setWifi(true);
                NetworkChange.getInstance().setMobile(false);
            }
        }else {
            NetworkChange.getInstance().setConnected(false);
            NetworkChange.getInstance().setMobile(false);
            NetworkChange.getInstance().setWifi(false);
        }
    }
}
