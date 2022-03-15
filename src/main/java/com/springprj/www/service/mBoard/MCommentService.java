package com.springprj.www.service.mBoard;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MCommentVO;
import com.springprj.www.handler.PagingHandler;

public interface MCommentService {
	int register(MCommentVO mcvo);
	PagingHandler getList(long mbId, PagingVO pgvo);
	int modify(MCommentVO mcvo);
	int removeOne(long mcId);
	int removeAll(long mbId);
}
