package com.whenguycan.kawori.anime;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 
 * @author whenguycan
 * @date 2017年4月14日 下午2:28:47
 */
@Table("t_e_user")
public class User {

	@Id
	@Column("ID")
	private Long id;
	
	@Column("f_username")
	private String username;
	@Column("f_password")
	private String password;
	@Column("f_realname")
	private String realname;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	
}
