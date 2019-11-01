package com.system.core.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.core.config.SysConfig;
import com.system.core.dao.SysAffixDao;
import com.system.core.entity.SysAffix;
import com.system.core.service.SysAffixService;

@Service
public class SysAffixServiceImpl implements SysAffixService {
	
	@Autowired
	private SysConfig config;
	@Autowired
	private SysAffixDao affixDao;
	@Override
	public void add(SysAffix sysAffix) {
		affixDao.save(sysAffix);
	}

	@Override
	public void update(SysAffix sysAffix) {
		affixDao.save(sysAffix);
	}

	@Override
	public void del(Integer id) {
		SysAffix sysAffix = affixDao.findOne(id);
		String filePath = config.getFileLocalDir()+sysAffix.getSource();
		File file = new File(filePath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	    }  
		this.affixDao.delete(id);
	}

	@Override
	public SysAffix get(Integer id) {
		return affixDao.findOne(id);
	}
}
