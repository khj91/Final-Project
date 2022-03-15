package com.springprj.www.repository;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.MovieVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.repository.movie.MovieDAO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Slf4j
public class MovieDAOTest {

	@Inject 
	private MovieDAO mdao;
	
	//movie
	
	@Test
	public void insertMovie() {
		MovieVO mvo = new MovieVO();
		mvo.setMid(124L);
		mvo.setTitle("test Movie");
		mvo.setPoster(null);
		mdao.insertMovieData(mvo);
	}
	
	@Test
	public void getLikedMovieList() {
		mdao.selectUserLikedMovieList("123@123.com");
	}
	
	@Test
	public void getRatedMovieList() {
		mdao.selectUserRatedMovieList("test2@test.com");
	}
	
	@Test 
	public void getReviewedMovieList() {
		mdao.selectUserReviewedMovieList("123@123.com");
	}
	
	
//	review
	
	@Test
	public void insertReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setContent("test Content");
		rvvo.setMid(123L);
		rvvo.setWriter("123@123.com");
		mdao.insertMovieReview(rvvo);
	}
	
	@Test
	public void updateReview() {
		ReviewVO rvvo = new ReviewVO();
		rvvo.setContent("modified Content");
		rvvo.setMid(123L);
		rvvo.setWriter("123@123.com");
		if(mdao.selectOneMovieReview(123L, "123@123.com") != null) {
			mdao.updateMovieReview(rvvo);
		}
	}
	
	@Test 
	public void getReviewList()	{
		mdao.seleListMovieReview(123L);
	}
	
	//like
	
	@Test
	public void insertLike() {
		LikeVO lvo = new LikeVO();
		lvo.setMid(124L);
		lvo.setEmail("123@123.com");
		if(mdao.selectOneMovieLike(124L, "123@123.com") == 0) {
			mdao.insertMovieLike(lvo);
		} 
	}
	
	@Test
	public void getLikeCount() {
		mdao.selectMovieLikeCount(123L);
	}
	
	@Test
	public void deleteLike() {
		
		mdao.deleteMovieLike(123L, "123@123.com");
	}
	
	// rating

	@Test
	public void insertRating() {
		for (int i = 0; i < 40; i++) {
			RatingVO rtvo = new RatingVO();
			rtvo.setEmail("test"+ i+ "@test.com");
			rtvo.setMid(123L);
			
			Double rating = Double.valueOf(String.format("%.1f",Math.random()*5));
			rtvo.setRating(rating);
			mdao.insertMovieRating(rtvo);
		}
	}
	
	@Test
	public void getAvgRating() {
		RatingVO rtvo = new RatingVO();
		
		rtvo.setMid(123L);
		mdao.selectMovieAvgRating(123L);
	}
	
	@Test
	public void updateRating() {
		RatingVO rtvo = new RatingVO();
		rtvo.setEmail("test3@test.com");
		rtvo.setMid(123L);
		rtvo.setRating(5.0);
		if(mdao.selectOneMovieRating(123L, "test3@test.com") != null) {
			mdao.updateMovieRating(rtvo);
		}
		mdao.selectMovieAvgRating(123L);
	}
	
	@Test
	public void deleteRating() {
		mdao.deleteMovieRating(123L, "test0@test.com");
	}
	
}
