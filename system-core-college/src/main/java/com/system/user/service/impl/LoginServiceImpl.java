package com.system.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.dao.base.BaseRepository;
import com.system.core.util.DigestMD5Util;
import com.system.user.dao.SysUsersDao;
import com.system.user.entity.SysMenu;
import com.system.user.entity.SysUsers;
import com.system.user.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private SysUsersDao sysUsersDao;
	@Autowired
	private BaseRepository<SysMenu> baseRepository;
	/**
	 * 用户登录
	 */
	@Transactional
	public String login(String username, String password, String randomCode,HttpServletRequest request) {
		int flag = 0;
		String mess = "";
		HttpSession session = request.getSession();
		String code =null==session.getAttribute("randomCode")?"":session.getAttribute("randomCode").toString();
		session.removeAttribute("randomCode");//清除session
		if(!code.equals(randomCode)){
			flag = 1;//验证码错误
			mess = "验证码错误";
		}else{
			SysUsers sysUsers = sysUsersDao.findSysUsersByUsername(username);
			if(sysUsers==null){
				flag = 2;//用户不存在
				mess = "用户不存在";
			}else{
				if(!DigestMD5Util.MD5(password).equals(sysUsers.getPassword())){
					flag = 3;//密码错误
					mess = "密码错误";
				}else if(sysUsers.getStatus()==1){
					flag = 4;//密码错误
					mess = "用户已禁用";
				}else{
					session.setAttribute("USER_ID", sysUsers.getId());
					mess = "验证成功";
				}
			}
		}
		return "{\"message\":\"" + mess + "\",\"flag\":\"" + flag + "\"}";
	}

	/**
	 *  用户修改自己的密码
	 */
	@Transactional
	public String updateMyPass(String oldPass, String newPass,HttpServletRequest request) {
		int flag = 0;
		String mess = "";
		HttpSession session = request.getSession();
		Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
	    SysUsers sysUsers =sysUsersDao.findOne(userId);
		if(!DigestMD5Util.MD5(oldPass).equals(sysUsers.getPassword())){
			flag = 1;//原始密码错误
			mess = "原始密码错误!";
		}else{
			sysUsersDao.upSysUsersPass(DigestMD5Util.MD5(newPass),new Integer[]{sysUsers.getId()});
			sysUsers.setPassword(DigestMD5Util.MD5(newPass));
			session.setAttribute("sys_users", sysUsers);
			mess = "修改成功!";
		}
		return "{\"message\":\"" + mess + "\",\"flag\":\"" + flag + "\"}";
	}
	
	/**
	 * 查询当前登录人的菜单
	 */
	public List<SysMenu> listLoginMenu(Integer userId) {
		String hql="from SysMenu where id in (select  sysMenuId from SysRoleMenu where sysRoleId in (select roleid from SysUsersRole where userid=?1 ))";
		hql+=" order by orderBy asc";
		return baseRepository.find(hql, new Object[]{userId},-1,-1);
	}
}
