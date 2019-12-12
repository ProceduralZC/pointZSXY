package com.android.commonapp.network;

import com.android.commonapp.config.URLConfig;
import com.android.commonapp.models.BoutiqueModel;
import com.android.commonapp.models.CommonCallModel;
import com.android.commonapp.models.FeedbackEntity;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.models.KnowledgeModel;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.models.MainPageModel;
import com.android.commonapp.models.MessageModel;
import com.android.commonapp.models.RegisterEntity;
import com.android.commonapp.models.StudentHistoryModel;
import com.android.commonapp.models.StudentModel;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.models.VersionModel;
import com.android.commonapp.models.VideoModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.android.commonapp.util.SharedPreferencesUtilDb.zhiwei;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public interface RetrofitService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @POST(URLConfig.login)
    Call<CommonCallModel<List<LoginEntity>>> login(@Query("username") String username, @Query("password") String password);

    @Multipart
    @POST(URLConfig.uploadFile)
    Call<CommonCallModel<TestModel>> uploadFile(@Part("file\"; filename=\"avatar.png\"") RequestBody file);

    @GET(URLConfig.test)
    Call<CommonCallModel<List<TestModel>>> test(@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.maintype)  //首页分类
    Call<CommonCallModel<List<MainPageModel>>> getType();
    @GET(URLConfig.mainluncher)  //首页轮播图
    Call<CommonCallModel<List<MainPageModel>>> getLuncher();

    @GET(URLConfig.jpcourse)//精品课程
    Call<CommonCallModel<List<StudentModel>>> jpcourse(@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.knowledgecourse)//知识课件
    Call<CommonCallModel<List<KnowledgeModel>>> knowledgecourse(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.coursetime)//知识课件--课时
    Call<CommonCallModel<List<VideoModel>>> coursetime(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.knowledgepath)//知识路径
    Call<CommonCallModel<List<KnowledgePathModel>>> knowledgepath(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.pathtime)//知识路径--课时
    Call<CommonCallModel<List<VideoModel>>> pathtime(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.knowledgebouti)//精品系列知识
    Call<CommonCallModel<List<BoutiqueModel>>> knowledgeboutique(@Path("id") String id,@Path("page") String page, @Path("size") String size);
    @GET(URLConfig.jpxlzstime)//精品系列知识---课时
    Call<CommonCallModel<List<VideoModel>>> jpxlzstimeinter(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.knowledgehot)//热门知识
    Call<CommonCallModel<List<HotModel>>> knowledgehot(@Path("typeid") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.jingxuankechengvideo)//精选课程视频
    Call<CommonCallModel<List<VideoModel>>> jingxuankechengVideo(@Path("id") String id,@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.systemmsg)//系统消息
    Call<CommonCallModel<List<MessageModel>>> systemmsg(@Path("page") String page, @Path("size") String size);

    @GET(URLConfig.versionInfo)//版本信息
    Call<CommonCallModel<List<VersionModel>>> versionInfo();

    @GET(URLConfig.studenthistory)//学习历史
    Call<CommonCallModel<List<StudentHistoryModel>>> studenthistory(@Path("id") String id, @Path("page") String page, @Path("size") String size);

    @GET(URLConfig.addstudenthistory)//添加学习记录
    Call<CommonCallModel<List<VideoModel>>> addstudenthistory(@Path("id") String id, @Path("type") String type, @Path("userid") String userid);

    @POST(URLConfig.addfeedback)//意见反馈
    Call<CommonCallModel<List<FeedbackEntity>>> feedback(@Query("contact") String contact, @Query("feedbackinfo") String feedbackinfo);

    @POST(URLConfig.updateUserinfo)
    Call<CommonCallModel<List<LoginEntity>>> updateUserInfo(@Path("id") String id, @Query("nickname") String nickname
    ,@Query("zhiweiinfo") String zhiweiinfo, @Query("jiesao") String jiesao,@Query("sex") String sex);

    @POST(URLConfig.register)
    Call<CommonCallModel<List<RegisterEntity>>> register(@Query("username") String username, @Query("password") String password);

}
