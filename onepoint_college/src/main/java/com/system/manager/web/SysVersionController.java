package com.system.manager.web;

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

import com.system.core.entity.SysAffix;
import com.system.core.util.ParamUtil;
import com.system.manager.entity.SysVersion;
import com.system.manager.service.SysVersionService;
import com.system.manager.web.model.VersionModel;
import com.system.user.web.base.BaseController;


@RestController
@RequestMapping(value="/system")
public class SysVersionController extends BaseController {
	@Autowired
	private SysVersionService versionService;
	/**
	 * 分页查询
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/versions", method = {RequestMethod.GET,RequestMethod.POST})
	public Page<SysVersion> findAll(@ModelAttribute VersionModel model,HttpServletRequest request, HttpServletResponse response) {
		Pageable pageable = super.getPageable(request);
		return versionService.pageList(pageable,model);
	}
	@RequestMapping(value = "/addVersion", method = {RequestMethod.POST })
	public String addVersion(@ModelAttribute VersionModel model,HttpServletRequest request) {
		SysVersion page = new SysVersion();
		ParamUtil.bindBean(page,model);
		if(model.getApkId()!=null){
			 page.setApk(new SysAffix(model.getApkId()));
		}
		versionService.addVersion(page);
		return super.message(page.getId().toString(), "操作成功!","success");
	}
	
	
	@RequestMapping(value = "/getVersion/{id}", method = {RequestMethod.GET })
	public SysVersion getVersion(@PathVariable Integer id) {
		return versionService.getVersion(id);
	}
	
	
	@RequestMapping(value = "/updateVersion/{id}", method = {RequestMethod.POST })
	public String updateVersion(@PathVariable Integer id,@ModelAttribute VersionModel model,HttpServletRequest request) {
		SysVersion page = versionService.getVersion(id);
		ParamUtil.bindBean(page,model);
		if(model.getApkId()!=null){
			 page.setApk(new SysAffix(model.getApkId()));
		}
		versionService.updateVersion(page);
		return super.message(id.toString(), "操作成功!","success");
	}
	
	@RequestMapping(value = "/delVersion", method = {RequestMethod.POST })
	public String delVersion(@RequestParam() String ids,HttpServletRequest request) {
		versionService.delVersion(ids);
		return super.message(ids, "操作成功!","success");
	}
}
