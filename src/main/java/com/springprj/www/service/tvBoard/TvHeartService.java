package com.springprj.www.service.tvBoard;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvHeartVO;
import com.springprj.www.handler.PagingHandler;

public interface TvHeartService {
	int register(TvHeartVO tvhvo);
	PagingHandler getList(long tvbId, PagingVO pgvo);
	int removeOne(long tvbId, String authEmail);
	int removeAll(long tvbId);
}
