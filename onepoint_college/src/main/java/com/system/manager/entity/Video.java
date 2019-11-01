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
@Table(name = "t_video")
public class Video implements Serializable{
	
	private static final long serialVersionUID = -759172323464862312L;
	
	public final static Integer MATERIAL_STATUS_TEMP=0;
	
	public final static Integer MATERIAL_STATUS_REAL=1;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="T_VIDEO_SEQ")
    @Column(name="ID")
    private Integer id;
	/**
	 *课程标题
	 */
	@Column(name="VIDEOTITLE",length=100)
	private String videotitle;
	/**
	 * 课程时长
	 */
	@Column(name="VIDEOLONG")
	private Integer videolong;
	/**
	 * 课程类型
	 */
	@Column(name="VIDEOTYPE",length=100)
	private String videotype;
	/**
	 * 课程路径
	 */
	@Column(name="VIDEOURL",length=200)
	private String videourl;
	/**
	 * 资源类型
	 */
	@Column(name="VIDEOSOURCETYPE",length=200)
	private String videosourcetype;
	/**
	 * 资源类型id
	 */
	@Column(name="VIDEOSOURCETYPEID",length=50)
	private String videosourcetypeid;
	/**
	 * 提交人
	 */
	@JoinColumn(name="VIDEOUSER")
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonSerialize(using=SysUserSerialize.class)
	private SysUsers videouser;
	/**
	 * 提交日期
	 */
	@Column(name="VIDEOSUBMITTIME",length=100)
	private String videosubmietime;
	/**
	 * 课程介绍
	 */
	@Column(name="VIDEODETAIL",length=100)
	private String videodetail;
	/**
	 * 备注
	 */
	@Column(name="REMARK",length=200)
	private String remark;
	
	
	public String getVideosourcetypeid() {
		return videosourcetypeid;
	}
	public void setVideosourcetypeid(String videosourcetypeid) {
		this.videosourcetypeid = videosourcetypeid;
	}
	public String getVideosourcetype() {
		return videosourcetype;
	}
	public void setVideosourcetype(String videosourcetype) {
		this.videosourcetype = videosourcetype;
	}
	public String getVideourl() {
		return videourl;
	}
	public void setVideourl(String videourl) {
		this.videourl = videourl;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getVideotitle() {
		return videotitle;
	}
	public void setVideotitle(String videotitle) {
		this.videotitle = videotitle;
	}
	public Integer getVideolong() {
		return videolong;
	}
	public void setVideolong(Integer videolong) {
		this.videolong = videolong;
	}
	public String getVideotype() {
		return videotype;
	}
	public void setVideotype(String videotype) {
		this.videotype = videotype;
	}
	
	public SysUsers getVideouser() {
		return videouser;
	}
	public void setVideouser(SysUsers videouser) {
		this.videouser = videouser;
	}
	public String getVideosubmietime() {
		return videosubmietime;
	}
	public void setVideosubmietime(String videosubmietime) {
		this.videosubmietime = videosubmietime;
	}
	public String getVideodetail() {
		return videodetail;
	}
	public void setVideodetail(String videodetail) {
		this.videodetail = videodetail;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
