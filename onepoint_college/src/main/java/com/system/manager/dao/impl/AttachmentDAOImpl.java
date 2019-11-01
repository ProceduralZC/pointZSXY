package com.system.manager.dao.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.system.manager.dao.IAttachmentDAO;
import com.system.manager.entity.Attachment;
import com.system.manager.service.impl.BaseRepository;
import com.system.manager.service.impl.EntityRepositoryImpl;
@Repository
public class AttachmentDAOImpl extends EntityRepositoryImpl<Attachment> implements IAttachmentDAO{
	@Autowired
	protected BaseRepository baseRepository;
	@Override
	public List<Attachment> findByProperty(String propertyName, Object value) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Attachment obj) {
		// TODO Auto-generated method stub
		baseRepository.save(obj);
	}

	@Override
	protected Class<Attachment> getPersistentClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Attachment obj) {
		// TODO Auto-generated method stub
		
	}

}
