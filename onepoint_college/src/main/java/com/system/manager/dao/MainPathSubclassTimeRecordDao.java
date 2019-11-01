package com.system.manager.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.manager.entity.MainPathSubclassTime;
import com.system.manager.entity.MainSubclassTime;
//知识路径--课时
@Repository
public interface  MainPathSubclassTimeRecordDao extends CrudRepository<MainPathSubclassTime,Integer>,JpaSpecificationExecutor<MainPathSubclassTime>{
	
//	@Query(" from MaterialsApplication d where d.id=?1 ")
//	public List<Video> findByApplicationBillId(String workshopId);//说明：from 后面的表明和下面的实体类（数据库表一样的类）一样
	
	@Query(" from MainPathSubclassTime d where d.typeid=?1")
	public List<MainPathSubclassTime> findByTime(String subclassId);
}
