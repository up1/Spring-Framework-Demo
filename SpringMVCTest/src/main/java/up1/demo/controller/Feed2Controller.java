package up1.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import up1.demo.bean.FeedBean;
import up1.demo.bean.FeedListData;
import up1.demo.service.FeedService;

@Controller
@RequestMapping(value = "/feed2")
public class Feed2Controller extends BaseController {
	
	@Autowired
	public FeedService feedService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody
	FeedBean info(@PathVariable int id) throws Exception {
		System.out.println("ID=" + id);
		return feedService.getInfo(id);
	}

	@RequestMapping(value = { "/check/{email}" }, method = RequestMethod.GET)
	public @ResponseBody
	FeedBean check(@PathVariable String email) throws Exception {
		System.out.println(email);
		FeedBean feedBean = new FeedBean();
		feedBean.setTitle("Somkiat");
		return feedBean;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public @ResponseBody
	FeedListData list() throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setTitle("Somkiat");

		List<FeedBean> feedBeanList = new ArrayList<FeedBean>();
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);

		FeedListData resultList = new FeedListData();
		resultList.setFeedBeanList(feedBeanList);
		return resultList;
	}

}
