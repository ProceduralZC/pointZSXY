package com.system.user.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="t_access_token")
public class AccessToken implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="APP_ACCESS_TOKEN_SEQ")
    @Column(name="ID")
    private Integer id;
	 /**
     * access_token凭证
    **/
	@Column(name="ACCESS_TOKEN",length=1000)
	private String accessToken;
	 /**
     * '凭证有效时间，单位：秒'
    **/
	@Column(name="EXPIRES_IN")
    private Long expiresIn;
	/**
     * 用户的id
     **/
	@Column(name="CUS_ID")
	private Integer cusId ;
	 /**
     * 当前时间毫秒数
    **/
	@Column(name="ACCESS_TIME")
	private Long accessTime ;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Integer getCusId() {
		return cusId;
	}
	public void setCusId(Integer cusId) {
		this.cusId = cusId;
	}
	public Long getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(Long accessTime) {
		this.accessTime = accessTime;
	}
}
