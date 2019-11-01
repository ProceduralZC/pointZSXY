package com.system.manager.web.model;
public class MainBoutiqueseriesModel{
	 private Integer id;
	/**
	 *精品知识名称
	 */
	private String boutiqueseriesname;
	/**
	 * 精品知识图片路径
	 */
	private String collegeimage;
	
	/**
	 * 精品知识图片id
	 */
	private Integer boutiqueseriesimageid;
	/**
	 * 课时
	 */
	private String boutiqueseriesnum;
	/**
	 * 时长
	 */
	private String boutiqueseriestimelong;
	/**
	 * 添加时间
	 */
	private String boutiqueseriesaddtime;
	/**
	 * 所属类型id---
	 */
	private String typeid;
	
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

	public String getBoutiqueseriesname() {
		return boutiqueseriesname;
	}

	public void setBoutiqueseriesname(String boutiqueseriesname) {
		this.boutiqueseriesname = boutiqueseriesname;
	}


	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public Integer getBoutiqueseriesimageid() {
		return boutiqueseriesimageid;
	}

	public void setBoutiqueseriesimageid(Integer boutiqueseriesimageid) {
		this.boutiqueseriesimageid = boutiqueseriesimageid;
	}

	public String getBoutiqueseriesnum() {
		return boutiqueseriesnum;
	}

	public void setBoutiqueseriesnum(String boutiqueseriesnum) {
		this.boutiqueseriesnum = boutiqueseriesnum;
	}

	public String getBoutiqueseriestimelong() {
		return boutiqueseriestimelong;
	}

	public void setBoutiqueseriestimelong(String boutiqueseriestimelong) {
		this.boutiqueseriestimelong = boutiqueseriestimelong;
	}

	public String getBoutiqueseriesaddtime() {
		return boutiqueseriesaddtime;
	}

	public void setBoutiqueseriesaddtime(String boutiqueseriesaddtime) {
		this.boutiqueseriesaddtime = boutiqueseriesaddtime;
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
