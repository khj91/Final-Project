package com.springprj.www.service.tv;

import java.util.List;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVDTO;
import com.springprj.www.domain.movietv.TVVO;

public interface TVService {
	
	// tv 전체 데이터 가져오기
	int registerTVIfNotExists(TVVO tvvo);
	TVDTO getTVData(long tvid, String email);
	
	// 유저가 리뷰 즐겨찾기 평점 남긴 tv 목록 가져오기
	List<TVVO> getUserReviewdList(String email);
	List<TVVO> getUserLikedList(String email);
	List<TVVO> getUserRatedList(String email);
	
	// review 
	int registerReview(ReviewVO rvvo);
	int modifyReview(ReviewVO rvvo);
	int deleteReview(long tvid, String email);
	
	// like 
	int registerLike(LikeVO lvo);
	int deleteLike(long tvid, String email);
	
	// rating
	Double registerRating(RatingVO rtvo);
	Double modifyRating(RatingVO rtvo);
	Double deleteRating(long tvid, String email);
	
	//rank
	List<TVVO> getLikeRankList();
	List<TVVO> getRatingRankList();
	
}
