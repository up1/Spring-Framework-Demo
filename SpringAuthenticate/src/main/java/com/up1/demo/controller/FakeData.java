package com.up1.demo.controller;

import java.util.HashMap;

import com.up1.demo.bean.UserBean;

public class FakeData {
	static HashMap<String, String> tokenMap;
	static HashMap<String, UserBean> userMap;
	static {
		tokenMap = new HashMap<String, String>();
		tokenMap.put("1", "xxxxx");
		tokenMap.put("2", "yyyyy");

		userMap = new HashMap<String, UserBean>();
		UserBean user1 = new UserBean();
		user1.setUserId(1);
		user1.setUserName("user1");
		user1.setPassword("my_password");
		UserBean user2 = new UserBean();
		user2.setUserId(2);
		user2.setUserName("user2");
		user2.setPassword("my_password");

		userMap.put("1", user1);
		userMap.put("2", user2);
	}

	/**
	 * Get token by user id
	 * 
	 * @param userId
	 * @return
	 */
	public static String getTokenByID(int userId) {
		return tokenMap.get(String.valueOf(userId));
	}

	public static UserBean getUserProfileById(int userId) {
		return userMap.get(String.valueOf(userId));
	}

}
