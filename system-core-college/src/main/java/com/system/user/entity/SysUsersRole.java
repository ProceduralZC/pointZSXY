package com.system.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sys_users_role")
public class SysUsersRole implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_USERS_ROLE_SEQ")
    @Column(name="ID")
    private Integer id;
    /**
     * 用户id
     */
    @Column(name="userid",nullable=false)
    private Integer userid;
    /**
     * 角色id
     */
    @Column(name="roleid",nullable=false)
    private Integer roleid;
    
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getRoleid() {
		return roleid;
	}
	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}
}
