package com.inteliment.searchcount.controller;

import com.inteliment.searchcount.service.SearchCountService;
import com.inteliment.searchcount.service.SearchCountServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchCountControllerTests {

	private static final Logger logger =
			LoggerFactory.getLogger(SearchCountServiceImpl.class);

	@Mock
	private SearchCountService searchCountService;

	private MockMvc mockMvc;


	@Before
	public void setup() {

		final SearchCountController searchCountController  = new SearchCountController();
		searchCountController.setSearchCountService(searchCountService);

		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(searchCountController).build();
	}

	@Test
	public void getCountTextTest() throws Exception {

		logger.info("Entering getCountTextTest method ..");

		Mockito.when(this.searchCountService.getCountText(new ArrayList())).thenReturn(new ArrayList());

		this.mockMvc.perform(post("/counter-api/search").
				contentType(MediaType.APPLICATION_JSON_VALUE).
				header("Authorization", "Basic aW50ZWxpbWVudDppbnRlbGltZW50").
				content("{\"searchText\": [\"Duis\", \"Sed\", \"Donec\", \"Augue\", \"Pellentesque\", \"123\"]}")).
				andDo(print()).
				andExpect(status().isOk()).
				andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

		logger.info("Exiting getCountTextTest method ..");
	}

	@Test
	public void getTopCountText() throws Exception {

		logger.info("Entering getTopCountText method ..");

		Mockito.when(this.searchCountService.getTopCountText(10)).thenReturn(new String("eget|17,vel|17,sed|16,in|15,et|14,eu|13,ut|13,ac|12,amet|12,id|12,"));

		this.mockMvc.perform(get("/counter-api/top/{topCount}", 10).
				contentType(MediaType.APPLICATION_JSON_VALUE).
				header("Authorization", "Basic aW50ZWxpbWVudDppbnRlbGltZW50")).
				andDo(print()).
				andExpect(status().isOk()).
				andExpect(content().contentType("text/csv"));

		logger.info("Exiting getTopCountText method ..");
	}


}
