package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//学习记录
@Entity
@Table(name = "t_studenthistory")
public class StudentHistory implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_STUHISTORY_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课程id
	 */
	@Column(name="COURSEID")
	private Integer courseid;
	/**
	 *标题
	 */
	@Column(name="TITLE",length=100)
	private String title;
	/**
	 *类型--是哪种资源
	 */
	@Column(name="TYPE")
	private Integer type;
	/**
	 * 内容信息
	 */
	@Column(name="CONTENT",length=2000)
	private String content;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	@Column(name="IMAGEID")
	private Integer imageid;
	/**
	 * 用户id
	 */
	@Column(name="USERID")
	private Integer userid;
	/**
	 * 添加时间
	 */
	@Column(name="ADDTIME",length=200)
	private String addtime;
	/**
	 * 课时
	 */
	@Column(name="KESHI",length=200)
	private String keshi;
	/**
	 * 时长
	 */
	@Column(name="SHICHANG",length=200)
	private String shichang;
	/**
	 * 学习人数
	 */
	@Column(name="XXPEOPLE",length=200)
	private String xxpeople;
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
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
