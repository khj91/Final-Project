package com.springprj.www.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVDTO;
import com.springprj.www.domain.movietv.TVVO;
import com.springprj.www.service.tv.TVService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class TVServiceTest {

	@Inject
	private TVService tsv;
	
	@Test
	public void registerTVIfNotExists() {
		TVVO tvvo = new TVVO();
		tvvo.setTvid(111L);
		tvvo.setTitle("test TV");
		tvvo.setPoster(null);
		tsv.registerTVIfNotExists(tvvo);
	}
	
	@Test
	public void getTVData() {
		TVDTO dto = tsv.getTVData(111L, "123@123.com");
		log.info("dto {}", dto);
	}
	
	@Test
	public void registerReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setTvid(111L);
		rvvo.setWriter("123@123.com");
		rvvo.setContent("test content");
		
	}
}
