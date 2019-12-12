package com.android.commonapp.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.FeedbackContact;
import com.android.commonapp.models.FeedbackEntity;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.presenter.FeedbackPresenter;
import com.android.commonapp.views.NavigationBar;

/**
 * 帮助与反馈
 */
public class FeedbackActivity extends BaseActivity<FeedbackContact.presenter> implements FeedbackContact.view, View.OnClickListener {

    private EditText inputContact;
    private TextView inputNum;

    private EditText inputOpinion;
    private TextView inputOpinionNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("意见反馈");
        // 联系方式
        inputContact = (EditText) findViewById(R.id.et_input_contact);
        inputNum = (TextView) findViewById(R.id.tv_contact_number);
        inputContact.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputNum.setText("" + s.length());
            }
        });

        // 意见反馈
        inputOpinion = (EditText) findViewById(R.id.et_input_opinion);
        inputOpinionNum = (TextView) findViewById(R.id.tv_opinion_number);
        inputOpinion.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                inputOpinionNum.setText("" + s.length());
            }
        });

        findViewById(R.id.btn_commit_opinion).setOnClickListener(this);

    }

    @Override
    public FeedbackContact.presenter initPresenter() {
        return new FeedbackPresenter(this);
    }

    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {

    }

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showToastMessage(String message) {
        super.showToastMessage(message);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_commit_opinion) {
            String contact = inputContact.getText().toString().trim(); // 联系方式
            if (TextUtils.isEmpty(contact)) { //todo 此处不为空也可以使用正则表达式匹配输入的邮箱等
                showToastMessage("联系方式不能为空");
                return;
            }
            String opinion = inputOpinion.getText().toString().trim(); // 意见反馈
            if (TextUtils.isEmpty(opinion)) {
                showToastMessage("意见反馈不能为空");
                return;
            }

//            showToastMessage("执行提交操作");
            presenter.feedback(contact,opinion);
        }
    }

    @Override
    public void Success(String code,String message) {
        if(code.equals("ok")){
            showToastMessage("感谢您的反馈,知识学院有你更完美！");
            inputContact.setText("");
            inputOpinion.setText("");
        }else{
            showToastMessage("反馈失败，再接再厉！");
        }

    }

    @Override
    public void loginFailure(Throwable e, boolean isNetWorkError, String msg) {

    }
}
