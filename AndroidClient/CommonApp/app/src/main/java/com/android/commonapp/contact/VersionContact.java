package com.android.commonapp.contact;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.models.VersionModel;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  版本更新
 */
public class VersionContact {

    public interface view extends BaseView {
        void success(List<VersionModel> modelList,String code ,String message);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getVersionInfo();
    }
}
