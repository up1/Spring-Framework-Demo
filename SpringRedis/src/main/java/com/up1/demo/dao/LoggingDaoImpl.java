package com.up1.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoggingDaoImpl implements LoggingDao {
	
	private final static String LOGGING_KEY = "logging";

	@Autowired
	RedisTemplate<String, String> redisTemplate;

	public void addData(String key, String value) {
		redisTemplate.opsForList().rightPush(LOGGING_KEY, key);
		redisTemplate.opsForValue().set(key, value);
		System.out.println( LOGGING_KEY );
		System.out.println( key );
	}

	public List<String> listOfData(String key, int startPosition, int endPosition) {
		List<String> keys = redisTemplate.opsForList().range(key, startPosition, endPosition);
		List<String> values = redisTemplate.opsForValue().multiGet(keys);
		return values;
	}
	
	public long countData() {
		return redisTemplate.opsForList().size(LOGGING_KEY);
	}

}
