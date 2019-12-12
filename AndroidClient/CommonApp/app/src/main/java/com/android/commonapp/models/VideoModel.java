package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 视频课时
 */

public class VideoModel implements Serializable {
    //精选课程
    private String knowledgesubclasstimename;//": "css3前段开发神器",
    private String knowledgesubclasstimeaddtime;//": "2017-12-13 08:23:56",
    private String knowledgesubclasstimenum;//": "5:40",
    private String typeid;//": 1,
    private String remark;//": "爱的噶发",
    private String id;//": 2,
    private String knowledgesubclassurl;//": "www.baidu.com"
    //精品系列知识
    private String jpsubclasstimenum;//": "2",
    private String jpsubclasstimeaddtime;//": "2017-12-10 21:52:44",
    private String jpsubclasstimename;//": "啥地方",
    private String jpsubclassurl;//": "3"
    //知识路径
    private String pathsubclasstimenum;//": "2:08",
    private String pathsubclassurl;//": "www.baidu.com",
    private String pathsubclasstimename;//": "课时1",
    private String pathsubclasstimeaddtime;//": "2017-12-10 19:46:46"
    //知识课件
    private String subclassurl;//": "吧",
    private String subclasstimeaddtime;//": "2017-12-05 12:09:34",
    private String subclasstimenum;//": "熬点饭",
    private String subclasstimename;//": "阿尔法"

    public String getSubclassurl() {
        return subclassurl;
    }

    public void setSubclassurl(String subclassurl) {
        this.subclassurl = subclassurl;
    }

    public String getSubclasstimeaddtime() {
        return subclasstimeaddtime;
    }

    public void setSubclasstimeaddtime(String subclasstimeaddtime) {
        this.subclasstimeaddtime = subclasstimeaddtime;
    }

    public String getSubclasstimenum() {
        return subclasstimenum;
    }

    public void setSubclasstimenum(String subclasstimenum) {
        this.subclasstimenum = subclasstimenum;
    }

    public String getSubclasstimename() {
        return subclasstimename;
    }

    public void setSubclasstimename(String subclasstimename) {
        this.subclasstimename = subclasstimename;
    }

    public String getPathsubclasstimenum() {
        return pathsubclasstimenum;
    }

    public void setPathsubclasstimenum(String pathsubclasstimenum) {
        this.pathsubclasstimenum = pathsubclasstimenum;
    }

    public String getPathsubclassurl() {
        return pathsubclassurl;
    }

    public void setPathsubclassurl(String pathsubclassurl) {
        this.pathsubclassurl = pathsubclassurl;
    }

    public String getPathsubclasstimename() {
        return pathsubclasstimename;
    }

    public void setPathsubclasstimename(String pathsubclasstimename) {
        this.pathsubclasstimename = pathsubclasstimename;
    }

    public String getPathsubclasstimeaddtime() {
        return pathsubclasstimeaddtime;
    }

    public void setPathsubclasstimeaddtime(String pathsubclasstimeaddtime) {
        this.pathsubclasstimeaddtime = pathsubclasstimeaddtime;
    }

    public String getJpsubclasstimenum() {
        return jpsubclasstimenum;
    }

    public void setJpsubclasstimenum(String jpsubclasstimenum) {
        this.jpsubclasstimenum = jpsubclasstimenum;
    }

    public String getJpsubclasstimeaddtime() {
        return jpsubclasstimeaddtime;
    }

    public void setJpsubclasstimeaddtime(String jpsubclasstimeaddtime) {
        this.jpsubclasstimeaddtime = jpsubclasstimeaddtime;
    }

    public String getJpsubclasstimename() {
        return jpsubclasstimename;
    }

    public void setJpsubclasstimename(String jpsubclasstimename) {
        this.jpsubclasstimename = jpsubclasstimename;
    }

    public String getJpsubclassurl() {
        return jpsubclassurl;
    }

    public void setJpsubclassurl(String jpsubclassurl) {
        this.jpsubclassurl = jpsubclassurl;
    }

    public String getKnowledgesubclasstimename() {
        return knowledgesubclasstimename;
    }

    public void setKnowledgesubclasstimename(String knowledgesubclasstimename) {
        this.knowledgesubclasstimename = knowledgesubclasstimename;
    }

    public String getKnowledgesubclasstimeaddtime() {
        return knowledgesubclasstimeaddtime;
    }

    public void setKnowledgesubclasstimeaddtime(String knowledgesubclasstimeaddtime) {
        this.knowledgesubclasstimeaddtime = knowledgesubclasstimeaddtime;
    }

    public String getKnowledgesubclasstimenum() {
        return knowledgesubclasstimenum;
    }

    public void setKnowledgesubclasstimenum(String knowledgesubclasstimenum) {
        this.knowledgesubclasstimenum = knowledgesubclasstimenum;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKnowledgesubclassurl() {
        return knowledgesubclassurl;
    }

    public void setKnowledgesubclassurl(String knowledgesubclassurl) {
        this.knowledgesubclassurl = knowledgesubclassurl;
    }
}
