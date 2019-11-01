package com.system.user.service;

import com.system.user.entity.AccessToken;

public interface AccessTokenService {

	public void update(AccessToken accessToken);
	
	public AccessToken getAccessToken(Integer cusId);
}
