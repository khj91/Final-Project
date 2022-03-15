package com.springprj.www.service.tvBoard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.repository.tvBoard.TvBoardDAO;
import com.springprj.www.repository.tvBoard.TvCommentDAO;
import com.springprj.www.repository.tvBoard.TvHeartDAO;
import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvBoardDTO;
import com.springprj.www.domain.tvBoard.TvBoardVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TvBoardServiceImpl implements TvBoardService {
	@Inject
	private TvBoardDAO tvbdao;
	
	@Inject
	private TvCommentDAO tvcdao;
	
	@Inject
	private TvHeartDAO tvhdao;
	
	@Override
	public int register(TvBoardVO tvbvo) {
		return tvbdao.insertTvBoard(tvbvo);
	}

	@Override
	public int modify(TvBoardVO tvbvo) {
		tvbdao.updateTvBoardReadCount(tvbvo.getTvbId(), -2);
		return tvbdao.updateTvBoard(tvbvo);
	}

	@Override
	public int remove(long tvbId) {
		tvcdao.deleteAllTvComment(tvbId);
		tvhdao.deleteAllTvHeart(tvbId);
		return tvbdao.deleteTvBoard(tvbId);
	}

	@Override
	public List<TvBoardVO> getLikeList(PagingVO pgvo) {
		return tvbdao.selectLikeListTvBoardPaging(pgvo);
	}

	@Override
	public List<TvBoardVO> getHateList(PagingVO pgvo) {
		return tvbdao.selectHateListTvBoardPaging(pgvo);
	}

	@Override
	public TvBoardDTO getDetail(long tvbId, String authEmail) {
		tvbdao.updateTvBoardReadCount(tvbId, 1);
		log.debug(">>> detail {}", tvbdao.selectOneTvBoard(tvbId));
		TvBoardVO tvbvo = tvbdao.selectOneTvBoard(tvbId);
		int check = tvhdao.heartCheck(tvbId, authEmail);
		TvBoardDTO tvbdto = new TvBoardDTO(tvbvo, check);
		log.debug(">>> tvbdto {}", tvbdto);
		return tvbdto;
	}

	@Override
	public int getLikeTotalCount(PagingVO pgvo) {
		return tvbdao.selectOneLikeTotalCount(pgvo);
	}

	@Override
	public int getHateTotalCount(PagingVO pgvo) {
		return tvbdao.selectOneHateTotalCount(pgvo);
	}
	
	@Override
	public List<TvBoardVO> getRelatedTvList(PagingVO pgvo) {
		return tvbdao.relatedTvList(pgvo);
	}

	@Override
	public int getRelatedTotalCount(int tvid, PagingVO pgvo) {
		return tvbdao.selectOneRelatedTotalCount(tvid, pgvo);
	}

}
