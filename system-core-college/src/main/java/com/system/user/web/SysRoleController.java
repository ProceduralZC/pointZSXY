package com.system.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.user.entity.SysRole;
import com.system.user.entity.SysRoleMenu;
import com.system.user.service.SysRoleMenuService;
import com.system.user.service.SysRoleService;
import com.system.user.web.base.BaseController;


@RestController
@RequestMapping(value="/system")
public class SysRoleController extends BaseController {
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysRoleMenuService roleMenuService;
	
	@RequestMapping(value = "/getSysRole/{id}", method = {RequestMethod.GET })
	public SysRole updateSysRole(@PathVariable Integer id) {
		return sysRoleService.getSysRoleByid(id);
	}
	@RequestMapping(value = "/findSysRole", method = {RequestMethod.POST})
	public List<SysRole> findSysRole(HttpServletRequest request, HttpServletResponse response) {
		List<SysRole> list = sysRoleService.findSysRole();
		return list;
	}
	/**
	 * 根据角色查询菜单
	 * @param roleid
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findMenuByRoles/{roleid}", method = {RequestMethod.GET})
	public List<SysRoleMenu> findMenuByRoles(@PathVariable Integer roleid,HttpServletRequest request, HttpServletResponse response) {
		return roleMenuService.listMenuByRoleId(roleid);
	}
	/**
	 * 插入角色菜单中间表
	 * @param roleid
	 * @param menusId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/insertSysRoleMenu/{roleid}", method = {RequestMethod.POST})
	public String findMenuByRoles(@PathVariable Integer roleid,@RequestParam String menusId, HttpServletRequest request, HttpServletResponse response) {
		roleMenuService.insertSysRoleMenu(roleid, menusId);
		return super.message("", "操作成功","success");
	}

}
