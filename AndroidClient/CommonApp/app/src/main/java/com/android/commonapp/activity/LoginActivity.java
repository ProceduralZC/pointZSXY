package com.android.commonapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.LoginContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.presenter.LoginPresenter;
import com.android.commonapp.views.NavigationBar;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 登录
 */
public class LoginActivity extends BaseActivity<LoginContact.presenter> implements LoginContact.view, View.OnClickListener {
    private LinearLayout back_image_left, liner_goodstype, menu_image_right;
    public ImageView item1;
    private EditText login_username_edit, login_password_edit;
    public Button login_but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("登录");
        TextView register = new TextView(this);
        register.setText("注册");
        register.setTextSize(16f);
        register.setTextColor(ContextCompat.getColor(this, R.color.white));
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                ScreenManager.getScreenManager().startPage(LoginActivity.this, registerIntent, false);
            }
        });
        navigationBar.addRightView(register);
        login_username_edit = (EditText) findViewById(R.id.login_username_edit);
        login_password_edit = (EditText) findViewById(R.id.login_password_edit);
        login_but = (Button) findViewById(R.id.login_but);
        login_but.setOnClickListener(this);

    }

    @Override
    public void loginSuccess(LoginEntity loginEntity, String code, Integer type) {
        showToastMessage("登录成功");
        preferencesUtil.setIsLog(true);
        preferencesUtil.setUid(loginEntity.getId() + "");
        preferencesUtil.setusername(loginEntity.getUsername());
        preferencesUtil.setphone(loginEntity.getTel());
        preferencesUtil.setaddtime(loginEntity.getCreateDate() + "");
        preferencesUtil.setaccess_token(loginEntity.getAccess_token());
        preferencesUtil.setroleId(loginEntity.getRoleId() + "");
        preferencesUtil.setacount(loginEntity.getAcount());
        preferencesUtil.setsex(loginEntity.getSex());
        preferencesUtil.setNickname(loginEntity.getNickname());
        preferencesUtil.setzhiwei(loginEntity.getZhiwei());
        preferencesUtil.setdetail(loginEntity.getDetail());
        preferencesUtil.setemail(loginEntity.getEmail());
        preferencesUtil.setvip(loginEntity.getRemark2());

        ScreenManager.getScreenManager().goBlackPage();
        finish();
    }

    @Override
    public void loginFailure(Throwable e, boolean isNetWorkError, String msg) {
        showFailureMessage(isNetWorkError, msg, "登录失败!");
    }

    @Override
    public LoginContact.presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_but://登录
                String user = login_username_edit.getText().toString().trim();
                String pass = login_password_edit.getText().toString().trim();
                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    showToastMessage("用户名或密码不能为空");
                }else {
                    presenter.login(user, pass);
                }
                break;
            default:
                break;
        }
    }


    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {
        showProgressDialog(msg, ifcancel, showloading);
    }

    @Override
    public void dismissLoadingDialog() {
        hideProgressDialog();
    }
}
