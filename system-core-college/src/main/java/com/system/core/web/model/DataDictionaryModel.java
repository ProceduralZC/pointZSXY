package com.system.core.web.model;




public class DataDictionaryModel {

	private Integer parentObjId;
	
	private String name;
	
	/**
     * 数据字典代码
     */
	private String dataCode;
	
	private Integer status;
	
	private Integer orderBy;
	/**
	 * 编号
	 */
	private String code;
	 /**
     * 深度
     */
    private Integer depth;
    /**
	 * 0:正常  1：特殊
	 */
	private Integer property;
	

	public Integer getParentObjId() {
		return parentObjId;
	}

	public void setParentObjId(Integer parentObjId) {
		this.parentObjId = parentObjId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getProperty() {
		return property;
	}

	public void setProperty(Integer property) {
		this.property = property;
	}
}
