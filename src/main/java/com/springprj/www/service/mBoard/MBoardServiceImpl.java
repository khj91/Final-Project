package com.springprj.www.service.mBoard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MBoardDTO;
import com.springprj.www.domain.mBoard.MBoardVO;
import com.springprj.www.repository.mBoard.MBoardDAO;
import com.springprj.www.repository.mBoard.MCommentDAO;
import com.springprj.www.repository.mBoard.MHeartDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MBoardServiceImpl implements MBoardService {
	@Inject
	private MBoardDAO mbdao;
	
	@Inject
	private MCommentDAO mcdao;
	
	@Inject
	private MHeartDAO mhdao;
	
	@Override
	public int register(MBoardVO mbvo) {
		return mbdao.insertMBoard(mbvo);
	}

	@Override
	public int modify(MBoardVO mbvo) {
		mbdao.updateMBoardReadCount(mbvo.getMbId(), -2);
		return mbdao.updateMBoard(mbvo);
	}

	@Override
	public int remove(long mbId) {
		mcdao.deleteAllMComment(mbId);
		mhdao.deleteAllMHeart(mbId);
		return mbdao.deleteMBoard(mbId);
	}

	@Override
	public List<MBoardVO> getLikeList(PagingVO pgvo) {
		return mbdao.selectLikeListMBoardPaging(pgvo);
	}

	@Override
	public List<MBoardVO> getHateList(PagingVO pgvo) {
		return mbdao.selectHateListMBoardPaging(pgvo);
	}

	@Override
	public MBoardDTO getDetail(long mbId, String authEmail) {
		mbdao.updateMBoardReadCount(mbId, 1);
		log.debug(">>> detail {}", mbdao.selectOneMBoard(mbId));
		MBoardVO mbvo = mbdao.selectOneMBoard(mbId);
		int check = mhdao.heartCheck(mbId, authEmail);
		MBoardDTO mbdto = new MBoardDTO(mbvo, check);
		log.debug(">>> mbdto {}", mbdto);
		return mbdto;
	}

	@Override
	public int getLikeTotalCount(PagingVO pgvo) {
		return mbdao.selectOneLikeTotalCount(pgvo);
	}
	
	@Override
	public int getHateTotalCount(PagingVO pgvo) {
		return mbdao.selectOneHateTotalCount(pgvo);
	}

	@Override
	public List<MBoardVO> getRelatedMovieList(PagingVO pgvo) {
		return mbdao.relatedMovieList(pgvo);
	}

	@Override
	public int getRelatedTotalCount(int mid, PagingVO pgvo) {
		return mbdao.selectOneRelatedTotalCount(mid, pgvo);
	}
}
