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
import com.system.core.entity.SysAffix;
import com.system.core.jsonSerialize.SysAffixSerialize;

@Entity
@Table(name = "sys_version")
public class SysVersion implements Serializable{
	private static final long serialVersionUID = -759172323464862312L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_VERSION_SEQ")
    @Column(name="ID")
    private Integer id;
	
	/**
	 * 版本号
	 */
	@Column(name="VERSIONNUM")
	private String versionNum;
	
	/**
	 * 更新内容
	 */
	@Column(name="CONTENT",length=4000)
	private String content;
	
	/**
	 * 上传的apk文件
	 */
	@JoinColumn(name="APK")
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonSerialize(using=SysAffixSerialize.class)
	private SysAffix apk;
	 /**
     * 状态 1启用，0 禁用
     */
    @Column(name="STATUS")
    private Integer status;
    /**
     * 是否删除1正常，0删除
     */
    @Column(name="ISDELETE")
    private Integer isdelete;
    
    /**
     * 版本更新日期
     */
    @Column(name="UPDATEDATE",length=50)
    private String updateDate;
    
    /**
     * 创建时间
     */
    @Column(name="CREATEDATE",length=50)
    private String createDate;
    /**
     * 修改时间
     */
    @Column(name="MODIFYDATE",length=50)
    private String modifyDate;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVersionNum() {
		return versionNum;
	}
	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public SysAffix getApk() {
		return apk;
	}
	public void setApk(SysAffix apk) {
		this.apk = apk;
	}
	
}
