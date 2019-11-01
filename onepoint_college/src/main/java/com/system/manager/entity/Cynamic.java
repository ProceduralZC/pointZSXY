package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//首页-动态
@Entity
@Table(name = "t_cynamic")
public class Cynamic implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_CYNAMIC_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *标题
	 */
	@Column(name="CYNAMICTITLE",length=100)
	private String cynamictitle;
	/**
	 * 内容信息
	 */
	@Column(name="CYNAMICCONTENT",length=2000)
	private String cynamiccontent;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	@Column(name="CYNAMICIMAGEID")
	private Integer cynamicimageid;
	/**
	 * 添加时间
	 */
	@Column(name="CYNAMICADDTIME",length=200)
	private String cynamicaddtime;
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;
	
	public String getCynamicaddtime() {
		return cynamicaddtime;
	}
	public void setCynamicaddtime(String cynamicaddtime) {
		this.cynamicaddtime = cynamicaddtime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCynamictitle() {
		return cynamictitle;
	}
	public void setCynamictitle(String cynamictitle) {
		this.cynamictitle = cynamictitle;
	}
	public String getCynamiccontent() {
		return cynamiccontent;
	}
	public void setCynamiccontent(String cynamiccontent) {
		this.cynamiccontent = cynamiccontent;
	}
	public String getCollegeimage() {
		return collegeimage;
	}
	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}
	public Integer getCynamicimageid() {
		return cynamicimageid;
	}
	public void setCynamicimageid(Integer cynamicimageid) {
		this.cynamicimageid = cynamicimageid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


	

}
