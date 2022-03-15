package com.springprj.www.repository.shop;

import java.util.List;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ShopVO;


public interface ShopDAO {
	int insertShop(ShopVO svo);
	List<ShopVO> selectListShop();
	int deleteShop(long sid);
	int selectOneTotalCount(PagingVO pagingVO);
	List<ShopVO> selectListShopPaging(PagingVO pagingVO);
	ShopVO selectOneShop(long sid);
}
