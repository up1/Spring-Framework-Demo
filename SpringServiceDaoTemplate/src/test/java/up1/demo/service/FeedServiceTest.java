package up1.demo.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import up1.demo.config.TestJdbcConfig;
import up1.demo.config.TestWebConfig;
import up1.demo.value.FeedValue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJdbcConfig.class, TestWebConfig.class })
public class FeedServiceTest {
	
	@Autowired
	private FeedService feedService;
	
	@Test
	public void createFeed() {
		FeedValue feedValue = new FeedValue();
		feedValue.setName("Name service");
		feedValue.setLongDescription("DESC service");
		feedValue.setLink("Link service");
		feedService.createFeed(feedValue);
	}
	
	@Test
	public void testGetFeedById() {
		FeedValue feedValue = feedService.getFeed(1);
		assertNotNull(feedValue);
		assertEquals(1,feedValue.getId());
	}
}
