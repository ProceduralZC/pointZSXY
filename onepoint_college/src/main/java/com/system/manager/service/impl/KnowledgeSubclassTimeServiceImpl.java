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
import com.system.manager.dao.KnowledgeSubclassTimeRecordDao;
import com.system.manager.entity.KnowledgeSubclassTime;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.KnowledgeSubclassTimeService;
import com.system.manager.web.model.KnowledgeSubclassTimeModel;
import com.system.manager.web.model.MainSubclassTimeModel;
@Service
public class KnowledgeSubclassTimeServiceImpl implements KnowledgeSubclassTimeService {
	@Autowired
	private KnowledgeSubclassTimeRecordDao knowledgeSubclassTimeRecordDao;
	/**
	 * 添加课时
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addKnowledgeSubclassTime(
			KnowledgeSubclassTimeModel model) {
		// TODO Auto-generated method stub
		KnowledgeSubclassTime bean = new KnowledgeSubclassTime();
		ParamUtil.bindBean(bean,model);		
		bean.setKnowledgesubclasstimeaddtime(DateUtils.getNowDateTime());
		bean.setId(null);
		knowledgeSubclassTimeRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}

	/**
	 * 查询所有课时
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<KnowledgeSubclassTime> pageList(Pageable pageable,
			KnowledgeSubclassTimeModel model) {
		// TODO Auto-generated method stub
		return  knowledgeSubclassTimeRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<KnowledgeSubclassTime> getSpecification(final KnowledgeSubclassTimeModel model){
		return new Specification<KnowledgeSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<KnowledgeSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<KnowledgeSubclassTime> findByPage(
			KnowledgeSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return knowledgeSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端通过条件查询
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			KnowledgeSubclassTimeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<KnowledgeSubclassTime> list =  knowledgeSubclassTimeRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (KnowledgeSubclassTime claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//id
			map.put("id",claimRecord.getId());
			//课时名称
			map.put("knowledgesubclasstimename",claimRecord.getKnowledgesubclasstimename());
			//时长
			map.put("knowledgesubclasstimenum",claimRecord.getKnowledgesubclasstimenum());
			//课程链接
			map.put("knowledgesubclassurl",claimRecord.getKnowledgesubclassurl());
			//所属类型id
			map.put("typeid",claimRecord.getTypeid());
			//添加时间
			map.put("knowledgesubclasstimeaddtime",claimRecord.getKnowledgesubclasstimeaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	public Specification<KnowledgeSubclassTime> getSpecificationSearch(final KnowledgeSubclassTimeModel model){
		return new Specification<KnowledgeSubclassTime>(){
			@Override
			public Predicate toPredicate(Root<KnowledgeSubclassTime> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(model.getTypeid()!= null && model.getTypeid()!= -1){
					namePredicate = cb.and(cb.equal(root.<Integer>get("typeid"),model.getTypeid()));
					andPredicates.add(namePredicate);
				}
//				
//				if(null != model.getCoursewarename()){
//					namePredicate = cb.and(cb.like(root.<String>get("coursewarename"),"%"+model.getCoursewarename()+"%"));
//					andPredicates.add(namePredicate);
//				}
				
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
	
	/**
	 * 删除
	 * 虽然 @Transactional 注解可以作用于接口、接口方法、类以及类方法上，但是 Spring 建议不要在接口或者接口方法上使用该注解，
	 * 因为这只有在使用基于接口的代理时它才会生效。另外， @Transactional 注解应该只被应用到 public 方法上，这是由 Spring AOP 
	 * 的本质决定的。如果你在 protected、private 或者默认可见性的方法上使用 @Transactional 注解，这将被忽略，也不会抛出任何异常。
	 * @param ids
	 */
	@Override
	@Transactional   //添加，执行回滚操作，一起失败一起成功
	public void delete(String ids) {
		if(!ParamUtil.isEmpty(ids)){
			Integer[] ides = ParamUtil.toIntegers(ids.split(","));
			for (Integer integer : ides) {
				knowledgeSubclassTimeRecordDao.delete(integer);
			}
		}
	}
	
}
