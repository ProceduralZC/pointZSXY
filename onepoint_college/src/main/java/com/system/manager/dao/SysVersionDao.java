package com.system.manager.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.manager.entity.SysVersion;
/**
 * 版本管理
 */
public interface  SysVersionDao extends CrudRepository<SysVersion,Integer>,JpaSpecificationExecutor<SysVersion>{

	@Modifying
	@Query("update SysVersion set isdelete=0 where id in ?1")
	public void delStartPage(Integer[] ids);
}
