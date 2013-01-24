package up1.demo.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import up1.demo.bean.FeedBean;
import up1.demo.config.TestServiceConfig;
import up1.demo.config.TestWebConfig;
import up1.demo.proxy.OtherProxy;
import up1.demo.service.FeedService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestWebConfig.class, TestServiceConfig.class })
public class Feed2ControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	FeedService feedServiceMock;
	OtherProxy otherProxyMock;

	@Before
	public void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void shouldSuccessWhenGetFeedAsJson() throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setId(1);
		feedBean.setTitle("My Link");

		feedServiceMock = wac.getBean(FeedService.class);
		when(feedServiceMock.getInfo(1)).thenReturn(feedBean);

		otherProxyMock = wac.getBean(OtherProxy.class);
		when(otherProxyMock.getData(1)).thenReturn(feedBean);

		this.mockMvc.perform(get("/feed2/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.title").value("My Link"));
	}

	@Test
	public void shouldErrorWhenGetFeedAsJson() throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setId(1);
		feedBean.setTitle("My Link");

		otherProxyMock = wac.getBean(OtherProxy.class);
		when(otherProxyMock.getData(2)).thenThrow(new Exception("Data not found in proxy"));

		feedServiceMock = wac.getBean(FeedService.class);
//		when(feedServiceMock.getInfo(2)).thenThrow(new Exception("Data not found in service"));
		when(feedServiceMock.getInfo(2)).thenCallRealMethod();

		this.mockMvc.perform(get("/feed2/2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.errorCode").value("xxx")).andExpect(jsonPath("$.errorType").value("xxx"));
	}

	@Test
	public void printInfo() throws Exception {
		FeedBean feedBean = new FeedBean();
		feedBean.setId(1);
		feedBean.setTitle("My Link");
		int id = 3;

		otherProxyMock = wac.getBean(OtherProxy.class);
		when(otherProxyMock.getData(id)).thenThrow(new Exception("Data not found from proxy"));

		feedServiceMock = wac.getBean(FeedService.class);
//		when(feedServiceMock.getInfo(id)).thenThrow(new Exception("Data not found"));
		when(feedServiceMock.getInfo(id)).thenCallRealMethod();

		this.mockMvc.perform(get("/feed2/3")).andDo(print());
	}

}
