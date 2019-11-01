package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.user.entity.SysUsers;
import com.system.user.jsonSerialize.SysUserSerialize;

@Entity
@Table(name = "t_maincollegetype")
public class MainCollegeType implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_MAINCOLLEGETYPE_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *知识名称
	 */
	@Column(name="COLLEGENAME",length=100)
	private String collegename;
	/**
	 * 知识类型
	 */
	@Column(name="COLLEGETYPE",length=100)
	private String collegetype;
	/**
	 * 知识类型code
	 */
	@Column(name="COLLEGETYPECODE",length=100)
	private String collegetypecode;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	
	/**
	 * 知识图片资源
	 */
	@Column(name="COLLEGEIMGID")
	private Integer collegeImgId;
	
	/**
	 * 日期
	 */
	@Column(name="COLLEGETIME",length=200)
	private String collegetime;
	/**
	 * 描述
	 */
	@Column(name="COLLEGECONTENT",length=500)
	private String collegecontent;
	
	/**
	 * 提交人
	 */
	@JoinColumn(name="COLLEGEUSER")
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonSerialize(using=SysUserSerialize.class)
	private SysUsers collegeuser;
	
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;

	public Integer getCollegeImgId() {
		return collegeImgId;
	}

	public void setCollegeImgId(Integer collegeImgId) {
		this.collegeImgId = collegeImgId;
	}

	public String getCollegetypecode() {
		return collegetypecode;
	}

	public void setCollegetypecode(String collegetypecode) {
		this.collegetypecode = collegetypecode;
	}

	public SysUsers getCollegeuser() {
		return collegeuser;
	}

	public void setCollegeuser(SysUsers collegeuser) {
		this.collegeuser = collegeuser;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCollegename() {
		return collegename;
	}

	public void setCollegename(String collegename) {
		this.collegename = collegename;
	}

	public String getCollegetype() {
		return collegetype;
	}

	public void setCollegetype(String collegetype) {
		this.collegetype = collegetype;
	}

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public String getCollegetime() {
		return collegetime;
	}

	public void setCollegetime(String collegetime) {
		this.collegetime = collegetime;
	}

	public String getCollegecontent() {
		return collegecontent;
	}

	public void setCollegecontent(String collegecontent) {
		this.collegecontent = collegecontent;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
