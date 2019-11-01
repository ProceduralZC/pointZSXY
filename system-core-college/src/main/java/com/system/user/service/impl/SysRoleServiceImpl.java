package com.system.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.user.dao.SysRoleDao;
import com.system.user.entity.SysRole;
import com.system.user.service.SysRoleService;

@Service
public class  SysRoleServiceImpl implements SysRoleService{
	
	@Autowired
	private SysRoleDao sysRoleDao;

	@Override
	public SysRole getSysRoleByid(Integer id) {
		return sysRoleDao.findOne(id);
	}
	@Override
	public List<SysRole> getSysRolesByUserId(Integer userId) {
		return sysRoleDao.findBySysUserId(userId);
	}
	@Override
	public List<SysRole> findSysRole() {
		return sysRoleDao.findSysRole();
	}
}
