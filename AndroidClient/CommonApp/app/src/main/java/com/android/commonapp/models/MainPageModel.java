package com.android.commonapp.models;

import java.io.Serializable;
import java.util.List;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe:
 */

public class MainPageModel implements Serializable {
    private List<courseware> courseware;
    private List<courseware> path;
    private List<courseware> hot;
    private List<courseware> boutique;
    private String id;//": 1,
    private String cynamictitle;//": "版本发布",
    private String cynamiccontent;//": "版本发布信息1.UI  2.功能",
    private String collegeimage;//": "MAINTYPE/1513688483767-48-.png",
    private String cynamicimageid;//": 15,
    private String cynamicaddtime;//": "2017-12-19 21:01:47",
    private String remark;//": "撒的发生的"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCynamictitle() {
        return cynamictitle;
    }

    public void setCynamictitle(String cynamictitle) {
        this.cynamictitle = cynamictitle;
    }

    public String getCynamiccontent() {
        return cynamiccontent;
    }

    public void setCynamiccontent(String cynamiccontent) {
        this.cynamiccontent = cynamiccontent;
    }

    public String getCollegeimage() {
        return collegeimage;
    }

    public void setCollegeimage(String collegeimage) {
        this.collegeimage = collegeimage;
    }

    public String getCynamicimageid() {
        return cynamicimageid;
    }

    public void setCynamicimageid(String cynamicimageid) {
        this.cynamicimageid = cynamicimageid;
    }

    public String getCynamicaddtime() {
        return cynamicaddtime;
    }

    public void setCynamicaddtime(String cynamicaddtime) {
        this.cynamicaddtime = cynamicaddtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<MainPageModel.courseware> getCourseware() {
        return courseware;
    }

    public void setCourseware(List<MainPageModel.courseware> courseware) {
        this.courseware = courseware;
    }

    public List<MainPageModel.courseware> getPath() {
        return path;
    }

    public void setPath(List<MainPageModel.courseware> path) {
        this.path = path;
    }

    public List<MainPageModel.courseware> getHot() {
        return hot;
    }

    public void setHot(List<MainPageModel.courseware> hot) {
        this.hot = hot;
    }

    public List<MainPageModel.courseware> getBoutique() {
        return boutique;
    }

    public void setBoutique(List<MainPageModel.courseware> boutique) {
        this.boutique = boutique;
    }

    public static class courseware implements Serializable{
            private String id;
            private String collegename;//": "HTML5",
            private String collegetype;//": "知识课件",
            private String collegetypecode;//": "1",
            private String collegeimage;//": "SJDT/1512791071932-95-.png",
            private String collegeImgId;//": 7,
            private String collegetime;//": "2017-12-03 14:12:08",
            private String collegecontent;//": "HTML5 是下一代的 HTML。HTML 5 作为新一代的超文本标记语言，增加了许多标签。这些标签不但更有语义，而且功能强大",
            private collegeuser collegeuser;//": {
            private String remark;//": "HTML5"

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCollegename() {
            return collegename;
        }

        public void setCollegename(String collegename) {
            this.collegename = collegename;
        }

        public String getCollegetype() {
            return collegetype;
        }

        public void setCollegetype(String collegetype) {
            this.collegetype = collegetype;
        }

        public String getCollegetypecode() {
            return collegetypecode;
        }

        public void setCollegetypecode(String collegetypecode) {
            this.collegetypecode = collegetypecode;
        }

        public String getCollegeimage() {
            return collegeimage;
        }

        public void setCollegeimage(String collegeimage) {
            this.collegeimage = collegeimage;
        }

        public String getCollegeImgId() {
            return collegeImgId;
        }

        public void setCollegeImgId(String collegeImgId) {
            this.collegeImgId = collegeImgId;
        }

        public String getCollegetime() {
            return collegetime;
        }

        public void setCollegetime(String collegetime) {
            this.collegetime = collegetime;
        }

        public String getCollegecontent() {
            return collegecontent;
        }

        public void setCollegecontent(String collegecontent) {
            this.collegecontent = collegecontent;
        }

        public MainPageModel.collegeuser getCollegeuser() {
            return collegeuser;
        }

        public void setCollegeuser(MainPageModel.collegeuser collegeuser) {
            this.collegeuser = collegeuser;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }

    public static class collegeuser implements Serializable{
        private String id;
        private String username;//": "admin",
        private String truename;//": "系统管理员",
        private String code;//": "1,2,3,4,5,6",
        private String sex;//": "0",
        private String tel;//": "13571893582"

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getTruename() {
            return truename;
        }

        public void setTruename(String truename) {
            this.truename = truename;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getTel() {
            return tel;
        }

        public void setTel(String tel) {
            this.tel = tel;
        }
    }
}
