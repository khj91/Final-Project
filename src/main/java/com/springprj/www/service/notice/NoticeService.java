package com.springprj.www.service.notice;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.notice.NoticeVO;

public interface NoticeService {
	int register(NoticeVO nvo);
	List<NoticeVO> getList(PagingVO pgvo);
	NoticeVO getDetail(long nid);
	int modify(NoticeVO nvo);
	int remove(long nid);
	int getTotalCount(PagingVO pgvo);
}
