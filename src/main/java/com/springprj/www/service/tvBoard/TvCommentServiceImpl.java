package com.springprj.www.service.tvBoard;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvCommentVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.repository.tvBoard.TvBoardDAO;
import com.springprj.www.repository.tvBoard.TvCommentDAO;

@Service
public class TvCommentServiceImpl implements TvCommentService {
	@Inject
	private TvCommentDAO tvcdao;
	
	@Inject
	private TvBoardDAO tvbdao;
	
	
	@Override
	public int register(TvCommentVO tvcvo) {
		int isUp = tvcdao.insertTvComment(tvcvo);
		tvbdao.updateTvBoardCmtQty(tvcvo.getTvbId(), 1);
		return isUp;
	}

	@Override
	public PagingHandler getList(long tvbId, PagingVO pgvo) {
		return new PagingHandler(pgvo, tvcdao.selectOneTvCommentTotalCount(tvbId), tvcdao.selectListTvComment(tvbId, pgvo));
	}

	@Override
	public int modify(TvCommentVO tvcvo) {
		return tvcdao.updateTvComment(tvcvo);
	}

	@Override
	public int removeOne(long tvcId) {
		long tvbId = tvcdao.selectOneTvComment(tvcId);
		int isUp = tvcdao.deleteOneTvComment(tvcId);
		tvbdao.updateTvBoardCmtQty(tvbId, -1);
		return isUp;
	}
	
	@Override
	public int removeAll(long tvbId) {
		int isUp = tvcdao.deleteAllTvComment(tvbId);
		return isUp;
	}
}
