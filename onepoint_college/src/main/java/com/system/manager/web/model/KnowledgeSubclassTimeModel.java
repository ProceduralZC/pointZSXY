package com.system.manager.web.model;


public class KnowledgeSubclassTimeModel{
	 private Integer id;
	/**
	 *课时名称
	 */
	private String knowledgesubclasstimename;
	/**
	 * 课时时长
	 */
	private String knowledgesubclasstimenum;
	/**
	 * 课程链接
	 */
	private String knowledgesubclassurl;
	/**
	 * 所属类型id---即。选择的添加到哪一个分类下面--通过id关联
	 */
	private Integer typeid;
	/**
	 * 添加时间
	 */
	private String knowledgesubclasstimeaddtime;
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

	public String getKnowledgesubclasstimename() {
		return knowledgesubclasstimename;
	}

	public void setKnowledgesubclasstimename(String knowledgesubclasstimename) {
		this.knowledgesubclasstimename = knowledgesubclasstimename;
	}

	public String getKnowledgesubclasstimenum() {
		return knowledgesubclasstimenum;
	}

	public void setKnowledgesubclasstimenum(String knowledgesubclasstimenum) {
		this.knowledgesubclasstimenum = knowledgesubclasstimenum;
	}

	public String getKnowledgesubclassurl() {
		return knowledgesubclassurl;
	}

	public void setKnowledgesubclassurl(String knowledgesubclassurl) {
		this.knowledgesubclassurl = knowledgesubclassurl;
	}

	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getKnowledgesubclasstimeaddtime() {
		return knowledgesubclasstimeaddtime;
	}

	public void setKnowledgesubclasstimeaddtime(String knowledgesubclasstimeaddtime) {
		this.knowledgesubclasstimeaddtime = knowledgesubclasstimeaddtime;
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
