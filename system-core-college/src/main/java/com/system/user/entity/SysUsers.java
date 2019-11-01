package com.system.user.entity;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.system.core.entity.SysDataDictionary;
import com.system.core.jsonSerialize.SysDataDictionarySerialize;

@Entity
@Table(name="sys_users")
public class SysUsers implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_USERS_SEQ")
    @Column(name="ID")
    private Integer id;
	//姓名
	@Column(name="USERNAME",length=20)
	private String username;
	//昵称
	@Column(name="NICKNAME",length=20)
	private String nickname;
	//职位
	@Column(name="ZHIWEI",length=100)
	private String zhiwei;
	//编号
	@Column(name="CODE",length=100)
	private String code;
	//性别
	@Column(name="SEX")
	private Integer sex;//0男，1女
	//简介
	@Column(name="DETAIL",length=300)
	private String detail;
	//账号
	@Column(name="ACOUNT",length=20)
	private String acount;
	//电话
	@Column(name="TEL",length=20)
	private String tel;
	//创建时间
	@Column(name="CREATEDATE")
	private Date createDate;
	//修改时间
	@Column(name="MODIFYDATE")
	private Date modifyDate;
	//角色id
	@Column(name="ROLE_ID")
	private Integer roleId;
	@Transient
	private String roleName;
    //密码
    @Column(name="PASSWORD",length=50)
    private String password;
    //邮箱	
    @Column(name="EMAIL",length=50)
    private String email;
    //备注
    @Column(name="REMARK",length=500)
    private String remark;
    //备注2
    @Column(name="REMARK2",length=500)
    private String remark2;
    //状态 0启用，1禁用
    @Column(name="STATUS",columnDefinition="int default 0")
    private Integer status;
    //是否删除0正常，1删除
    @Column(name="ISDELETE",columnDefinition="int default 0")
    private Integer isdelete;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getZhiwei() {
		return zhiwei;
	}
	public void setZhiwei(String zhiwei) {
		this.zhiwei = zhiwei;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getAcount() {
		return acount;
	}
	public void setAcount(String acount) {
		this.acount = acount;
	}
	public SysUsers() {
		super();
	}
	public SysUsers(Integer id) {
		super();
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
