package com.up1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.up1.demo.bean.FeedBean;

@Controller
@RequestMapping(value = "/feed")
public class FeedController extends BaseController {

	/**
	 * Get feed by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	FeedBean info(@PathVariable int id) {
		// Get feed by id
		return new FeedBean();
	}

	/**
	 * Create new feed
	 * 
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	public FeedBean newFeed(@RequestBody FeedBean feedBean) {
		// Create new feed
		FeedBean outputFeed = new FeedBean();
		outputFeed.setId(1);
		return outputFeed;
	}

	/**
	 * Update feed
	 * 
	 * @param feedBean
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	FeedBean updateFeed(@RequestBody FeedBean feedBean, @PathVariable int id) {
		// Update feed
		return feedBean;
	}

	/**
	 * Delete feed
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	FeedBean deleteFeed(@PathVariable int id) {
		// Delete feed by id
		return new FeedBean();
	}

}
