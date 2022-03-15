package com.springprj.www.service.mBoard;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MBoardDTO;
import com.springprj.www.domain.mBoard.MBoardVO;

public interface MBoardService {
	int register(MBoardVO mbvo);
	int modify(MBoardVO mbvo);
	int remove(long mbId);
	List<MBoardVO> getLikeList(PagingVO pgvo);
	List<MBoardVO> getHateList(PagingVO pgvo);
	MBoardDTO getDetail(long mbId, String authEmail);
	int getLikeTotalCount(PagingVO pgvo);
	int getHateTotalCount(PagingVO pgvo);
	List<MBoardVO> getRelatedMovieList(PagingVO pgvo);
	int getRelatedTotalCount(int mid, PagingVO pgvo);
}