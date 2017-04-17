package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.whenguycan.kawori.common.Season;
import com.whenguycan.kawori.common.Status;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:21:50
 */
@Table("t_e_anime")
public class Anime{

	@Id
	@Column("ID")
	private Long id;
	
	@Column("f_name")
	private String name;
	@Column("f_curr")
	private Integer curr;
	@Column("f_all")
	private Integer all;
	@Column("f_season")
	private Season season;
	@Column("f_status")
	private Status status;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCurr() {
		return curr;
	}
	public void setCurr(Integer curr) {
		this.curr = curr;
	}
	public Integer getAll() {
		return all;
	}
	public void setAll(Integer all) {
		this.all = all;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
