package up1.demo.config;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import up1.demo.proxy.OtherProxy;
import up1.demo.proxy.impl.OtherProxyImpl;
import up1.demo.service.FeedService;
import up1.demo.service.impl.FeedServiceImpl;

@Configuration
public class TestServiceConfig {
	
	@Bean
	public FeedService getFeedService() {
		return Mockito.mock(FeedServiceImpl.class);
	}
	
	@Bean
	public OtherProxy getOtherProxy() {
		return Mockito.mock(OtherProxyImpl.class);
	}
}
