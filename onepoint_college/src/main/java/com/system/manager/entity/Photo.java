package com.system.manager.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_photo")
public class Photo {
	@Id
	@Column(name="pid",length=64)
	private String pid; 
	
	@Column(name="purl",length=200)
	private String purl; 
	
	@Column(name="pfkid",length=200)
    private String pfkid;
    
	@Column(name="pwidth",length=20)
    private String pwidth;
    
	@Column(name="porder",length=20)
    private String porder;
    
	@Column(name="pheight",length=20)
    private String pheight;
    
    public String getPorder() {
		return porder == null ? "" : porder;
	}
	public void setPorder(String porder) {
		this.porder = porder;
	}
	public String getPwidth() {
		return pwidth == null ? "" : pwidth;
	}
	public void setPwidth(String pwidth) {
		this.pwidth = pwidth;
	}
	public String getPheight() {
		return pheight == null ? "" : pheight;
	}
	public void setPheight(String pheight) {
		this.pheight = pheight;
	}
	public String getPid() {
		return pid==null?"":pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPurl() {
		return purl==null?"":purl;
	}
	public void setPurl(String purl) {
		this.purl = purl;
	}
	public String getPfkid() {
		return pfkid;
	}
	public void setPfkid(String pfkid) {
		this.pfkid = pfkid;
	}
}
