package com.android.commonapp.contact;

import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.interfaces.BaseView;
import com.android.commonapp.models.BoutiqueModel;
import com.android.commonapp.models.VideoModel;

import java.util.List;

/**
 * @date: 2017/11/23.
 * @author: CHEN
 * @describe:  视频路径
 */

public class VideoContact {

    public interface view extends BaseView {
        void success(List<VideoModel> modelList);
        void addsuccess(List<VideoModel> modelList,String code ,String massage);

        void failure(Throwable e, boolean isNetWorkError, String msg);
    }

    public interface presenter extends BasePresenter {
        void getVideoInfo(String id,String page, String size);//精选课程
        void getJPXLZSVideoInfo(String id,String page, String size);//精品系列知识
        void getZSLJVideoInfo(String id,String page, String size);//知识路径
        void getZSKJVideoInfo(String id,String page, String size);//知识课件
        void addStudentHistory(String id,String type, String userid);//添加学习记录

    }
}
