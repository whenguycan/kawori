package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.whenguycan.kawori.common.Group;
import com.whenguycan.kawori.common.Season;
import com.whenguycan.kawori.common.Status;
import com.whenguycan.kawori.common.StringUtils;

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
	
	public static Anime create(String line){
		if(!StringUtils.isNotBlank(line)){
			return null;
		}
		char pre = line.charAt(0);
		if(pre != '-'){
			return null;
		}
		String[] info = line.split("-+");
		if(info == null || info.length < 2){
			return null;
		}
		Anime anime = new Anime();
		anime.setName(info[1]);
		if(info.length > 2){
			anime.setSeason(Season.forName(info[2]));
		}
		if(info.length > 3){
			try {
				int curr = Integer.parseInt(info[3]);
				anime.setCurr(curr);
			} catch (Exception e) {
				System.out.println("error curr : "+info[3]);
			}
		}
		if(info.length > 4){
			try {
				int all = Integer.parseInt(info[4]);
				anime.setAll(all);
			} catch (Exception e) {
				System.out.println("error all : "+info[4]);
			}
		}
		return anime;
	}
	
	public Group generateGroup(){
		this.group = Group.generateGroup(this.name);
		return this.group;
	}
	
	public void generateStatus(){
		this.status = this.curr!=null&&this.all!=null&&this.curr.intValue()==this.all.intValue()?Status.END:Status.ING;
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
