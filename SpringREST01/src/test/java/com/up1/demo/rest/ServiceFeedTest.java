package com.up1.demo.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.up1.demo.bean.FeedBean;

public class ServiceFeedTest {
	private String url = "http://localhost:8585/demo01/feed/{id}";
	private String urlPost = "http://localhost:8585/demo01/feed/";
	private RestTemplate restTemplate = new RestTemplate();

	@Test
	public void getFeedOk() {
		FeedBean feedBean = restTemplate.getForObject(url, FeedBean.class, "1");
		assertNotNull(feedBean);
		assertEquals(0, feedBean.getId());
	}

	@Test
	public void getFeedMethodNotAllow() {
		try {
			restTemplate.getForObject(url, FeedBean.class, "");
		} catch (HttpClientErrorException e) {
			assertEquals(HttpStatus.METHOD_NOT_ALLOWED, e.getStatusCode());
		} catch (Exception e) {
			fail("this isn't the expected exception: " + e.getMessage());
		}
	}

	@Test
	public void createFeed() {
		FeedBean feedBean = new FeedBean();
		FeedBean afterFeedBean = restTemplate.postForObject(urlPost, feedBean, FeedBean.class);
		assertTrue(afterFeedBean.getId() > 0);
		assertEquals(afterFeedBean.getTitle(), afterFeedBean.getTitle());
	}

	@Test
	public void updateFeed() {
		FeedBean feedBean = new FeedBean();
		feedBean.setId(2);
		restTemplate.put(url, feedBean, "1");
	}

	@Test
	public void deleteFeed() {
		FeedBean feedBean = new FeedBean();
		feedBean.setId(2);
		restTemplate.delete(url, "1");
	}
}
