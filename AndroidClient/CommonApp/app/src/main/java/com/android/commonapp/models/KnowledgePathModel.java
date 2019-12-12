package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 知识路径
 */

public class KnowledgePathModel implements Serializable {
    private String id;//id
    private String coursepathname;//": "变量与类型",
    private String coursepathnum;//": " 2",
    private String coursepathtimelong;//": "5",
    private String typeid;//": "4",
    private String coursepathaddtime;//": "2017-12-10 18:37:29",
    private String remark;//": "啊山东干"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCoursepathname() {
        return coursepathname;
    }

    public void setCoursepathname(String coursepathname) {
        this.coursepathname = coursepathname;
    }

    public String getCoursepathnum() {
        return coursepathnum;
    }

    public void setCoursepathnum(String coursepathnum) {
        this.coursepathnum = coursepathnum;
    }

    public String getCoursepathtimelong() {
        return coursepathtimelong;
    }

    public void setCoursepathtimelong(String coursepathtimelong) {
        this.coursepathtimelong = coursepathtimelong;
    }

    public String getTypeid() {
        return typeid;
    }

    public void setTypeid(String typeid) {
        this.typeid = typeid;
    }

    public String getCoursepathaddtime() {
        return coursepathaddtime;
    }

    public void setCoursepathaddtime(String coursepathaddtime) {
        this.coursepathaddtime = coursepathaddtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
