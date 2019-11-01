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
import com.system.manager.dao.MainCoursePathRecordDao;
import com.system.manager.dao.MainPathSubclassTimeRecordDao;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.entity.MainPathSubclassTime;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.MainCoursePathService;
import com.system.manager.web.model.MainCoursePathModel;
//知识路径
@Service
public class MainCoursePathServiceImpl implements MainCoursePathService {
	@Autowired
	private MainCoursePathRecordDao mainCoursePathRecordDao;
	@Autowired
	private MainPathSubclassTimeRecordDao mainPathSubclassTimeRecordDao;
	

	/**
	 * 添加知识课件
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addCoursePath(
			MainCoursePathModel model) {
		// TODO Auto-generated method stub
		MainCoursePath bean = new MainCoursePath();
		ParamUtil.bindBean(bean,model);		
		bean.setCoursepathaddtime(DateUtils.getNowDateTime());
		mainCoursePathRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	
	/**
	 * 通过id查询一条数据对象
	 */
	@Override
	public MainCoursePath getCollege(Integer id) {
		return mainCoursePathRecordDao.findOne(id);
	}

	/**
	 * 查询所有知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainCoursePath> pageList(Pageable pageable,
			MainCoursePathModel model) {
		// TODO Auto-generated method stub
		return  mainCoursePathRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainCoursePath> getSpecification(final MainCoursePathModel model){
		return new Specification<MainCoursePath>(){
			@Override
			public Predicate toPredicate(Root<MainCoursePath> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<MainCoursePath> findByPage(
			MainCoursePathModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainCoursePathRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	public Specification<MainCoursePath> getSpecificationSearch(final MainCoursePathModel model){
		return new Specification<MainCoursePath>(){
			@Override
			public Predicate toPredicate(Root<MainCoursePath> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getTypeid())){
					namePredicate = cb.and(cb.like(root.<String>get("typeid"),"%"+model.getTypeid()+"%"));
					andPredicates.add(namePredicate);
				}
//				
				if(null != model.getCoursepathname()){
					namePredicate = cb.and(cb.like(root.<String>get("coursepathname"),"%"+model.getCoursepathname()+"%"));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("coursepathaddtime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("coursepathaddtime"),model.getEndDate()));
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
	
	@Override
	public List<MainCoursePath> findByPageType(MainCoursePathModel model) {
		// TODO Auto-generated method stub
		return mainCoursePathRecordDao.findAll(getSpecificationTypeSearch(model));
	}
	
	/**
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			Integer id,MainCoursePathModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MainCoursePath> list =  mainCoursePathRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MainCoursePath claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			if(id == Integer.valueOf(claimRecord.getTypeid())){
				//id
				map.put("id",claimRecord.getId());
				//课件名称
				map.put("coursepathname",claimRecord.getCoursepathname());
				
				List<MainPathSubclassTime> timeInfo = mainPathSubclassTimeRecordDao.findByTime(claimRecord.getId()+"");
				System.out.print(""+timeInfo.size());
				int timelong = 0;
				for (MainPathSubclassTime subclassTime : timeInfo) {
					int timaitem = Integer.parseInt(subclassTime.getPathsubclasstimenum());
					timelong=timelong+timaitem;
				}
				
				//课时
				map.put("coursepathnum",timeInfo.size());
				
				String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
				//时长
				map.put("coursepathtimelong",timesc);
				//所属类型id
				map.put("typeid",claimRecord.getTypeid());
				//添加时间
				map.put("coursepathaddtime",claimRecord.getCoursepathaddtime());
				//备注
				map.put("remark",claimRecord.getRemark());
				
				mapList.add(map);
			}
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	public Specification<MainCoursePath> getSpecificationTypeSearch(final MainCoursePathModel model){
		return new Specification<MainCoursePath>(){
			@Override
			public Predicate toPredicate(Root<MainCoursePath> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	
}
