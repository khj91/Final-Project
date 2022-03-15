package com.springprj.www.service.mBoard;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MCommentVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.repository.mBoard.MBoardDAO;
import com.springprj.www.repository.mBoard.MCommentDAO;
import com.springprj.www.repository.user.UserDAO;

@Service
public class MCommentServiceImpl implements MCommentService {
	@Inject
	private MCommentDAO mcdao;
	
	@Inject
	private MBoardDAO mbdao;
	
	@Inject
	private UserDAO udao;
	
	
	@Override
	public int register(MCommentVO mcvo) {
		int isUp = mcdao.insertMComment(mcvo);
		mbdao.updateMBoardCmtQty(mcvo.getMbId(), 1);
		return isUp;
	}

	@Override
	public PagingHandler getList(long mbId, PagingVO pgvo) {
		return new PagingHandler(mcdao.selectListMComment(mbId, pgvo), pgvo, mcdao.selectOneMCommentTotalCount(mbId));
	}

	@Override
	public int modify(MCommentVO mcvo) {
		return mcdao.updateMComment(mcvo);
	}

	@Override
	public int removeOne(long mcId) {
		long mbId = mcdao.selectOneMComment(mcId);
		int isUp = mcdao.deleteOneMComment(mcId);
		mbdao.updateMBoardCmtQty(mbId, -1);
		return isUp;
	}

	@Override
	public int removeAll(long mbId) {
		int isUp = mcdao.deleteAllMComment(mbId);
		return isUp;
	}

}
