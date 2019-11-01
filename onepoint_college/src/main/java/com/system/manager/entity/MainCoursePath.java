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
@Table(name = "t_coursepath")
public class MainCoursePath implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_COURSEPATH_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课件名称
	 */
	@Column(name="COURSEPATHNAME",length=100)
	private String coursepathname;
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
	@Column(name="COURSEPATHNUM",length=100)
	private String coursepathnum;
	/**
	 * 时长
	 */
	@Column(name="COURSEPATHTIMELONG",length=100)
	private String coursepathtimelong;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="COURSEPATHADDTIME",length=100)
	private String coursepathaddtime;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;

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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	

}
