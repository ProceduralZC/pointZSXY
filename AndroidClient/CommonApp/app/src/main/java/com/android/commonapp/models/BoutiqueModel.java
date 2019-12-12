package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 精品系列知识
 */

public class BoutiqueModel implements Serializable {
    private String id;//": "2017-12-10 20:31:58",
    private String boutiqueseriesaddtime;//": "2017-12-10 20:31:58",
    private String boutiqueseriestimelong;//": "10",
    private String boutiqueseriesname;//": "android项目开发",
    private String collegeimage;//": null,
    private String boutiqueseriesnum;//": "2",
    private String typeid;//": "6",
    private String remark;//": "阿斯顿发生",
    private String boutiqueseriesimageid;//": 10

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBoutiqueseriesaddtime() {
        return boutiqueseriesaddtime;
    }

    public void setBoutiqueseriesaddtime(String boutiqueseriesaddtime) {
        this.boutiqueseriesaddtime = boutiqueseriesaddtime;
    }

    public String getBoutiqueseriestimelong() {
        return boutiqueseriestimelong;
    }

    public void setBoutiqueseriestimelong(String boutiqueseriestimelong) {
        this.boutiqueseriestimelong = boutiqueseriestimelong;
    }

    public String getBoutiqueseriesname() {
        return boutiqueseriesname;
    }

    public void setBoutiqueseriesname(String boutiqueseriesname) {
        this.boutiqueseriesname = boutiqueseriesname;
    }

    public String getCollegeimage() {
        return collegeimage;
    }

    public void setCollegeimage(String collegeimage) {
        this.collegeimage = collegeimage;
    }

    public String getBoutiqueseriesnum() {
        return boutiqueseriesnum;
    }

    public void setBoutiqueseriesnum(String boutiqueseriesnum) {
        this.boutiqueseriesnum = boutiqueseriesnum;
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

    public String getBoutiqueseriesimageid() {
        return boutiqueseriesimageid;
    }

    public void setBoutiqueseriesimageid(String boutiqueseriesimageid) {
        this.boutiqueseriesimageid = boutiqueseriesimageid;
    }
}
