package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.BoutiqueModel;
import com.android.commonapp.models.HotModel;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  热门路径
 */

public class HotContact {

    public interface view extends BaseView {
        void success(List<HotModel> modelList);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {

        void getKnowledgeHot(String id,String page, String size);

    }
}
