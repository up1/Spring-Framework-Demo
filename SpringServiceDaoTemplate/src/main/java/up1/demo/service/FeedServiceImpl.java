package up1.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import up1.demo.dao.FeedDao;
import up1.demo.value.FeedValue;

public class FeedServiceImpl implements FeedService {
	
	@Autowired
	private FeedDao feedDao;

	@Override
	public void createFeed(FeedValue feedValue) {
		feedDao.createFeed(feedValue);
	}

	@Override
	public List<FeedValue> listFeed() {
		return null;
	}

	@Override
	public FeedValue getFeed(int id) {
		return feedDao.getFeed(id);
	}

}
