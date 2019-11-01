package com.system;

import javax.servlet.DispatcherType;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.system.phone.filter.CustomerLoginFilter;
import com.system.user.web.base.SysUsersManager;
import com.system.user.web.filter.LoginFilter;

@SpringBootApplication(scanBasePackages="com.system")
public class MaterialsApplication extends SpringBootServletInitializer{
	
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MaterialsApplication.class);
    }
	
	@Bean
	public FilterRegistrationBean loginCustomerFilter() {
	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    CustomerLoginFilter customerLoginFilter = new CustomerLoginFilter();
	    customerLoginFilter.setSysUsersManager(difSysUsersManager());
	    registration.setFilter(customerLoginFilter);
	    registration.addUrlPatterns("/ms/*");
	    registration.setDispatcherTypes(DispatcherType.ASYNC,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.REQUEST);
	    return registration;
	}
	
	@Bean
	public SysUsersManager difSysUsersManager(){
		return new SysUsersManager();
	}
	
	@Bean
	public FilterRegistrationBean difloginFilter() {//过滤后台的接口
		FilterRegistrationBean registration = new FilterRegistrationBean();
		LoginFilter loginFilter = new LoginFilter();
		loginFilter.setSysUsersManager(difSysUsersManager());
		registration.setFilter(loginFilter);
		registration.addUrlPatterns("/manager/*,/system/*".split(","));
		registration.addInitParameter("notCheckJsp",
				"/manager/jquery,/manager/kindeditor,/manager/head.js,/manager/script,/login.html,/manager/css,/manager/images," +
				"/affix,/download,/showImage,/getRandomCode,/userLogin,/login_out");
		registration.addInitParameter("login_url","/manager/home/login.html");
		registration.setDispatcherTypes(DispatcherType.ASYNC,DispatcherType.FORWARD,DispatcherType.INCLUDE,DispatcherType.REQUEST);
		return registration;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MaterialsApplication.class, args);
	}
}