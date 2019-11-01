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
@Table(name = "t_knowledgesubclasstime")
public class KnowledgeSubclassTime implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_KNOWLEDGESUBCLASSTIME_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课时名称
	 */
	@Column(name="KNOWLEDGESUBCLASSTIMENAME",length=100)
	private String knowledgesubclasstimename;
	/**
	 * 课时时长
	 */
	@Column(name="KNOWLEDGESUBCLASSTIMENUM",length=100)
	private String knowledgesubclasstimenum;
	/**
	 * 课程链接
	 */
	@Column(name="KNOWLEDGESUBCLASSURL",length=500)
	private String knowledgesubclassurl;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID")
	private Integer typeid;
	/**
	 * 添加时间
	 */
	@Column(name="KNOWLEDGESUBCLASSTIMEADDTIME",length=100)
	private String knowledgesubclasstimeaddtime;
	
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

	public String getKnowledgesubclasstimename() {
		return knowledgesubclasstimename;
	}

	public void setKnowledgesubclasstimename(String knowledgesubclasstimename) {
		this.knowledgesubclasstimename = knowledgesubclasstimename;
	}

	public String getKnowledgesubclasstimenum() {
		return knowledgesubclasstimenum;
	}

	public void setKnowledgesubclasstimenum(String knowledgesubclasstimenum) {
		this.knowledgesubclasstimenum = knowledgesubclasstimenum;
	}

	public String getKnowledgesubclassurl() {
		return knowledgesubclassurl;
	}

	public void setKnowledgesubclassurl(String knowledgesubclassurl) {
		this.knowledgesubclassurl = knowledgesubclassurl;
	}


	public Integer getTypeid() {
		return typeid;
	}

	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}

	public String getKnowledgesubclasstimeaddtime() {
		return knowledgesubclasstimeaddtime;
	}

	public void setKnowledgesubclasstimeaddtime(String knowledgesubclasstimeaddtime) {
		this.knowledgesubclasstimeaddtime = knowledgesubclasstimeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
