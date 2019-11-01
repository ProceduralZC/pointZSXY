package com.system.user.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
import com.system.user.web.model.SysUsersModel;


@RestController
@RequestMapping(value="/system")
public class SysUsersController extends BaseController {
	@Autowired
	private SysUsersService sysUsersService;
	
	/**
	 * 分页查询
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/sysUsers", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<SysUsers> findAll(@ModelAttribute SysUsersModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return sysUsersService.pageList(model,pageable);
	}
	/**
	 * 添加用户
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/addSysUsers", method = {RequestMethod.POST })
	public String addSysUsers(@ModelAttribute SysUsersModel model,HttpServletRequest request) {
		SysUsers sysUsers = sysUsersService.getSysUsersByUsername(model.getUsername(), null);
		if(sysUsers!=null){
			return super.message("","对不起,用户名重复!","error");
		}
		Integer id = sysUsersService.addSysUsers(model);
		return super.message(id.toString(), "操作成功","success");
	}
	/**
	 * 获取用户
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getSysUsers/{id}", method = {RequestMethod.GET })
	public SysUsers getSysUsers(@PathVariable Integer id) {
		return sysUsersService.getSysUsersByid(id);
	}
	/**
	 * 更新用户
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateSysUsers/{id}", method = {RequestMethod.POST })
	public String updateSysUsers(@PathVariable Integer id,@ModelAttribute SysUsersModel model,HttpServletRequest request) {
		SysUsers sysUsers = sysUsersService.getSysUsersByUsername(model.getUsername(), id);
		if(sysUsers!=null){
			return super.message("","对不起,用户名重复!","error");
		}
		sysUsersService.updateSysUsers(id,model);
		return super.message(id.toString(), "操作成功","success");
	}
	/**
	 * 删除用户
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delSysUsers", method = {RequestMethod.POST })
	public String delSysUsers(@RequestParam() String ids,HttpServletRequest request) {
		sysUsersService.delSysUsers(ids);
		return super.message(ids, "操作成功","success");
	}
	
	@RequestMapping(value = "/upSysUsersStatus", method = {RequestMethod.POST })
	public String upSysUsersStatus(@RequestParam() Integer status,@RequestParam() String ids,HttpServletRequest request) {
		sysUsersService.upSysUsersStatus(status, ids);
		return super.message(ids, "操作成功","success");
	}
	/**
	 * 更新密码
	 */
	@RequestMapping(value = "/upSysUsersPass", method = {RequestMethod.POST })
	public String upSysUsersPass(@RequestParam() String password,@RequestParam() String ids,HttpServletRequest request) {
		sysUsersService.upSysUsersPass(password, ids);
		return super.message(ids, "操作成功","success");
	}
}
