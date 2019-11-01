package com.system.manager.web.model;
//动态--首页
public class CynamicModel{
	 private Integer id;
	/**
	 *标题
	 */
	private String cynamictitle;
	/**
	 * 内容信息
	 */
	private String cynamiccontent;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	
	/**
	 * 知识图片id
	 */
	private Integer cynamicimageid;
	
	/**
	 * 添加时间
	 */
	private String cynamicaddtime;
	/**
	 * 备注
	 */
	private String remark;
	
    public String getCynamicaddtime() {
		return cynamicaddtime;
	}

	public void setCynamicaddtime(String cynamicaddtime) {
		this.cynamicaddtime = cynamicaddtime;
	}

	private String beginDate;
	
	private String endDate;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCynamictitle() {
		return cynamictitle;
	}

	public void setCynamictitle(String cynamictitle) {
		this.cynamictitle = cynamictitle;
	}

	public String getCynamiccontent() {
		return cynamiccontent;
	}

	public void setCynamiccontent(String cynamiccontent) {
		this.cynamiccontent = cynamiccontent;
	}

	public String getCollegeimage() {
		return collegeimage;
	}

	public void setCollegeimage(String collegeimage) {
		this.collegeimage = collegeimage;
	}

	public Integer getCynamicimageid() {
		return cynamicimageid;
	}

	public void setCynamicimageid(Integer cynamicimageid) {
		this.cynamicimageid = cynamicimageid;
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
