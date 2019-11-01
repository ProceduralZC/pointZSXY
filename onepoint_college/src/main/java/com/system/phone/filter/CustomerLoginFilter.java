package com.system.phone.filter;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system.core.jsonpacket.JsonResponse;
import com.system.core.jsonpacket.StatusCode;
import com.system.user.web.base.SysUsersManager;

public class CustomerLoginFilter implements Filter {
	
	@Autowired
	private SysUsersManager sysUsersManager;
	public void setSysUsersManager(SysUsersManager sysUsersManager) {
		this.sysUsersManager = sysUsersManager;
	}

	/**
	 * 不需要验证的页面
	 */
	private String[] notCheckJsp = new String[] {};
	/**
	 * 默认的编码 utf-8
	 */
	private String defaultEncode = "UTF-8";
	private JsonResponse jsonResponse = null;
	
	@Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
		request.setCharacterEncoding(defaultEncode);
		response.setCharacterEncoding(defaultEncode);
		((HttpServletResponse) response).setHeader("Expires", "0");
		((HttpServletResponse) response).setHeader("Cache-Control", "no-cache");
		((HttpServletResponse) response).setHeader("Prama", "no-cache");
		// 查看有没有登陆，如果没有登陆，那么要进行跳转
		String nowUrl = (String) ((HttpServletRequest) request).getRequestURI();
		boolean isNotCheckJsp = false;
		for (String s : notCheckJsp) {
			if (nowUrl.indexOf(s) != -1) {
				isNotCheckJsp = true;
				break;
			}
		}
		if (isNotCheckJsp) {
			// 如果检测到当前页面不需要验证用户，那么传入下一个filter
			chain.doFilter(request, response);
		} else {
			if (!hasLogin((HttpServletRequest) request)) {
				((HttpServletRequest) request).getSession().setAttribute("nowUrl", ((HttpServletRequest) request).getRequestURL());
				response.getWriter().print(objectToJson(jsonResponse));
			} else {
				chain.doFilter(request, response);
			}
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String _notCheckJsp = filterConfig.getInitParameter("notCheckJsp");
		if (_notCheckJsp != null)
		      this.notCheckJsp = _notCheckJsp.split(",");
		jsonResponse = new JsonResponse();
		jsonResponse.setCode(StatusCode.CODE_FAIL);
		jsonResponse.setMsg("登录失效,请重新登录!");
	}
	
	@Override
	public void destroy() {
		
	}
	
	private boolean hasLogin(HttpServletRequest request) {
		 if(null!=sysUsersManager.getSysUsers(request, false)){
			 return true;
		 }
		 return false;
	}

	public static String objectToJson(Object obj){
		    ObjectMapper mapper = new ObjectMapper();
		    String jsonStr = "";
		    try {
		      jsonStr = mapper.writeValueAsString(obj);
		    } catch (IOException e) {
		      e.printStackTrace();
		    }
		    return jsonStr;
	 }
}
