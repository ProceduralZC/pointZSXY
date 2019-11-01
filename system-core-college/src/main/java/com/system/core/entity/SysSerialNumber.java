package com.system.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 序列号
 * @description
 */
@Entity
@Table(name="sys_serial_number")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler","fieldHandler"})
public class SysSerialNumber {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@SequenceGenerator(name="SYS_SERIAL_NUMBER_SEQ")
	@Column(name="ID")
	private Integer id;
	
	@Column(name="TARGET")
	private Integer target;//使用序列号的目标
	
	@Column(name="NUMBER")
	private Long number;//代码
	
	@Column(name="REMARK")
	private String remark;//说明

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTarget() {
		return target;
	}

	public void setTarget(Integer target) {
		this.target = target;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
