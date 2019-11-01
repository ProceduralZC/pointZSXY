package com.system.user.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.user.entity.SysUsers;
import com.system.user.web.model.SysUsersModel;

public interface SysUsersService{
	/**
	 * 分页查询
	 * @return
	 */
	public Page<SysUsers> pageList(SysUsersModel model,Pageable pageable);
	/**
	 * 查询详情
	 * @param id
	 * @return
	 */
	public SysUsers getSysUsersByid(Integer id);
	/**
	 * 根据用户名查询
	 * @param userName
	 * @return
	 */
	public SysUsers getSysUsersByUsername(String userName,Integer id);
	/**
	 * 添加
	 * @param model
	 */
	public Integer addSysUsers(SysUsersModel model);
	/**
	 * 更新
	 * @param model
	 */
	public void updateSysUsers(Integer id,SysUsersModel model);
	/**
	 * 更新
	 * @param model
	 */
	public void updateSysUsersPhone(Integer id,String nickname,String zhiwei,String jiesao,Integer sex);
	/**
	 * 更新vip
	 * @param model
	 */
	public void updateSysUsersVip(Integer id,SysUsers model);
	/**
	 * 删除明星
	 * @param ids
	 */
	public void delSysUsers(String ids);
	/**
	 * 更新状态
	 * @param ids
	 */
	public void upSysUsersStatus(Integer status,String ids);
	/**
	 * 更改密码
	 * @param ids
	 */
	public void upSysUsersPass(String password,String ids);
	/**
	 * 根据token查询用户
	 * @param access_token
	 * @return
	 */
	public SysUsers findSysUsersByToken(String access_token); 
}