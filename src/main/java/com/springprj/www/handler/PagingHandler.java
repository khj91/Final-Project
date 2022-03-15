package com.springprj.www.handler;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MCommentVO;
import com.springprj.www.domain.mBoard.MHeartVO;
import com.springprj.www.domain.shop.ColorVO;
import com.springprj.www.domain.shop.ShopVO;
import com.springprj.www.domain.tvBoard.TvCommentVO;
import com.springprj.www.domain.tvBoard.TvHeartVO;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
public class PagingHandler {
	private int startPage;
	private int endPage;
	private boolean prev, next;
	
	private int totalCount;
	private PagingVO pgvo;
	
	private List<ColorVO> cList;
	private List<ShopVO> sList;
	private List<MCommentVO> cmtListM;
	private List<MHeartVO> cmtListMh;
	private List<TvCommentVO> cmtListTv;
	private List<TvHeartVO> cmtListTvh;
	
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		this.endPage = (int)(Math.ceil(pgvo.getPageNo() / (pgvo.getQty()*1.0)))*pgvo.getQty();
		this.startPage = this.endPage - 4;
		int realEndPage = (int)(Math.ceil((totalCount * 1.0)/pgvo.getQty()));
		if(realEndPage < this.endPage) {
			this.endPage = realEndPage;
		}
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
//	public PagingHandler(List<ColorVO> cList, PagingVO pgvo, int totalCount) {
//		this(pgvo, totalCount);
//		this.cList = cList;
//	}
//	public PagingHandler(PagingVO pgvo, int totalCount, List<ShopVO> sList) {
//		this(pgvo, totalCount);
//		this.sList = sList;
//	}
	public PagingHandler(List<MCommentVO> cmtListM, PagingVO pgvo, int totalCount) {
		this(pgvo, totalCount);
		this.cmtListM = cmtListM;
	}
	
	public PagingHandler(PagingVO pgvo, int totalCount, List<TvCommentVO> cmtListTv) {
		 this(pgvo, totalCount);
		 this.cmtListTv = cmtListTv;
	}
	
	public PagingHandler(PagingVO pgvo, List<MHeartVO> cmtListMh, int totalCount) {
		 this(pgvo, totalCount);
		 this.cmtListMh = cmtListMh;
	}
	
	public PagingHandler(int totalCount, List<TvHeartVO> cmtListTvh, PagingVO pgvo) {
		 this(pgvo, totalCount);
		 this.cmtListTvh = cmtListTvh;
	}
}
