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
@Table(name = "t_message")
public class MessageSys implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_MESSAGE_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *消息标题
	 */
	@Column(name="MSGTITLE",length=100)
	private String msgtitle;
	/**
	 * 消息内容
	 */
	@Column(name="MSGCONTENT",length=2000)
	private String msgcontent;
	/**
	 * 消息是否读取状态
	 */
	@Column(name="MSGIFREAD")
	private Integer msgifread;
	
	/**
	 * 类型编号---系统消息还是其他课程消息---1,系统消息，其他 做的时候单独查询
	 */
	@Column(name="MSGTYPECODE")
	private Integer msgtypecode;
	/**
	 * 消息时间
	 */
	@Column(name="MSGADDTIME",length=100)
	private String msgaddtime;
	
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

	
	

}
