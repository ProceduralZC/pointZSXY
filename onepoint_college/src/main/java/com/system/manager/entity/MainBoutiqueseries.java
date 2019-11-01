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
@Table(name = "t_boutiqueseries")
public class MainBoutiqueseries implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_BOUTIQUESERIES_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *精品知识名称
	 */
	@Column(name="BOUTIQUESERIESNAME",length=100)
	private String boutiqueseriesname;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	@Column(name="BOUTIQUESERIESIMAGEID")
	private Integer boutiqueseriesimageid;
	/**
	 * 课时
	 */
	@Column(name="BOUTIQUESERIESNUM",length=100)
	private String boutiqueseriesnum;
	/**
	 * 时长
	 */
	@Column(name="BOUTIQUESERIESTIMELONG",length=100)
	private String boutiqueseriestimelong;
	
	/**
	 * 所属类型id
	 */
	@Column(name="TYPEID",length=100)
	private String typeid;
	/**
	 * 添加时间
	 */
	@Column(name="BOUTIQUESERIESADDTIME",length=100)
	private String boutiqueseriesaddtime;
	
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

	public String getBoutiqueseriesname() {
		return boutiqueseriesname;
	}

	public void setBoutiqueseriesname(String boutiqueseriesname) {
		this.boutiqueseriesname = boutiqueseriesname;
	}

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public Integer getBoutiqueseriesimageid() {
		return boutiqueseriesimageid;
	}

	public void setBoutiqueseriesimageid(Integer boutiqueseriesimageid) {
		this.boutiqueseriesimageid = boutiqueseriesimageid;
	}

	public String getBoutiqueseriesnum() {
		return boutiqueseriesnum;
	}

	public void setBoutiqueseriesnum(String boutiqueseriesnum) {
		this.boutiqueseriesnum = boutiqueseriesnum;
	}

	public String getBoutiqueseriestimelong() {
		return boutiqueseriestimelong;
	}

	public void setBoutiqueseriestimelong(String boutiqueseriestimelong) {
		this.boutiqueseriestimelong = boutiqueseriestimelong;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getBoutiqueseriesaddtime() {
		return boutiqueseriesaddtime;
	}

	public void setBoutiqueseriesaddtime(String boutiqueseriesaddtime) {
		this.boutiqueseriesaddtime = boutiqueseriesaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
