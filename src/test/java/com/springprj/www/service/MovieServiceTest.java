package com.springprj.www.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.MovieDTO;
import com.springprj.www.domain.movietv.MovieVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.service.movie.MovieService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class MovieServiceTest {

	@Inject
	private MovieService msv;

	@Test
	public void registerMovieIfNotExist() {
		MovieVO mvvo = new MovieVO();
		mvvo.setMid(111L);
		mvvo.setPoster(null);
		mvvo.setTitle("service test");
		msv.registerMovieIfNotExists(mvvo);
	}

	@Test
	public void getMovieData() {
		MovieDTO dto = msv.getMovieData(111L, "123@123.com");
		log.info("{}", dto);
	}

	@Test
	public void registerReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setMid(1);
		rvvo.setWriter("123@123.com");
		rvvo.setContent("this is test review for eternals");
		int isUp = msv.registerReview(rvvo);
		log.info(("register " + (isUp > 0 ? "success, {}" : "failed")), msv.getMovieData(111L, rvvo.getWriter()));
	}

	@Test
	public void modifyReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setMid(111L);
		rvvo.setWriter("123@123.com");
		rvvo.setContent("this is modified test review for movie 111");
		int isUp = msv.modifyReview(rvvo);
		log.info("modify " + (isUp > 0 ? "success" : "failed"));
	}

	@Test
	public void deleteReview() {
		log.info("delete " + (msv.deleteReview(111L, "123@123.com") > 0 ? "success" : "failed"));
	}
	
	@Test
	public void registerLike()	{
		LikeVO lvo = new LikeVO();
		lvo.setMid(111L);
		lvo.setEmail("123@123.com");
		log.info("like " + (msv.registerLike(lvo) > 0 ? "success"  : "failed"));
	}
	
	@Test
	public void deleteLike() {
		log.info("like delete "+ (msv.deleteLike(111L, "123@123.com") > 0 ? "success" : "failed"));
	}
	
	@Test
	public void registerRating() {
		RatingVO rtvo = new RatingVO();
		rtvo.setMid(111L);
		rtvo.setEmail("124@123.com");
		rtvo.setRating(1.2);
		Double rate = msv.registerRating(rtvo);
		log.info("current rating: {}", rate);
	}
	
	@Test
	public void modifyRating() {
		RatingVO rtvo = new RatingVO();
		rtvo.setMid(111L);
		rtvo.setEmail("123@123.com");
		rtvo.setRating(5.0);
		Double rate = msv.modifyRating(rtvo);
		log.info("current rating: {}", rate);
	}
	
	@Test
	public void deleteRating() {
		msv.deleteRating(111L, "124@123.com");
	}
	
}
