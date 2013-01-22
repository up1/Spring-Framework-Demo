package up1.demo.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import up1.demo.bean.ErrorBean;
import up1.demo.exception.UnknownResourceException;


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
