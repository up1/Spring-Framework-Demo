package com.up1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up1.demo.bean.UserBean;
import com.up1.demo.exception.UserException;
import com.up1.demo.util.EncryptUtil;

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	UserBean getInfo(
			@PathVariable("id") int userId, 
			@RequestParam(value = "ip", required = false, defaultValue = "") String ip,
			@RequestParam(value = "checksum", required = false, defaultValue = "") String checkSum) throws UserException {
		
		StringBuffer input = new StringBuffer();
		input.append(userId);
		input.append(ip);
		String privateKey = FakeData.getTokenByID(userId);
		String serverCheckSum = EncryptUtil.buildHmacSignature(privateKey, input.toString());
		if (serverCheckSum.equals(checkSum)) {
			return FakeData.getUserProfileById(userId);
		} else {
			throw new UserException("Invalid checksum");
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	UserBean update(
			@RequestBody UserBean userBean, 
			@PathVariable("id") int userId, 
			@RequestParam(value = "ip", required = false, defaultValue = "") String ip,
			@RequestParam(value = "checksum", required = false, defaultValue = "") String checkSum) throws UserException {
		
		StringBuffer input = new StringBuffer();
		input.append(userId);
		input.append(userBean.toString());
		input.append(ip);
		String privateKey = FakeData.getTokenByID(userId);
		String serverCheckSum = EncryptUtil.buildHmacSignature(privateKey, input.toString());
		if (serverCheckSum.equals(checkSum)) {
			return userBean;
		} else {
			throw new UserException("Invalid checksum");
		}
	}

}
