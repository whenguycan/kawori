package com.whenguycan.kawori.common;

/**
 * 
 * @author whenguycan
 * @date 2017年4月13日 上午9:43:56
 */
public class Search {

	private String LIKE_name;
	private String EQ_status;
	private String groupSelect;
	private String statusSelect;
	
	public String getLIKE_name() {
		return LIKE_name;
	}
	public void setLIKE_name(String lIKE_name) {
		LIKE_name = lIKE_name;
	}
	public String getEQ_status() {
		return EQ_status;
	}
	public void setEQ_status(String iS_status) {
		EQ_status = iS_status;
	}
	public String getGroupSelect() {
		return groupSelect;
	}
	public void setGroupSelect(String groupSelect) {
		this.groupSelect = groupSelect;
	}
	public String getStatusSelect() {
		return statusSelect;
	}
	public void setStatusSelect(String statusSelect) {
		this.statusSelect = statusSelect;
	}
	
}
