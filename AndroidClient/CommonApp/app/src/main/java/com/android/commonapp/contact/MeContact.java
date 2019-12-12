package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.LoginEntity;

import okhttp3.RequestBody;

/**
 * @date: 2017/11/18.
 * @author: CHEN
 * @describe:
 */

public class MeContact {

    public interface view extends BaseView {
        void uploadSuccess(LoginEntity loginEntity);

        void uploadFailure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {

        void upload(RequestBody requestBody);

    }
}
