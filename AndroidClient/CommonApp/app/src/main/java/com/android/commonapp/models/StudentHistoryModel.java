package com.android.commonapp.models;
import java.io.Serializable;
/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 学习历史
 */
public class StudentHistoryModel implements Serializable {
    private String id;//id
    private String shichang;//": "2小时10分",
    private String content;//": "",
    private String addtime;//": "2017-12-04 19:54:13",
    private String title;//": "Java语法",
    private String collegeimage;//": "MAINTYPE/1512895979504-94-.png",
    private String remark;//": "暂无",
    private String keshi;//": "3",
    private String userid;//": 1,
    private String xxpeople;//": "68",
    private String type;//": 1,
    private String imageid;//": 8,
    private String courseid;//": 1

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShichang() {
        return shichang;
    }

    public void setShichang(String shichang) {
        this.shichang = shichang;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCollegeimage() {
        return collegeimage;
    }

    public void setCollegeimage(String collegeimage) {
        this.collegeimage = collegeimage;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKeshi() {
        return keshi;
    }

    public void setKeshi(String keshi) {
        this.keshi = keshi;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getXxpeople() {
        return xxpeople;
    }

    public void setXxpeople(String xxpeople) {
        this.xxpeople = xxpeople;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid;
    }

    public String getCourseid() {
        return courseid;
    }

    public void setCourseid(String courseid) {
        this.courseid = courseid;
    }
}
