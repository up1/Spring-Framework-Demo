package com.up1.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up1.demo.dao.LoggingDaoImpl;

@Service
public class LogService {

	@Autowired
	LoggingDaoImpl loggingDao;

	public void addData(String key, String value) {
		loggingDao.addData(key, value);
	}

}
