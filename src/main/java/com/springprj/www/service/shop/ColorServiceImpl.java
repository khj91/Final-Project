package com.springprj.www.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ColorVO;
import com.springprj.www.repository.shop.ColorDAO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ColorServiceImpl implements ColorService {
	
	
	@Inject
	private ColorDAO cdao;
	
	@Override
	public int register(ColorVO cvo) {
		return cdao.insertColor(cvo);
	}


	@Override
	public int remove(long sid) {
		return cdao.deleteColor(sid);
	}

	@Override
	public List<ColorVO> getList() {
		return cdao.selectListColor();
	}

	@Override
	public int getTotalCount(PagingVO pagingVO) {
		return cdao.selectOneTotalCount(pagingVO);
	}


	@Override
	public List<ColorVO> getList(PagingVO pgvo) {
		return cdao.selectListColorPaging(pgvo);
	}

}
