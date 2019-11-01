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
import com.system.manager.dao.MainCourseWareRecordDao;
import com.system.manager.dao.MainSubclassTimeRecordDao;
import com.system.manager.entity.MainCollegeType;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.service.MainCourseWareService;
import com.system.manager.web.model.MainCourseWareModel;
//知识课件
@Service
public class MainCourseWareServiceImpl implements MainCourseWareService {
	@Autowired
	private MainCourseWareRecordDao mainCourseWareRecordDao;
	@Autowired
	private MainSubclassTimeRecordDao mainSubclassTimeRecordDao;

	/**
	 * 添加知识课件
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addCourseWare(
			MainCourseWareModel model) {
		// TODO Auto-generated method stub
		MainCourseWare bean = new MainCourseWare();
		ParamUtil.bindBean(bean,model);		
		bean.setCoursewareaddtime(DateUtils.getNowDateTime());
		mainCourseWareRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	
	/**
	 * 通过id查询一条数据对象
	 */
	@Override
	public MainCourseWare getCollege(Integer id) {
		return mainCourseWareRecordDao.findOne(id);
	}

	/**
	 * 查询所有知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainCourseWare> pageList(Pageable pageable,
			MainCourseWareModel model) {
		// TODO Auto-generated method stub
		return  mainCourseWareRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainCourseWare> getSpecification(final MainCourseWareModel model){
		return new Specification<MainCourseWare>(){
			@Override
			public Predicate toPredicate(Root<MainCourseWare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	public Page<MainCourseWare> findByPage(
			MainCourseWareModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainCourseWareRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	/**
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			Integer id,MainCourseWareModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MainCourseWare> list =  mainCourseWareRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MainCourseWare claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			if(id == Integer.valueOf(claimRecord.getTypeid())){
			//id
			map.put("id",claimRecord.getId());
			//课件名称
			map.put("coursewarename",claimRecord.getCoursewarename());
			//知识图片路径
			map.put("collegeimage",claimRecord.getCollegeimage());
			//知识图片资源
			map.put("coursewareimageid",claimRecord.getCoursewareimageid());
			
			List<MainSubclassTime> timeInfo = mainSubclassTimeRecordDao.findByTime(claimRecord.getId()+"");
			System.out.print(""+timeInfo.size());
			int timelong = 0;
			for (MainSubclassTime subclassTime : timeInfo) {
				int timaitem = Integer.parseInt(subclassTime.getSubclasstimenum());
				timelong=timelong+timaitem;
			}
			
			//课时
			map.put("coursewarenum",timeInfo.size());
			String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
			//时长
			map.put("coursewaretimelong",timesc);
			
			//所属类型id
			map.put("typeid",claimRecord.getTypeid());
			//添加时间
			map.put("coursewareaddtime",claimRecord.getCoursewareaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
			}
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	public Specification<MainCourseWare> getSpecificationSearch(final MainCourseWareModel model){
		return new Specification<MainCourseWare>(){
			@Override
			public Predicate toPredicate(Root<MainCourseWare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getTypeid())){
					namePredicate = cb.and(cb.like(root.<String>get("typeid"),"%"+model.getTypeid()+"%"));
					andPredicates.add(namePredicate);
				}
//				
				if(null != model.getCoursewarename()){
					namePredicate = cb.and(cb.like(root.<String>get("coursewarename"),"%"+model.getCoursewarename()+"%"));
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
	
	@Override
	public List<MainCourseWare> findByPageType(MainCourseWareModel model) {
		// TODO Auto-generated method stub
		return mainCourseWareRecordDao.findAll(getSpecificationTypeSearch(model));
	}
	
	public Specification<MainCourseWare> getSpecificationTypeSearch(final MainCourseWareModel model){
		return new Specification<MainCourseWare>(){
			@Override
			public Predicate toPredicate(Root<MainCourseWare> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	
	
	
//	@Override
//	public Page<Map<String, Object>> findByPage(MaterialsModel model,Pageable pageable){
//		SysUsers sysUsers= model.getSysUsers();
//		StringBuffer hql = new StringBuffer();
//		List<Object> params = new ArrayList<Object>();
//		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
//		hql.append(" from MaterialsClaimRecord where 1=1 ");
//		if(null!=model.getClaimStatus() && -1!=model.getClaimStatus() ){
//			hql.append(" and claim="+model.getClaimStatus());
//		}
//		if(sysUsers.getRoleId()==1){
//			hql.append(" and claimType=1 ");
//			hql.append(" and materials.materiaClassify in ("+sysUsers.getCode()+")");
//		}else if(sysUsers.getRoleId()==2){//车间管理员
//			hql.append(" and claimType=2 ");
//			hql.append(" and workshop.id= "+sysUsers.getWorkshop().getId());
//		}else if(sysUsers.getRoleId()==3){//工区管理员
//			hql.append(" and claimType=3 ");
//			hql.append(" and workshop.id= "+sysUsers.getWorkshop().getId());
//			hql.append(" and team.id= "+sysUsers.getTeam().getId());
//			hql.append(" and team.property=0 ");
//		}
//		createHqlWhere(model,hql,params);
//		List<MaterialsClaimRecord> beans=baseRepository.find(hql.toString(),params.toArray(), pageable.getPageNumber()*pageable.getPageSize(), pageable.getPageSize());
//		for (MaterialsClaimRecord claimRecord : beans) {
//			Map<String,Object> map = new HashMap<String, Object>();
//			returData(map, claimRecord);
//			//一级数量
//			map.put("materiaCount",claimRecord.getMaterialCount());
//			//二级数量
//			map.put("materiaSecCount",claimRecord.getMaterialSecCount());
//			//返回时间
//			map.put("claimDate",claimRecord.getClaimDate());
//			//返回操作人
//			map.put("claimUser",claimRecord.getClaimUser()==null?"":claimRecord.getClaimUser().getTruename());
//			mapList.add(map);
//		}
//		Long total=baseRepository.count(hql.toString(),params.toArray());
//		return new PageImpl<Map<String,Object>>(mapList, pageable, total);
//	}
	
}
