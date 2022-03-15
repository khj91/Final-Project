package com.springprj.www.repository;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVVO;
import com.springprj.www.repository.tv.TVDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Slf4j
public class TVDAOTest {

	@Inject
	private TVDAO tdao;
	
	@Test
	public void insertTV() {
		TVVO tvvo = new TVVO();
		tvvo.setTvid(123L);
		tvvo.setTitle("test TV");
		tvvo.setPoster(null);
		tdao.insertTVData(tvvo);
	}
	
	@Test
	public void getLikedTVList() {
		tdao.selectUserLikedTVList("123@123.com");
	}
	
	@Test
	public void getRatedTVList() {
		tdao.selectUserRatedTVList("123@123.com");
	}
	
	@Test
	public void getReviewedTVList() {
		tdao.selectUserReviewedTVList("123@123.com");
	}
	
	// review 
	
	@Test 
	public void insertReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setContent("test Content");
		rvvo.setTvid(123L);
		rvvo.setWriter("123@123.com");
		tdao.insertTVReview(rvvo);
	}
	
	@Test
	public void updateReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setContent("modified Content");
		rvvo.setTvid(123L);
		rvvo.setWriter("123@123.com");
		if(tdao.selectOneTVReview(123L, "123@123.com") != null) {
			tdao.updateTVReview(rvvo);
		}
	}
	
	@Test
	public void getReviewList() {
		tdao.selectListTVReview(123L);
	}
	
	// like 
	@Test
	public void insertLike() {
		LikeVO lvo = new LikeVO();
		lvo.setTvid(123L);
		lvo.setEmail("123@123.com");
		if(tdao.selectOneTVLike(123L, "123@123.com") == 0) {
			tdao.insertTVLike(lvo);
		} 
	}
	
	@Test
	public void getLikeCount() {
		tdao.selectTVLikeCount(123L);
	}
	
	@Test
	public void deleteLike() {
		tdao.deleteTVLike(123L, "123@123.com");
	}
	
	// rating
	@Test
	public void insertRating() {
		for (int i = 0; i < 40; i++) {
			RatingVO rtvo = new RatingVO();
			rtvo.setTvid(123L);
			rtvo.setEmail("test"+i+"@test.com");
			rtvo.setRating(Double.valueOf(String.format("%.1f", Math.random()*10)));
			tdao.insertTVRating(rtvo);
		}
	}
	
	@Test
	public void getAvgRating() {
		tdao.selectTVAvgRating(123L);
	}
	
	@Test
	public void updateRating() {
		RatingVO rtvo = new RatingVO();
		rtvo.setEmail("test39@test.com");
		rtvo.setTvid(123L);
		rtvo.setRating(10L);
		if(tdao.selectOneTVRating(123L, "test39@test.com") != null) {
			tdao.updateTVRating(rtvo);
		}
		tdao.selectTVAvgRating(123L);
	}
	
	@Test
	public void deleteRating() {
		tdao.deleteTVRating(123L, "test39@test.com");
	}
}
