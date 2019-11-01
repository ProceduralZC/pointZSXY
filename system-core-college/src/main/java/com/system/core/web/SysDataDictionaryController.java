package com.system.core.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.entity.SysDataDictionary;
import com.system.core.service.SysDataDictionaryService;
import com.system.core.util.ParamUtil;
import com.system.core.web.model.DataDictionaryModel;
import com.system.user.web.base.BaseController;

@RestController
@RequestMapping({ "/system" })
public class SysDataDictionaryController extends BaseController {

	@Autowired
	private SysDataDictionaryService dataDictionaryService;
	
	public void bindBean(DataDictionaryModel model, SysDataDictionary entity) {
		ParamUtil.bindBean(entity,model);
		if (model.getParentObjId() != null){
			entity.setParentObj(new SysDataDictionary(model.getParentObjId()));
		}else{
			entity.setParentObj(null);
		}
	}

	/**
	 * 增加数据字典
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/addDataDictionary" }, method = {RequestMethod.POST,RequestMethod.GET })
	public String add(@ModelAttribute DataDictionaryModel model,HttpServletRequest request) {
		SysDataDictionary bean = new SysDataDictionary();
		bindBean(model, bean);
		if (bean.getParentObj()!=null){
			if(null!=dataDictionaryService.findByCodeAndName(model.getDataCode(),model.getName(),model.getDepth(),null)){
				return super.message("", "数据字典名称重复!","error");
			}
		}else{//校验字典编码是否重复
			List<SysDataDictionary> list = this.dataDictionaryService.findDataDictionaryByCodeAndDepth(model.getDataCode(),1,null);
			if(null!=list && !list.isEmpty()){
				return super.message("", "根节点数据字典代码重复!","error");
			}
		}
		String[] str=bean.getName().split("、");
		for(String s:str){
			if(!s.equals("")){
				SysDataDictionary bean1 = new SysDataDictionary();
				bean1.setCode(bean.getCode());
				bean1.setDataCode(bean.getDataCode());
				bean1.setDepth(bean.getDepth());
				bean1.setName(s);
				bean1.setOrderBy(1);
				bean1.setStatus(1);
				bean1.setParentObj(bean.getParentObj());
				this.dataDictionaryService.add(bean1);
			}
		}
		return super.message("", "添加成功!","success");
	}

	/**
	 * 查询数据字典
	 * @param id
	 * @return
	 */
	@RequestMapping(value = { "/getDataDictionary/{id}" }, method = {RequestMethod.GET })
	public SysDataDictionary get(@PathVariable Integer id) {
		SysDataDictionary bean = this.dataDictionaryService.get(id);
		return bean;
	}

	/**
	 * 修改数据字典
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/updateDataDictionary/{id}" }, method = {RequestMethod.POST })
	public String update(@PathVariable Integer id,@ModelAttribute DataDictionaryModel model, HttpServletRequest request) {
		SysDataDictionary bean = this.dataDictionaryService.get(id);
		bindBean(model, bean);
		if (bean.getParentObj()!=null){
			if(null!=dataDictionaryService.findByCodeAndName(model.getDataCode(),model.getName(),model.getDepth(),id)){
				return super.message("", "数据字典名称重复!","error");
			}
		}else{//校验字典编码是否重复
			List<SysDataDictionary> list = this.dataDictionaryService.findDataDictionaryByCodeAndDepth(model.getDataCode(),1,id);
			if(null!=list &&!list.isEmpty()){
				return super.message("", "根节点数据字典代码重复!","error");
			}
		}
		dataDictionaryService.update(bean);
		return super.message(id.toString(), "修改成功","success");
	}
	/**
	 * 删除数据字典
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(value = { "/delDataDictionary" }, method = {RequestMethod.POST })
	public String delete(@RequestParam() String ids, HttpServletRequest request) {
		this.dataDictionaryService.del(ids);
		return super.message(ids, "删除成功","success");
	}
	/**
	 * 获取数据字典树
	 * @return
	 */
	@RequestMapping(value = { "/dataDictionary/tree" }, method = {RequestMethod.GET,RequestMethod.POST })
	public List<SysDataDictionary> tree() {
		List<SysDataDictionary> list = this.dataDictionaryService.getTree();
		return list;
	}
	/**
	 * 根据字典编码查询数据字典集合
	 * @param dataCode
	 * @return
	 */
	@RequestMapping(value = { "/dataDictionary/getListByCode" }, method = {RequestMethod.GET,RequestMethod.POST })
	public List<SysDataDictionary> getListByCode(@RequestParam String dataCode,@RequestParam Integer depth) {
		List<SysDataDictionary> list = this.dataDictionaryService.findDataDictionaryByCodeAndDepth(dataCode,depth,null);
		return list;
	}
	
	@RequestMapping(value = { "/dataDictionary/findByParentId/{parentId}" }, method = {RequestMethod.GET,RequestMethod.POST })
	public List<SysDataDictionary> findByParentId(@PathVariable Integer parentId) {
		List<SysDataDictionary> list =dataDictionaryService.findByParentId(parentId);
		return list;
	}
}
