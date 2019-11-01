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

import com.system.core.util.ParamUtil;
import com.system.manager.dao.KnowledgeSubclassTimeRecordDao;
import com.system.manager.dao.MainJPSubclassTimeRecordDao;
import com.system.manager.dao.MainPathSubclassTimeRecordDao;
import com.system.manager.dao.MainSubclassTimeRecordDao;
import com.system.manager.dao.StudentHistoryRecordDao;
import com.system.manager.entity.KnowledgeCourse;
import com.system.manager.entity.KnowledgeSubclassTime;
import com.system.manager.entity.MainBoutiqueseries;
import com.system.manager.entity.MainCoursePath;
import com.system.manager.entity.MainCourseWare;
import com.system.manager.entity.MainJPSubclassTime;
import com.system.manager.entity.MainPathSubclassTime;
import com.system.manager.entity.MainSubclassTime;
import com.system.manager.entity.StudentHistory;
import com.system.manager.service.KnowledgeCourseService;
import com.system.manager.service.MainBoutiqueseriesService;
import com.system.manager.service.MainCoursePathService;
import com.system.manager.service.MainCourseWareService;
import com.system.manager.service.StudentHistoryService;
import com.system.manager.web.model.StudentHistoryModel;
@Service
public class StudentHistoryServiceImpl implements StudentHistoryService {
	@Autowired
	private StudentHistoryRecordDao studentHistoryRecordDao;
	@Autowired
	private StudentHistoryService studentHistoryService;//学习历史
	
	
	@Autowired
	private MainCourseWareService mainCourseWareService;//知识课件
	@Autowired
	private MainSubclassTimeRecordDao mainSubclassTimeRecordDao;//知识课件--课时
	
	@Autowired
	private MainCoursePathService mainCoursePathService;//知识路径
	@Autowired
	private MainPathSubclassTimeRecordDao mainPathSubclassTimeRecordDao;//知识路径--课时
	
	@Autowired
	private MainBoutiqueseriesService mainBoutiqueseriesService;//精品系列知识
	
	@Autowired
	private MainJPSubclassTimeRecordDao mainJPSubclassTimeRecordDao;//精品系列知识--课时
	
	@Autowired
	private KnowledgeCourseService knowledgeCourseService;//精选课程
	@Autowired
	private KnowledgeSubclassTimeRecordDao knowledgeSubclassTimeRecordDao;//精选课程--课时
	
	
	/**
	 * 添加学习记录
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addStudentHistory(
			StudentHistoryModel model) {
		// TODO Auto-generated method stub
		StudentHistory bean = new StudentHistory();
		ParamUtil.bindBean(bean,model);		
//		bean.setCynamicaddtime(DateUtils.getNowDateTime());
		studentHistoryRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}
	/**
	 * 添加学习记录 多类型
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addStudentHistoryType(Integer id,Integer type,Integer userid) {
		// TODO Auto-generated method stub
		StudentHistory bean = new StudentHistory();
		StudentHistory stuhistory = studentHistoryRecordDao.findByinfo(id);
		if(stuhistory == null){
			if(type == 1){//知识课件
				MainCourseWare type1 = mainCourseWareService.getCollege(id);
				bean.setCourseid(type1.getId());
				bean.setTitle(type1.getCoursewarename());
				bean.setType(type);
				bean.setContent("");
				bean.setCollegeimage(type1.getCollegeimage());
				bean.setImageid(type1.getCoursewareimageid());
				bean.setUserid(userid);
				bean.setAddtime(type1.getCoursewareaddtime());
				
				List<MainSubclassTime> timeInfo = mainSubclassTimeRecordDao.findByTime(type1.getId()+"");
				System.out.print(""+timeInfo.size());
				int timelong = 0;
				for (MainSubclassTime subclassTime : timeInfo) {
					int timaitem = Integer.parseInt(subclassTime.getSubclasstimenum());
					timelong=timelong+timaitem;
				}
				
				bean.setKeshi(timeInfo.size()+"");
				String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
				bean.setShichang(timesc);
				bean.setXxpeople("68");
				bean.setRemark(type1.getRemark());
				
			}else if(type == 2){//路径
				MainCoursePath type1 = mainCoursePathService.getCollege(id);
				bean.setCourseid(type1.getId());
				bean.setTitle(type1.getCoursepathname());
				bean.setType(type);
				bean.setContent("");
				bean.setCollegeimage(type1.getCollegeimage());
				bean.setImageid(type1.getCoursewareimageid());
				bean.setUserid(userid);
				bean.setAddtime(type1.getCoursepathaddtime());
				
				List<MainPathSubclassTime> timeInfo = mainPathSubclassTimeRecordDao.findByTime(type1.getId()+"");
				System.out.print(""+timeInfo.size());
				int timelong = 0;
				for (MainPathSubclassTime subclassTime : timeInfo) {
					int timaitem = Integer.parseInt(subclassTime.getPathsubclasstimenum());
					timelong=timelong+timaitem;
				}
				
				bean.setKeshi(timeInfo.size()+"");
				String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
				bean.setShichang(timesc);
				bean.setXxpeople("68");
				bean.setRemark(type1.getRemark());
				
			}else if(type == 3){//精品系列知识
				MainBoutiqueseries type1 = mainBoutiqueseriesService.getCollege(id);
				bean.setCourseid(type1.getId());
				bean.setTitle(type1.getBoutiqueseriesname());
				bean.setType(type);
				bean.setContent("");
				bean.setCollegeimage(type1.getCollegeimage());
				bean.setImageid(type1.getBoutiqueseriesimageid());
				bean.setUserid(userid);
				bean.setAddtime(type1.getBoutiqueseriesaddtime());
				
				List<MainJPSubclassTime> timeInfo = mainJPSubclassTimeRecordDao.findByTime(type1.getId()+"");
				System.out.print(""+timeInfo.size());
				int timelong = 0;
				for (MainJPSubclassTime subclassTime : timeInfo) {
					int timaitem = Integer.parseInt(subclassTime.getJpsubclasstimenum());
					timelong=timelong+timaitem;
				}
				
				bean.setKeshi(timeInfo.size()+"");
				String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
				bean.setShichang(timesc);
				bean.setXxpeople("68");
				bean.setRemark(type1.getRemark());
				
			}else if(type == 4){//热门知识
				
			}else if(type == 5){//精选课程
				KnowledgeCourse type1 = knowledgeCourseService.getCollege(id);
				bean.setCourseid(type1.getId());
				bean.setTitle(type1.getKnowledgename());
				bean.setType(type);
				bean.setContent("");
				bean.setCollegeimage(type1.getCollegeimage());
				bean.setImageid(type1.getKnowledgeimageid());
				bean.setUserid(userid);
				bean.setAddtime(type1.getKnowledgeaddtime());
				
				List<KnowledgeSubclassTime> timeInfo = knowledgeSubclassTimeRecordDao.findByTime(type1.getId());
				System.out.print(""+timeInfo.size());
				int timelong = 0;
				for (KnowledgeSubclassTime subclassTime : timeInfo) {
					int timaitem = Integer.parseInt(subclassTime.getKnowledgesubclasstimenum());
					timelong=timelong+timaitem;
				}
				
				bean.setKeshi(timeInfo.size()+"");
				String timesc = (Math.round(timelong/60) + "小时" + (timelong%60) + "分" );
				bean.setShichang(timesc);
				bean.setXxpeople("68");
				bean.setRemark(type1.getRemark());
				
			}
			
			studentHistoryRecordDao.save(bean);
		}
		
		//插入学习记录
		return bean.getId();
	}

	/**
	 * 查询所有学习记录
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<StudentHistory> pageList(Pageable pageable,
			StudentHistoryModel model) {
		// TODO Auto-generated method stub
		return  studentHistoryRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<StudentHistory> getSpecification(final StudentHistoryModel model){
		return new Specification<StudentHistory>(){
			@Override
			public Predicate toPredicate(Root<StudentHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 通过条件查询学习记录
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<StudentHistory> findByPage(
			StudentHistoryModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return studentHistoryRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端查询学习记录
	 * @param pageable
	 * @param model
	 */
	@Override
	public List<StudentHistory> findByPageType(StudentHistoryModel model) {
		// TODO Auto-generated method stub
		return studentHistoryRecordDao.findAll(getSpecificationTypeSearch(model));
	}
	
