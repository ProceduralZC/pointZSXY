package com.system.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.user.entity.SysRole;

public interface  SysRoleDao extends CrudRepository<SysRole,Integer>,JpaSpecificationExecutor<SysRole>{
	/*@Modifying
	@Query("update SysRole set isdelete=1 where id in ?1")
	public void delSysUsers(Integer[] ids);*/

	@Query("from SysRole where isdelete=0")
	public List<SysRole> findSysRole();
	
	@Query("from SysRole where isdelete=0 and id in (select  roleid from SysUsersRole where userid=?1 ))")
	public List<SysRole> findBySysUserId(Integer sysUserId);
}
