package up1.demo.test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import up1.demo.bean.FeedBean;
import up1.demo.config.TestServiceConfig;
import up1.demo.config.TestWebConfig;
import up1.demo.controller.Feed2Controller;
import up1.demo.service.FeedService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestWebConfig.class, TestServiceConfig.class })
public class Feed2ControllerTest {
	private MockMvc mockMvc;

	FeedService feedServiceMock;

	@Before
	public void setup() {
		feedServiceMock = mock(FeedService.class);
		Feed2Controller feedController = new Feed2Controller();
		feedController.feedService = feedServiceMock;
		FeedBean feedBean = new FeedBean();
		feedBean.setId(1);
		feedBean.setTitle("My Link");
		when(feedServiceMock.getInfo(1)).thenReturn(feedBean);
		when(feedServiceMock.getInfo(2)).thenThrow(new Exception());
		
		this.mockMvc = MockMvcBuilders.standaloneSetup(feedController).build();
	}

	@Test
	public void shouldGetTestFeedAsJson() throws Exception {
		this.mockMvc.perform(get("/feed2/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.title").value("My Link"));
	}

}
