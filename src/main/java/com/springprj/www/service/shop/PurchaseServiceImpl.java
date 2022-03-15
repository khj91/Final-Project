package com.springprj.www.service.shop;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.springprj.www.domain.shop.ProductVO;
import com.springprj.www.repository.purchase.PurchaseDAO;

@Service
public class PurchaseServiceImpl implements PurchaseService {
	
	@Inject
	private PurchaseDAO pdao;
	
	@Override
	public int buyProduct(ProductVO pdvo) {
		
		return pdao.insertProduct(pdvo);
	}

	@Override
	public List<ProductVO> getUsersPosterList(String email) {

		return pdao.selectListPoster(email);
	}

	@Override
	public List<ProductVO> getUsersFontColorList(String email) {

		return pdao.selectListFontColor(email);
	}

	@Override
	public List<ProductVO> getUsersAllProduct(String email) {

		return pdao.selectListAllProduct(email);
	}

	@Override
	public int removeProduct(long sid) {

		return pdao.deleteProduct(sid);
	}

	@Override
	public int removeAllProduct(String email) {

		return pdao.deleteAllProduct(email);
	}

}
