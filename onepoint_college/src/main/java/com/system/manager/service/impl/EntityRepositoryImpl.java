package com.system.manager.service.impl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public abstract class EntityRepositoryImpl<T> implements EntityRepository<T> {

	protected abstract Class<T> getPersistentClass();

	@Autowired
	protected BaseRepository baseRepository;
	
	public Long findSequence(String lsh) throws Exception{
		return baseRepository.findSequence(getPersistentClass(),lsh);
	}
	
//	public void save(T obj) {
//		if (obj instanceof BaseEntity) {
//			BaseEntity entity = (BaseEntity) obj;
//			if (entity.getCjrq() == null) {
//				entity.setCjrq(new Date());
//			}
//			if (entity.getZhxrq() == null) {
//				entity.setZhxrq(new Date());
//			}
//		}
//		baseRepository.save(obj);
//	}
//
//	public void update(T obj) {
//		if (obj instanceof BaseEntity) {
//			BaseEntity entity = (BaseEntity) obj;
//			entity.setZhxrq(new Date());
//		}
//		baseRepository.update(obj);
//	}

	@Override
	public void delete(Object[] ids) {
		baseRepository.delete(getPersistentClass(), ids);
	}

	@Override
	public void deleteById(Object id) {
		baseRepository.deleteById(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T getById(Object id) {
		return (T) baseRepository.getById(getPersistentClass(), id);
	}

	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		return (List<T>) baseRepository.getBy(false, false, getPersistentClass(), null, 0, 0);
	}

	public List<?> query(String sql, Map<String, Object> parmeters){
		return baseRepository.query(sql, parmeters);
	}
	
	public BaseRepository getBaseRepository() {
		return baseRepository;
	}

	public void setBaseRepository(BaseRepository baseRepository) {
		this.baseRepository = baseRepository;
	}
	
}