	public Specification<StudentHistory> getSpecificationTypeSearch(final StudentHistoryModel model){
		return new Specification<StudentHistory>(){
			@Override
			public Predicate toPredicate(Root<StudentHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	
	
	public Specification<StudentHistory> getSpecificationSearch(final StudentHistoryModel model){
		return new Specification<StudentHistory>(){
			@Override
			public Predicate toPredicate(Root<StudentHistory> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
//				
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
				studentHistoryRecordDao.delete(integer);
			}
	  }
	}
	
	/**
	 * 通过id获取信息
	 * @param ids
	 * @return
	 */
	@Override
	public StudentHistory get(Integer id) {
		return studentHistoryRecordDao.findOne(id);
	}
	
	/**
	 *编辑
	 */
	@Override
	public void editKnowledgeCourse(Integer id, StudentHistoryModel model) {
		StudentHistory bean = studentHistoryRecordDao.findOne(id);
//		bean.setKnowledgename(model.getKnowledgename());
//		bean.setCollegeimage(model.getCollegeimage());
//		bean.setKnowledgeimageid(model.getKnowledgeimageid());
		bean.setRemark(model.getRemark());
		
		studentHistoryRecordDao.save(bean);
	}
	
	/**
	 * 手机端通过条件查询知识课件
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			Integer id,StudentHistoryModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<StudentHistory> list =  studentHistoryRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (StudentHistory claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			if(id == Integer.valueOf(claimRecord.getUserid())){
				//id
				map.put("id",claimRecord.getId());
				//课程id
				map.put("courseid",claimRecord.getCourseid());
				//标题
				map.put("title",claimRecord.getTitle());
				//哪种类型
				map.put("type",claimRecord.getType());
				//内容
				map.put("content",claimRecord.getContent());
				//图片路径
				map.put("collegeimage",claimRecord.getCollegeimage());
				//图片资源
				map.put("imageid",claimRecord.getImageid());
				//用户id
				map.put("userid",claimRecord.getUserid());
				//添加时间
				map.put("addtime",claimRecord.getAddtime());
				//课时
				map.put("keshi",claimRecord.getKeshi());
				//时长
				map.put("shichang",claimRecord.getShichang());
				//学习人数
				map.put("xxpeople",claimRecord.getXxpeople());
				//备注
				map.put("remark",claimRecord.getRemark());
				
				mapList.add(map);
			}
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
}
