package up1.demo.bean;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class FeedListData {
	@JsonProperty("book-list")
	public List<FeedBean> feedBeanList;

	public FeedListData() {
	}

	public List<FeedBean> getFeedBeanList() {
		return feedBeanList;
	}

	public void setFeedBeanList(List<FeedBean> feedBeanList) {
		this.feedBeanList = feedBeanList;
	}

}