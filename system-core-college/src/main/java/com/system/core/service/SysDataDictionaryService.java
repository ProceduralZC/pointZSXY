package com.system.core.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.system.core.entity.SysDataDictionary;

public interface SysDataDictionaryService {

  /**
   * 添加
   * @param bean
   */
  @Transactional
  public  void add(SysDataDictionary bean);
  	/**
  	 * 修改
  	 * @param bean
  	 */
  @Transactional
  public  void update(SysDataDictionary bean);
  	/**
  	 * 获取
  	 * @param id
  	 * @return
  	 */
  @Transactional(readOnly=true)
  public  SysDataDictionary get(Integer id);
  	/**
  	 * 删除
  	 * @param id
  	 */
  public  void del(String ids);
  	/**
  	 * 获取列表
  	 * @return
  	 */
  public  List<SysDataDictionary> getTree();
  /**
   * 根据父节点id查询子节点集合
   * @param parentId
   * @return
   */
  public List<SysDataDictionary> findByParentId(Integer parentId) ;
  
  public SysDataDictionary findByCode(String code);
  /**
   * 根据code和那么查询数据字典信息
   * @param dataCode
   * @param name
   * @return
   */
  public SysDataDictionary findByCodeAndName(String dataCode,String name,Integer depth,Integer id);
  
  public List<SysDataDictionary> findDataDictionaryByCodeAndDepth(String dataCode,Integer depth,Integer id);
}
