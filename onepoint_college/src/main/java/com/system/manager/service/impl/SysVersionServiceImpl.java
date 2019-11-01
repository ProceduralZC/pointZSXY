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

import com.system.core.dao.base.BaseRepository;
import com.system.core.util.DateUtils;
import com.system.core.util.ParamUtil;
import com.system.manager.dao.SysVersionDao;
import com.system.manager.entity.SysVersion;
import com.system.manager.service.SysVersionService;
import com.system.manager.web.model.VersionModel;
@Service
public class SysVersionServiceImpl implements SysVersionService {
	@Autowired
	private SysVersionDao versionDao;
	@Autowired
	private BaseRepository<SysVersion> baseRepository;
	
	@Override
	@Transactional
	public void addVersion(SysVersion page) {
		page.setIsdelete(1);
		page.setCreateDate(DateUtils.getNowDateTime());
		versionDao.save(page);
	}

	@Override
	@Transactional
	public void updateVersion(SysVersion page) {
		page.setIsdelete(1);
		page.setModifyDate(DateUtils.getNowDateTime());
		versionDao.save(page);
	}

	@Override
	@Transactional
	public void delVersion(String ids) {
		Integer[] ides = ParamUtil.toIntegers(ids.split(","));
		versionDao.delStartPage(ides);
	}

	@Override
	public SysVersion getVersion(Integer id) {
		return versionDao.findOne(id);
	}

	@Override
	public Page<SysVersion> pageList(Pageable pageable, VersionModel model) {
		return  versionDao.findAll(getSpecification(model),pageable);
	}

	public Specification<SysVersion> getSpecification(final VersionModel model){
		return new Specification<SysVersion>(){
			@Override
			public Predicate toPredicate(Root<SysVersion> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				namePredicate = cb.and(cb.equal(root.<Integer>get("isdelete"),1));
				andPredicates.add(namePredicate);
				if(null!=model.getStatus()){
					namePredicate = cb.and(cb.equal(root.<Integer>get("status"),model.getStatus()));
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
	public SysVersion findSysVersion() {
		try {
			StringBuffer hql = new StringBuffer();
			hql.append(" from SysVersion where status=1 and isdelete=1 ");
			List<Object> params = new ArrayList<Object>();
			hql.append(" order by updateDate desc ");
			List<SysVersion> list=baseRepository.find(hql.toString(), params.toArray());
			if(!list.isEmpty()){
				return list.get(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
