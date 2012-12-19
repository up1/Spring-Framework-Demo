package up1.demo.dao;

import up1.demo.value.FeedValue;

public interface FeedDao {
	
	public void createFeed( FeedValue feedValue );
	public FeedValue getFeed( int id );

}
