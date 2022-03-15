package com.springprj.www.service.rank;

import java.util.List;

import com.springprj.www.domain.BoardRankVO;
import com.springprj.www.domain.CommentRankVO;
import com.springprj.www.domain.PointRankVO;

public interface RankService {
	List<PointRankVO> getPointList();
	List<PointRankVO> getPointLowList();
	List<BoardRankVO> getMreadcountList();
	List<BoardRankVO> getTreadcountList();
	List<CommentRankVO> getTCommentList();
	List<CommentRankVO> getMCommentList();
}
