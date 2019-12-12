package com.android.commonapp.models;

import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 系统消息
 */

public class MessageModel implements Serializable {
    private String id;//": "2017-12-10 20:31:58",
    private String msgaddtime;//": "2017-12-18 16:23:40",
    private String remark;//": "备注一下信息",
    private String msgtypecode;//": 1,
    private String msgcontent;//": "一点知识学院发布啦.....<br /><br /><div class=\"c-row c-gap-top-small\">\t<div class=\"general_image_pic c-span6\">\t\t<a class=\"c-img6\" href=\"http://www.baidu.com/link?url=JPow1YeXHCCkz0wCMObMsthnvLWx1JzeTlaInuEDgvlulg2RrHjOwNXqPtttRhBe\" target=\"_blank\"><img class=\"c-img c-img6\" src=\"https://ss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=991890567,3530662074&amp;fm=58&amp;s=0FF6E812C5A54D035854BAF400000035\" style=\"height:75px;\" /></a>\t</div>\t<div class=\"c-span18 c-span-last\">\t\t<div class=\"c-abstract\">\t\t\t网易旗下利用大数据技术提供移动互联网应用的子公司,过去8年,先后推出有道词典、有道<em>翻译</em>官、有道云笔记、惠惠网、有道推广、有道精品课、有道口语大师等系列产品,总...\t\t</div>\t\t<div class=\"f13\">\t\t\t<a target=\"_blank\" href=\"http://www.baidu.com/link?url=JPow1YeXHCCkz0wCMObMsthnvLWx1JzeTlaInuEDgvlulg2RrHjOwNXqPtttRhBe\" class=\"c-showurl\">dict.youdao.com/&nbsp;</a><span class=\"c-icons-outer\"><span class=\"c-icons-inner\"></span></span>&nbsp;-&nbsp;<a href=\"http://cache.baiducontent.com/c?m=9f65cb4a8c8507ed4fece76310408c375f438014778a97432c898448e435061e5a72a6e667741f13a3c06b1477b83f5cfd874665367337c19ddffe39cacae63f59fc&amp;p=937fd31d85cc43e40cbd9b7e0e1d80&amp;newp=b47cde16d9c111a058ee9f277f4e81231610db2151d4d11e6b82c825d7331b001c3bbfb423241b01d6c0766202af4c5fedf6327033012ba3dda5c91d9fb4c57479&amp;user=baidu&amp;fm=sc&amp;query=%B7%AD%D2%EB%D4%DA%CF%DF&amp;qid=e366533200021d86&amp;p1=8\" target=\"_blank\" class=\"m\">百度快照</a><span class=\"c-pingjia\">&nbsp;-&nbsp;<a href=\"https://www.baidu.com/tools?url=http%3A%2F%2Fwww.baidu.com%2Flink%3Furl%3DJPow1YeXHCCkz0wCMObMsthnvLWx1JzeTlaInuEDgvlulg2RrHjOwNXqPtttRhBe&amp;jump=https%3A%2F%2Fkoubei.baidu.com%2Fp%2Fsentry%3Ftitle%3D%01%E6%9C%89%E9%81%93%01%E9%A6%96%E9%A1%B5%01%26q%3D%E7%BF%BB%E8%AF%91%E5%9C%A8%E7%BA%BF%26from%3Dps_pc4&amp;key=surl\" target=\"_blank\" class=\"m\">691条评价</a></span>\t\t</div>\t</div></div><br />",
    private String msgifread;//": null,
    private String msgtitle;//": "版本发布通知"

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMsgaddtime() {
        return msgaddtime;
    }

    public void setMsgaddtime(String msgaddtime) {
        this.msgaddtime = msgaddtime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getMsgtypecode() {
        return msgtypecode;
    }

    public void setMsgtypecode(String msgtypecode) {
        this.msgtypecode = msgtypecode;
    }

    public String getMsgcontent() {
        return msgcontent;
    }

    public void setMsgcontent(String msgcontent) {
        this.msgcontent = msgcontent;
    }

    public String getMsgifread() {
        return msgifread;
    }

    public void setMsgifread(String msgifread) {
        this.msgifread = msgifread;
    }

    public String getMsgtitle() {
        return msgtitle;
    }

    public void setMsgtitle(String msgtitle) {
        this.msgtitle = msgtitle;
    }
}
