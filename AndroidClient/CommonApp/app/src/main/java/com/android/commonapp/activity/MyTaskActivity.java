package com.android.commonapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.android.commonapp.R;
import com.android.commonapp.contact.MyTaskContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.presenter.MyTaskPresenter;
import com.android.commonapp.views.NavigationBar;

/**
 * 我的任务
 */
public class MyTaskActivity extends BaseActivity<MyTaskContact.presenter> implements MyTaskContact.view, View.OnClickListener {

    private Button oneDayVip;
    private Button weWhatVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_task);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("我的任务");

        oneDayVip = (Button) findViewById(R.id.btn_one_day_vip);
        oneDayVip.setOnClickListener(this);

        weWhatVip = (Button) findViewById(R.id.btn_weWhat_vip);
        weWhatVip.setOnClickListener(this);

        if(!preferencesUtil.getvip().equals("")){
            oneDayVip.setTextColor(ContextCompat.getColor(this, R.color.white));
            oneDayVip.setBackgroundResource(R.drawable.gray_bgnormal);
            oneDayVip.setText("已领取");
            oneDayVip.setEnabled(false);
        }else{
            oneDayVip.setTextColor(ContextCompat.getColor(this, R.color.white));
            oneDayVip.setBackgroundResource(R.drawable.bg_btn_selector);
            oneDayVip.setText("领取");
            oneDayVip.setEnabled(true);
        }

    }

    @Override
    public MyTaskContact.presenter initPresenter() {
        return new MyTaskPresenter(this);
    }

    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one_day_vip: // 登录领取1天VIP
//                oneDayVip.setTextColor(ContextCompat.getColor(this, R.color.white));
//                oneDayVip.setBackgroundResource(R.drawable.gray_bgnormal);
//                oneDayVip.setText("已领取");
//                oneDayVip.setEnabled(false);
                if(preferencesUtil.getIsLog()) {

                }else{
                    Intent intent3 = new Intent(MyTaskActivity.this, LoginActivity.class);
                    ScreenManager.getScreenManager().startPage(MyTaskActivity.this, intent3, true);
                }

                break;
            case R.id.btn_weWhat_vip: // 微信分享送1天VIP
//                weWhatVip.setTextColor(ContextCompat.getColor(this, R.color.white));
//                weWhatVip.setText("已领取");
//                weWhatVip.setEnabled(false);
                break;
            default:
                break;
        }
    }
}
