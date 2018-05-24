package com.inteliment.searchcount.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearchCountServiceTests {

	private static final Logger logger =
			LoggerFactory.getLogger(SearchCountServiceImpl.class);

	@Autowired
	private SearchCountService searchCountService;

	@Test
	public void getCountTextTest() throws Exception{

		logger.info("Entering getCountTextTest method ..");

		List<String> list = new ArrayList<>();
		list.add("Duis");
		list.add("Sed");
		list.add("Donec");

		List<Map> countTextMap = searchCountService.getCountText(list);

		Assert.assertEquals(11,((Long)(countTextMap.get(0).get("Duis"))).intValue());
		Assert.assertEquals(16,((Long)(countTextMap.get(1).get("Sed"))).intValue());
		Assert.assertEquals(8,((Long)(countTextMap.get(2).get("Donec"))).intValue());

		logger.info("Exiting getCountTextTest method ..");
	}

	@Test
	public void getTopCountText() throws Exception {

		logger.info("Entering getTopCountText method ..");

		String result = searchCountService.getTopCountText(10);
		Assert.assertEquals("eget|17,vel|17,sed|16,in|15,et|14,eu|13,ut|13,ac|12,amet|12,id|12,",
				result);

		logger.info("Exiting getTopCountText method ..");
	}

}
