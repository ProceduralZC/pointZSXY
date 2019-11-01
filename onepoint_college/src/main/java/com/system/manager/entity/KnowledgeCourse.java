package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//知识课程
@Entity
@Table(name = "t_knowledge")
public class KnowledgeCourse implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_KNOWLEDGE_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *知识名称
	 */
	@Column(name="KNOWLEDGENAME",length=100)
	private String knowledgename;
	/**
	 * 知识图片路径
	 */
	@Column(name="COLLEGEIMAGE",length=200)
	private String collegeimage;
	/**
	 * 知识图片资源
	 */
	@Column(name="KNOWLEDGEIMAGEID")
	private Integer knowledgeimageid;
	/**
	 * 课时
	 */
	@Column(name="KNOWLEDGENUM")
	private Integer knowledgenum;
	/**
	 * 时长
	 */
	@Column(name="KNOWLEDGETIMELONG",length=200)
	private String knowledgetimelong;
	
	/**
	 * 原价
	 */
	@Column(name="KNOWLEDGEINITPRICE")
	private Integer knowledgeinitprice;
	/**
	 * vip价格
	 */
	@Column(name="KNOWLEDGEVIPPRICE")
	private Integer knowledgevipprice;
	/**
	 * 是否开启付费
	 */
	@Column(name="KNOWLEDGEIFPRICE",length=200)
	private String knowledgeifprice;
	/**
	 * 购买人数
	 */
	@Column(name="KNOWLEDGEPEOPLE",length=200)
	private String knowledgepeople;
	/**
	 * 添加时间
	 */
	@Column(name="KNOWLEDGEADDTIME",length=100)
	private String knowledgeaddtime;
	
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

	public String getKnowledgename() {
		return knowledgename;
	}

	public void setKnowledgename(String knowledgename) {
		this.knowledgename = knowledgename;
	}

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public Integer getKnowledgeimageid() {
		return knowledgeimageid;
	}

	public void setKnowledgeimageid(Integer knowledgeimageid) {
		this.knowledgeimageid = knowledgeimageid;
	}

	public Integer getKnowledgenum() {
		return knowledgenum;
	}

	public void setKnowledgenum(Integer knowledgenum) {
		this.knowledgenum = knowledgenum;
	}


	public String getKnowledgetimelong() {
		return knowledgetimelong;
	}

	public void setKnowledgetimelong(String knowledgetimelong) {
		this.knowledgetimelong = knowledgetimelong;
	}

	public Integer getKnowledgeinitprice() {
		return knowledgeinitprice;
	}

	public void setKnowledgeinitprice(Integer knowledgeinitprice) {
		this.knowledgeinitprice = knowledgeinitprice;
	}

	public Integer getKnowledgevipprice() {
		return knowledgevipprice;
	}

	public void setKnowledgevipprice(Integer knowledgevipprice) {
		this.knowledgevipprice = knowledgevipprice;
	}

	public String getKnowledgeifprice() {
		return knowledgeifprice;
	}

	public void setKnowledgeifprice(String knowledgeifprice) {
		this.knowledgeifprice = knowledgeifprice;
	}

	public String getKnowledgepeople() {
		return knowledgepeople;
	}

	public void setKnowledgepeople(String knowledgepeople) {
		this.knowledgepeople = knowledgepeople;
	}

	public String getKnowledgeaddtime() {
		return knowledgeaddtime;
	}

	public void setKnowledgeaddtime(String knowledgeaddtime) {
		this.knowledgeaddtime = knowledgeaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
