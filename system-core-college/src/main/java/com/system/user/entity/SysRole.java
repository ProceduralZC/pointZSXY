package com.system.user.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sys_role")
public class SysRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_ROLE_SEQ")
    @Column(name="ID")
    private Integer id;
    
    //名字
    @Column(name="NAME",length=20,nullable=false)
    private String name;
    //描述
    @Column(name="REMARK",length=200)
    private String remark;
    
    //是否删除0正常，1删除
    @Column(name="ISDELETE",columnDefinition="int default 0")
    private Integer isdelete;
    
    //创建时间
    @Column(name="CREATEDATE")
    private Date createDate;
    //修改时间
    @Column(name="MODIFYDATE")
    private Date modifyDate;
    
    @Column(name = "EXTENDF1", length = 200)
	private String extendf1;

	@Column(name = "EXTENDF2", length = 200)
	private String extendf2;
	
	@Column(name = "EXTENDF3", length = 200)
	private String extendf3;

	@Column(name = "EXTENDF4", length = 200)
	private String extendf4;
	
	@Column(name = "EXTENDF5", length = 200)
	private String extendf5;
	
	public SysRole() {
		super();
	}
	public SysRole(Integer id) {
		super();
		this.id = id;
	}

	public SysRole(Integer id, String name, String remark) {
		super();
		this.id = id;
		this.name = name;
		this.remark = remark;
	}
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsdelete() {
		return isdelete;
	}
	public void setIsdelete(Integer isdelete) {
		this.isdelete = isdelete;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	public String getExtendf3() {
		return extendf3;
	}
	public void setExtendf3(String extendf3) {
		this.extendf3 = extendf3;
	}
	public String getExtendf4() {
		return extendf4;
	}
	public void setExtendf4(String extendf4) {
		this.extendf4 = extendf4;
	}
	public String getExtendf5() {
		return extendf5;
	}
	public void setExtendf5(String extendf5) {
		this.extendf5 = extendf5;
	}
}
