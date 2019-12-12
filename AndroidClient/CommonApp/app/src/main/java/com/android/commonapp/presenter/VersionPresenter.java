package com.android.commonapp.presenter;
import com.android.commonapp.contact.HotContact;
import com.android.commonapp.contact.VersionContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.models.VersionModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe: 版本信息
 */

public class VersionPresenter extends BasePresenterImpl<VersionContact.view> implements VersionContact.presenter {

    public VersionPresenter(VersionContact.view view) {
        super(view);
    }

    @Override
    public void getVersionInfo() {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("数据传输中，请稍后",false,true);
        RetrofitFactory.getInstance().api().versionInfo()
            .enqueue(new CommonCallback<List<VersionModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VersionModel>> t) throws Exception {
                    view.dismissLoadingDialog();
                    if (t.getData() != null) {
                        view.success(t.getData(),t.getCode(),t.getMessage());
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
