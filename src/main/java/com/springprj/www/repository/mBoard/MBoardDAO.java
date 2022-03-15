package com.springprj.www.repository.mBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MBoardVO;

public interface MBoardDAO {
	int insertMBoard(MBoardVO mbvo);
	List<MBoardVO> selectLikeListMBoardPaging(PagingVO pgvo);
	List<MBoardVO> selectHateListMBoardPaging(PagingVO pgvo);
	int updateMBoard(MBoardVO mbvo);
	int deleteMBoard(long mbId);
	MBoardVO selectOneMBoard(long mbId);
	int selectOneLikeTotalCount(PagingVO pgvo);
	int selectOneHateTotalCount(PagingVO pgvo);
	void updateMBoardReadCount(@Param("mbId")long mbId, @Param("cnt")int cnt);
	void updateMBoardCmtQty(@Param("mbId")long mbId, @Param("cnt")int cnt);
	int mBoardHeartCount(long mbId);
	List<MBoardVO> relatedMovieList(PagingVO pgvo);
	int selectOneRelatedTotalCount(@Param("mid")int mid, @Param("pgvo")PagingVO pgvo);
}
