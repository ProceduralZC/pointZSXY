package com.system.manager.web.model;

import javax.persistence.Column;

//动态--首页
public class StudentHistoryModel{
    private Integer id;
    /**
	 *课程id
	 */
	private Integer courseid;
	/**
	 *标题
	 */
	private String title;
	/**
	 *类型
	 */
	private Integer type;
	/**
	 * 内容信息
	 */
	private String content;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	private Integer imageid;
	/**
	 * 用户id
	 */
	private Integer userid;
	/**
	 * 添加时间
	 */
	private String addtime;
	/**
	 * 课时
	 */
	private String keshi;
	/**
	 * 时长
	 */
	private String shichang;
	/**
	 * 学习人数
	 */
	private String xxpeople;
	/**
	 * 备注
	 */
	private String remark;
	
	
	public Integer getCourseid() {
		return courseid;
	}
	public void setCourseid(Integer courseid) {
		this.courseid = courseid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCollegeimage() {
		return collegeimage;
	}
	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}
	public Integer getImageid() {
		return imageid;
	}
	public void setImageid(Integer imageid) {
		this.imageid = imageid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getAddtime() {
		return addtime;
	}
	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}
	public String getKeshi() {
		return keshi;
	}
	public void setKeshi(String keshi) {
		this.keshi = keshi;
	}
	public String getShichang() {
		return shichang;
	}
	public void setShichang(String shichang) {
		this.shichang = shichang;
	}
	public String getXxpeople() {
		return xxpeople;
	}
	public void setXxpeople(String xxpeople) {
		this.xxpeople = xxpeople;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	
}
