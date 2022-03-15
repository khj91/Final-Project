package com.springprj.www.service.shop;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ShopVO;

public interface ShopService {
	int register(ShopVO svo);
	List<ShopVO> getList(PagingVO pgvo);
	List<ShopVO> getList();
	int remove(long sid);
	int getTotalCount(PagingVO pgvo);
	ShopVO getDetail(long sid);
}
