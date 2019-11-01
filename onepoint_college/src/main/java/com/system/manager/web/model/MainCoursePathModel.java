package com.system.manager.web.model;

import javax.persistence.Column;

public class MainCoursePathModel{
	 private Integer id;
	/**
	 *路径名称
	 */
	private String coursepathname;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	private Integer coursewareimageid;
	/**
	 * 课时
	 */
	private String coursepathnum;
	/**
	 * 时长
	 */
	private String coursepathtimelong;
	/**
	 * 添加时间
	 */
	private String coursepathaddtime;
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

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public Integer getCoursewareimageid() {
		return coursewareimageid;
	}

	public void setCoursewareimageid(Integer coursewareimageid) {
		this.coursewareimageid = coursewareimageid;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCoursepathname() {
		return coursepathname;
	}

	public void setCoursepathname(String coursepathname) {
		this.coursepathname = coursepathname;
	}

	public String getCoursepathnum() {
		return coursepathnum;
	}

	public void setCoursepathnum(String coursepathnum) {
		this.coursepathnum = coursepathnum;
	}

	public String getCoursepathtimelong() {
		return coursepathtimelong;
	}

	public void setCoursepathtimelong(String coursepathtimelong) {
		this.coursepathtimelong = coursepathtimelong;
	}

	public String getCoursepathaddtime() {
		return coursepathaddtime;
	}

	public void setCoursepathaddtime(String coursepathaddtime) {
		this.coursepathaddtime = coursepathaddtime;
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
