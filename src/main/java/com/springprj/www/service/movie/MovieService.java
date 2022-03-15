package com.springprj.www.service.movie;

import java.util.List;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.MovieDTO;
import com.springprj.www.domain.movietv.MovieVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVVO;

public interface MovieService {
	
	
	// 영화 전체 데이터 가져오기
	int registerMovieIfNotExists(MovieVO mvvo);
	MovieDTO getMovieData(long mid, String email); 
	
	// 유저가 리뷰, 즐겨찾기, 평점 남긴 영화 목록 가져오기
	List<MovieVO> getUserReviewedList(String email);
	List<MovieVO> getUserLikedList(String email);
	List<MovieVO> getUserRatedList(String email);
	
	// review
	int registerReview(ReviewVO rvvo);
	int modifyReview(ReviewVO rvvo);
	int deleteReview(long mid, String email);
	
	// like
	int registerLike(LikeVO lvo);
	int deleteLike(long mid ,String email);
	
	// rating
	Double registerRating(RatingVO rtvo);
	Double modifyRating(RatingVO rtvo);
	Double deleteRating(long mid, String email);
	
	//rank
	List<MovieVO> getLikeRankList();
	List<MovieVO> getRatingRankList();
}
