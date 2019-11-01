package com.system.core.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.core.entity.SysAffix;
@Repository
public interface SysAffixDao extends CrudRepository<SysAffix,Integer>,JpaSpecificationExecutor<SysAffix>{
	
}
