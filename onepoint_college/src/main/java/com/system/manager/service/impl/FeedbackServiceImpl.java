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
import com.system.manager.dao.FeedbackRecordDao;
import com.system.manager.entity.Feedback;
import com.system.manager.service.FeedbackService;
import com.system.manager.web.model.FeedbackModel;
@Service
public class FeedbackServiceImpl implements FeedbackService {
	@Autowired
	private FeedbackRecordDao feedbackRecordDao;
	/**
	 * 添加意见反馈
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addFeedback(
			FeedbackModel model) {
		// TODO Auto-generated method stub
		Feedback bean = new Feedback();
		ParamUtil.bindBean(bean,model);		
		bean.setFeedbackaddtime(DateUtils.getNowDateTime());
		//插入意见反馈
		feedbackRecordDao.save(bean);
		return bean.getId();
	}

	/**
	 * 查询所有知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Feedback> pageList(Pageable pageable,
			FeedbackModel model) {
		// TODO Auto-generated method stub
		return  feedbackRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<Feedback> getSpecification(final FeedbackModel model){
		return new Specification<Feedback>(){
			@Override
			public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Feedback> findByPage(
			FeedbackModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		return feedbackRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端查询所有动态
	 * @param pageable
	 * @param model
	 */
	@Override
	public List<Feedback> findByPageType(FeedbackModel model) {
		// TODO Auto-generated method stub
		return feedbackRecordDao.findAll(getSpecificationTypeSearch(model));
	}
	
	public Specification<Feedback> getSpecificationTypeSearch(final FeedbackModel model){
		return new Specification<Feedback>(){
			@Override
			public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<Map<String, Object>> findByPagePhone(
			Integer id,FeedbackModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<Feedback> list =  feedbackRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (Feedback claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//id
			map.put("id",claimRecord.getId());
//			//课件名称
//			map.put("coursepathname",claimRecord.getCoursepathname());
//			//添加时间
//			map.put("coursepathaddtime",claimRecord.getCoursepathaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	
	public Specification<Feedback> getSpecificationSearch(final FeedbackModel model){
		return new Specification<Feedback>(){
			@Override
			public Predicate toPredicate(Root<Feedback> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
//				if(null != model.getCynamictitle()){
//					namePredicate = cb.and(cb.like(root.<String>get("cynamictitle"),"%"+model.getCynamictitle()+"%"));
//					andPredicates.add(namePredicate);
//				}
//				
//				if(!ParamUtil.isEmpty(model.getBeginDate())){
//					namePredicate = cb.and(cb.greaterThan(root.<String>get("cynamicaddtime"),model.getBeginDate()));
//					andPredicates.add(namePredicate);
//				}
//				
//				if(!ParamUtil.isEmpty(model.getEndDate())){
//					namePredicate = cb.and(cb.lessThan(root.<String>get("cynamicaddtime"),model.getEndDate()));
//					andPredicates.add(namePredicate);
//				}
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
				feedbackRecordDao.delete(integer);
			}
	  }
	}
	
	/**
	 * 通过id获取信息
	 * @param ids
	 * @return
	 */
	@Override
	public Feedback get(Integer id) {
		return feedbackRecordDao.findOne(id);
	}
	
	/**
	 *编辑
	 */
	@Override
	public void editFeedback(Integer id, FeedbackModel model) {
		Feedback bean = feedbackRecordDao.findOne(id);
//		bean.setKnowledgename(model.getKnowledgename());
//		bean.setCollegeimage(model.getCollegeimage());
//		bean.setKnowledgeimageid(model.getKnowledgeimageid());
//		bean.setKnowledgeifprice(model.getKnowledgeifprice());
//		bean.setKnowledgenum(model.getKnowledgenum());
//		bean.setKnowledgetimelong(model.getKnowledgetimelong());
//		bean.setKnowledgevipprice(model.getKnowledgevipprice());
//		bean.setKnowledgeinitprice(model.getKnowledgeinitprice());
		bean.setRemark(model.getRemark());
		
		feedbackRecordDao.save(bean);
	}
}
