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
import com.system.manager.dao.MainHotSubclassTimeRecordDao;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.entity.MainHotSubclassTime;
import com.system.manager.service.MainHotSubclassTimeService;
import com.system.manager.web.model.MainCourseWareModel;
import com.system.manager.web.model.MainHotSubclassTimeModel;

//热门知识
@Service
public class MainHotSubclassTimeServiceImpl implements MainHotSubclassTimeService {
	@Autowired
	private MainHotSubclassTimeRecordDao mainHotSubclassTimeRecordDao;
	/**
	 * 添加课时
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addHotSubclassTime(
			MainHotSubclassTimeModel model) {
		// TODO Auto-generated method stub
		MainHotSubclassTime bean = new MainHotSubclassTime();
		ParamUtil.bindBean(bean,model);		
		bean.setHotsubclasstimeaddtime(DateUtils.getNowDateTime());
		bean.setId(null);
		mainHotSubclassTimeRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}

	/**
	 * 查询所有课时
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainHotSubclassTime> pageList(Pageable pageable,
			MainHotSubclassTimeModel model) {
		// TODO Auto-generated method stub
		return  mainHotSubclassTimeRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainHotSubclassTime> getSpecification(final MainHotSubclassTimeModel model){
		return new Specification<MainHotSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<MainHotSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(Integer typeid,
			MainHotSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MainHotSubclassTime> list =  mainHotSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MainHotSubclassTime claimRecord : list) {
			if(typeid == Integer.valueOf(claimRecord.getTypeid())){//判断是否相等
				Map<String,Object> map = new HashMap<String, Object>();
				//id
				map.put("id",claimRecord.getId());
				//课时名称
				map.put("hotsubclasstimename",claimRecord.getHotsubclasstimename());
				//时长
				map.put("hotsubclasstimenum",claimRecord.getHotsubclasstimenum());
				//课程链接
				map.put("hotsubclassurl",claimRecord.getHotsubclassurl());
				//所属类型id
				map.put("typeid",claimRecord.getTypeid());
				//添加时间
				map.put("hotsubclasstimeaddtime",claimRecord.getHotsubclasstimeaddtime());
				//备注
				map.put("remark",claimRecord.getRemark());
				
				mapList.add(map);
			}
		}
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}

	/**
	 * 通过条件查询课时
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainHotSubclassTime> findByPage(
			MainHotSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainHotSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	public Specification<MainHotSubclassTime> getSpecificationSearch(final MainHotSubclassTimeModel model){
		return new Specification<MainHotSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<MainHotSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getTypeid())){
					namePredicate = cb.and(cb.like(root.<String>get("typeid"),"%"+model.getTypeid()+"%"));
					andPredicates.add(namePredicate);
				}
				if(!ParamUtil.isEmpty(model.getHotsubclasstimename())){
					namePredicate = cb.and(cb.like(root.<String>get("hotsubclasstimename"),"%"+model.getHotsubclasstimename()+"%"));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("coursewareaddtime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("coursewareaddtime"),model.getEndDate()));
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
