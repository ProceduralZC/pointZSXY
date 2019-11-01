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
import com.system.manager.dao.MainBoutiqueseriesRecordDao;
import com.system.manager.dao.MainJPSubclassTimeRecordDao;
import com.system.manager.entity.MainBoutiqueseries;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.entity.MainJPSubclassTime;
import com.system.manager.service.MainBoutiqueseriesService;
import com.system.manager.web.model.MainBoutiqueseriesModel;
//精品-知识
@Service
public class MainBoutiqueseriesServiceImpl implements MainBoutiqueseriesService {
	@Autowired
	private MainBoutiqueseriesRecordDao mainBoutiqueseriesRecordDao;
	@Autowired
	private MainJPSubclassTimeRecordDao mainJPSubclassTimeRecordDao;
	
	/**
	 * 添加知识课件
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addBoutiqueseries(
			MainBoutiqueseriesModel model) {
		// TODO Auto-generated method stub
		MainBoutiqueseries bean = new MainBoutiqueseries();
		ParamUtil.bindBean(bean,model);		
		bean.setBoutiqueseriesaddtime(DateUtils.getNowDateTime());
		mainBoutiqueseriesRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	
	/**
	 * 通过id查询一条数据对象
	 */
	@Override
	public MainBoutiqueseries getCollege(Integer id) {
		return mainBoutiqueseriesRecordDao.findOne(id);
	}

	/**
	 * 查询所有知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainBoutiqueseries> pageList(Pageable pageable,
			MainBoutiqueseriesModel model) {
		// TODO Auto-generated method stub
		return  mainBoutiqueseriesRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainBoutiqueseries> getSpecification(final MainBoutiqueseriesModel model){
		return new Specification<MainBoutiqueseries>(){
			@Override
			public Predicate toPredicate(Root<MainBoutiqueseries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<MainBoutiqueseries> findByPage(
			MainBoutiqueseriesModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainBoutiqueseriesRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	public Specification<MainBoutiqueseries> getSpecificationSearch(final MainBoutiqueseriesModel model){
		return new Specification<MainBoutiqueseries>(){
			@Override
			public Predicate toPredicate(Root<MainBoutiqueseries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getTypeid())){
					namePredicate = cb.and(cb.like(root.<String>get("typeid"),"%"+model.getTypeid()+"%"));
					andPredicates.add(namePredicate);
				}
//				
				if(null != model.getBoutiqueseriesname()){
					namePredicate = cb.and(cb.like(root.<String>get("boutiqueseriesname"),"%"+model.getBoutiqueseriesname()+"%"));
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
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			Integer id,MainBoutiqueseriesModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MainBoutiqueseries> list =  mainBoutiqueseriesRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MainBoutiqueseries claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			if(id == Integer.valueOf(claimRecord.getTypeid())){
			//id
			map.put("id",claimRecord.getId());
			//精品知识名称
			map.put("boutiqueseriesname",claimRecord.getBoutiqueseriesname());
			//知识图片路径
			map.put("collegeimage",claimRecord.getCollegeimage());
			//知识图片资源
			map.put("boutiqueseriesimageid",claimRecord.getBoutiqueseriesimageid());
			
			List<MainJPSubclassTime> timeInfo = mainJPSubclassTimeRecordDao.findByTime(claimRecord.getId()+"");
			System.out.print(""+timeInfo.size());
			int timelong = 0;
			for (MainJPSubclassTime subclassTime : timeInfo) {
				int timaitem = Integer.parseInt(subclassTime.getJpsubclasstimenum());
				timelong=timelong+timaitem;
			}
			
			//课时
			map.put("boutiqueseriesnum",timeInfo.size());
			String timesc = (Math.round(timelong/60)+ "小时" + (timelong%60) + "分" );
			//时长
			map.put("boutiqueseriestimelong",timesc);
			//所属类型id
			map.put("typeid",claimRecord.getTypeid());
			//添加时间
			map.put("boutiqueseriesaddtime",claimRecord.getBoutiqueseriesaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
			}
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	@Override
	public List<MainBoutiqueseries> findByPageType(MainBoutiqueseriesModel model) {
		// TODO Auto-generated method stub
		return mainBoutiqueseriesRecordDao.findAll(getSpecificationTypeSearch(model));
	}
	
	public Specification<MainBoutiqueseries> getSpecificationTypeSearch(final MainBoutiqueseriesModel model){
		return new Specification<MainBoutiqueseries>(){
			@Override
			public Predicate toPredicate(Root<MainBoutiqueseries> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
