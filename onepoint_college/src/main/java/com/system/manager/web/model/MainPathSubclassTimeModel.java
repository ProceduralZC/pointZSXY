package com.system.manager.web.model;


public class MainPathSubclassTimeModel{
	 private Integer id;
	/**
	 *课时名称
	 */
	private String pathsubclasstimename;
	/**
	 * 课时时长
	 */
	private String pathsubclasstimenum;
	/**
	 * 课程链接
	 */
	private String pathsubclassurl;
	/**
	 * 所属类型id---即。选择的添加到哪一个分类下面--通过id关联
	 */
	private String typeid;
	/**
	 * 添加时间
	 */
	private String pathsubclasstimeaddtime;
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

	public String getPathsubclasstimename() {
		return pathsubclasstimename;
	}

	public void setPathsubclasstimename(String pathsubclasstimename) {
		this.pathsubclasstimename = pathsubclasstimename;
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

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getPathsubclasstimeaddtime() {
		return pathsubclasstimeaddtime;
	}

	public void setPathsubclasstimeaddtime(String pathsubclasstimeaddtime) {
		this.pathsubclasstimeaddtime = pathsubclasstimeaddtime;
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
