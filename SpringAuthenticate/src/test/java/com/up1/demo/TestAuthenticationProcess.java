package com.up1.demo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.up1.demo.bean.UserBean;
import com.up1.demo.util.EncryptUtil;

public class TestAuthenticationProcess {

	private String urlPost = "http://localhost:8585/demo03/authen/";
	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void authenPass() {
		UserBean userBean = new UserBean();
		userBean.setUserName("user1");
		userBean.setPassword("my_password");
		UserBean outputBean = restTemplate.postForObject(urlPost, userBean, UserBean.class);
		assertNotNull(outputBean);
		assertNotNull(outputBean.getToken());
	}

	@Test
	public void authenFailWithNotFoundUserName() {
		UserBean outputBean = null;
		UserBean userBean = new UserBean();
		userBean.setUserName("user2");
		userBean.setPassword("my_password");
		try {
			outputBean = restTemplate.postForObject(urlPost, userBean, UserBean.class);
		} catch (RestClientException exception) {			
		}
		assertNull(outputBean);
	}
	
	@Test
	public void passForAuthenAndGetProfileWithToken() {
		UserBean userBean = new UserBean();
		userBean.setUserName("user1");
		userBean.setPassword("my_password");
		UserBean outputBean = restTemplate.postForObject(urlPost, userBean, UserBean.class);
		assertNotNull(outputBean);
		assertNotNull(outputBean.getToken());
		
		String USER_REST_URL = "http://localhost:8585/demo03/user/{id}/?ip={ip}&checksum={checksum}";
		String clientIp = "127.0.0.1";
		StringBuffer input = new StringBuffer();
		input.append(outputBean.getUserId());
		input.append(clientIp);
		String checkSum = EncryptUtil.buildHmacSignature(outputBean.getToken(), input.toString());
		System.out.println(checkSum);
		UserBean output2Bean = restTemplate.getForObject(USER_REST_URL, UserBean.class, outputBean.getUserId(), clientIp, checkSum);
		assertNotNull(output2Bean);
		assertNull(output2Bean.getToken());		
	}
	
	@Test
	public void passForUpdateProfile() {	
		UserBean userBean = new UserBean();
		userBean.setUserName("user1");
		userBean.setPassword("my_password");
		UserBean outputBean = restTemplate.postForObject(urlPost, userBean, UserBean.class);
		assertNotNull(outputBean);
		assertNotNull(outputBean.getToken());
		
		outputBean.setUserName("Update username");
		
		String USER_REST_URL = "http://localhost:8585/demo03/user/{id}/?ip={ip}&checksum={checksum}";
		String clientIp = "127.0.0.1";
		StringBuffer input = new StringBuffer();
		input.append(outputBean.getUserId());
		input.append(outputBean.toString());
		input.append(clientIp);
		String checkSum = EncryptUtil.buildHmacSignature(outputBean.getToken(), input.toString());
		System.out.println(">>" + checkSum);
		restTemplate.put(USER_REST_URL, outputBean, outputBean.getUserId(), clientIp, checkSum);
	}

}
