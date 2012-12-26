package com.up1.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.up1.demo.bean.ErrorBean;
import com.up1.demo.exception.UnknownResourceException;
import com.up1.demo.exception.UserException;

public class BaseController {

	@ExceptionHandler(Exception.class)
	public @ResponseBody
	ErrorBean handleAllExceptions(Exception exception, WebRequest request, HttpServletResponse response) {

		if (exception instanceof UnknownResourceException) {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			ErrorBean errorBean = new ErrorBean();
			errorBean.setErrorCode("unknow_resource");
			errorBean.setErrorMessage(exception.getMessage());
			errorBean.setErrorType("unknow_resource");
			return errorBean;
		} else if (exception instanceof UserException) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ErrorBean errorBean = new ErrorBean();
			errorBean.setErrorCode("user_error");
			errorBean.setErrorMessage(exception.getMessage());
			errorBean.setErrorType("user_error");
			return errorBean;
		} else {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			ErrorBean errorBean = new ErrorBean();
			errorBean.setErrorCode("xxx");
			errorBean.setErrorMessage(exception.getMessage());
			errorBean.setErrorType("xxx");
			return errorBean;
		}
	}

}
