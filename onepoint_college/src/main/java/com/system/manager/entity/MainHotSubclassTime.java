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
@Table(name = "t_hotsubclasstime")
public class MainHotSubclassTime implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_HOTSUBCLASSTIME_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课时名称
	 */
	@Column(name="HOTSUBCLASSTIMENAME",length=100)
	private String hotsubclasstimename;
	/**
	 * 课时时长
	 */
	@Column(name="HOTSUBCLASSTIMENUM",length=100)
	private String hotsubclasstimenum;
	/**
	 * 课程链接
	 */
	@Column(name="HOTSUBCLASSURL",length=500)
	private String hotsubclassurl;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="HOTSUBCLASSTIMEADDTIME",length=100)
	private String hotsubclasstimeaddtime;
	
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

	public String getHotsubclasstimename() {
		return hotsubclasstimename;
	}

	public void setHotsubclasstimename(String hotsubclasstimename) {
		this.hotsubclasstimename = hotsubclasstimename;
	}

	public String getHotsubclasstimenum() {
		return hotsubclasstimenum;
	}

	public void setHotsubclasstimenum(String hotsubclasstimenum) {
		this.hotsubclasstimenum = hotsubclasstimenum;
	}

	public String getHotsubclassurl() {
		return hotsubclassurl;
	}

	public void setHotsubclassurl(String hotsubclassurl) {
		this.hotsubclassurl = hotsubclassurl;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getHotsubclasstimeaddtime() {
		return hotsubclasstimeaddtime;
	}

	public void setHotsubclasstimeaddtime(String hotsubclasstimeaddtime) {
		this.hotsubclasstimeaddtime = hotsubclasstimeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
