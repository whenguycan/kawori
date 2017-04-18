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
	public static final String TOKEN = "accessToken";

	private static Map<String, Token> tokens = new HashMap<String, Token>();
	
	public static String login(User user){
		synchronized (tokens) {
			String accessToken = UUID.randomUUID().toString().replace("-", "");
			Token token = new Token(user);
			tokens.put(accessToken, token);
			return accessToken;
		}
	}
	
	public static void logOut(String accessToken){
		synchronized (tokens) {
			tokens.remove(accessToken);
		}
	}
	
	public static Token getToken(String accessToken){
		synchronized (tokens) {
			if(tokens.containsKey(accessToken)){
				Token t = tokens.get(accessToken);
				if(System.currentTimeMillis() > t.getLoginTime() + tokenTimeOut){
					tokens.remove(accessToken);
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