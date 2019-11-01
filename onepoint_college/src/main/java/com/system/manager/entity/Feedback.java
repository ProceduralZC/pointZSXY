package com.system.manager.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
//意见反馈
@Entity
@Table(name = "t_feedback")
public class Feedback implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_FEEDBACK_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *联系方式
	 */
	@Column(name="FEEDBACKCONTACT",length=100)
	private String feedbackcontact;
	/**
	 *意见反馈建议
	 */
	@Column(name="FEEDBACKPROPOSAL",length=500)
	private String feedbackproposal;
	/**
	 * 添加时间
	 */
	@Column(name="FEEDBACKADDTIME",length=200)
	private String feedbackaddtime;
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
	public String getFeedbackcontact() {
		return feedbackcontact;
	}
	public void setFeedbackcontact(String feedbackcontact) {
		this.feedbackcontact = feedbackcontact;
	}
	public String getFeedbackproposal() {
		return feedbackproposal;
	}
	public void setFeedbackproposal(String feedbackproposal) {
		this.feedbackproposal = feedbackproposal;
	}
	public String getFeedbackaddtime() {
		return feedbackaddtime;
	}
	public void setFeedbackaddtime(String feedbackaddtime) {
		this.feedbackaddtime = feedbackaddtime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	

}
