package com.android.commonapp.contact;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.StudentHistoryModel;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe: 学习历史
 */
public class StudentHistoryContact {
    public interface view extends BaseView {
        void success(List<StudentHistoryModel> modelList, String code, String message);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getStudentHistory(String id, String page, String size);

    }
}
