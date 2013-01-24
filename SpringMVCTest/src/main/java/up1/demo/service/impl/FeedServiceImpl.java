package up1.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import up1.demo.bean.FeedBean;
import up1.demo.bean.FeedListData;
import up1.demo.proxy.OtherProxy;
import up1.demo.service.FeedService;

public class FeedServiceImpl implements FeedService {
	
	@Autowired
	public OtherProxy otherProxy;

	@Override
	public FeedBean getInfo(int id) throws Exception {
		return otherProxy.getData(id);
	}

	@Override
	public FeedListData getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
