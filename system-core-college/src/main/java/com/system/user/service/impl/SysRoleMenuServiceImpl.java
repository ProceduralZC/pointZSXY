package com.system.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.util.ParamUtil;
import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysRoleMenu;
import com.system.user.service.SysRoleMenuService;
@Service
public class SysRoleMenuServiceImpl implements SysRoleMenuService {
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;//角色菜单中间表
	/**
	 * 插入角色菜单中间表
	 * @param roleId
	 * @param menusId
	 */
	@Transactional
	@Override
	public void insertSysRoleMenu(Integer roleId,String menusId) {
		sysRoleMenuDao.delByRoleId(roleId);//删除
		if(!ParamUtil.isEmpty(menusId)){
			Integer[] mensId = ParamUtil.toIntegers(menusId.split(","));//转换为int数组
			SysRoleMenu bean = null;
			for (Integer integer : mensId) {
				bean = new SysRoleMenu();
				bean.setSysRoleId(roleId);
				bean.setSysMenuId(integer);
				sysRoleMenuDao.save(bean);
			}
		}
	}
	
	/**
	 * 根据角色查询菜单
	 * @param roleId
	 * @return
	 */
	@Override
	public List<SysRoleMenu> listMenuByRoleId(Integer roleId) {
		return sysRoleMenuDao.findByRoleId(roleId);
	}

}
