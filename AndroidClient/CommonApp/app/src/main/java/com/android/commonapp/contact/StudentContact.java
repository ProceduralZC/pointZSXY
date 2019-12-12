package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.StudentModel;

import java.util.List;
/**
 * @date: 2017/11/20.
 * @author: CHEN
 * @describe:  知识--课程
 */

public class StudentContact {
    public interface view extends BaseView {
        void success(List<StudentModel> modelList);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getStudent(String page,String size);
    }
}
