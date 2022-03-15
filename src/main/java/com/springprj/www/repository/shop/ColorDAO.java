package com.springprj.www.repository.shop;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ColorVO;

public interface ColorDAO {
	int insertColor(ColorVO cvo);
	List<ColorVO> selectListColor();
	int deleteColor(long sid);
	int selectOneTotalCount(PagingVO pagingVO);
	List<ColorVO> selectListColorPaging(PagingVO pagingVO);
}
