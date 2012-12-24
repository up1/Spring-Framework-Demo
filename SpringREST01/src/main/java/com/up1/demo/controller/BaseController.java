package com.up1.demo.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up1.demo.bean.ErrorBean;
import com.up1.demo.exception.UnknownResourceException;

public class BaseController {

	@ExceptionHandler(Exception.class)
	public @ResponseBody
	ErrorBean handleAllExceptions(Exception exception) {

		if (exception instanceof UnknownResourceException) {
			ErrorBean errorBean = new ErrorBean();
			errorBean.setErrorCode("unknow_resource");
			errorBean.setErrorMessage(exception.getMessage());
			errorBean.setErrorType("unknow_resource");
			return errorBean;
		} else {
			return new ErrorBean();
		}
	}

}
