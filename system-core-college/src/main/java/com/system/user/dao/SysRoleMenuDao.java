package com.system.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.user.entity.SysRoleMenu;
/**
 * 角色中菜单中间表
 * @author Administrator
 *
 */
public interface SysRoleMenuDao extends CrudRepository<SysRoleMenu,Integer>,JpaSpecificationExecutor<SysRoleMenu>{
	
	@Query("from SysRoleMenu where sysRoleId=?1")
	public List<SysRoleMenu> findByRoleId(Integer roleid);
	
	@Modifying
	@Query("delete from SysRoleMenu where sysRoleId=?1")
	public void delByRoleId(Integer roleid);
	
	@Modifying
	@Query("delete from SysRoleMenu where sysMenuId=?1")
	public void delByMenuId(Integer menuId);
}
