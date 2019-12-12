package com.android.commonapp.config;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 接口地址
 */

public interface URLConfig {

    //登录
    String login = "onepoint_college/ms/userlogin";
    //注册
    String register = "onepoint_college/ms/userRegister";
    /*** 测试上传图片*/
    String uploadFile = "http://192.168.31.190:8080/FileUploadTest/upload";

    String test = "materials/ms/materialsHouseOutList/{page}/{size}";

    //加载图片
    String loadimage = "onepoint_college/ms/showimage?in=";

    //首页分类
    String maintype = "onepoint_college/ms/queryAllCollegetypeBytypeInfo";
    //首页轮播图
    String mainluncher = "onepoint_college/ms/queryAllPhoneCynamic";
    //精品课程
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhoneKnowledgeCoursepage/1/2
    String jpcourse = "onepoint_college/ms/queryAllPhoneKnowledgeCoursepage/{page}/{size}";
    //知识课件
//    http://127.0.0.1:8089/onepoint_college/ms//queryAllZSCourseWarepage/{start}/{size}
    String knowledgecourse = "onepoint_college/ms/queryAllZSCourseWarepage/{id}/{page}/{size}";
    //知识课件--课时
//    http://127.0.0.1:8089/onepoint_college/ms//queryAllPhoneSubclassTimepage/{start}/{size}
    String coursetime = "onepoint_college/ms/queryAllPhoneSubclassTimepage/{id}/{page}/{size}";

    //知识路径
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhoneCoursePathpage/{start}/{size}
    String knowledgepath = "onepoint_college/ms/queryAllPhoneCoursePathpage/{id}/{page}/{size}";
    //知识路径--课时
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhonePathSubclassTimepage/{id}/{start}/{size}
    String pathtime = "onepoint_college/ms/queryAllPhonePathSubclassTimepage/{id}/{page}/{size}";

    //精品系列知识
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllBoutiqueseriespage/{start}/{size}
    String knowledgebouti = "onepoint_college/ms/queryAllBoutiqueseriespage/{id}/{page}/{size}";

    //精品系列知识--课时
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllJPSubclassTimeSearchpage/{start}/{size}
    String jpxlzstime = "onepoint_college/ms/queryAllJPSubclassTimeSearchpage/{id}/{page}/{size}";

    //热门知识
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhoneHotSubclassTime/{typeid}/{start}/{size}
    String knowledgehot = "onepoint_college/ms/queryAllPhoneHotSubclassTime/{typeid}/{page}/{size}";

    //精选课程视频
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhoneKnowledgeSubclassTimepage/{id}/{start}/{size}
    String jingxuankechengvideo = "onepoint_college/ms/queryAllPhoneKnowledgeSubclassTimepage/{id}/{page}/{size}";

    //系统消息
//    http://127.0.0.1:8089/onepoint_college/ms/queryAllPhoneMessagepage/{start}/{size}
    String systemmsg = "onepoint_college/ms/queryAllPhoneMessagepage/{page}/{size}";
    //版本信息
//    http://127.0.0.1:8089/onepoint_college/ms/versionUpdate
    String versionInfo = "onepoint_college/ms/versionUpdate";

    //消息详情
//    http://127.0.0.1:8089/onepoint_college/ms/getMessageWeb/{id}
    String messagedetail = "onepoint_college/ms/getMessageWeb/";
    //学习历史
//    http://127.0.0.1:8089/onepoint_college/ms/queryStudentHistorypage/{id}/{start}/{size}
    String studenthistory = "onepoint_college/ms/queryStudentHistorypage/{id}/{page}/{size}";
    //添加学习记录
//    http://127.0.0.1:8089/onepoint_college/ms/addStudentHistory/{id}/{type}/{userid}
    String addstudenthistory = "onepoint_college/ms/addStudentHistory/{id}/{type}/{userid}";
    //意见反馈
//    http://127.0.0.1:8089/onepoint_college/ms/addfeedback
    String addfeedback = "onepoint_college/ms/addfeedback";
    //更新用户信息
//    http://127.0.0.1:8089/onepoint_college/ms/updateUserDate/{id}
    String updateUserinfo = "onepoint_college/ms/updateUserDate/{id}";
}
