package com.android.commonapp.contact;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.FeedbackEntity;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.models.StudentModel;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  帮助与反馈
 */
public class FeedbackContact {

    public interface view extends BaseView {
        void Success(String code,String message);

        void loginFailure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void feedback(String contact, String peoplestes);
    }
}
