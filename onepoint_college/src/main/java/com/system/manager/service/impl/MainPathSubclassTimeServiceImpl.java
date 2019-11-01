package com.system.manager.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.util.DateUtils;
import com.system.core.util.ParamUtil;
import com.system.manager.dao.MainPathSubclassTimeRecordDao;
import com.system.manager.entity.MainPathSubclassTime;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.MainPathSubclassTimeService;
import com.system.manager.web.model.MainPathSubclassTimeModel;
import com.system.manager.web.model.MainSubclassTimeModel;
@Service
public class MainPathSubclassTimeServiceImpl implements MainPathSubclassTimeService {
	@Autowired
	private MainPathSubclassTimeRecordDao mainPathSubclassTimeRecordDao;
	/**
	 * 添加课时
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addPathSubclassTime(
			MainPathSubclassTimeModel model) {
		// TODO Auto-generated method stub
		MainPathSubclassTime bean = new MainPathSubclassTime();
		ParamUtil.bindBean(bean,model);		
		bean.setPathsubclasstimeaddtime(DateUtils.getNowDateTime());
		bean.setId(null);
		mainPathSubclassTimeRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}

	/**
	 * 查询所有课时
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainPathSubclassTime> pageList(Pageable pageable,
			MainPathSubclassTimeModel model) {
		// TODO Auto-generated method stub
		return  mainPathSubclassTimeRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainPathSubclassTime> getSpecification(final MainPathSubclassTimeModel model){
		return new Specification<MainPathSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<MainPathSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				if(andPredicates.isEmpty()){
					return null;
				}else{
					Predicate predicate = cb.conjunction();
					predicate.getExpressions().addAll(andPredicates);
					return predicate;
				}
			}
		};
	}

	/**
	 * 通过条件查询课时
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainPathSubclassTime> findByPage(
			MainPathSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainPathSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端通过条件查询
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			MainPathSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MainPathSubclassTime> list =  mainPathSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MainPathSubclassTime claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//id
			map.put("id",claimRecord.getId());
			//课时名称
			map.put("pathsubclasstimename",claimRecord.getPathsubclasstimename());
			//时长
			map.put("pathsubclasstimenum",claimRecord.getPathsubclasstimenum());
			//课程链接
			map.put("pathsubclassurl",claimRecord.getPathsubclassurl());
			//所属类型id
			map.put("typeid",claimRecord.getTypeid());
			//添加时间
			map.put("pathsubclasstimeaddtime",claimRecord.getPathsubclasstimeaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	public Specification<MainPathSubclassTime> getSpecificationSearch(final MainPathSubclassTimeModel model){
		return new Specification<MainPathSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<MainPathSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getTypeid())){
					namePredicate = cb.and(cb.like(root.<String>get("typeid"),"%"+model.getTypeid()+"%"));
					andPredicates.add(namePredicate);
				}
//				
//				if(null != model.getCoursewarename()){
//					namePredicate = cb.and(cb.like(root.<String>get("coursewarename"),"%"+model.getCoursewarename()+"%"));
//					andPredicates.add(namePredicate);
//				}
				
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("pathcoursewareaddtime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("pathcoursewareaddtime"),model.getEndDate()));
					andPredicates.add(namePredicate);
				}
				if(andPredicates.isEmpty()){
					return null;
				}else{
					Predicate predicate = cb.conjunction();
					predicate.getExpressions().addAll(andPredicates);
					return predicate;
				}
			}
		};
	}
	
}
