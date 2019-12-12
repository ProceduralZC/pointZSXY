package com.android.commonapp.models;
import java.io.Serializable;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 版本更新
 */

public class VersionModel implements Serializable {
    private String id;
    private String versionNum;//": "2.0",
    private String content;//": "",
    private apk apk;//": {
    private String status;//": 1,
    private String isdelete;//": 1,
    private String updateDate;//": "2017-11-13 20:43:53",
    private String createDate;//": "2017-11-12 20:44:22",
    private String modifyDate;//": null

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(String versionNum) {
        this.versionNum = versionNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public VersionModel.apk getApk() {
        return apk;
    }

    public void setApk(VersionModel.apk apk) {
        this.apk = apk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(String isdelete) {
        this.isdelete = isdelete;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public class apk implements Serializable {
        private String id;//": 3,
        private String name;//": "beimi-release.apk",
        private String source;//": "/file/2017/11/d5c775d4-3b18-4ce6-a43f-4792f3152209.apk",
        private String size;//": "46.00MB",
        private String type;//": "application/octet-stream",
        private String extname;//": "apk"

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getExtname() {
            return extname;
        }

        public void setExtname(String extname) {
            this.extname = extname;
        }
    }
}
