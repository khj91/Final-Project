package com.springprj.www.repository.rank;

import java.util.List;

import com.springprj.www.domain.BoardRankVO;
import com.springprj.www.domain.CommentRankVO;
import com.springprj.www.domain.PointRankVO;

public interface RankDAO {
	List<PointRankVO> selectPointList();
	List<PointRankVO> selectPointLowList();
	List<CommentRankVO> selectMCommentList();
	List<CommentRankVO> selectTCommentList();
	List<BoardRankVO> selectTReadCountList();
	List<BoardRankVO> selectMReadCountList();
}
