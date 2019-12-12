package com.android.commonapp.presenter;

import com.android.commonapp.contact.VideoContact;
import com.android.commonapp.interfaces.BasePresenterImpl;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.VideoModel;
import com.android.commonapp.network.CommonCallback;
import com.android.commonapp.network.RetrofitFactory;

import java.util.List;
/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe: 视频资源
 */
public class VideoPresenter extends BasePresenterImpl<VideoContact.view> implements VideoContact.presenter {
    public VideoPresenter(VideoContact.view view) {
        super(view);
    }
    /**
     * 精选课程
     * @param id
     * @param page
     * @param size
     */
    @Override
    public void getVideoInfo(String id,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("资源获取中，请稍后",false,true);
        RetrofitFactory.getInstance().api().jingxuankechengVideo(id,page, size)
            .enqueue(new CommonCallback<List<VideoModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VideoModel>> t) throws Exception {
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

    /**
     * 精品系列知识
     * @param id
     * @param page
     * @param size
     */
    @Override
    public void getJPXLZSVideoInfo(String id,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("资源获取中，请稍后",false,true);
        RetrofitFactory.getInstance().api().jpxlzstimeinter(id,page, size)
            .enqueue(new CommonCallback<List<VideoModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VideoModel>> t) throws Exception {
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
    /**
     * 知识路径
     * @param id
     * @param page
     * @param size
     */
    @Override
    public void getZSLJVideoInfo(String id,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("资源获取中，请稍后",false,true);
        RetrofitFactory.getInstance().api().pathtime(id,page, size)
            .enqueue(new CommonCallback<List<VideoModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VideoModel>> t) throws Exception {
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
    /**
     * 知识课件
     * @param id
     * @param page
     * @param size
     */
    @Override
    public void getZSKJVideoInfo(String id,String page, String size) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("资源获取中，请稍后",false,true);
        RetrofitFactory.getInstance().api().coursetime(id,page, size)
            .enqueue(new CommonCallback<List<VideoModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VideoModel>> t) throws Exception {
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
    /**
     * 添加学习记录
     * @param id
     * @param type
     * @param userid
     */
    @Override
    public void addStudentHistory(String id,String type, String userid) {
        //参数1 加载说明   参数2 是否需要触屏消失 参数3 是否需要显示进度条
        view.showLoadingDialog("资源获取中，请稍后",false,false);
        RetrofitFactory.getInstance().api().addstudenthistory(id,type, userid)
            .enqueue(new CommonCallback<List<VideoModel>>() {
                @Override
                protected void onSuccess(CommonCallModel<List<VideoModel>> t) throws Exception {
                    view.dismissLoadingDialog();
                    if (t.getData() != null) {
                        view.addsuccess(t.getData(),t.getCode(),t.getMessage());
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
