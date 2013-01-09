package up1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import up1.demo.bean.FeedBean;


@Controller
@RequestMapping(value = "/feed")
public class FeedController {
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	FeedBean info(@PathVariable int id) throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setTitle("Somkiat");
		return feedBean;
	} 

}
