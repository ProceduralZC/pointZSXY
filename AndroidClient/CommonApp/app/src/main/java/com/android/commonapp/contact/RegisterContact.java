package com.android.commonapp.contact;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.models.RegisterEntity;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  注册
 */
public class RegisterContact {

    public interface view extends BaseView {
        void registerSuccess(RegisterEntity loginEntity, String code , Integer type);

        void registerFailure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void register(String username, String password);
    }
}
