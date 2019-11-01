package com.system.user.web.model;
public class SysUsersModel{
	//姓名
	private String username;
	//昵称
	private String nickname;
	//职位
	private String zhiwei;
	//编号
    private String code;
	//性别
    private Integer sex;//0男，1女
    //简介
    private String detail;
    //账号
    private String acount;
  //电话
    private String tel;
    //密码
    private String password;
    //邮箱	
    private String email;
    //备注
    private String remark;
    //备注2
    private String remark2;
    //状态 0启用，1禁用
    private Integer status;
    //角色id
    private Integer roleId;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
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
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRemark2() {
		return remark2;
	}
	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
}
