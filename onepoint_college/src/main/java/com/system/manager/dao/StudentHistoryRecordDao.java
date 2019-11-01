package com.system.manager.dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.manager.entity.StudentHistory;

@Repository
public interface  StudentHistoryRecordDao extends CrudRepository<StudentHistory,Integer>,JpaSpecificationExecutor<StudentHistory>{
	
//	@Query(" from MaterialsApplication d where d.id=?1 ")
//	public List<Video> findByApplicationBillId(String workshopId);//说明：from 后面的表明和下面的实体类（数据库表一样的类）一样
	
	@Query(" from StudentHistory d where d.courseid=?1")
	public StudentHistory findByinfo(Integer subclassId);
}
