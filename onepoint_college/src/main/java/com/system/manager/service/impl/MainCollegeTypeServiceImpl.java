package com.system.manager.service.impl;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.entity.SysDataDictionary;
import com.system.core.util.DateUtils;
import com.system.core.util.ParamUtil;
import com.system.manager.dao.MainCollegeTypeRecordDao;
import com.system.manager.entity.MainCollegeType;
import com.system.manager.entity.SysVersion;
import com.system.manager.service.MainCollegeTypeService;
import com.system.manager.web.model.MainCollegeTypeModel;
import com.system.user.entity.SysUsers;
@Service
public class MainCollegeTypeServiceImpl implements MainCollegeTypeService {
	@Autowired
	private MainCollegeTypeRecordDao mainCollegeTypeRecordDao;
	

	/**
	 * 添加知识类型
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addCollegeType(
			MainCollegeTypeModel model,SysUsers sysUsers) {
		// TODO Auto-generated method stub
		MainCollegeType bean = new MainCollegeType();
		ParamUtil.bindBean(bean,model);		
		bean.setCollegetime(DateUtils.getNowDateTime());
		
		if(model.getCollegetype().equals("1")){
			bean.setCollegetype("知识课件");
		}else if(model.getCollegetype().equals("2")){
			bean.setCollegetype("知识路径");
		}else if(model.getCollegetype().equals("3")){
			bean.setCollegetype("精品系列知识");
		}else if(model.getCollegetype().equals("4")){
			bean.setCollegetype("热门知识");
		}
		
		bean.setCollegetypecode(model.getCollegetype());
		
		bean.setCollegeuser(sysUsers);
		mainCollegeTypeRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	/**
	 * 通过id查询一条数据对象
	 */
	@Override
	public MainCollegeType getCollege(Integer id) {
		return mainCollegeTypeRecordDao.findOne(id);
	}

	/**
	 * 查询所有知识类型
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainCollegeType> pageList(Pageable pageable,
			MainCollegeTypeModel model) {
		// TODO Auto-generated method stub
		return  mainCollegeTypeRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MainCollegeType> getSpecification(final MainCollegeTypeModel model){
		return new Specification<MainCollegeType>(){
			@Override
			public Predicate toPredicate(Root<MainCollegeType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 通过条件查询知识类型
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MainCollegeType> findByPage(
			MainCollegeTypeModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return mainCollegeTypeRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	public Specification<MainCollegeType> getSpecificationSearch(final MainCollegeTypeModel model){
		return new Specification<MainCollegeType>(){
			@Override
			public Predicate toPredicate(Root<MainCollegeType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(null!=model.getSysUsers()){
					namePredicate = cb.and(cb.equal(root.<SysDataDictionary>get("collegeuser").<Integer>get("truename"),model.getCollegeuser()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getCollegename())){
					namePredicate = cb.and(cb.like(root.<String>get("collegename"),"%"+model.getCollegename()+"%"));
					andPredicates.add(namePredicate);
				}
				
				if(null != model.getCollegetypecode()){
					namePredicate = cb.and(cb.like(root.<String>get("collegetypecode"),"%"+model.getCollegetypecode()+"%"));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("collegetime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("collegetime"),model.getEndDate()));
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
	public List<MainCollegeType> findByPageType(MainCollegeTypeModel model,String collegetypecode) {
		// TODO Auto-generated method stub
		return mainCollegeTypeRecordDao.findAll(getSpecificationTypeSearch(model,collegetypecode));
	}
	
	public Specification<MainCollegeType> getSpecificationTypeSearch(final MainCollegeTypeModel model,final String collegetypecode){
		return new Specification<MainCollegeType>(){
			@Override
			public Predicate toPredicate(Root<MainCollegeType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(!ParamUtil.isEmpty(model.getCollegetypecode())){
					if(model.getCollegetypecode().equals(collegetypecode)){
						namePredicate = cb.and(cb.like(root.<String>get("collegetypecode"),"%"+model.getCollegetypecode()+"%"));
						andPredicates.add(namePredicate);
					}
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
