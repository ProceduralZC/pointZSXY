package com.system.phone.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.system.core.jsonpacket.JsonResponse;
import com.system.core.util.DigestMD5Util;
import com.system.core.util.ParamUtil;
import com.system.core.util.RandomCode;
import com.system.user.entity.AccessToken;
import com.system.user.entity.SysUsers;
import com.system.user.service.AccessTokenService;
import com.system.user.service.SysUsersService;
import com.system.user.web.base.BaseController;
import com.system.user.web.model.SysUsersModel;
/**
 * 用户管理控制器
 */
@RestController
@RequestMapping(value="/ms")
public class UserLoginController extends BaseController{
	
	@Autowired
	private SysUsersService sysUsersService;
	@Autowired
	private AccessTokenService accessTokenService;
	/**
	 * http://localhost:8080/materials/ms/userlogin.do?username=13256986335&password=123456
	 * 用户登录
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/userlogin",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse userLogin(@RequestParam String username,@RequestParam String password
			,HttpServletRequest request, HttpServletResponse response){
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		try {
			if(ParamUtil.isEmpty(username)){
				return super.failureResponse("用户名不能为空！");
			}
			if(ParamUtil.isEmpty(password)){
				return super.failureResponse("密码不能为空！");
			}
			SysUsers sysUsers =sysUsersService.getSysUsersByUsername(username, null);
			
			//登录空的时候 更新用户信息时间添加到下一天
			if(null == sysUsers.getRemark2() || sysUsers.getRemark2().equals("")){
				sysUsersService.updateSysUsersVip(sysUsers.getId(),sysUsers);
				
			}else{
				
			}
			
			if(null==sysUsers){
				return super.failureResponse("用户名不存在或者用户已停用！");
			}
			String userPwd = sysUsers.getPassword();
			password=DigestMD5Util.MD5(password);
			//从数据库取得密码是经过加密的
			if(!password.equals(userPwd)){
				return super.failureResponse("密码有误！");
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("username", sysUsers.getUsername());
			map.put("nickname", sysUsers.getNickname());
			map.put("zhiwei", sysUsers.getZhiwei());
			map.put("sex", sysUsers.getSex());
			map.put("detail", sysUsers.getDetail());
			map.put("acount", sysUsers.getAcount());
			map.put("tel", sysUsers.getTel());
			map.put("createDate", sysUsers.getCreateDate());
			map.put("email", sysUsers.getEmail());
			map.put("id", sysUsers.getId());
			map.put("roleId", sysUsers.getRoleId());
			map.put("remark2", sysUsers.getRemark2());
			
			AccessToken accessToken=accessTokenService.getAccessToken(sysUsers.getId());
			if(null!=accessToken){
				//重置access_token
				accessToken.setAccessToken(createAccessToken(sysUsers.getId()));
			}else{
				//生成access_token并返回
				accessToken=getAccessToken(sysUsers.getId());
			}
			map.put("access_token", accessToken.getAccessToken());
			accessTokenService.update(accessToken);
			items.add(map);
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	/**
	 * 注册用户
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/userRegister",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse userRegister(@RequestParam String username,@RequestParam String password
			/*,@RequestParam String yzm*/,HttpServletRequest request, HttpServletResponse response){
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			SysUsers sysUsers = sysUsersService.getSysUsersByUsername(username, null);
			if(sysUsers!=null){
				return super.failureResponse("对不起,用户名重复!");
			}
			
			SysUsersModel model = new SysUsersModel();
			model.setUsername(username);
			model.setPassword(password);
			Integer id = sysUsersService.addSysUsers(model);
			
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
    }
	
	
	@RequestMapping(value="/updateUserDate/{id}",method={RequestMethod.GET,RequestMethod.POST})
	public JsonResponse updateUser(@PathVariable Integer id,@RequestParam String nickname,@RequestParam String zhiweiinfo
			,@RequestParam String jiesao,@RequestParam Integer sex,HttpServletRequest request, HttpServletResponse response){
		JsonResponse jsonResponse = super.sucessResponse();
		List<Map<String,Object>> items = new ArrayList<Map<String,Object>>();
		try {
			sysUsersService.updateSysUsersPhone(id,nickname,zhiweiinfo,jiesao,sex);
			
			jsonResponse.setItems(items);
			return jsonResponse;
		} catch (Exception e) {
			e.printStackTrace();
			return super.failureResponse();
		}
	}
	
	private  AccessToken getAccessToken(Integer cusId) {
		AccessToken token = null;
		try {
			token=new AccessToken();
			token.setExpiresIn(Long.valueOf(1000*3600*2*60));
			token.setAccessToken(createAccessToken(cusId));
			token.setAccessTime(new Date().getTime());
			token.setCusId(cusId);
		} catch (Exception e) {
			token = null;
			e.printStackTrace();
		}
		return token;
	}

	private String createAccessToken(Integer cusId) {
		//随机字符串
		String nonce = RandomCode.createRandom(false, 6);
		//时间戳
		String timeStamp = Long.toString(System.currentTimeMillis());
		String accessToken=DigestMD5Util.MD5(nonce+timeStamp+cusId);
		return accessToken;
	}
	
	
	
}
