package up1.demo.test;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.anything;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import up1.demo.config.TestWebConfig;
import up1.demo.controller.FeedController;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { TestWebConfig.class })
public class FeedControllerTest {
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Test
	public void getInfo() throws Exception {
		this.mockMvc.perform(get("/feed/1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.title").value("Somkiat"));
	}

	@Test
	public void check() throws Exception {
		this.mockMvc.perform(get("/feed/check/somkiat@gmail.com").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.title").value("Somkiat"));
	}

	// @Test
	// public void getAll() throws Exception {
	// this.mockMvc.perform(get("/feed/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType("application/json;charset=UTF-8"));
	// }

	@Test
	public void callFromRestClient() throws Exception {
		RestTemplate restTemplate = new RestTemplate();
		MockRestServiceServer mockServer = MockRestServiceServer.createServer(restTemplate);

		mockServer.expect(anything());
		mockServer.expect(anything());
		mockServer.expect(requestTo("/feed/xxx/1")).andRespond(withSuccess("Hello world", MediaType.APPLICATION_JSON));

		try {
			mockServer.verify();
		} catch (AssertionError error) {
			assertTrue(error.getMessage(), error.getMessage().contains("0 out of 3 were executed"));
		}
	}

	@Configuration
	public static class TestConfiguration {

		@Bean
		public FeedController feedController() {
			return new FeedController();
		}

	}
}
