package com.system.manager.web.model;

import com.system.user.entity.SysUsers;

public class VideoModel{
	/**
	 * id
	 */
	private String id;
	
	/**
	 * 课程标题
	 */
	private String videotitle;
	/**
	 * 课程时长
	 */
	private Integer videolong;
	/**
	 * 课程类型
	 */
	private String videotype;
	/**
	 * 资源类型
	 */
	private String videosourcetype;
	
	private String videosourcetypeid;
	/**
	 * 课程url
	 */
	private String videourl;
	/**
	 * 提交人
	 */
	private String videouser;
	/**
	 * 提交日期
	 */
	private String videosubmietime;
	/**
	 * 视频介绍
	 */
	private String videodetail;
	/**
	 * 备注
	 */
	private String remark;
	
	 /**
     * 当前登录人
     */
    private SysUsers sysUsers;
    
    private String beginDate;
	
	private String endDate;

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getVideouser() {
		return videouser;
	}

	public void setVideouser(String videouser) {
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

	public SysUsers getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(SysUsers sysUsers) {
		this.sysUsers = sysUsers;
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
