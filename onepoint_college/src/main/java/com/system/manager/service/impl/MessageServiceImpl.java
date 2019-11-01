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
import com.system.manager.dao.MessageRecordDao;
import com.system.manager.entity.MessageSys;
import com.system.manager.service.MessageService;
import com.system.manager.web.model.MessageSysModel;
@Service
public class MessageServiceImpl implements MessageService {
	@Autowired
	private MessageRecordDao messageRecordDao;
	/**
	 * 添加消息
	 * @param model
	 */
	@Override
	@Transactional
	public Integer addMessage(
			MessageSysModel model) {
		// TODO Auto-generated method stub
		MessageSys bean = new MessageSys();
		ParamUtil.bindBean(bean,model);		
		bean.setMsgaddtime(DateUtils.getNowDateTime());
		bean.setMsgtypecode(1);//系统消息是1
		messageRecordDao.save(bean);
		//插入验收记录
		return bean.getId();
	}

	/**
	 * 查询所有消息
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MessageSys> pageList(Pageable pageable,
			MessageSysModel model) {
		// TODO Auto-generated method stub
		return  messageRecordDao.findAll(getSpecification(model),pageable);
	}
	
	public Specification<MessageSys> getSpecification(final MessageSysModel model){
		return new Specification<MessageSys>(){
			@Override
			public Predicate toPredicate(Root<MessageSys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
	 * 通过条件查询消息
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<MessageSys> findByPage(
			MessageSysModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		
		return messageRecordDao.findAll(getSpecificationSearch(model),pageable);
	}
	
	/**
	 * 手机端通过条件查询消息
	 * @param pageable
	 * @param model
	 */
	@Override
	public Page<Map<String, Object>> findByPagePhone(
			MessageSysModel model, Pageable pageable) {
		// TODO Auto-generated method stub
		List<Map<String,Object>> mapList = new ArrayList<Map<String,Object>>();
		Page<MessageSys> list =  messageRecordDao.findAll(getSpecificationSearch(model),pageable);
		for (MessageSys claimRecord : list) {
			Map<String,Object> map = new HashMap<String, Object>();
			//id
			map.put("id",claimRecord.getId());
			//标题
			map.put("msgtitle",claimRecord.getMsgtitle());
			//内容
//			map.put("msgcontent",claimRecord.getMsgcontent());
			map.put("msgcontent","");
			//是否读取
			map.put("msgifread",claimRecord.getMsgifread());
			//消息类型
			map.put("msgtypecode",claimRecord.getMsgtypecode());
			//时间
			map.put("msgaddtime",claimRecord.getMsgaddtime());
			//备注
			map.put("remark",claimRecord.getRemark());
			
			mapList.add(map);
		}
		
		return new PageImpl<Map<String,Object>>(mapList, pageable,mapList.size());
	}
	
	
	public Specification<MessageSys> getSpecificationSearch(final MessageSysModel model){
		return new Specification<MessageSys>(){
			@Override
			public Predicate toPredicate(Root<MessageSys> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
//				
//				if(null != model.getKnowledgename()){
//					namePredicate = cb.and(cb.like(root.<String>get("knowledgename"),"%"+model.getKnowledgename()+"%"));
//					andPredicates.add(namePredicate);
//				}
				
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
				messageRecordDao.delete(integer);
			}
	  }
	}
	
	/**
	 * 通过id获取信息
	 * @param ids
	 * @return
	 */
	@Override
	public MessageSys get(Integer id) {
		return messageRecordDao.findOne(id);
	}
	
	/**
	 *编辑
	 */
	@Override
	public void editKnowledgeCourse(Integer id, MessageSysModel model) {
		MessageSys bean = messageRecordDao.findOne(id);
//		bean.setKnowledgename(model.getKnowledgename());
//		bean.setCollegeimage(model.getCollegeimage());
//		bean.setKnowledgeimageid(model.getKnowledgeimageid());
//		bean.setKnowledgeifprice(model.getKnowledgeifprice());
//		bean.setKnowledgenum(model.getKnowledgenum());
//		bean.setKnowledgetimelong(model.getKnowledgetimelong());
//		bean.setKnowledgevipprice(model.getKnowledgevipprice());
//		bean.setKnowledgeinitprice(model.getKnowledgeinitprice());
		bean.setRemark(model.getRemark());
		
		messageRecordDao.save(bean);
	}
}
