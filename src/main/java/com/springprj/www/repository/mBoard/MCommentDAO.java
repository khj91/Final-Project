package com.springprj.www.repository.mBoard;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MCommentVO;

public interface MCommentDAO {
	int insertMComment(MCommentVO mcvo);
	List<MCommentVO> selectListMComment(@Param("mbId") long mbId, @Param("pgvo") PagingVO pgvo);
	int updateMComment(MCommentVO mcvo);
	int deleteOneMComment(long mcId);
	int deleteAllMComment(long mbId);
	long selectOneMComment(long mcId);
	int selectOneMCommentTotalCount(long mbId);
}
