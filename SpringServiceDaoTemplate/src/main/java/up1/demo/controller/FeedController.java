package up1.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import up1.demo.bean.FeedBean;
import up1.demo.service.FeedService;
import up1.demo.value.FeedValue;

@Controller
@RequestMapping(value = "/feed")
public class FeedController extends BaseController {
	
	@Autowired
	private FeedService feedService;

	/**
	 * Get feed by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	FeedBean info(@PathVariable int id) {
		FeedBean feedBean = new FeedBean();
		//Convert process value to bean
		FeedValue feedValue = feedService.getFeed(id);
		feedBean.setId(feedValue.getId());
		feedBean.setTitle(feedValue.getName());		
		return feedBean;
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
		
		//Convert process bean to value  
		FeedValue feedValue = new FeedValue();
		feedValue.setName("Name controller");
		feedValue.setLongDescription("Desc controller");
		feedValue.setLink("Link controller");
		feedService.createFeed(feedValue);
		
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
