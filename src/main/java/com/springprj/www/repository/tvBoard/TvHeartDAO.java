package com.springprj.www.repository.tvBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvHeartVO;

public interface TvHeartDAO {
	int insertTvHeart(TvHeartVO tvhvo);
	List<TvHeartVO> selectListTvHeart(@Param("tvbId") long tvbId, @Param("pgvo") PagingVO pgvo);
	int deleteOneTvHeart(@Param("tvbId") long tvbId, @Param("authEmail") String authEmail);
	int deleteAllTvHeart(long tvbId);
	int selectOneTotalCount(long tvbId);
	int heartCheck(@Param("tvbId") long tvbId, @Param("authEmail") String authEmail);
}
