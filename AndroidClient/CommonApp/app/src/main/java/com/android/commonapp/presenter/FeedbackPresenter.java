package com.android.commonapp.presenter;

import com.android.commonapp.contact.FeedbackContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.FeedbackEntity;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class FeedbackPresenter extends BasePresenterImpl<FeedbackContact.view> implements FeedbackContact.presenter {

    public FeedbackPresenter(FeedbackContact.view view) {
        super(view);
    }

    @Override
    public void feedback(String contact, String feedbackinfo) {
        view.showLoadingDialog("数据传输中,请稍后",true,true);
        RetrofitFactory.getInstance().api().feedback(contact, feedbackinfo)
                .enqueue(new CommonCallback<List<FeedbackEntity>>() {
                    @Override
                    protected void onSuccess(CommonCallModel<List<FeedbackEntity>> t) throws Exception {
                        view.dismissLoadingDialog();
                            view.Success(t.getCode(),t.getMessage());
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                        view.dismissLoadingDialog();
                        view.loginFailure(e, isNetWorkError, msg);
                    }
                });
    }
}
