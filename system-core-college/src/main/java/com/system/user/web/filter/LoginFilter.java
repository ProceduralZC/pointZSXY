package com.system.user.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.system.user.entity.SysUsers;
import com.system.user.web.base.SysUsersManager;

public class LoginFilter implements Filter {
	private String defaultEncode = "UTF-8";//默认的编码 utf-8
	private String login_url = "/manager/index.html";
	private String[] notCheckJsp = new String[] { "/userlogin" };

	@Autowired
	private SysUsersManager sysUsersManager;
	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(this.defaultEncode);
	    HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String basePath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort();
        String path = req.getContextPath();
		String nowUrl = req.getRequestURI();
		boolean isNotCheckJsp = false;
		for (String s : this.notCheckJsp) {
			if (nowUrl.indexOf(s) != -1) {
				isNotCheckJsp = true;
				break;
			}
		}
		if (isNotCheckJsp) {
			chain.doFilter(req, res);
			return ;
		}else if (!hasLogin((HttpServletRequest) request)) {
			res.sendRedirect(basePath+path+login_url);
			return;
		}else {
			chain.doFilter(req, res);
			return;
		}

	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		String encode = config.getInitParameter("Encode");
		String _notCheckJsp = config.getInitParameter("notCheckJsp");
		String _login_url = config.getInitParameter("login_url");
		if (_login_url != null) {
			this.login_url = _login_url;
		}
		if (encode != null) {
			this.defaultEncode = encode;
		}
		if (_notCheckJsp != null)
			this.notCheckJsp = _notCheckJsp.split(",");

	}
	private boolean hasLogin(HttpServletRequest request) {
		SysUsers sysUsers =sysUsersManager.getSysUsers(request,true);
		if (sysUsers!= null) {
			return true;
		}
	    return false;
	}
}
