package com.system.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *系统上传的附件(包括图片)
 */
@Entity
@Table(name="sys_affix")
public class SysAffix  implements Serializable{
	  /**
	 * 
	 */
	  private static final long serialVersionUID = 1L;
	  @Id
	  @GeneratedValue(strategy=GenerationType.AUTO)
	  @SequenceGenerator(name="SYS_AFFIX_SEQ")
	  @Column(name="ID")
	  private Integer id;
	  
	  @Column(name="NAME", length=250)
	  private String name;
	  
	  @Column(name="SOURCE", length=250)
	  private String source;
	  

	  @Column(name="TYPE", length=200)
	  private String type;

	  @Column(name="SIZE", length=50)
	  private String size;
	  
	  @Column(name="EXTNAME", length=50)
	  private String extname;
	  
	  @Column(name="CREATE_DATE")
	  private Date createDate;
	  /**
	   * 图片的宽度
	   */
	  @Column(name="WIDTH")
	  private Integer width;
	  /**
	   * 图片的高度
	   */
	  @Column(name="HEIGHT")
	  private Integer height;
	  

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

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getExtname() {
		return extname;
	}

	public void setExtname(String extname) {
		this.extname = extname;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public SysAffix() {
		super();
	}

	public SysAffix(Integer id) {
		super();
		this.id = id;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
