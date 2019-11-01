package com.system.manager.web.model;
//知识课程
public class KnowledgeCourseModel{
	 private Integer id;
	/**
	 *知识名称
	 */
	private String knowledgename;
	/**
	 * 知识图片路径
	 */
	private String collegeimage;
	
	/**
	 * 知识图片id
	 */
	private Integer knowledgeimageid;
	/**
	 * 课时
	 */
	private Integer knowledgenum;
	/**
	 * 时长
	 */
	private String knowledgetimelong;
	
	/**
	 * 原价
	 */
	private Integer knowledgeinitprice;
	/**
	 * vip价格
	 */
	private Integer knowledgevipprice;
	
	/**
	 * 是否开启付费
	 */
	private String knowledgeifprice;
	
	/**
	 * 购买人数
	 */
	private String knowledgepeople;
	
	/**
	 * 添加时间
	 */
	private String knowledgeaddtime;
	
	
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
