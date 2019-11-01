package com.system.manager.web.model;
public class MainCourseWareModel{
	 private Integer id;
	/**
	 *课件名称
	 */
	private String coursewarename;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	
	/**
	 * 知识图片id
	 */
	private Integer coursewareimageid;
	/**
	 * 课时
	 */
	private String coursewarenum;
	/**
	 * 时长
	 */
	private String coursewaretimelong;
	/**
	 * 添加时间
	 */
	private String coursewareaddtime;
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

	public String getCoursewarename() {
		return coursewarename;
	}

	public void setCoursewarename(String coursewarename) {
		this.coursewarename = coursewarename;
	}

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public String getCoursewarenum() {
		return coursewarenum;
	}

	public void setCoursewarenum(String coursewarenum) {
		this.coursewarenum = coursewarenum;
	}

	public String getCoursewaretimelong() {
		return coursewaretimelong;
	}

	public void setCoursewaretimelong(String coursewaretimelong) {
		this.coursewaretimelong = coursewaretimelong;
	}

	public String getCoursewareaddtime() {
		return coursewareaddtime;
	}

	public void setCoursewareaddtime(String coursewareaddtime) {
		this.coursewareaddtime = coursewareaddtime;
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
