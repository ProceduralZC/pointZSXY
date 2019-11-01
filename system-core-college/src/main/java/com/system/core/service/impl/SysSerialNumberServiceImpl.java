package com.system.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.system.core.dao.SysSerialNumberDao;
import com.system.core.entity.SysSerialNumber;
import com.system.core.service.SysSerialNumberService;

@Service
public class SysSerialNumberServiceImpl implements SysSerialNumberService {
	
	@Autowired
	private SysSerialNumberDao sysSerialNumberDao;
	
	
	@Transactional
	public synchronized String getNumberByTarget(Integer target,Integer count) {
		List<SysSerialNumber> list = sysSerialNumberDao.findByTarget(target);
		SysSerialNumber serialNumber = new SysSerialNumber();
		if(list == null || list.size()==0){
			serialNumber.setNumber(getNumber("1", 7));
			serialNumber.setRemark("");
			serialNumber.setTarget(1);
		}else{
			serialNumber=list.get(0);
			serialNumber.setNumber(serialNumber.getNumber()+1);
			save(serialNumber);
		}
		return serialNumber.getNumber()+"";
	}

	private void save(SysSerialNumber bean) {
		sysSerialNumberDao.save(bean);
	}
	
	private Long getNumber(String s,Integer n){
		StringBuffer str = new StringBuffer(s);
		for(int i=0;i<n;i++){
			str.append("0");
		}
		return Long.valueOf(str.toString());
	}
}
