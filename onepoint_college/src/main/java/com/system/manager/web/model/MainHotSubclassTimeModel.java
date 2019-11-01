package com.system.manager.web.model;


public class MainHotSubclassTimeModel{
	 private Integer id;
	/**
	 *课时名称
	 */
	private String hotsubclasstimename;
	/**
	 * 课时时长
	 */
	private String hotsubclasstimenum;
	/**
	 * 课程链接
	 */
	private String hotsubclassurl;
	/**
	 * 所属类型id---即。选择的添加到哪一个分类下面--通过id关联
	 */
	private String typeid;
	/**
	 * 添加时间
	 */
	private String hotsubclasstimeaddtime;
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

	public String getHotsubclasstimename() {
		return hotsubclasstimename;
	}

	public void setHotsubclasstimename(String hotsubclasstimename) {
		this.hotsubclasstimename = hotsubclasstimename;
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

	public String getHotsubclasstimeaddtime() {
		return hotsubclasstimeaddtime;
	}

	public void setHotsubclasstimeaddtime(String hotsubclasstimeaddtime) {
		this.hotsubclasstimeaddtime = hotsubclasstimeaddtime;
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
