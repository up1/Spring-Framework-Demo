package up1.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import up1.demo.service.FeedService;
import up1.demo.service.impl.FeedServiceImpl;

@Configuration
public class TestServiceConfig {
	
	@Bean
	public FeedService getFeedService() {
		return new FeedServiceImpl();
	}
}
