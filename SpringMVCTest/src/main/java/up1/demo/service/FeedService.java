package up1.demo.service;

import up1.demo.bean.FeedBean;
import up1.demo.bean.FeedListData;

public interface FeedService {
	FeedBean getInfo(int id);
	FeedListData getAll();
}
