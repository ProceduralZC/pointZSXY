package com.system.manager.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.manager.entity.Cynamic;

@Repository
public interface  CynamicRecordDao extends CrudRepository<Cynamic,Integer>,JpaSpecificationExecutor<Cynamic>{
	
//	@Query(" from MaterialsApplication d where d.id=?1 ")
//	public List<Video> findByApplicationBillId(String workshopId);//说明：from 后面的表明和下面的实体类（数据库表一样的类）一样
}
