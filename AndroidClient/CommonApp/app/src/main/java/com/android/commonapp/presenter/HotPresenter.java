package com.android.commonapp.presenter;
import com.android.commonapp.contact.BoutiqueContact;
import com.android.commonapp.contact.HotContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.BoutiqueModel;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe: 热门知识
 */

public class HotPresenter extends BasePresenterImpl<HotContact.view> implements HotContact.presenter {

    public HotPresenter(HotContact.view view) {
        super(view);
    }

    @Override
    public void getKnowledgeHot(String typeid,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("数据传输中，请稍后",false,false);
        RetrofitFactory.getInstance().api().knowledgehot(typeid,page, size)
            .enqueue(new CommonCallback<List<HotModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<HotModel>> t) throws Exception {
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
