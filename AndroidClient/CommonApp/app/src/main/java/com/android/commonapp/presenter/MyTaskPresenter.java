package com.android.commonapp.presenter;

import com.android.commonapp.contact.MyTaskContact;
import com.android.commonapp.interfaces.BasePresenterImpl;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 我的任务
 */

public class MyTaskPresenter extends BasePresenterImpl<MyTaskContact.view> implements MyTaskContact.presenter {

    public MyTaskPresenter(MyTaskContact.view view) {
        super(view);
    }


}
