package com.system.user.service;

import java.util.List;

import com.system.user.entity.SysMenu;
import com.system.user.web.model.SysMenuModel;

public interface SysMenuService{
	/**
	 * 查询所有的菜单
	 * @return
	 */
	public List<SysMenu> findListMenu();
	/**
	 * 查询一个
	 * @param id
	 * @return
	 */
	public SysMenu getMenu(Integer id);
	
	/**
	 * 添加
	 * @param model
	 */
	public Integer addSysMenu(SysMenuModel model);
	/**
	 * 更新
	 * @param model
	 */
	public Integer updateSysMenu(Integer id,SysMenuModel model);
	/**
	 * 删除菜单
	 * @param ids
	 */
	public void delSysMenu(String ids);
	
}

