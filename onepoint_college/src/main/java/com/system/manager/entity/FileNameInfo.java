package com.system.manager.entity;

public class FileNameInfo {

	private String path;

	private String oriName;

	private String sign;

	private String suffix;

	public String getOriName() {
		return oriName;
	}

	public void setOriName(String oriName) {
		this.oriName = oriName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	/**
	 * 获取带全路径的文件名
	 * 
	 * @return
	 */
	public String getFullPathName() {
		return path + "/" + oriName + FileNameUtils.separator + sign + suffix;
	}

	/**
	 * 获得不带目录结构的路径
	 * 
	 * @return
	 */
	public String getCurrentName() {
		return oriName + FileNameUtils.separator + sign + suffix;
	}

}
