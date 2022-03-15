package com.springprj.www.service.movie;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.MovieDTO;
import com.springprj.www.domain.movietv.MovieVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.repository.movie.MovieDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

	@Inject
	private MovieDAO mdao;

	@Override
	public int registerMovieIfNotExists(MovieVO mvvo) {
		int isUp = 0;
		if (mdao.selectOneMovie(mvvo.getMid()) != null) {
			isUp = 0;
		} else {
			isUp = mdao.insertMovieData(mvvo);
		}
		return isUp;
	}

	@Override
	public MovieDTO getMovieData(long mid, String loggedInUser) {
		MovieDTO dto = new MovieDTO();

		if (loggedInUser != null && !loggedInUser.equals("")) {
			dto.setRvdto(mdao.selectOneMovieReview(mid, loggedInUser));
			dto.setIsLiked(mdao.selectOneMovieLike(mid, loggedInUser));
			dto.setRating(mdao.selectOneMovieRating(mid, loggedInUser));
		}

		// 영화의 리뷰, 즐겨찾기 수, 평균 평점
		dto.setAvgRating(mdao.selectMovieAvgRating(mid));
		dto.setLikeCount(mdao.selectMovieLikeCount(mid));
		dto.setRvList(mdao.seleListMovieReview(mid));

		return dto;
	}

	@Override
	public List<MovieVO> getUserReviewedList(String email) {
		return mdao.selectUserReviewedMovieList(email);
	}

	@Override
	public List<MovieVO> getUserLikedList(String email) {
		return mdao.selectUserLikedMovieList(email);
	}

	@Override
	public List<MovieVO> getUserRatedList(String email) {
		return mdao.selectUserRatedMovieList(email);
	}

	@Override
	public int registerReview(ReviewVO rvvo) {
		if (mdao.selectOneMovieReview(rvvo.getMid(), rvvo.getWriter()) == null) {
			return mdao.insertMovieReview(rvvo);
		} else {
			return -1;
		}
	}

	@Override
	public int modifyReview(ReviewVO rvvo) {

		return mdao.updateMovieReview(rvvo);
	}

	@Override
	public int deleteReview(long mid, String email) {

		return mdao.deleteMovieReview(mid, email);
	}

	@Override
	public int registerLike(LikeVO lvo) {
		if (mdao.selectOneMovieLike(lvo.getMid(), lvo.getEmail()) == 0) {
			return mdao.insertMovieLike(lvo);
		} else {
			return 0;
		}
	}

	@Override
	public int deleteLike(long mid,String email) {

		return mdao.deleteMovieLike(mid, email);
	}

	@Override
	@Transactional
	public Double registerRating(RatingVO rtvo) {
		if(mdao.selectOneMovieRating(rtvo.getMid(), rtvo.getEmail()) == null) {
			mdao.insertMovieRating(rtvo);
		}
		return mdao.selectMovieAvgRating(rtvo.getMid());
	}

	@Override
	@Transactional
	public Double modifyRating(RatingVO rtvo) {
		mdao.updateMovieRating(rtvo);
		return mdao.selectMovieAvgRating(rtvo.getMid());
	}

	@Override
	@Transactional
	public Double deleteRating(long mid, String email) {
		mdao.deleteMovieRating(mid, email);
		return mdao.selectMovieAvgRating(mid);
	}

	@Override
	public List<MovieVO> getLikeRankList() {
		
		return mdao.selectListLikeRankMovie();
	}

	@Override
	public List<MovieVO> getRatingRankList() {
		
		return mdao.selectListRatingRankMovie();
	}

}
