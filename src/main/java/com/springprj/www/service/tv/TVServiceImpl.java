package com.springprj.www.service.tv;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVDTO;
import com.springprj.www.domain.movietv.TVVO;
import com.springprj.www.repository.tv.TVDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TVServiceImpl implements TVService {

	@Inject
	private TVDAO tdao;

	@Override
	public int registerTVIfNotExists(TVVO tvvo) {
		int isUp = 0;
		if (tdao.selectOneTV(tvvo.getTvid()) != null) {
			isUp = 0;
		} else {
			isUp = tdao.insertTVData(tvvo);
		}
		return isUp;
	}

	@Override
	public TVDTO getTVData(long tvid, String loggedInUser) {
		TVDTO dto = new TVDTO();
		if (loggedInUser != null && !loggedInUser.equals("")) {
			dto.setRvdto(tdao.selectOneTVReview(tvid, loggedInUser));
			dto.setIsLiked(tdao.selectOneTVLike(tvid, loggedInUser));
			dto.setRating(tdao.selectOneTVRating(tvid, loggedInUser));
		}

		// 영화의 리뷰, 즐겨찾기 수, 평균 평점
		dto.setAvgRating(tdao.selectTVAvgRating(tvid));
		dto.setRvList(tdao.selectListTVReview(tvid));
		dto.setLikeCount(tdao.selectTVLikeCount(tvid));

		return dto;
	}

	@Override
	public List<TVVO> getUserReviewdList(String email) {

		return tdao.selectUserReviewedTVList(email);
	}

	@Override
	public List<TVVO> getUserLikedList(String email) {

		return tdao.selectUserLikedTVList(email);
	}

	@Override
	public List<TVVO> getUserRatedList(String email) {

		return tdao.selectUserRatedTVList(email);
	}

	// =============== review ================
	@Override
	public int registerReview(ReviewVO rvvo) {
		if (tdao.selectOneTVReview(rvvo.getTvid(), rvvo.getWriter()) == null) {
			return tdao.insertTVReview(rvvo);
		} else {
			return -1;
		}
		
	}

	@Override
	public int modifyReview(ReviewVO rvvo) {

		return tdao.updateTVReview(rvvo);
	}

	@Override
	public int deleteReview(long tvid, String email) {

		return tdao.deleteTVReview(tvid, email);
	}

	// ======================== like ========================
	@Override
	public int registerLike(LikeVO lvo) {
		if (tdao.selectOneTVLike(lvo.getTvid(), lvo.getEmail()) == 0) {
			return tdao.insertTVLike(lvo);
		} else {
			return 0;
		}
	}

	@Override
	public int deleteLike(long tvid, String email) {

		return tdao.deleteTVLike(tvid, email);
	}

	// ====================== rating ========================
	@Override
	@Transactional
	public Double registerRating(RatingVO rtvo) {
		if (tdao.selectOneTVRating(rtvo.getTvid(), rtvo.getEmail()) == null) {
			tdao.insertTVRating(rtvo);
		}
		return tdao.selectTVAvgRating(rtvo.getTvid());
	}

	@Override
	@Transactional
	public Double modifyRating(RatingVO rtvo) {
		tdao.updateTVRating(rtvo);
		return tdao.selectTVAvgRating(rtvo.getTvid());
	}

	@Override
	@Transactional
	public Double deleteRating(long tvid, String email) {
		tdao.deleteTVRating(tvid, email);
		return tdao.selectTVAvgRating(tvid);
	}

	@Override
	public List<TVVO> getLikeRankList() {
		
		return tdao.selectListLikeRankTV();
	}

	@Override
	public List<TVVO> getRatingRankList() {
		
		return tdao.selectListRatingRankTV();
	}
	
	

}
