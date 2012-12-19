package up1.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import up1.demo.service.FeedService;
import up1.demo.service.FeedServiceImpl;

@Configuration
@ComponentScan(basePackages = { "up1.demo.controller", "up1.demo.dao", "up1.demo.value", "up1.demo.service" })
public class TestWebConfig {

	@Bean
	public ViewResolver getViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("WEB-INF/pages/");
		resolver.setSuffix(".jsp");

		return resolver;
	}

	@Bean
	public FeedService getFeedService() {
		return new FeedServiceImpl();
	}
	
	

}