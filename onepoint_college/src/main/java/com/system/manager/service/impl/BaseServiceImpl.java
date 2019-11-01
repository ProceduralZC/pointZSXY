package com.system.manager.service.impl;
import org.springframework.stereotype.Service;
@Service
public class BaseServiceImpl {
	/**
	 * 当前的编码   最小8位
	 * @param prefix  前缀  挂号 就是gh  
	 * @param order 当前的流水号
	 * @author lanwei
	 * 2013-12-10
	 */
	public String currentCode(String prefix,String order) throws Exception{
		String bm = prefix;
//		if(order < 10){
//			bm = bm + "0000000" + order;
//		}else if(order < 100){
//			bm = bm + "000000" + order;
//		}else if(order < 1000){
//			bm = bm + "00000" + order;
//		}else if(order < 10000){
//			bm = bm + "0000" + order;
//		}else if(order < 100000){
//			bm = bm + "000" + order;
//		}else if(order < 1000000){
//			bm = bm + "00" + order;
//		}else if(order < 10000000){
//			bm = bm + "0" + order;
//		}else{
			bm = bm + order;
//		}
		return bm;
	}
}
