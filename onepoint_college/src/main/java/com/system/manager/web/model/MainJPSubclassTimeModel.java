package com.system.manager.web.model;


public class MainJPSubclassTimeModel{
	 private Integer id;
	/**
	 *课时名称
	 */
	private String jpsubclasstimename;
	/**
	 * 课时时长
	 */
	private String jpsubclasstimenum;
	/**
	 * 课程链接
	 */
	private String jpsubclassurl;
	/**
	 * 所属类型id---即。选择的添加到哪一个分类下面--通过id关联
	 */
	private String typeid;
	/**
	 * 添加时间
	 */
	private String jpsubclasstimeaddtime;
	/**
	 * 备注
	 */
	private String remark;
	
    private String beginDate;
	
	private String endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJpsubclasstimename() {
		return jpsubclasstimename;
	}

	public void setJpsubclasstimename(String jpsubclasstimename) {
		this.jpsubclasstimename = jpsubclasstimename;
	}

	public String getJpsubclasstimenum() {
		return jpsubclasstimenum;
	}

	public void setJpsubclasstimenum(String jpsubclasstimenum) {
		this.jpsubclasstimenum = jpsubclasstimenum;
	}

	public String getJpsubclassurl() {
		return jpsubclassurl;
	}

	public void setJpsubclassurl(String jpsubclassurl) {
		this.jpsubclassurl = jpsubclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getJpsubclasstimeaddtime() {
		return jpsubclasstimeaddtime;
	}

	public void setJpsubclasstimeaddtime(String jpsubclasstimeaddtime) {
		this.jpsubclasstimeaddtime = jpsubclasstimeaddtime;
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
	
	
}
