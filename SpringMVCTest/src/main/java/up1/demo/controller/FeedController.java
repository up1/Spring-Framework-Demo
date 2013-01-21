package up1.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import up1.demo.bean.FeedBean;


@Controller
@RequestMapping(value = "/feed")
public class FeedController {
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public @ResponseBody
	FeedBean info(@PathVariable int id) throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setTitle("Somkiat");
		return feedBean;
	} 
	
	@RequestMapping( value="/all", method = RequestMethod.GET)
	public @ResponseBody
	FeedList list() throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setTitle("Somkiat");
		
		List<FeedBean> feedBeanList = new ArrayList<FeedBean>();
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);
		feedBeanList.add(feedBean);
		
		FeedList resultList = new FeedList();
		resultList.setFeedBeanList(feedBeanList);
		return resultList;
	} 

}


class FeedList {
	@JsonProperty("book-list")
	private List<FeedBean> feedBeanList;

	public List<FeedBean> getFeedBeanList() {
		return feedBeanList;
	}

	public void setFeedBeanList(List<FeedBean> feedBeanList) {
		this.feedBeanList = feedBeanList;
	}
	
	
}
