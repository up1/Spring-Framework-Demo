package com.up1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up1.demo.bean.UserBean;
import com.up1.demo.exception.AuthenticationException;

@Controller
@RequestMapping(value = "/authen")
public class AuthenticationController extends BaseController {
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public UserBean login( @RequestBody UserBean userBean ) throws AuthenticationException{
		if( userBean != null ) {
			if( "user1".equals(userBean.getUserName()) ) {
				UserBean resultBean = new UserBean();
				resultBean.setUserId(1);
				resultBean.setUserName(userBean.getUserName());
				resultBean.setToken(FakeData.getTokenByID(1));
				return resultBean;
			} else {
				throw new AuthenticationException("Username not found");
			}
		}
		throw new AuthenticationException("Parameter not valid");
	}
}