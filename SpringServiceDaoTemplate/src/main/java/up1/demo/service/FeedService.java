package up1.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import up1.demo.value.FeedValue;

@Service
public interface FeedService {

	@Transactional
	public void createFeed( FeedValue feedValue  );
	
	@Transactional
	public List<FeedValue> listFeed();
	
	@Transactional
	public FeedValue getFeed( int id );
	
}
