package up1.demo.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import up1.demo.config.TestJdbcConfig;
import up1.demo.config.TestWebConfig;
import up1.demo.value.FeedValue;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJdbcConfig.class, TestWebConfig.class })
public class FeedDaoTest {

	@Autowired
	private FeedDao feedDao;
	
	@Test
	public void testCreateFeed() {
		FeedValue feedValue = new FeedValue();
		feedValue.setName("Name");
		feedValue.setLongDescription("DESC");
		feedValue.setLink("Link");
		feedDao.createFeed(feedValue);
	}
	
	@Test
	public void testGetFeedById() {
		FeedValue feedValue = feedDao.getFeed(1);
		assertNotNull(feedValue);
		assertEquals(1,feedValue.getId());
	}
}
