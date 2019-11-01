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
@Table(name="sys_role_menu")
public class SysRoleMenu implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_ROLE_SEQ")
    @Column(name="ID")
    private Integer id;
    
    //角色的id
    @Column(name="SYS_ROLE_ID")
    private Integer sysRoleId;
    
    //菜单id
    @Column(name="SYS_MENU_ID")
    private Integer sysMenuId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(Integer sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public Integer getSysMenuId() {
		return sysMenuId;
	}

	public void setSysMenuId(Integer sysMenuId) {
		this.sysMenuId = sysMenuId;
	}
}
