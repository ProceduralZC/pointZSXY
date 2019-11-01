package com.system.manager.web.model;

import com.system.user.entity.SysUsers;

public class MainCollegeTypeModel{
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 知识名称
	 */
	private String collegename;
	/**
	 * 知识类型
	 */
	private String collegetype;
	/**
	 * 知识类型code
	 */
	private String collegetypecode;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	
	/**
	 * 知识图片资源
	 */
	private Integer collegeImgId;
	/**
	 * 日期
	 */
	private String collegetime;
	
	/**
	 * 描述
	 */
	private String collegecontent;
	/**
	 * 提交人
	 */
	private String collegeuser;
	
	 /**
     * 当前登录人
     */
    private SysUsers sysUsers;
	/**
	 * 备注
	 */
	private String remark;
	
    private String beginDate;
	
	private String endDate;

	public Integer getCollegeImgId() {
		return collegeImgId;
	}

	public void setCollegeImgId(Integer collegeImgId) {
		this.collegeImgId = collegeImgId;
	}

	public SysUsers getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
	}

	public String getCollegetypecode() {
		return collegetypecode;
	}

	public void setCollegetypecode(String collegetypecode) {
		this.collegetypecode = collegetypecode;
	}

	public String getCollegeuser() {
		return collegeuser;
	}

	public void setCollegeuser(String collegeuser) {
		this.collegeuser = collegeuser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
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
	
	
}
