package com.system.user.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

import com.system.core.util.DateUtils;
import com.system.core.util.DigestMD5Util;
import com.system.core.util.ParamUtil;
import com.system.user.dao.AccessTokenDao;
import com.system.user.dao.SysRoleDao;
import com.system.user.dao.SysUsersDao;
import com.system.user.dao.SysUsersRoleDao;
import com.system.user.entity.AccessToken;
import com.system.user.entity.SysRole;
import com.system.user.entity.SysUsers;
import com.system.user.entity.SysUsersRole;
import com.system.user.service.SysUsersService;
import com.system.user.web.model.SysUsersModel;

@Service("sysUsersService")
public class  SysUsersServiceImpl implements SysUsersService{
	
	@Autowired
	private SysUsersDao sysUsersDao;
	@Autowired
	private SysUsersRoleDao sysUsersRoleDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private AccessTokenDao accessTokenDao;

	@Transactional(readOnly=true)
	public Page<SysUsers> pageList(SysUsersModel model,Pageable pageable){
		Page<SysUsers> page =sysUsersDao.findAll(getSpecification(model),pageable);
		for (SysUsers sysUsers : page) {
			SysRole sysRole=sysRoleDao.findOne(sysUsers.getRoleId());
			if(null!=sysRole){
				sysUsers.setRoleName(sysRole.getName());
			}else{
				sysUsers.setRoleName("");
			}
		}
		return page;
	}
	
