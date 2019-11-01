package com.system.manager.web.model;


public class MessageSysModel{
	 private Integer id;
	/**
	 *消息标题
	 */
	private String msgtitle;
	/**
	 * 消息内容
	 */
	private String msgcontent;
	/**
	 * 消息是否读取状态
	 */
	private Integer msgifread;
	/**
	 * 类型编号---系统消息还是其他课程消息
	 */
	private Integer msgtypecode;
	/**
	 * 消息时间
	 */
	private String msgaddtime;
	/**
	 * 备注
	 */
	private String remark;
	
    private String beginDate;
	
	private String endDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getMsgtitle() {
		return msgtitle;
	}

	public void setMsgtitle(String msgtitle) {
		this.msgtitle = msgtitle;
	}

	public String getMsgcontent() {
		return msgcontent;
	}

	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}

	public Integer getMsgifread() {
		return msgifread;
	}

	public void setMsgifread(Integer msgifread) {
		this.msgifread = msgifread;
	}

	public Integer getMsgtypecode() {
		return msgtypecode;
	}

	public void setMsgtypecode(Integer msgtypecode) {
		this.msgtypecode = msgtypecode;
	}

	public String getMsgaddtime() {
		return msgaddtime;
	}

	public void setMsgaddtime(String msgaddtime) {
		this.msgaddtime = msgaddtime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
}
