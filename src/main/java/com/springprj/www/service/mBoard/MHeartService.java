package com.springprj.www.service.mBoard;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MHeartVO;
import com.springprj.www.handler.PagingHandler;

public interface MHeartService {
	int register(MHeartVO mhvo);
	PagingHandler getList(long mbId, PagingVO pgvo);
	int removeOne(long mbId, String authEmail);
	int removeAll(long mbId);
}
