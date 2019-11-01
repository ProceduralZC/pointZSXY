package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "t_courseware")
public class MainCourseWare implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_COURSEWARE_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课件名称
	 */
	@Column(name="COURSEWARENAME",length=100)
	private String coursewarename;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	@Column(name="COURSEWAREIMAGEID")
	private Integer coursewareimageid;
	/**
	 * 课时
	 */
	@Column(name="COURSEWARENUM",length=100)
	private String coursewarenum;
	/**
	 * 时长
	 */
	@Column(name="COURSEWARETIMELONG",length=100)
	private String coursewaretimelong;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="COURSEWAREADDTIME",length=100)
	private String coursewareaddtime;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;

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
	

}
