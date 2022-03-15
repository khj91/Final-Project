package com.springprj.www.service.rank;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.BoardRankVO;
import com.springprj.www.domain.CommentRankVO;
import com.springprj.www.domain.PointRankVO;
import com.springprj.www.repository.rank.RankDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RankServiceImpl implements RankService {
	
	@Inject
	private RankDAO rdao;
	
	@Override
	public List<PointRankVO> getPointList() {
		return rdao.selectPointList();
	}

	@Override
	public List<PointRankVO> getPointLowList() {
		return rdao.selectPointLowList();
	}

	@Override
	public List<BoardRankVO> getMreadcountList() {
		return rdao.selectMReadCountList();
	}

	@Override
	public List<BoardRankVO> getTreadcountList() {
		return rdao.selectTReadCountList();
	}

	@Override
	public List<CommentRankVO> getTCommentList() {
		return rdao.selectMCommentList();
	}

	@Override
	public List<CommentRankVO> getMCommentList() {
		return rdao.selectTCommentList();
	}

}
