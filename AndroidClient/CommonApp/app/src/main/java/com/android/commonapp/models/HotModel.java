package com.android.commonapp.models;
import java.io.Serializable;
/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 热门知识
 */

public class HotModel implements Serializable {
    private String id ;
    private String hotsubclasstimenum;//": "5:04",
    private String hotsubclassurl;//": "www.baidu.com",
    private String typeid;//": "",
    private String remark;//": "啊发生过",
    private String hotsubclasstimeaddtime;//": "2017-12-10 21:50:45",
    private String hotsubclasstimename;//": "课时1"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHotsubclasstimenum() {
        return hotsubclasstimenum;
    }

    public void setHotsubclasstimenum(String hotsubclasstimenum) {
        this.hotsubclasstimenum = hotsubclasstimenum;
    }

    public String getHotsubclassurl() {
        return hotsubclassurl;
    }

    public void setHotsubclassurl(String hotsubclassurl) {
        this.hotsubclassurl = hotsubclassurl;
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

    public String getHotsubclasstimeaddtime() {
        return hotsubclasstimeaddtime;
    }

    public void setHotsubclasstimeaddtime(String hotsubclasstimeaddtime) {
        this.hotsubclasstimeaddtime = hotsubclasstimeaddtime;
    }

    public String getHotsubclasstimename() {
        return hotsubclasstimename;
    }

    public void setHotsubclasstimename(String hotsubclasstimename) {
        this.hotsubclasstimename = hotsubclasstimename;
    }
}
