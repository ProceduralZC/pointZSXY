package com.system.user.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sys_menu")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "fieldHandler" })
public class SysMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@SequenceGenerator(name = "MAX_MENU_SEQ")
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME", length = 200)
	private String name;

	@Column(name = "ENAME", length = 200)
	private String ename;

	@Column(name = "DESCRIPTION", length = 200)
	private String description;

	@Column(name = "ORDER_BY")
	private Long orderBy;

	@Column(name = "IMG", length = 200)
	private String img;

	@Column(name = "LINK", length = 200)
	private String link;

	@JoinColumn(name = "PARENT_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private SysMenu parentObj;

	@Column(name = "PARENT_ID")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parentObj", cascade = { javax.persistence.CascadeType.ALL })
	@OrderBy("ORDER_BY")
	@JsonIgnore
	private Set<SysMenu> childs;

	@Column(name = "CUR_DATE")
	private Date curDate;

	@Column(name = "ALIAS", length = 200)
	private String alias;

	@Column(name = "EXTENDF1", length = 200)
	private String extendf1;

	@Column(name = "EXTENDF2", length = 200)
	private String extendf2;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public SysMenu getParentObj() {
		return parentObj;
	}

	public void setParentObj(SysMenu parentObj) {
		this.parentObj = parentObj;
	}

	public Set<SysMenu> getChilds() {
		return childs;
	}

	public void setChilds(Set<SysMenu> childs) {
		this.childs = childs;
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

	public SysMenu(Integer id, String name, String ename, String description,
			Long orderBy, String img, String link, SysMenu parentObj,
			Set<SysMenu> childs, Date curDate, String alias, String extendf1,
			String extendf2) {
		super();
		this.id = id;
		this.name = name;
		this.ename = ename;
		this.description = description;
		this.orderBy = orderBy;
		this.img = img;
		this.link = link;
		this.parentObj = parentObj;
		this.childs = childs;
		this.curDate = curDate;
		this.alias = alias;
		this.extendf1 = extendf1;
		this.extendf2 = extendf2;
	}

	public SysMenu(Integer id) {
		super();
		this.id = id;
	}

	public SysMenu() {
		super();
		// TODO Auto-generated constructor stub
	}
}