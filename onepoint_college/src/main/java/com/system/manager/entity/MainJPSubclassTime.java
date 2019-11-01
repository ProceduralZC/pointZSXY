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
@Table(name = "t_jpsubclasstime")
public class MainJPSubclassTime implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_JPSUBCLASSTIME_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课时名称
	 */
	@Column(name="JPSUBCLASSTIMENAME",length=100)
	private String jpsubclasstimename;
	/**
	 * 课时时长
	 */
	@Column(name="JPSUBCLASSTIMENUM",length=100)
	private String jpsubclasstimenum;
	/**
	 * 课程链接
	 */
	@Column(name="JPSUBCLASSURL",length=500)
	private String jpsubclassurl;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="JPSUBCLASSTIMEADDTIME",length=100)
	private String jpsubclasstimeaddtime;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getJpsubclasstimename() {
		return jpsubclasstimename;
	}

	public void setJpsubclasstimename(String jpsubclasstimename) {
		this.jpsubclasstimename = jpsubclasstimename;
	}

	public String getJpsubclasstimenum() {
		return jpsubclasstimenum;
	}

	public void setJpsubclasstimenum(String jpsubclasstimenum) {
		this.jpsubclasstimenum = jpsubclasstimenum;
	}

	public String getJpsubclassurl() {
		return jpsubclassurl;
	}

	public void setJpsubclassurl(String jpsubclassurl) {
		this.jpsubclassurl = jpsubclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getJpsubclasstimeaddtime() {
		return jpsubclasstimeaddtime;
	}

	public void setJpsubclasstimeaddtime(String jpsubclasstimeaddtime) {
		this.jpsubclasstimeaddtime = jpsubclasstimeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
