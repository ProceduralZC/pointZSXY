package com.system.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.util.ParamUtil;
import com.system.user.dao.SysMenuDao;
import com.system.user.dao.SysRoleMenuDao;
import com.system.user.entity.SysMenu;
import com.system.user.service.SysMenuService;
import com.system.user.web.model.SysMenuModel;

@Service("sysMenuService")
public class  SysMenuServiceImpl implements SysMenuService{
	
	@Autowired
	private SysMenuDao sysMenuDao;
	@Autowired
	private SysRoleMenuDao sysRoleMenuDao;//角色菜单中间表
	
	/**
	 * 查询所有的菜单
	 * @return
	 */
	public List<SysMenu> findListMenu(){
		return sysMenuDao.findAll();
	}

	@Override
	public SysMenu getMenu(Integer id) {
		return sysMenuDao.findOne(id);
	}

	@Override
	public Integer addSysMenu(SysMenuModel model) {
		SysMenu sysMenu = new SysMenu();
		ParamUtil.bindBean(sysMenu,model);
		sysMenu.setParentObj(new SysMenu(model.getParentId()));
		sysMenuDao.save(sysMenu);
		return sysMenu.getId();
	}

	@Override
	public Integer updateSysMenu(Integer id, SysMenuModel model) {
		SysMenu sysMenu = sysMenuDao.findOne(id);
		ParamUtil.bindBean(sysMenu,model);
		sysMenu.setParentObj(new SysMenu(model.getParentId()));
		sysMenuDao.save(sysMenu);
		return sysMenu.getId();
	}

	@Override
	@Transactional
	public void delSysMenu(String ids) {
		if(!ParamUtil.isEmpty(ids)){
			Integer[] ides = ParamUtil.toIntegers(ids.split(","));
			for (Integer integer : ides) {
				List<SysMenu> listSysMenu = sysMenuDao.findByParentId(integer);//查出所有的子结构
				if(listSysMenu!=null && listSysMenu.size()>0){
					for(SysMenu sysMenu : listSysMenu) {
						sysRoleMenuDao.delByMenuId(sysMenu.getId());//删除中间表
						sysMenuDao.delete(sysMenu.getId());
					}
				}
				sysRoleMenuDao.delByMenuId(integer);
				sysMenuDao.delete(integer);
			}
		}
	}
}
