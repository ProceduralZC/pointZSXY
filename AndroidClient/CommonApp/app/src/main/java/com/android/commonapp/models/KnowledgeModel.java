package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 知识课件
 */

public class KnowledgeModel implements Serializable {
    private String id;
    private String collegeimage;//": "MAINTYPE/1512895979504-94-.png",
    private String coursewareimageid;//": 8,
    private String typeid;//": "5",
    private String remark;//": "暂无",
    private String coursewarenum;//": "2",
    private String coursewaretimelong;//": "5",
    private String coursewarename;//": "Java语法",
    private String coursewareaddtime;//": "2017-12-04 19:54:13"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollegeimage() {
        return collegeimage;
    }

    public void setCollegeimage(String collegeimage) {
        this.collegeimage = collegeimage;
    }

    public String getCoursewareimageid() {
        return coursewareimageid;
    }

    public void setCoursewareimageid(String coursewareimageid) {
        this.coursewareimageid = coursewareimageid;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCoursewarenum() {
        return coursewarenum;
    }

    public void setCoursewarenum(String coursewarenum) {
        this.coursewarenum = coursewarenum;
    }

    public String getCoursewaretimelong() {
        return coursewaretimelong;
    }

    public void setCoursewaretimelong(String coursewaretimelong) {
        this.coursewaretimelong = coursewaretimelong;
    }

    public String getCoursewarename() {
        return coursewarename;
    }

    public void setCoursewarename(String coursewarename) {
        this.coursewarename = coursewarename;
    }

    public String getCoursewareaddtime() {
        return coursewareaddtime;
    }

    public void setCoursewareaddtime(String coursewareaddtime) {
        this.coursewareaddtime = coursewareaddtime;
    }
}
