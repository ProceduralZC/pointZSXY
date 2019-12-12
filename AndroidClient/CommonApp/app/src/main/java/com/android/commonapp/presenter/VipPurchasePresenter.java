package com.android.commonapp.presenter;

import com.android.commonapp.contact.VipPurchaseContact;
import com.android.commonapp.interfaces.BasePresenterImpl;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: vip购买
 */

public class VipPurchasePresenter extends BasePresenterImpl<VipPurchaseContact.view> implements VipPurchaseContact.presenter {

    public VipPurchasePresenter(VipPurchaseContact.view view) {
        super(view);
    }


}
