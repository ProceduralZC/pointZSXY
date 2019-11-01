package com.system.manager.service.impl;

import java.util.List;
import java.util.Map;


public interface EntityRepository<T> {
	/**
	 * 保存记录
	 */
	public void save(T obj);
	/**
	 * 更新记录
	 */
	public void update(T obj);
	/**
	 * 根据数组ids删除对应的记录
	 * @param id
	 * @return
	 */
	public void delete(Object[] ids);
	/**
	 * 根据id删除对应的记录
	 * @param id
	 * @return
	 */
	public void deleteById(Object id);
	/**
	 * 根据id查找对应的记录
	 * @param id
	 * @return
	 */
	public T getById(Object id);

	/**
	 * 查询下一个id
	 * lsh 流水号的名称
	 */
	public Long findSequence(String lsh) throws Exception;
	/**
	 * 按条件进行查找
	 * @param sql
	 * @param parmeters
	 * @return
	 */
	public List<?> query(String sql, Map<String, Object> parmeters);
	/**
	 * 查找所有的记录
	 */
	public List<T> findAll();
}
