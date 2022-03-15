package com.springprj.www.service.notice;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.notice.NoticeVO;
import com.springprj.www.repository.notice.NoticeDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NoticeServiceImpl implements NoticeService {
	@Inject
	private NoticeDAO ndao;
	
	@Override
	public int register(NoticeVO nvo) {
		return ndao.insertNotice(nvo);
	}

	@Override
	public List<NoticeVO> getList(PagingVO pgvo) {
		return ndao.selectListNotice(pgvo);
	}

	@Override
	public NoticeVO getDetail(long nid) {
		ndao.updateNoticeReadCount(nid, 1);
		return ndao.selectOneNotice(nid);
	}

	@Override
	public int modify(NoticeVO nvo) {
		ndao.updateNoticeReadCount(nvo.getNid(), -2);
		return ndao.updateNotice(nvo);
	}

	@Override
	public int remove(long nid) {
		return ndao.deleteNotice(nid);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return ndao.selectOneTotalCount(pgvo);
	}

}
