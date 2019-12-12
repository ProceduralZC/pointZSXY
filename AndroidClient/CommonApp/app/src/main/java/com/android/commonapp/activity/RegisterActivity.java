package com.android.commonapp.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.RegisterContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.models.RegisterEntity;
import com.android.commonapp.presenter.LoginPresenter;
import com.android.commonapp.presenter.RegisterPresenter;
import com.android.commonapp.util.CheckTextFormatUtils;
import com.android.commonapp.util.EditUtil;
import com.android.commonapp.util.SMSCountDown;
import com.android.commonapp.views.NavigationBar;

/**
 * 注册
 */
public class RegisterActivity extends BaseActivity<RegisterContact.presenter> implements RegisterContact.view,View.OnClickListener {

    private EditText accountPhoneEditText;
    private EditText passwordEditText;
    private EditText verifyCodeEditText;
    private SMSCountDown smsCountDown;
    private TextView pCode;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("快速注册");
        accountPhoneEditText = (EditText) findViewById(R.id.register_username_edit);
        passwordEditText = (EditText) findViewById(R.id.register_password_edit);
        verifyCodeEditText = (EditText) findViewById(R.id.edit_dynamic_code);
        pCode = (TextView) findViewById(R.id.tv_verify_code);
        pCode.setOnClickListener(this);
        register = (Button) findViewById(R.id.btn_register);
        register.setOnClickListener(this);
        smsCountDown = new SMSCountDown(pCode, 60 * 1000);
    }

    @Override
    public RegisterContact.presenter initPresenter() {
        return new RegisterPresenter(this);
    }


    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                String phoneNumber = EditUtil.getContent(accountPhoneEditText); // 手机号
                String password = EditUtil.getContent(passwordEditText); // 密码
//                String verificationCode = EditUtil.getContent(verifyCodeEditText); // 验证码
                String verificationCode = "123456"; // 验证码
                if (TextUtils.isEmpty(phoneNumber)) {
                    showToastMessage("请输入手机号码");
                    return;
                } else {
                    if (!CheckTextFormatUtils.checkPhoneNumberFormat(phoneNumber)) {
                        showToastMessage("手机号码填写有误");
                        accountPhoneEditText.requestFocus();
                        return;
                    }
                }
                if (TextUtils.isEmpty(password)) {
                    showToastMessage("请输入密码");
                    return;
                } else {
                    if (!CheckTextFormatUtils.checkPasswordFormat(password)) {
                        showToastMessage("密码填写有误");
                        passwordEditText.requestFocus();
                        return;
                    }
                }
                if (TextUtils.isEmpty(verificationCode)) {
                    showToastMessage("请输入验证码");
                    return;
                } else {
                    if (!CheckTextFormatUtils.checkVerificationCodeFormat(verificationCode)) {
                        showToastMessage("验证码填写有误");
                        verifyCodeEditText.requestFocus();
                        return;
                    }
                }
                //todo 请求注册接口

                presenter.register(phoneNumber, password);

                break;
            case R.id.tv_verify_code:
                String phone = EditUtil.getContent(accountPhoneEditText);
                if (TextUtils.isEmpty(phone)){
                    showToastMessage("请输入手机号码");
                }else {
                    if (CheckTextFormatUtils.checkPhoneNumberFormat(phone)) {
                        smsCountDown.start();
                        //todo 请求获取验证码接口
                    }else {
                        showToastMessage("手机号码格式不正确");
                        accountPhoneEditText.requestFocus();
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        smsCountDown = null;
    }

    @Override
    public void registerSuccess(RegisterEntity loginEntity, String code, Integer type) {
        showToastMessage("注册成功,请登录！");

        ScreenManager.getScreenManager().goBlackPage();
        finish();
    }

    @Override
    public void registerFailure(Throwable e, boolean isNetWorkError, String msg) {

    }
}
