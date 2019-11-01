package com.system.manager.web.model;


public class VersionModel {

	private String updateDate;
	
	private Integer status;
	
	/**
	 * APK文件
	 */
	private Integer apkId;
	/**
	 * 版本号
	 */
	private String versionNum;
	
	/**
	 * 更新内容
	 */
	private String content;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	public Integer getApkId() {
		return apkId;
	}

	public void setApkId(Integer apkId) {
		this.apkId = apkId;
	}
	
}
