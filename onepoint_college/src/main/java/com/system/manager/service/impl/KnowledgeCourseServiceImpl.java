package com.system.manager.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
import com.system.manager.dao.KnowledgeCourseRecordDao;
import com.system.manager.dao.KnowledgeSubclassTimeRecordDao;
import com.system.manager.entity.KnowledgeCourse;
import com.system.manager.entity.KnowledgeSubclassTime;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.service.KnowledgeCourseService;
import com.system.manager.web.model.KnowledgeCourseModel;
@Service
public class KnowledgeCourseServiceImpl implements KnowledgeCourseService {
	@Autowired
	private KnowledgeCourseRecordDao knowledgeCourseRecordDao;
	@Autowired
	private KnowledgeSubclassTimeRecordDao knowledgeSubclassTimeRecordDao;
	/**
	 * 添加知识课件
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addKnowledgeCourse(
			KnowledgeCourseModel model) {
		// TODO Auto-generated method stub
		KnowledgeCourse bean = new KnowledgeCourse();
		ParamUtil.bindBean(bean,model);		
		bean.setKnowledgeaddtime(DateUtils.getNowDateTime());
		knowledgeCourseRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	
	/**
	 * 通过id查询一条数据对象
	 */
	@Override
	public KnowledgeCourse getCollege(Integer id) {
		return knowledgeCourseRecordDao.findOne(id);
	}

	/**
	 * 查询所有知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<KnowledgeCourse> pageList(Pageable pageable,
			KnowledgeCourseModel model) {
		// TODO Auto-generated method stub
		return  knowledgeCourseRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<KnowledgeCourse> getSpecification(final KnowledgeCourseModel model){
		return new Specification<KnowledgeCourse>(){
			@Override
			public Predicate toPredicate(Root<KnowledgeCourse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<KnowledgeCourse> findByPage(
			KnowledgeCourseModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return knowledgeCourseRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			KnowledgeCourseModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<KnowledgeCourse> list =  knowledgeCourseRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (KnowledgeCourse claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//id
			map.put("id",claimRecord.getId());
			//名称
			map.put("knowledgename",claimRecord.getKnowledgename());
			//知识图片路径
			map.put("collegeimage",claimRecord.getCollegeimage());
			//知识图片资源
			map.put("knowledgeimageid",claimRecord.getKnowledgeimageid());
			
			List<KnowledgeSubclassTime> timeInfo = knowledgeSubclassTimeRecordDao.findByTime(claimRecord.getId());
			System.out.print(""+timeInfo.size());
			int timelong = 0;
			for (KnowledgeSubclassTime knowledgeTime : timeInfo) {
				int timaitem = Integer.parseInt(knowledgeTime.getKnowledgesubclasstimenum());
				timelong=timelong+timaitem;
			}
			//课时
			map.put("knowledgenum",timeInfo.size());
			
			String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
			//时长
			map.put("knowledgetimelong",timesc);
			//原价
			map.put("knowledgeinitprice",claimRecord.getKnowledgeinitprice());
			//vip价格
			map.put("knowledgevipprice",claimRecord.getKnowledgevipprice());
			//是否开启付费
			map.put("knowledgeifprice",claimRecord.getKnowledgeifprice());
			
			int min=10;
            int max=300;
            Random random = new Random();
            int number = random.nextInt(max)%(max-min+1) + min;
            
			//购买人数
			map.put("knowledgepeople",number+"");
			//添加时间
			map.put("knowledgeaddtime",claimRecord.getKnowledgeaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	
	public Specification<KnowledgeCourse> getSpecificationSearch(final KnowledgeCourseModel model){
		return new Specification<KnowledgeCourse>(){
			@Override
			public Predicate toPredicate(Root<KnowledgeCourse> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
//				
				if(null != model.getKnowledgename()){
					namePredicate = cb.and(cb.like(root.<String>get("knowledgename"),"%"+model.getKnowledgename()+"%"));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("boutiqueseriesaddtime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("boutiqueseriesaddtime"),model.getEndDate()));
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
				knowledgeCourseRecordDao.delete(integer);
				knowledgeSubclassTimeRecordDao.delByTypeId(integer);//删除该资源下的课程
			}
	  }
	}
	
	/**
	 * 通过id获取信息
	 * @param ids
	 * @return
	 */
	@Override
	public KnowledgeCourse get(Integer id) {
		return knowledgeCourseRecordDao.findOne(id);
	}
	
	/**
	 *编辑
	 */
	@Override
	public void editKnowledgeCourse(Integer id, KnowledgeCourseModel model) {
		KnowledgeCourse bean = knowledgeCourseRecordDao.findOne(id);
		bean.setKnowledgename(model.getKnowledgename());
		bean.setCollegeimage(model.getCollegeimage());
		bean.setKnowledgeimageid(model.getKnowledgeimageid());
		bean.setKnowledgeifprice(model.getKnowledgeifprice());
		bean.setKnowledgenum(model.getKnowledgenum());
		bean.setKnowledgetimelong(model.getKnowledgetimelong());
		bean.setKnowledgevipprice(model.getKnowledgevipprice());
		bean.setKnowledgeinitprice(model.getKnowledgeinitprice());
		bean.setRemark(model.getRemark());
		
		knowledgeCourseRecordDao.save(bean);
	}
}
