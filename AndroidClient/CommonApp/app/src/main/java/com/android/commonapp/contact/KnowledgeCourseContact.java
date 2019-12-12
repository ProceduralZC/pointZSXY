package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.KnowledgeModel;
import com.android.commonapp.models.TestModel;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  知识课件
 */

public class KnowledgeCourseContact {

    public interface view extends BaseView {
        void success(List<KnowledgeModel> modelList);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getKnowledgeCourse(String id,String page, String size);

    }
}
