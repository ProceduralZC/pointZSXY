package com.android.commonapp.presenter;

import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.contact.LoginContact;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class LoginPresenter extends BasePresenterImpl<LoginContact.view> implements LoginContact.presenter {

    public LoginPresenter(LoginContact.view view) {
        super(view);
    }

    @Override
    public void login(String username, String password) {
        view.showLoadingDialog("数据传输中,请稍后",true,true);
        RetrofitFactory.getInstance().api().login(username, password)
                .enqueue(new CommonCallback<List<LoginEntity>>() {
                    @Override
                    protected void onSuccess(CommonCallModel<List<LoginEntity>> t) throws Exception {
                        view.dismissLoadingDialog();
                        List<LoginEntity> loginEntityList = t.getData();
                        if (!loginEntityList.isEmpty()) {
                            view.loginSuccess(loginEntityList.get(0),t.getCode(),1);
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                        view.dismissLoadingDialog();
                        view.loginFailure(e, isNetWorkError, msg);
                    }
                });
    }

    @Override
    public void updateUserInfo(String id, String nickname, String zhiwei, String jiesao, String sex) {
        view.showLoadingDialog("数据传输中,请稍后",true,true);
        RetrofitFactory.getInstance().api().updateUserInfo( id,nickname,zhiwei,jiesao,sex)
                .enqueue(new CommonCallback<List<LoginEntity>>() {
                    @Override
                    protected void onSuccess(CommonCallModel<List<LoginEntity>> t) throws Exception {
                        view.dismissLoadingDialog();
                        if(t.getCode().equals("ok")){
                            view.loginSuccess(null,t.getCode(),2);
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                        view.dismissLoadingDialog();
                        view.loginFailure(e, isNetWorkError, msg);
                    }
                });
    }

}
