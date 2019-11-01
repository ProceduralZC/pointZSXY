package com.system.manager.service.impl;

import java.util.List;
import java.util.Map;

public interface BaseRepository {
	/**
	 * 查询下一个id
	 * lsh 流水号的名称
	 */
	public Long findSequence(Class<?> cls,String lsh) throws Exception;
	
	// 新增
	public void save(Object obj);

	// 删除
	public void delete(Class<?> cls, Object[] ids);

	public void deleteById(Class<?> cls, Object Id);

	// 更新
	public void update(Object obj);

	// 获得所有记录
	public List<?> getAll(Class<?> cls);

	// 按ID号查询
	public Object getById(Class<?> cls, Object id);

	// 获得总记录数
	public long getRowSize(Class<?> cls);

	// 按精确匹配获得符合条件的记录数
	public long getRowSizeByEqual(Class<?> cls, Map<String, Object> map);

	// 按模糊匹配获得符合条件的记录数
	public long getRowSizeByLike(Class<?> cls, Map<String, String> map);

	// 原始查询
	public List<?> query(String sql);

	// 带条件查询
	public List<?> query(String sql, Map<String, Object> parmeters);

	// -----------------------------------------

	// 获得分页数据
	public List<?> getSub(Class<?> cls, int from, int size);

	// 按字段是否相等来查询
	public List<?> getByEqual(Class<?> cls, Map<String, Object> paramsMap);

	// 按字段是否相等来查询获得分页数据
	public List<?> getSubByEqual(Class<?> cls, Map<String, Object> paramsMap, int from, int size);

	// 按字段模糊匹配查询
	public List<?> getByLike(Class<?> cls, Map<String, String> map);

	// 按字段模糊匹配来查询获得分页数据
	public List<?> getSubByLike(Class<?> cls, Map<String, String> map, int from, int size);

	public List<?> getBy(boolean getRowCount, boolean isVagou, Class<?> cls, Map<String, Object> paramsMap, int from, int size);

	public <N> List<N> queryByType(Class<N> clazz, String sql);

	public <N> List<N> queryByType(Class<N> clazz, String sql, Map<String, Object> parmeters);

}
