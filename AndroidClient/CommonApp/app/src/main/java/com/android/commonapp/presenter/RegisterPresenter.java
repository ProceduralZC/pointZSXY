package com.android.commonapp.presenter;

import com.android.commonapp.contact.LoginContact;
import com.android.commonapp.contact.RegisterContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.models.RegisterEntity;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class RegisterPresenter extends BasePresenterImpl<RegisterContact.view> implements RegisterContact.presenter {

    public RegisterPresenter(RegisterContact.view view) {
        super(view);
    }

    @Override
    public void register(String username, String password) {
        view.showLoadingDialog("数据传输中,请稍后",true,true);
        RetrofitFactory.getInstance().api().register(username, password)
            .enqueue(new CommonCallback<List<RegisterEntity>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<RegisterEntity>> t) throws Exception {
                    view.dismissLoadingDialog();
//                    List<RegisterEntity> loginEntityList = t.getData();
//                    if (!loginEntityList.isEmpty()) {
                        view.registerSuccess(null,t.getCode(),1);
//                    }
                }

                @Override
                protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                    view.dismissLoadingDialog();
                    view.registerFailure(e, isNetWorkError, msg);
                }
            });
    }

}
