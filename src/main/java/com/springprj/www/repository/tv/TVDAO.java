package com.springprj.www.repository.tv;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewDTO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVVO;

public interface TVDAO {
	// tv
	List<TVVO> selectUserLikedTVList(String email);
	List<TVVO> selectUserRatedTVList(String email);
	List<TVVO> selectUserReviewedTVList(String email);
	TVVO selectOneTV(long tvid);
	int insertTVData(TVVO tvvo);
	
	// review 
	List<ReviewDTO> selectListTVReview(long tvid);
	ReviewDTO selectOneTVReview( @Param("tvid") long tvid,@Param("writer") String email); // 중복 리뷰 작성 방지용
	int insertTVReview(ReviewVO rvvo );
	int updateTVReview(ReviewVO rvvo);
	int deleteTVReview( @Param("tvid") long tvid,@Param("writer") String email);
	
	// like
	int selectTVLikeCount(long tvid);
	int selectOneTVLike(@Param("tvid") long tvid, @Param("email") String email); //중복 좋아요 방지용 
	int insertTVLike(LikeVO lvo);
	int deleteTVLike(@Param("tvid") long tvid, @Param("email") String email);
	
	// rating
	
	Double selectTVAvgRating(long tvid);
	Double selectOneTVRating( @Param("tvid") long tvid,@Param("email") String email); //중복 평점 방지용(nullable)
	int insertTVRating(RatingVO rtvo);
	int updateTVRating(RatingVO rtvo);
	int deleteTVRating( @Param("tvid") long tvid,@Param("email") String email);
	
	
	//rank
	List<TVVO> selectListLikeRankTV();
	List<TVVO> selectListRatingRankTV();
}
