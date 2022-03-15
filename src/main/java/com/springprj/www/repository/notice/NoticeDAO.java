package com.springprj.www.repository.notice;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.notice.NoticeVO;

public interface NoticeDAO {
	int insertNotice(NoticeVO nvo);
	List<NoticeVO> selectListNotice(PagingVO pgvo);
	int updateNotice(NoticeVO nvo);
	NoticeVO selectOneNotice(long nid);
	int deleteNotice(long nid);
	int selectOneTotalCount(PagingVO pgvo);
	void updateNoticeReadCount(@Param("nid") long nid, @Param("cnt") int cnt);
}
