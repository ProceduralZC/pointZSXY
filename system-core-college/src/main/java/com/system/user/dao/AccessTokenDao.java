package com.system.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.system.user.entity.AccessToken;

public interface  AccessTokenDao extends CrudRepository<AccessToken,Integer>,JpaSpecificationExecutor<AccessToken>{
	
	@Query("from AccessToken where  cusId=?1 ")
	public List<AccessToken> findAccessTokenByCusId(Integer cusId);
	
	@Query("from AccessToken where  accessToken=?1 ")
	public List<AccessToken> findAccessTokenByToken(String access_token);
	
}
