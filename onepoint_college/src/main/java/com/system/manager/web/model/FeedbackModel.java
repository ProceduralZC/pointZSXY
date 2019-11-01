package com.system.manager.web.model;
//意见反馈
public class FeedbackModel{
	 private Integer id;
	 /**
	 *联系方式
	 */
	private String feedbackcontact;
	/**
	 *意见反馈建议
	 */
	private String feedbackproposal;
	/**
	 * 添加时间
	 */
	private String feedbackaddtime;
	/**
	 * 备注
	 */
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