	public Specification<SysUsers> getSpecification(final SysUsersModel model){
		return new Specification<SysUsers>(){
			@Override
			public Predicate toPredicate(Root<SysUsers> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Expression<Boolean>> andPredicates = new ArrayList<Expression<Boolean>>();
				Predicate namePredicate = null;
				
				namePredicate = cb.and(cb.equal(root.<Integer>get("isdelete"),0));
				andPredicates.add(namePredicate);
				
				if(!ParamUtil.isEmpty(model.getUsername())){
					namePredicate = cb.and(cb.like(root.<String>get("username"),"%"+model.getUsername()+"%"));
					andPredicates.add(namePredicate);
				}
//				if(!ParamUtil.isEmpty(model.getTruename())){
//					namePredicate = cb.and(cb.like(root.<String>get("truename"),"%"+model.getTruename()+"%"));
//					andPredicates.add(namePredicate);
//				}
				if(!ParamUtil.isEmpty(model.getCode())){
					namePredicate = cb.and(cb.like(root.<String>get("code"),"%"+model.getCode()+"%"));
					andPredicates.add(namePredicate);
				}
				if(model.getStatus()!=null && model.getStatus()!=-1){
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
	public SysUsers getSysUsersByid(Integer id) {
		SysUsers sysUsers = sysUsersDao.findOne(id);
		return sysUsers;
	}
	public SysUsers getSysUsersByUsername(String userName,Integer id){
		if(id==null){
			return sysUsersDao.findSysUsersByUsername(userName);
		}else{
			return sysUsersDao.findSysUsersByUsername(userName,id);
		}
	}
	@Override
	@Transactional
	public Integer addSysUsers(SysUsersModel model) {
		SysUsers sysUsers = new SysUsers();
		model.setPassword(DigestMD5Util.MD5(model.getPassword()));
		ParamUtil.bindBean(sysUsers,model);
		sysUsers.setIsdelete(0);
		//账号
		sysUsers.setAcount(System.currentTimeMillis()+"");
		//添加时间
		sysUsers.setCreateDate(DateUtils.getNowTime("yyyy-MM-dd HH:mm:ss"));
		sysUsersDao.save(sysUsers);
		if(null!=model.getRoleId()&& -1!=model.getRoleId()){
			addUserRoles(model.getRoleId(), sysUsers.getId(),false);
		}
		return sysUsers.getId();
	}
	@Transactional
	private void addUserRoles(Integer roleId,Integer userid,boolean flag) {
		if(flag){
			sysUsersRoleDao.delByUserId(userid);
		}
		SysUsersRole sysUsersRole = new SysUsersRole();
		sysUsersRole.setUserid(userid);
		sysUsersRole.setRoleid(roleId);
		sysUsersRoleDao.save(sysUsersRole);
	}
	
	@Override
	@Transactional
	public void updateSysUsers(Integer id,SysUsersModel model) {
		SysUsers sysUsers = sysUsersDao.findOne(id);
		if(sysUsers!=null){
			ParamUtil.bindBean(sysUsers,model);
			sysUsers.setModifyDate(DateUtils.getNowTime("yyyy-MM-dd HH:mm:ss"));

			sysUsersDao.save(sysUsers);
			if(null!=model.getRoleId()&& -1!=model.getRoleId()){
				addUserRoles(model.getRoleId(), sysUsers.getId(),true);
			}
		}
	}
	//手机端更新用户信息
	@Override
	@Transactional
	public void updateSysUsersPhone(Integer id,String nickname,String zhiwei,String jiesao,Integer sex) {
		SysUsers sysUsers = sysUsersDao.findOne(id);
		if(sysUsers!=null){
			sysUsers.setModifyDate(DateUtils.getNowTime("yyyy-MM-dd HH:mm:ss"));
			//昵称
			if(null == nickname || nickname.equals("")){
				sysUsers.setNickname(sysUsers.getNickname());
			}else{
				sysUsers.setNickname(nickname);
			}
			//职位
			if(null == zhiwei || zhiwei.equals("")){
				sysUsers.setZhiwei(sysUsers.getZhiwei());
			}else{
				sysUsers.setZhiwei(zhiwei);
			}
			//介绍
			if(null == jiesao || jiesao.equals("")){
				sysUsers.setDetail(sysUsers.getDetail());
			}else{
				sysUsers.setDetail(jiesao);
			}

			sysUsersDao.save(sysUsers);
		}
	}
	
	
	@Override
	@Transactional
	public void updateSysUsersVip(Integer id,SysUsers model) {
		SysUsers sysUsers = sysUsersDao.findOne(id);
		if(sysUsers!=null){
			ParamUtil.bindBean(sysUsers,model);
			sysUsers.setRemark2(getNextDay(DateUtils.getNowTime("yyyy-MM-dd HH:mm:ss")).toString());
			
			sysUsersDao.save(sysUsers);
			if(null!=model.getRoleId()&& -1!=model.getRoleId()){
				addUserRoles(model.getRoleId(), sysUsers.getId(),true);
			}
		}
	}
	
    public static Date getNextDay(Date date) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(date);  
        calendar.add(Calendar.DAY_OF_MONTH, -1);  
        date = calendar.getTime();  
        return date;  
    }  
	
	@Override
	@Transactional
	public void delSysUsers(String ids) {
		Integer[] ides = ParamUtil.toIntegers(ids.split(","));
		for (Integer integer : ides) {
			sysUsersRoleDao.delByUserId(integer);//删除用户角色中间表
		}
		sysUsersDao.delSysUsers(ides);
	}
	@Override
	@Transactional
	public void upSysUsersStatus(Integer status,String ids) {
		Integer[] ides = ParamUtil.toIntegers(ids.split(","));
		sysUsersDao.upSysUsersStatus(status,ides);
	}
	@Override
	@Transactional
	public void upSysUsersPass(String password,String ids) {
		Integer[] ides = ParamUtil.toIntegers(ids.split(","));
		password = DigestMD5Util.MD5(password);
		sysUsersDao.upSysUsersPass(password,ides);
	}
	
	@Override
	public SysUsers findSysUsersByToken(String access_token) {
		List<AccessToken> list=accessTokenDao.findAccessTokenByToken(access_token);
		if(null!=list && list.size()>0){
			SysUsers customer=sysUsersDao.findOne(list.get(0).getCusId());
			return customer;
		}
		return null;
	}
}
