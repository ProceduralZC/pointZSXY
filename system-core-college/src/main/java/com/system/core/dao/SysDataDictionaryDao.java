package com.system.core.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.system.core.entity.SysDataDictionary;
/**
 * 数据字典
 */
@Repository
public interface SysDataDictionaryDao extends CrudRepository<SysDataDictionary,Integer>,JpaSpecificationExecutor<SysDataDictionary> {
	  @Modifying
	  @Query("update SysDataDictionary set isdelete=1 where id=?1")
	  public void delDataDictionary(Integer id);
	  
	  @Query(" from SysDataDictionary d where d.parentObj.id is null and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findParentDataDictionary();
	  
	  @Query(" from SysDataDictionary d where d.parentObj.id=?1 and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findByParentId(Integer parentId);
	  
	  
	  @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.depth=?2 and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findDataDictionaryByCode(String dataCode,Integer depth);
	  
	  @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.depth=?2 and d.id<>?3 and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findDataDictionaryByCode(String dataCode,Integer depth,Integer id );
	  
	  @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.name=?2  and d.depth=?3  and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findDataDictionaryByCodeAndName(String dataCode,String name,Integer depth);
	  
	  @Query(" from SysDataDictionary d where  d.dataCode=?1 and d.name=?2  and d.depth=?3  and id<>?4 and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findDataDictionaryByCodeAndName(String dataCode,String name,Integer depth,Integer id);
	  
	  @Query(" from SysDataDictionary d where  d.code=?1 and d.isdelete=0 and d.status=1 order by d.orderBy ")
	  public List<SysDataDictionary> findByCode(String code);
	  
}
