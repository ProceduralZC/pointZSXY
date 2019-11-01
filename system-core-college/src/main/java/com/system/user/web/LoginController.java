package com.system.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUsers;
import com.system.user.service.LoginService;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;


@RestController
@RequestMapping(value="/system")
public class LoginController extends BaseController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private SysUsersService sysUsersService;
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @param randomCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/userLogin", method = {RequestMethod.GET,RequestMethod.POST})
	public String userLogin(@RequestParam() String username,
			@RequestParam() String password,
			@RequestParam() String randomCode,
			HttpServletRequest request) {
		return loginService.login(username, password, randomCode, request);
	}
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login_out", method = {RequestMethod.GET})
	public String login_out(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("USER_ID");
		session.removeAttribute("orgList");
		session.invalidate();
		return super.message("ok", "注销成功!", "1");
	}
	/**
	 * 用户修改密码
	 * @param username
	 * @param password
	 * @param randomCode
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateMyPass", method = {RequestMethod.GET,RequestMethod.POST})
	public String updateMyPass(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass,
			HttpServletRequest request) {
		return loginService.updateMyPass(oldPass, newPass, request);
	}
	
	/**
	 * 查询当前登录人的菜单
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listLoginMenu", method = {RequestMethod.GET,RequestMethod.POST})
	public List<SysMenu> updateMyPass(HttpServletRequest request) {
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
	    SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
	    List<SysMenu> list= loginService.listLoginMenu(sysUsers.getId());
	    List<SysMenu> beans = new ArrayList<SysMenu>();
	    if(1==sysUsers.getRoleId()){
	    	String code  =sysUsers.getCode();
		    for (SysMenu sysMenu : list) {
		    		if(sysMenu.getId()==28){
		    			if(code.contains("1")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else if(sysMenu.getId()==26){
		    			if(code.contains("2")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else if(sysMenu.getId()==29){
		    			if(code.contains("3")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else if(sysMenu.getId()==27){
		    			if(code.contains("4")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else if(sysMenu.getId()==30){
		    			if(code.contains("5")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else if(sysMenu.getId()==37){
		    			if(code.contains("6")){
		    				beans.add(sysMenu);
		    			}else{
		    				continue;
		    			}
		    		}else{
		    			beans.add(sysMenu);
		    		}
				}
		    return beans;
		}else{
			return list;
		}
	}
	
	
	@RequestMapping(value = "/getLoginUser", method = RequestMethod.GET)
	public SysUsers getLoginUser(HttpServletRequest request) {
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
	    SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
		return sysUsers;
	}
}
