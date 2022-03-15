package com.springprj.www.repository.mBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MHeartVO;

public interface MHeartDAO {
	int insertMHeart(MHeartVO mhvo);
	List<MHeartVO> selectListMHeart(@Param("mbId") long mbId, @Param("pgvo") PagingVO pgvo);
	int deleteOneMHeart(@Param("mbId") long mbId, @Param("authEmail") String authEmail);
	int deleteAllMHeart(long mbId);
	int selectOneTotalCount(long mbId);
	int heartCheck(@Param("mbId") long mbId, @Param("authEmail") String authEmail);
}
