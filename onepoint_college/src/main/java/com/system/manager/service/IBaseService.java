package com.system.manager.service;

public interface IBaseService {
	/**
	 * 当前的编码   最小8位
	 * @param prefix  前缀  挂号 就是gh  
	 * @param order 当前的流水号
	 * @author lanwei
	 * 2013-12-10
	 */
	public String currentCode(String prefix,String order) throws Exception;
}
