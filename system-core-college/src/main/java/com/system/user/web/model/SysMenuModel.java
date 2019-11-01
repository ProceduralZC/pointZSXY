package com.system.user.web.model;

import java.util.Date;

public class SysMenuModel{

	private String name;

	private String ename;

	private String description;

	private Long orderBy;

	private String img;

	private String link;

	private Integer parentId;

	private Date curDate;

	private String alias;

	private String extendf1;

	private String extendf2;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Long orderBy) {
		this.orderBy = orderBy;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Date getCurDate() {
		return curDate;
	}

	public void setCurDate(Date curDate) {
		this.curDate = curDate;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getExtendf1() {
		return extendf1;
	}

	public void setExtendf1(String extendf1) {
		this.extendf1 = extendf1;
	}

	public String getExtendf2() {
		return extendf2;
	}

	public void setExtendf2(String extendf2) {
		this.extendf2 = extendf2;
	}
}
