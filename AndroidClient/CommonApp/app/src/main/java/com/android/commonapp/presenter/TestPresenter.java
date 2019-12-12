package com.android.commonapp.presenter;

import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.contact.TestContact;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:
 */

public class TestPresenter extends BasePresenterImpl<TestContact.view> implements TestContact.presenter {

    public TestPresenter(TestContact.view view) {
        super(view);
    }

    @Override
    public void getData(String page, String size) {
        view.showLoadingDialog("请稍后...",true,true);
        RetrofitFactory.getInstance().api()
                .test(page, size)
                .enqueue(new CommonCallback<List<TestModel>>() {
                    @Override
                    protected void onSuccess(CommonCallModel<List<TestModel>> t) throws Exception {
                        view.dismissLoadingDialog();
                        if (t.getData() != null) {
                            view.success(t.getData());
                        }
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                        view.dismissLoadingDialog();
                        view.failure(e, isNetWorkError, msg);
                    }
                });

    }
}
