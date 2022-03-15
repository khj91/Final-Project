package com.springprj.www.repository.tvBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvCommentVO;

public interface TvCommentDAO {
	int insertTvComment(TvCommentVO tvcvo);
	List<TvCommentVO> selectListTvComment(@Param("tvbId") long tvbId, @Param("pgvo") PagingVO pgvo);
	int updateTvComment(TvCommentVO tvcvo);
	int deleteOneTvComment(long tvcId);
	int deleteAllTvComment(long tvbId);
	long selectOneTvComment(long tvcId);
	int selectOneTvCommentTotalCount(long tvbId);
}
