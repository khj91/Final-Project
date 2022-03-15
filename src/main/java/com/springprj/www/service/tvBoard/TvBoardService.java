package com.springprj.www.service.tvBoard;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MBoardVO;
import com.springprj.www.domain.tvBoard.TvBoardDTO;
import com.springprj.www.domain.tvBoard.TvBoardVO;

public interface TvBoardService {
	int register(TvBoardVO tvbvo);
	int modify(TvBoardVO tvbvo);
	int remove(long tvbId);
	List<TvBoardVO> getLikeList(PagingVO pgvo);
	List<TvBoardVO> getHateList(PagingVO pgvo);
	TvBoardDTO getDetail(long tvbId, String authEmail);
	int getLikeTotalCount(PagingVO pgvo);
	int getHateTotalCount(PagingVO pgvo);
	List<TvBoardVO> getRelatedTvList(PagingVO pgvo);
	int getRelatedTotalCount(int tvid, PagingVO pgvo);
}
