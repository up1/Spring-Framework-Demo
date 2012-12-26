package com.up1.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.up1.demo.exception.UnknownResourceException;

@Controller
public class DefaultController extends BaseController {
	@RequestMapping("/**")
    public void unmappedRequest(HttpServletRequest request) {
        String uri = request.getRequestURI();
        throw new UnknownResourceException("No resource for path " + uri);
    }
}
