package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.MainPageModel;
import com.android.commonapp.models.TestModel;

import java.util.List;

/**
 * @date: 2017/11/20.
 * @author: CHEN
 * @describe:
 */

public class IndexContact {

    public interface view extends BaseView {
        void success(List<MainPageModel> modelList,int type);
        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getData(int type);//获取分类
        void getMainLuncher(int type);//获取轮播图
    }
}
