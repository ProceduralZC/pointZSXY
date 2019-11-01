package com.system.manager.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.manager.entity.KnowledgeSubclassTime;
/**
 * 知识课时
 * @author
 *
 */
@Repository
public interface  KnowledgeSubclassTimeRecordDao extends CrudRepository<KnowledgeSubclassTime,Integer>,JpaSpecificationExecutor<KnowledgeSubclassTime>{
	
//	@Query(" from MaterialsApplication d where d.id=?1 ")
//	public List<Video> findByApplicationBillId(String workshopId);//说明：from 后面的表明和下面的实体类（数据库表一样的类）一样
	
	@Modifying    //删除操作添加该属性
	@Query("delete from KnowledgeSubclassTime where typeid=?1")
	public void delByTypeId(Integer typeId);
	
	@Query(" from KnowledgeSubclassTime d where d.typeid=?1")
	public List<KnowledgeSubclassTime> findByTime(Integer knowledgeId);
}
