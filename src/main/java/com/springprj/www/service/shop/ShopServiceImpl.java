package com.springprj.www.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ShopVO;
import com.springprj.www.repository.shop.ShopDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService{
	
	@Inject
	private ShopDAO sdao;
	
	@Override
	public int register(ShopVO svo) {
		return sdao.insertShop(svo);
	}

	@Override
	public List<ShopVO> getList(PagingVO pgvo) {
		return sdao.selectListShopPaging(pgvo);
	}

	@Override
	public int remove(long sid) {
		return sdao.deleteShop(sid);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		return sdao.selectOneTotalCount(pgvo);
	}

	@Override
	public ShopVO getDetail(long sid) {
		return sdao.selectOneShop(sid);
	}

	@Override
	public List<ShopVO> getList() {
		return sdao.selectListShop();
	}

}
