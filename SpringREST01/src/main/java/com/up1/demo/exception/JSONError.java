package com.up1.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

public class JSONError {
	private final String message;

	public JSONError(String message) {
		this.message = message;
	}

	public ModelAndView asModelAndView() {
		MappingJacksonJsonView jsonView = new MappingJacksonJsonView();
		Map<String, String> outputMap = new HashMap<String, String>();
		outputMap.put("error", message);
		outputMap.put("code", "number-incorrect");
		return new ModelAndView(jsonView, outputMap);
	}
}
