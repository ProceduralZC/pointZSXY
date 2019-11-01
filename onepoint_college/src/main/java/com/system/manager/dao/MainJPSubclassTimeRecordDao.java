package com.system.manager.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.manager.entity.MainJPSubclassTime;
import com.system.manager.entity.MainSubclassTime;

@Repository
public interface  MainJPSubclassTimeRecordDao extends CrudRepository<MainJPSubclassTime,Integer>,JpaSpecificationExecutor<MainJPSubclassTime>{
	
	@Query(" from MainJPSubclassTime d where d.typeid=?1")
	public List<MainJPSubclassTime> findByTime(String subclassId);
}
