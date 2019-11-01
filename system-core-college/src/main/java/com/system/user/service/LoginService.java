package com.system.user.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.system.user.entity.SysMenu;


public interface LoginService{
	/**
	 * 用户登录
	 * @param id
	 * @return
	 */
	public String login(String username,String password,String randomCode,HttpServletRequest request);
	/**
	 * 用户修改自己的密码
	 * @param oldPass
	 * @param newPass
	 * @param request
	 * @return
	 */
	public String updateMyPass(String oldPass,String newPass,HttpServletRequest request);
	
	/**
	 * 查询当前登录人的菜单
	 * @param userId
	 * @return
	 */
	public List<SysMenu> listLoginMenu(Integer userId);
}