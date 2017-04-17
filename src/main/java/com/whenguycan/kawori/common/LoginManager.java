package com.whenguycan.kawori.common;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.whenguycan.kawori.anime.User;

/**
 * 
 * @author whenguycan
 * @date 2017年4月14日 下午2:47:33
 */
public class LoginManager {
	
	private static final long tokenTimeOut = 10 * 60 * 1000L;
	public static final String token = "accessToken";

	private static Map<String, Token> tokens = new HashMap<String, Token>();
	
	public static String login(User user){
		synchronized (tokens) {
			String token = UUID.randomUUID().toString().replace("-", "");
			Token t = new Token(user);
			tokens.put(token, t);
			return token;
		}
	}
	
	public static void logOut(String token){
		synchronized (tokens) {
			tokens.remove(token);
		}
	}
	
	public static Token getToken(String token){
		synchronized (tokens) {
			if(tokens.containsKey(token)){
				Token t = tokens.get(token);
				if(System.currentTimeMillis() > t.getLoginTime() + tokenTimeOut){
					tokens.remove(token);
					return null;
				}else{
					t.setLoginTime(System.currentTimeMillis());
					return t;
				}
			}else{
				return null;
			}
		}
	}
}

class Token{
	
	private long loginTime;
	private User loginUser;
	
	public Token(User user){
		this.loginTime = System.currentTimeMillis();
		this.loginUser = user;
	}
	
	public long getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(long loginTime) {
		this.loginTime = loginTime;
	}
	public User getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(User loginUser) {
		this.loginUser = loginUser;
	}
	
}