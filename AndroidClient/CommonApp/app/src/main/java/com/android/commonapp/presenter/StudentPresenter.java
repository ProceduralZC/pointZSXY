package com.android.commonapp.presenter;
import com.android.commonapp.contact.IndexContact;
import com.android.commonapp.contact.StudentContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.StudentModel;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  知识课程  接口
 */
public class StudentPresenter extends BasePresenterImpl<StudentContact.view> implements StudentContact.presenter {
    public StudentPresenter(StudentContact.view view) {
        super(view);
    }

    @Override
    public void getStudent(String page, String size) {
        view.showLoadingDialog("数据传输中,请稍后",true,false);
        RetrofitFactory.getInstance().api().jpcourse(page, size)
            .enqueue(new CommonCallback<List<StudentModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<StudentModel>> t) throws Exception {
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
