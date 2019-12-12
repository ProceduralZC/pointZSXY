package com.android.commonapp.activity;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.VipPurchaseContact;
import com.android.commonapp.presenter.VipPurchasePresenter;
import com.android.commonapp.views.NavigationBar;

/**
 * Vip购买
 */
public class VipPurchaseActivity extends BaseActivity<VipPurchaseContact.presenter> implements VipPurchaseContact.view, View.OnClickListener {

    private TextView month;
    private TextView year;

    private CheckBox checkBoxMonth;
    private CheckBox checkBoxYear;

    private CheckBox checkBoxAlipay;
    private CheckBox checkBoxWeChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vip_purchase);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("VIP购买");
        month = (TextView) findViewById(R.id.tv_month);
        month.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        year = (TextView) findViewById(R.id.tv_year);
        year.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        checkBoxMonth = (CheckBox) findViewById(R.id.cb_month); // 月度
        checkBoxYear = (CheckBox) findViewById(R.id.cb_year); // 年度

        checkBoxAlipay = (CheckBox) findViewById(R.id.cb_alipay); // 支付宝
        checkBoxWeChat = (CheckBox) findViewById(R.id.cb_weChat); // 微信

        findViewById(R.id.btn_purchase_vip).setOnClickListener(this);
    }

    @Override
    public VipPurchaseContact.presenter initPresenter() {
        return new VipPurchasePresenter(this);
    }

    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_purchase_vip) {
        }
    }

}
