package com.springprj.www.service.mBoard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MHeartVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.repository.mBoard.MHeartDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MHeartServiceImpl implements MHeartService {
	@Inject
	private MHeartDAO mhdao;
	
	@Override
	public int register(MHeartVO mhvo) {
		mhdao.heartCheck(mhvo.getMbId(), mhvo.getEmail());
		return mhdao.insertMHeart(mhvo);
	}

	@Override
	public PagingHandler getList(long mbId, PagingVO pgvo) {
		int totalCount = mhdao.selectOneTotalCount(mbId);
		List<MHeartVO> list = mhdao.selectListMHeart(mbId, pgvo);
		PagingHandler phd = new PagingHandler(pgvo, list, totalCount);
		return phd;
	}

	@Override
	public int removeOne(long mbId, String authEmail) {
		mhdao.heartCheck(mbId, authEmail);
		return mhdao.deleteOneMHeart(mbId, authEmail);
	}

	@Override
	public int removeAll(long mbId) {
		return mhdao.deleteAllMHeart(mbId);
	}
}
