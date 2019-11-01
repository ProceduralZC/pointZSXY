package com.system.core.service;

import com.system.core.entity.SysAffix;

public interface SysAffixService {

	  public abstract void add(SysAffix sysAffix);

	  public abstract void update(SysAffix sysAffix);

	  public abstract void del(Integer id);

	  public abstract SysAffix get(Integer id);

}
