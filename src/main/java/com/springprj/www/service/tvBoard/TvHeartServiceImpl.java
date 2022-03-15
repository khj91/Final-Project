package com.springprj.www.service.tvBoard;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvHeartVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.repository.tvBoard.TvHeartDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TvHeartServiceImpl implements TvHeartService {
	@Inject
	private TvHeartDAO tvhdao;
	
	@Override
	public int register(TvHeartVO tvhvo) {
		tvhdao.heartCheck(tvhvo.getTvbId(), tvhvo.getEmail());
		return tvhdao.insertTvHeart(tvhvo);
	}

	@Override
	public PagingHandler getList(long tvbId, PagingVO pgvo) {
		int totalCount = tvhdao.selectOneTotalCount(tvbId);
		List<TvHeartVO> list = tvhdao.selectListTvHeart(tvbId, pgvo);
		PagingHandler phd = new PagingHandler(totalCount, list, pgvo);
		return phd;
	}

	@Override
	public int removeOne(long tvbId, String authEmail) {
		tvhdao.heartCheck(tvbId, authEmail);
		return tvhdao.deleteOneTvHeart(tvbId, authEmail);
	}

	@Override
	public int removeAll(long tvbId) {
		return tvhdao.deleteAllTvHeart(tvbId);
	}
}
