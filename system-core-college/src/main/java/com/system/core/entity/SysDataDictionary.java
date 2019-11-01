package com.system.core.entity;

import java.io.Serializable;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.core.jsonSerialize.SysDataDictionarySerialize;

/**
 * 数据字典
 */
@Entity
@Table(name="sys_datadictionary")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"}) 
public class SysDataDictionary implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_DATADICTIONARY_SEQ")
	@Column(name="ID")
    private Integer id;
	/**
     * 名称
     */
	@Column(name="NAME", length=50)
	private String name;
	/**
     * 数据字典代码
     */
	@Column(name="DATA_CODE", length=50)
	private String dataCode;
	
	/**
	 * 排序字段
	 */
	@Column(name="ORDER_BY")
	private Integer orderBy;
	  
	@JoinColumn(name="PARENT_ID")
	@ManyToOne(fetch=FetchType.EAGER)
	@JsonSerialize(using=SysDataDictionarySerialize.class)
	private SysDataDictionary parentObj;
	
	@Column(name="PARENT_ID")
	@OneToMany(fetch=FetchType.LAZY, mappedBy="parentObj", cascade={javax.persistence.CascadeType.ALL})
	@JsonIgnore
	private Set<SysDataDictionary> childs;
	 
	/**
     * 状态 默认1正常，0禁用
     */
	@Column(name="STATUS",columnDefinition="int default 1")
    private Integer status;
	
	 /**
     * 是否删除1正常，0删除
     */
    @Column(name="ISDELETE",columnDefinition="int default 1")
    private Integer isdelete;
    
    /**
     * 深度
     */
    @Column(name="DEPTH")
    private Integer depth;
	/**
     * 创建时间
     */
	@Column(name="CREATEDATE")
    private String createDate;
	/**
     * 修改时间
     */
	@Column(name="MODIFYDATE")
    private String modifyDate;
	/**
	 * 编号
	 */
	@Column(name="CODE",length=400)
	private String code;
	/**
	 * 0:正常  1：特殊
	 */
	@Column(name="PROPERTY")
	private Integer property=0;
	
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
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	public Integer getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}
	public SysDataDictionary getParentObj() {
		return parentObj;
	}
	public void setParentObj(SysDataDictionary parentObj) {
		this.parentObj = parentObj;
	}
	public Set<SysDataDictionary> getChilds() {
		return childs;
	}
	public void setChilds(Set<SysDataDictionary> childs) {
		this.childs = childs;
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
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public SysDataDictionary(Integer id) {
		super();
		this.id = id;
	}
	public SysDataDictionary() {
		super();
	}
	public Integer getProperty() {
		return property;
	}
	public void setProperty(Integer property) {
		this.property = property;
	}
}
