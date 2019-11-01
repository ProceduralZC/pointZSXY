package com.system.user.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.user.entity.SysUsers;

public interface SysUsersDao extends CrudRepository<SysUsers,Integer>,JpaSpecificationExecutor<SysUsers>{
	@Modifying
	@Query("update SysUsers set isdelete=1 where id in ?1")
	public void delSysUsers(Integer[] ids);
	
	@Modifying
	@Query("update SysUsers set status=?1 where id in ?2")
	public void upSysUsersStatus(Integer status,Integer[] ids);
	
	@Modifying
	@Query("update SysUsers set password=?1 where id in ?2")
	public void upSysUsersPass(String password,Integer[] ides);
	
	@Query("from SysUsers where username=?1 and isdelete=0 ")
	public SysUsers findSysUsersByUsername(String username);
	
	@Query("from SysUsers where isdelete=0 and username=?1 and id <> ?2")
	public SysUsers findSysUsersByUsername(String username,Integer id);
	
}
