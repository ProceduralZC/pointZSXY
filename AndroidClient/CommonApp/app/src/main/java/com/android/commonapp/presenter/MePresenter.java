package com.android.commonapp.presenter;

import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.contact.MeContact;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import okhttp3.RequestBody;

/**
 * @date: 2017/11/18.
 * @author: CHEN
 * @describe:
 */

public class MePresenter extends BasePresenterImpl<MeContact.view> implements MeContact.presenter {

//    private BaseActivity baseActivity;

    public MePresenter(MeContact.view view) {
        super(view);
//        this.baseActivity = baseActivity;
    }

    @Override
    public void upload(RequestBody requestBody) {
        view.showLoadingDialog("上传中...",true,true);
        RetrofitFactory.getInstance().api()
                .uploadFile(requestBody)
                .enqueue(new CommonCallback<TestModel>() {
                    @Override
                    protected void onSuccess(CommonCallModel<TestModel> t) throws Exception {
                        view.dismissLoadingDialog();
                        view.uploadSuccess(null);
                    }

                    @Override
                    protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                        view.dismissLoadingDialog();
                        view.uploadFailure(e, isNetWorkError, msg);
                    }
                });
    }
}
