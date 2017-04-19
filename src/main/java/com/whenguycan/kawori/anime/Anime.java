package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.whenguycan.kawori.common.Group;
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
	@Column("f_group")
	private Group group;
	@Column("f_curr")
	private Integer curr;
	@Column("f_all")
	private Integer all;
	@Column("f_season")
	private Season season;
	@Column("f_status")
	private Status status;
	@Column("f_creator")
	private Long creator;
	
	public Anime(){
		
	}
	
	public Anime(String... fields){
		if(fields.length > 0){
			this.name = fields[0];
		}
		if(fields.length > 1){
			this.season = Season.forName(fields[1]);
		}
		try {
			if(fields.length > 1){
				this.curr = Integer.parseInt(fields[2]);
			}
		} catch (Exception e) {
		}
		try {
			if(fields.length > 2){
				this.all = Integer.parseInt(fields[3]);
			}
		} catch (Exception e) {
			
		}
	}
	
	public void generateGroup(){
		this.group = Group.generateGroup(this.name);
	}
	
	public void generateStatus(){
		this.status = this.curr!=null&&this.curr.intValue()==this.all.intValue()?Status.END:Status.ING;
	}
	
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
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	
}
