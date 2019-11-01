package com.system.manager.dao.impl;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.manager.dao.PhotoDao;
import com.system.manager.entity.Photo;
import com.system.manager.service.impl.BaseRepository;
import com.system.manager.service.impl.EntityRepositoryImpl;
@Repository
public class PhotoDaoImpl extends EntityRepositoryImpl<Photo> implements PhotoDao {
	
	@PersistenceContext 
	EntityManager em;
	@Autowired
	protected BaseRepository baseRepository;
	@Override
	protected Class<Photo> getPersistentClass() {
		return Photo.class;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Photo> queryPhotoListByFkId(String themeId) {
		Query q=em.createQuery("from Photo where pfkid=:fkid order by pid");
		q.setParameter("fkid", themeId);
		return q.getResultList();
	}
	@Override
	public void savePhoto(Photo obj) {
//		em.persist(p);
		baseRepository.save(obj);
	}
	@Override
	public void deletePhoto(String pid) {
		Query qy=em.createQuery("delete from Photo  where pfkid=:pfkid");
		qy.setParameter("pfkid", pid);
		qy.executeUpdate();
	}
	@Override
	public void save(Photo obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Photo obj) {
		// TODO Auto-generated method stub
		
	}
}
