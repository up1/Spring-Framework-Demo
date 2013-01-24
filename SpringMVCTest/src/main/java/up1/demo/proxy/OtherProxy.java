package up1.demo.proxy;

import up1.demo.bean.FeedBean;

public interface OtherProxy {
	FeedBean getData(int id) throws Exception;
}
