package com.system.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.core.entity.SysSerialNumber;

@Repository
public interface SysSerialNumberDao extends CrudRepository<SysSerialNumber,Integer>,JpaSpecificationExecutor<SysSerialNumber>{

	  @Query(" from SysSerialNumber where target=?1 ")
	  public List<SysSerialNumber> findByTarget(Integer target);
	  
}
