package com.system.manager.dao;

import java.util.List;

import com.system.manager.entity.Attachment;
import com.system.manager.service.impl.EntityRepository;

public interface IAttachmentDAO extends EntityRepository<Attachment>{
	public List<Attachment> findByProperty(String propertyName, Object value) throws Exception;
}
