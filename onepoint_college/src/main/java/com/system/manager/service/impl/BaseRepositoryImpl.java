package com.system.manager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.system.manager.entity.UniqId;

@Repository
@Service
public class BaseRepositoryImpl implements BaseRepository {

	// 让容器来负责构建一个实体管理器,并且将该实体管理器注入到Bean 中.
	@PersistenceContext
	protected EntityManager entityManager;
	
	public Long findSequence(Class<?> cls,String lsh) throws Exception{
		Long num = Long.valueOf(UniqId.getInstance().getUniqID());
//		if(lsh != null && lsh.trim().length() != 0){
//			String className = cls.getSimpleName(); // 对应的类名
//			List list =  entityManager.createQuery("select "+lsh+" from "+className+" as model order by "+lsh+" desc").setMaxResults(1).getResultList();
//			if(list != null && list.size() > 0){
//				Object value = list.get(0);
//				if(value == null){
//					num = 1l;
//				}else{
//					num = Long.valueOf(value+"")+1;
//				}
//			}else{
//				num = 1l;
//			}
//		}
		return num;
	}
	
	public void save(Object obj) {
		entityManager.persist(obj);
	}

	@Override
	public void update(Object obj) {
		entityManager.merge(obj);
	}

	@Override
	public List<?> query(String sql) {
		return entityManager.createQuery(sql).getResultList();
	}

	public <N> List<N> queryByType(Class<N> clazz, String sql) {
		return entityManager.createQuery(sql, clazz).getResultList();
	}

	@SuppressWarnings("unchecked")
	public <N> List<N> queryByType(Class<N> clazz, String sql, Map<String, Object> parmeters) {
		Query query = entityManager.createQuery(sql.trim(), clazz);
		for (String key : parmeters.keySet()) {
			query.setParameter(key, parmeters.get(key));
		}
		return query.getResultList();
	}

	@Override
	public List<?> query(String sql, Map<String, Object> parmeters) {
		Query query = entityManager.createQuery(sql.trim());
		for (String key : parmeters.keySet()) {
			query.setParameter(key, parmeters.get(key));
		}
		return query.getResultList();
	}

	@Override
	public void delete(Class<?> cls, Object[] ids) {
		Object obj = null;
		for (Object id : ids) {
			obj = entityManager.find(cls, id);
			entityManager.remove(obj);
		}
	}

	public void deleteById(Class<?> cls, Object id) {
		Object obj = entityManager.find(cls, id);
		entityManager.remove(obj);
	}

	@Override
	public Object getById(Class<?> cls, Object id) {
		return entityManager.find(cls, id);
	}

	@Override
	public List<?> getAll(Class<?> cls) {
		return getBy(false, false, cls, null, 0, 0);
	}

	@Override
	public long getRowSize(Class<?> cls) {
		return new Long(getBy(true, true, cls, null, 0, 0).get(0).toString());
	}

	@Override
	public long getRowSizeByEqual(Class<?> cls, Map<String, Object> paramsMap) {
		return new Long(getBy(true, false, cls, paramsMap, 0, 0).get(0).toString());
	}

	@Override
	public long getRowSizeByLike(Class<?> cls, Map<String, String> paramsMap) {
		Map<String, Object> map = toStringObjectMap(paramsMap);
		return new Long(getBy(true, true, cls, map, 0, 0).get(0).toString());
	}

	@Override
	public List<?> getSub(Class<?> cls, int from, int size) {
		return getBy(false, false, cls, null, from, size);
	}

	@Override
	public List<?> getSubByEqual(Class<?> cls, Map<String, Object> paramsMap, int from, int size) {
		return getBy(false, false, cls, paramsMap, from, size);
	}

	@Override
	public List<?> getByEqual(Class<?> cls, Map<String, Object> paramsMap) {
		return getBy(false, false, cls, paramsMap, 0, 0);
	}

	@Override
	public List<?> getSubByLike(Class<?> cls, Map<String, String> paramsMap, int from, int size) {
		Map<String, Object> map = toStringObjectMap(paramsMap);
		return getBy(false, true, cls, map, from, size);
	}

	@Override
	public List<?> getByLike(Class<?> cls, Map<String, String> paramsMap) {
		Map<String, Object> map = toStringObjectMap(paramsMap);
		return getBy(false, true, cls, map, 0, 0);
	}

	/**
	 * 可构造性的原始查询
	 * 
	 * @param <?>
	 * @param getRowCount
	 *            是否计算记录条数的查询（即，select count(*)）
	 * @param isVagou
	 *            是否模糊查询like（相对于精确查询equal）
	 * @param cls
	 *            对应的实体类名
	 * @param paramsMap
	 *            查询字段映射数据
	 * @param from
	 *            起始记录序号
	 * @param size
	 *            本次查询的记录数
	 * @return
	 */
	@Override
	public List<?> getBy(boolean getRowCount, boolean isVagou, Class<?> cls, Map<String, Object> paramsMap, int from, int size) {
		String paramTag = null; // sql语句中的占位参数标记名
		String paramValue = null; // sql语句中的参数值
		String entityValue = null; // sql语句中的实体参数名
		String className = cls.getSimpleName(); // 对应的类名
		String preSql = getRowCount ? "select count(e) from " : "select e from ";
		StringBuffer sql = new StringBuffer(preSql);
		sql.append(className).append(" e where e.id!=0 and 1=1 ");
		Query query = null;

		if (null != paramsMap) {
			// 构型
			for (String key : paramsMap.keySet()) {
				paramTag = ":".concat(key.replace(".", "_"));
				entityValue = "lower(e.".concat(key).concat(")");
				if (!isVagou) {
					sql.append(" and ").append(entityValue).append("=").append(paramTag).append(" ");
				} else if (paramTag.endsWith("_id")) {
					// 如果对应的是主键，则将like改为equal
					sql.append(" and ").append(entityValue).append("=").append(paramTag).append(" ");
				} else {
					sql.append(" and ").append(entityValue).append(" like ").append(paramTag).append(" ");
				}
			}
		}
		query = entityManager.createQuery(sql.toString());

		if (null != paramsMap) {
			// 填值
			for (String key : paramsMap.keySet()) {
				paramTag = key.replace(".", "_");
				paramValue = paramsMap.get(key).toString().toLowerCase();
				if (!isVagou) {
					try {
						query.setParameter(paramTag, new Long(paramValue));
					} catch (Exception ex) {
						query.setParameter(paramTag, paramValue);
					}
				} else {
					// 如果对应的是主键，则将like改为equal
					if (paramTag.endsWith("_id")) {
						query.setParameter(paramTag, new Long(paramValue));
					} else {
						query.setParameter(paramTag, "%" + paramValue + "%");
					}
				}
			}
		}
		if (from == 0 && size == 0) {
			return query.getResultList();
		}
		return query.setFirstResult(from).setMaxResults(size).getResultList();
	}

	/**
	 * 将<String, String>类型的映射转换为<String, Object>类型
	 * 
	 * @param paramsMap
	 * @return
	 */
	private Map<String, Object> toStringObjectMap(Map<String, String> paramsMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (String key : paramsMap.keySet()) {
			map.put(key, paramsMap.get(key));
		}
		return map;
	}

}
