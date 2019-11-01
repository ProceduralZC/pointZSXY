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
@Table(name = "t_subclasstime")
public class MainSubclassTime implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_SUBCLASSTIME_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课时名称
	 */
	@Column(name="SUBCLASSTIMENAME",length=100)
	private String subclasstimename;
	/**
	 * 课时时长
	 */
	@Column(name="SUBCLASSTIMENUM",length=100)
	private String subclasstimenum;
	/**
	 * 课程链接
	 */
	@Column(name="SUBCLASSURL",length=500)
	private String subclassurl;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="SUBCLASSTIMEADDTIME",length=100)
	private String subclasstimeaddtime;
	
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

	public String getSubclasstimename() {
		return subclasstimename;
	}

	public void setSubclasstimename(String subclasstimename) {
		this.subclasstimename = subclasstimename;
	}

	public String getSubclasstimenum() {
		return subclasstimenum;
	}

	public void setSubclasstimenum(String subclasstimenum) {
		this.subclasstimenum = subclasstimenum;
	}

	

	public String getSubclassurl() {
		return subclassurl;
	}

	public void setSubclassurl(String subclassurl) {
		this.subclassurl = subclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getSubclasstimeaddtime() {
		return subclasstimeaddtime;
	}

	public void setSubclasstimeaddtime(String subclasstimeaddtime) {
		this.subclasstimeaddtime = subclasstimeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
