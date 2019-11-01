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
@Table(name = "t_pathsubclasstime")
public class MainPathSubclassTime implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_PATHSUBCLASSTIME_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课时名称
	 */
	@Column(name="PATHSUBCLASSTIMENAME",length=100)
	private String pathsubclasstimename;
	/**
	 * 课时时长
	 */
	@Column(name="PATHSUBCLASSTIMENUM",length=100)
	private String pathsubclasstimenum;
	/**
	 * 课程链接
	 */
	@Column(name="PATHSUBCLASSURL",length=500)
	private String pathsubclassurl;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="PATHSUBCLASSTIMEADDTIME",length=100)
	private String pathsubclasstimeaddtime;
	
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

	public String getPathsubclasstimename() {
		return pathsubclasstimename;
	}

	public void setPathsubclasstimename(String pathsubclasstimename) {
		this.pathsubclasstimename = pathsubclasstimename;
	}

	public String getPathsubclasstimenum() {
		return pathsubclasstimenum;
	}

	public void setPathsubclasstimenum(String pathsubclasstimenum) {
		this.pathsubclasstimenum = pathsubclasstimenum;
	}

	public String getPathsubclassurl() {
		return pathsubclassurl;
	}

	public void setPathsubclassurl(String pathsubclassurl) {
		this.pathsubclassurl = pathsubclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getPathsubclasstimeaddtime() {
		return pathsubclasstimeaddtime;
	}

	public void setPathsubclasstimeaddtime(String pathsubclasstimeaddtime) {
		this.pathsubclasstimeaddtime = pathsubclasstimeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
