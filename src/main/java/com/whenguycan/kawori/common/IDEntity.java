package com.whenguycan.kawori.common;

import org.nutz.dao.entity.annotation.Column;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:20:57
 */
public class IDEntity {

	@Column("ID")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
