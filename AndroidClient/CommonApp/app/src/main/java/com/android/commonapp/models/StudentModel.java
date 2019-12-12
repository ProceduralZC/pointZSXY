package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe:
 */

public class StudentModel implements Serializable {
    private String id;//": 1,
    private String collegeimage;//": "",
    private String remark;//": "案是否单身",
    private String knowledgepeople;//": null,
    private String knowledgeimageid;//": null,
    private String knowledgeifprice;//": "1",
    private String knowledgevipprice;//": 1,
    private String knowledgename;//": "0基础学习前段",
    private String knowledgeaddtime;//": "2017-12-12 18:18:44",
    private String knowledgeinitprice;//": 2,
    private String knowledgetimelong;//": "3:08",
    private String knowledgenum;//": 2

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getKnowledgepeople() {
        return knowledgepeople;
    }

    public void setKnowledgepeople(String knowledgepeople) {
        this.knowledgepeople = knowledgepeople;
    }

    public String getKnowledgeimageid() {
        return knowledgeimageid;
    }

    public void setKnowledgeimageid(String knowledgeimageid) {
        this.knowledgeimageid = knowledgeimageid;
    }

    public String getKnowledgeifprice() {
        return knowledgeifprice;
    }

    public void setKnowledgeifprice(String knowledgeifprice) {
        this.knowledgeifprice = knowledgeifprice;
    }

    public String getKnowledgevipprice() {
        return knowledgevipprice;
    }

    public void setKnowledgevipprice(String knowledgevipprice) {
        this.knowledgevipprice = knowledgevipprice;
    }

    public String getKnowledgename() {
        return knowledgename;
    }

    public void setKnowledgename(String knowledgename) {
        this.knowledgename = knowledgename;
    }

    public String getKnowledgeaddtime() {
        return knowledgeaddtime;
    }

    public void setKnowledgeaddtime(String knowledgeaddtime) {
        this.knowledgeaddtime = knowledgeaddtime;
    }

    public String getKnowledgeinitprice() {
        return knowledgeinitprice;
    }

    public void setKnowledgeinitprice(String knowledgeinitprice) {
        this.knowledgeinitprice = knowledgeinitprice;
    }

    public String getKnowledgetimelong() {
        return knowledgetimelong;
    }

    public void setKnowledgetimelong(String knowledgetimelong) {
        this.knowledgetimelong = knowledgetimelong;
    }

    public String getKnowledgenum() {
        return knowledgenum;
    }

    public void setKnowledgenum(String knowledgenum) {
        this.knowledgenum = knowledgenum;
    }
}
