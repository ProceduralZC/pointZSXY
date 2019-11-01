package com.system.manager.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_attachment")
public class Attachment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 832391981838652755L;
	@Id
	private String id;
	private String fileUrl;
	private Date timeAt;
	private long fileSize;
	private String fileType;
	private int status ;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public Date getTimeAt() {
		return timeAt;
	}
	public void setTimeAt(Date timeAt) {
		this.timeAt = timeAt;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
