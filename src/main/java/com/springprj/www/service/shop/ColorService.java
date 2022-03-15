package com.springprj.www.service.shop;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ColorVO;

public interface ColorService {
	int register(ColorVO cvo);
	List<ColorVO> getList();
	int remove(long sid);
	int getTotalCount(PagingVO pagingVO);
	List<ColorVO> getList(PagingVO pgvo);
}
