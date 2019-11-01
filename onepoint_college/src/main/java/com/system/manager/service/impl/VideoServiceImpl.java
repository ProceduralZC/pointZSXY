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
import com.system.manager.dao.VideoRecordDao;
import com.system.manager.entity.Video;
import com.system.manager.service.VideoService;
import com.system.manager.web.model.VideoModel;
import com.system.user.entity.SysUsers;
@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private VideoRecordDao videoRecordDao;
	

	/**
	 * 添加课程
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addVideo(
			VideoModel model,SysUsers sysUsers) {
		// TODO Auto-generated method stub
		Video bean = new Video();
		ParamUtil.bindBean(bean,model);		
		bean.setVideosubmietime(DateUtils.getNowDateTime());
		
		if(model.getVideotype().equals("1")){// 1基础，2一般，3经典
			bean.setVideotype("基础视频");
		}else if(model.getVideotype().equals("2")){
			bean.setVideotype("一般视频");
		}else if(model.getVideotype().equals("3")){
			bean.setVideotype("经典视频");
		}
		
		if(model.getVideosourcetype().equals("1")){// 1基础，2一般，3经典
			bean.setVideosourcetype("视频");
		}else if(model.getVideosourcetype().equals("2")){
			bean.setVideosourcetype("课件");
		}else if(model.getVideosourcetype().equals("3")){
			bean.setVideosourcetype("PPT");
		}else if(model.getVideosourcetype().equals("4")){
			bean.setVideosourcetype("文档");
		}else if(model.getVideosourcetype().equals("5")){
			bean.setVideosourcetype("图片");
		}
		
		bean.setVideosourcetypeid(model.getVideosourcetype());
		
		bean.setVideouser(sysUsers);
		videoRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}

	/**
	 * 查询所有课程
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Video> pageList(Pageable pageable,
			VideoModel model) {
		// TODO Auto-generated method stub
		return  videoRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<Video> getSpecification(final VideoModel model){
		return new Specification<Video>(){
			@Override
			public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 通过条件查询课程
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Video> findByPage(
			VideoModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return videoRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	public Specification<Video> getSpecificationSearch(final VideoModel model){
		return new Specification<Video>(){
			@Override
			public Predicate toPredicate(Root<Video> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				if(null!=model.getSysUsers()){
					namePredicate = cb.and(cb.equal(root.<SysDataDictionary>get("videouser").<Integer>get("truename"),model.getVideouser()));
					andPredicates.add(namePredicate);
				}
				
				if(!ParamUtil.isEmpty(model.getVideotitle())){
					namePredicate = cb.and(cb.like(root.<String>get("videotitle"),"%"+model.getVideotitle()+"%"));
					andPredicates.add(namePredicate);
				}
				if(null != model.getVideosourcetype()){
					namePredicate = cb.and(cb.like(root.<String>get("videosourcetypeid"),"%"+model.getVideosourcetypeid()+"%"));
					andPredicates.add(namePredicate);
				}
				if(!ParamUtil.isEmpty(model.getBeginDate())){
					namePredicate = cb.and(cb.greaterThan(root.<String>get("videosubmietime"),model.getBeginDate()));
					andPredicates.add(namePredicate);
				}
				if(!ParamUtil.isEmpty(model.getEndDate())){
					namePredicate = cb.and(cb.lessThan(root.<String>get("videosubmietime"),model.getEndDate()));
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
