package com.android.commonapp.presenter;
import com.android.commonapp.contact.IndexContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.MainPageModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;
import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:
 */

public class IndexPresenter extends BasePresenterImpl<IndexContact.view> implements IndexContact.presenter {
    public IndexPresenter(IndexContact.view view) {
        super(view);
    }

    /**
     * 获取首页分类
     */
    @Override
    public void getData(final int type) {
        view.showLoadingDialog("请稍后...",true,true);
        RetrofitFactory.getInstance().api().getType()
            .enqueue(new CommonCallback<List<MainPageModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<MainPageModel>> t) throws Exception {
                    view.dismissLoadingDialog();
                    if (t.getData() != null) {
                        view.success(t.getData(),type);
                    }
                }
                @Override
                protected void onFailure(Throwable e, boolean isNetWorkError, String msg) throws Exception {
                    view.dismissLoadingDialog();
                    view.failure(e, isNetWorkError, msg);
                }
            });
    }

    /**
     * 获取轮播图
     */
    @Override
    public void getMainLuncher(final int type) {
        view.showLoadingDialog("数据传输中，请稍后",true,true);
        RetrofitFactory.getInstance().api().getLuncher()
            .enqueue(new CommonCallback<List<MainPageModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<MainPageModel>> t) throws Exception {
                    view.dismissLoadingDialog();
                    if (t.getData() != null) {
                        view.success(t.getData(),type);
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
