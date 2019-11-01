package com.system.user.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.system.user.dao.AccessTokenDao;
import com.system.user.entity.AccessToken;
import com.system.user.service.AccessTokenService;

@Service
public class AccessTokenServiceImpl implements AccessTokenService{

	@Autowired
	private AccessTokenDao accessTokenDao;

	@Override
	public void update(AccessToken accessToken) {
		accessTokenDao.save(accessToken);		
	}

	@Override
	public AccessToken getAccessToken(Integer cusId) {
		List<AccessToken> list = accessTokenDao.findAccessTokenByCusId(cusId);
		if(null!=list && list.size()>0){
			return list.get(0);
		}
		return null;
	}
}
