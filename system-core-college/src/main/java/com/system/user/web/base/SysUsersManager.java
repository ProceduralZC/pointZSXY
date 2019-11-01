package com.system.user.web.base;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.user.entity.SysUsers;
import com.system.user.service.SysUsersService;

public class SysUsersManager {
	
	@Autowired
	private SysUsersService sysUsersService;
	
	public SysUsers getSysUsers(HttpServletRequest request,boolean flag){
		if(flag){
			Integer userId=(Integer)request.getSession().getAttribute("USER_ID");
			if(null==userId){
				return null;
			}
			SysUsers sysUsers =sysUsersService.getSysUsersByid(userId);
			return sysUsers;
		}else{
			 Cookie jsessionId = getSessionCookie(request);
			 if(jsessionId!=null){
				 String access_token = jsessionId.getValue();
				 return sysUsersService.findSysUsersByToken(access_token);
			 }
		}
		return null;
	}
	
	private Cookie getSessionCookie(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies) {
				if ("ACCESS_TOKEN".equals(cookie.getName())) {
					return cookie;
				}
			}
		return null;
	}
}
