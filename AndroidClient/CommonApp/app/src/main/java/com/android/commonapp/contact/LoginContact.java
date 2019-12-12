package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.LoginEntity;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class LoginContact {

    public interface view extends BaseView {
        void loginSuccess(LoginEntity loginEntity,String code ,Integer type);

        void loginFailure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void login(String username, String password);
        void updateUserInfo(String id,String nickname, String zhiwei,String jiesao,String sex);
    }
}
