package com.system.user.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.user.entity.SysUsersRole;

public interface  SysUsersRoleDao extends CrudRepository<SysUsersRole,Integer>,JpaSpecificationExecutor<SysUsersRole>{
	@Modifying
	@Query("delete from SysUsersRole where roleid=?1 and userid=?2")
	public void delSysUserRole(Integer roleid,Integer userid);
	
	@Modifying
	@Query("delete from SysUsersRole where roleid=?1")
	public void delByRoleId(Integer roleid);
	
	@Modifying
	@Query("delete from SysUsersRole where userid=?1")
	public void delByUserId(Integer userid);
}
