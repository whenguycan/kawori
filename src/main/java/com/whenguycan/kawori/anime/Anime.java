package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.whenguycan.kawori.common.enums.Group;
import com.whenguycan.kawori.common.enums.Status;
import com.whenguycan.kawori.common.utils.StringUtils;

/**
 * 
 * @author whenguycan
 * @date 2017年4月10日 上午11:21:50
 */
@Table("t_e_anime")
public class Anime {

	@Id
	@Column("ID")
	private Long id;

	@Column("f_name")
	private String name;
	@Column("f_group")
	private String group;
	@Column("f_curr")
	private Integer curr;
	@Column("f_all")
	private Integer all;
	@Column("f_season")
	private String season;
	@Column("f_status")
	private String status;
	@Column("f_creator")
	private Long creator;

	public Anime() {

	}

	public static Anime create(String line) {
		if (!StringUtils.isNotBlank(line)) {
			return null;
		}
		char pre = line.charAt(0);
		if (pre != '-') {
			return null;
		}
		String[] info = line.split("-+");
		if (info == null || info.length < 2) {
			return null;
		}
		Anime anime = new Anime();
		anime.setName(info[1]);
		if (info.length > 2) {
			anime.setSeason(info[2]);
		}
		if (info.length > 3) {
			try {
				int curr = Integer.parseInt(info[3]);
				anime.setCurr(curr);
			} catch (Exception e) {
				System.out.println("error curr : " + info[3]);
			}
		}
		if (info.length > 4) {
			try {
				int all = Integer.parseInt(info[4]);
				anime.setAll(all);
			} catch (Exception e) {
				System.out.println("error all : " + info[4]);
			}
		}
		return anime;
	}

	public String generateGroup() {
		this.group = Group.generateGroup(this.name);
		return this.group;
	}

	public void generateStatus() {
		this.status = this.curr != null && this.all != null && this.curr.intValue() == this.all.intValue() ? Status.END.code() : Status.ING.code();
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

	public String getGroup(){
		return group;
	}

	public void setGroup(String group){
		this.group = group;
	}

	public String getSeason(){
		return season;
	}

	public void setSeason(String season){
		this.season = season;
	}

	public String getStatus(){
		return status;
	}

	public void setStatus(String status){
		this.status = status;
	}

}
