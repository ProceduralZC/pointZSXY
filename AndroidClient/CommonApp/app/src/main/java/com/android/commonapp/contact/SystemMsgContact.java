package com.android.commonapp.contact;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.MessageModel;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  系统消息
 */

public class SystemMsgContact {

    public interface view extends BaseView {
        void success(List<MessageModel> modelList);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getSystemMsg(String page, String size);
    }
}
