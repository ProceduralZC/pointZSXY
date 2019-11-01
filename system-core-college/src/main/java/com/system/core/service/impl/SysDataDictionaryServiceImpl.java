package com.system.core.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.dao.SysDataDictionaryDao;
import com.system.core.entity.SysDataDictionary;
import com.system.core.service.SysDataDictionaryService;
import com.system.core.util.DateUtils;
import com.system.core.util.ParamUtil;

@Service
public class SysDataDictionaryServiceImpl implements SysDataDictionaryService {
	
	@Autowired
	private SysDataDictionaryDao dataDictionaryDao;
	
	@Override
	public void add(SysDataDictionary bean) {
		bean.setCreateDate(DateUtils.getNowDateTime());
		bean.setIsdelete(0);
		dataDictionaryDao.save(bean);
	}

	@Override
	public void update(SysDataDictionary bean) {
		bean.setModifyDate(DateUtils.getNowDateTime());
		bean.setIsdelete(0);
		dataDictionaryDao.save(bean);
	}

	@Override
	public SysDataDictionary get(Integer id) {
		return dataDictionaryDao.findOne(id);
	}

	@Override
	@Transactional()
	public void del(String ids){
		Integer[] ides = ParamUtil.toIntegers(ids.split(","));
		for(Integer id:ides){
			SysDataDictionary bean = this.dataDictionaryDao.findOne(id);
			List<SysDataDictionary> tree = new ArrayList<SysDataDictionary>();
			loadTreeChilds(bean,tree);
			if(tree.size()>0){
				for(SysDataDictionary rootObj:tree){
					this.dataDictionaryDao.delDataDictionary(rootObj.getId());
				}
			}else{
				this.dataDictionaryDao.delDataDictionary(id);
			}
		}
	}

    //获取列表
	@Override
    @Transactional(readOnly=true)
	public List<SysDataDictionary> getTree(){
		//默认根节点的parentId为null，取根节点的列表
		List<SysDataDictionary> list= this.dataDictionaryDao.findParentDataDictionary();
		List<SysDataDictionary> tree = new ArrayList<SysDataDictionary>();
		for(SysDataDictionary rootObj:list ){
			loadTreeChilds(rootObj, tree);
		}
		return tree;
	}

	private void loadTreeChilds(SysDataDictionary d, List<SysDataDictionary> tree){
		tree.add(d);
		if (d.getChilds() == null){
			return;
		}
		Iterator<SysDataDictionary> childs = d.getChilds().iterator();
		while (childs.hasNext()){
			SysDataDictionary child = childs.next();
			if(child.getIsdelete()==0){
				loadTreeChilds(child, tree);
			}
		}
	}

	@Override
	public List<SysDataDictionary> findDataDictionaryByCodeAndDepth(String dataCode,Integer depth,Integer id){
		if(null!=id){
			List<SysDataDictionary> list=dataDictionaryDao.findDataDictionaryByCode(dataCode,depth,id);
			if(!list.isEmpty()){
				return list;
			}
		}else{
			List<SysDataDictionary> list=dataDictionaryDao.findDataDictionaryByCode(dataCode,depth);
			if(!list.isEmpty()){
				return list;
			}
		}
		return null;
	}

	@Override
	public SysDataDictionary findByCodeAndName(String dataCode, String name,Integer depth,Integer id){
		if(null!=id){
			List<SysDataDictionary> list=dataDictionaryDao.findDataDictionaryByCodeAndName(dataCode,name,depth,id);
			if(!list.isEmpty()){
				return list.get(0);
			}
		}else{
			List<SysDataDictionary> list=dataDictionaryDao.findDataDictionaryByCodeAndName(dataCode,name,depth);
			if(!list.isEmpty()){
				return list.get(0);
			}
		}
		return null;
	}

	@Override
	public List<SysDataDictionary> findByParentId(Integer parentId){
		if(0==parentId){
			return dataDictionaryDao.findParentDataDictionary();
		}
		return dataDictionaryDao.findByParentId(parentId);
	}

	@Override
	public SysDataDictionary findByCode(String code){
		List<SysDataDictionary> list=dataDictionaryDao.findByCode(code);
		if(!list.isEmpty()){
			return list.get(0);
		}
		return null;
	}
}
