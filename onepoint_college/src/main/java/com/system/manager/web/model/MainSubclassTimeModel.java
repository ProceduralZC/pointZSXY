package com.system.manager.web.model;


public class MainSubclassTimeModel{
	 private Integer id;
	/**
	 *课时名称
	 */
	private String subclasstimename;
	/**
	 * 课时时长
	 */
	private String subclasstimenum;
	/**
	 * 课程链接
	 */
	private String subclassurl;
	/**
	 * 所属类型id---即。选择的添加到哪一个分类下面--通过id关联
	 */
	private String typeid;
	/**
	 * 添加时间
	 */
	private String subclasstimeaddtime;
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

	public String getSubclasstimename() {
		return subclasstimename;
	}

	public void setSubclasstimename(String subclasstimename) {
		this.subclasstimename = subclasstimename;
	}

	public String getSubclasstimenum() {
		return subclasstimenum;
	}

	public void setSubclasstimenum(String subclasstimenum) {
		this.subclasstimenum = subclasstimenum;
	}

	public String getSubclassurl() {
		return subclassurl;
	}

	public void setSubclassurl(String subclassurl) {
		this.subclassurl = subclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getSubclasstimeaddtime() {
		return subclasstimeaddtime;
	}

	public void setSubclasstimeaddtime(String subclasstimeaddtime) {
		this.subclasstimeaddtime = subclasstimeaddtime;
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
