package com.springprj.www.service.tvBoard;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvCommentVO;
import com.springprj.www.handler.PagingHandler;

public interface TvCommentService {
	int register(TvCommentVO tvcvo);
	PagingHandler getList(long tvbId, PagingVO pgvo);
	int modify(TvCommentVO tvcvo);
	int removeOne(long tvcId);
	int removeAll(long tvbId);
}
