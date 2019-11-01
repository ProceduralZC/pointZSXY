package com.system.manager.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.system.manager.entity.SysVersion;
import com.system.manager.web.model.VersionModel;

public interface SysVersionService {
	  /**
	   * 增加
	   * @param page
	   */
	  public abstract void addVersion(SysVersion page);
	  /**
	   * 更新
	   * @param page
	   */
	  public abstract void updateVersion(SysVersion page);
	  /**
	   * 删除
	   * @param ids
	   */
	  public abstract void delVersion(String ids);
	  /**
	   * 根据id查询
	   * @param id
	   * @return
	   */
	  public abstract SysVersion getVersion(Integer id);
	  /**
		 * 分页查询
		 * @return
		 */
	 public Page<SysVersion> pageList(Pageable pageable,VersionModel model);
	 
	 public SysVersion findSysVersion();

}
