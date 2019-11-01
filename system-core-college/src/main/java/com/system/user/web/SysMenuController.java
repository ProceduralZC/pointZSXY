package com.system.user.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.user.entity.SysMenu;
import com.system.user.service.SysMenuService;
import com.system.user.web.base.BaseController;
import com.system.user.web.model.SysMenuModel;

/**
 * 菜单管理
 * @author Administrator
 *
 */
@RestController
@RequestMapping(value="/system")
public class SysMenuController extends BaseController {
	@Autowired
	private SysMenuService sysMenuService;
	
	/**
	 * 分页查询
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysMenus", method = {RequestMethod.GET,RequestMethod.POST})
	public List<SysMenu> findAll(HttpServletRequest request, HttpServletResponse response) {
		return sysMenuService.findListMenu();
	}
	
	@RequestMapping(value = "/addSysMenu", method = {RequestMethod.POST })
	public String addSysMenu(@ModelAttribute SysMenuModel model,HttpServletRequest request) {
		Integer id = sysMenuService.addSysMenu(model);
		return super.message(id.toString(), "操作成功","success");
	}
	@RequestMapping(value = "/getSysMenu/{id}", method = {RequestMethod.GET })
	public SysMenu updateSysMenu(@PathVariable Integer id) {
		return sysMenuService.getMenu(id);
	}
	@RequestMapping(value = "/updateSysMenu/{id}", method = {RequestMethod.POST })
	public String updateSysMenu(@PathVariable Integer id,@ModelAttribute SysMenuModel model,HttpServletRequest request) {
		sysMenuService.updateSysMenu(id,model);
		return super.message(id.toString(), "操作成功","success");
	}
	
	@RequestMapping(value = "/delSysMenu", method = {RequestMethod.POST })
	public String delSysMenu(@RequestParam() String ids,HttpServletRequest request) {
		sysMenuService.delSysMenu(ids);
		return super.message(ids, "操作成功","success");
	}
}
