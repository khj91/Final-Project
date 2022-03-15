package com.springprj.www.service.shop;

import java.util.List;

import com.springprj.www.domain.shop.ProductVO;

import lombok.extern.slf4j.Slf4j;


public interface PurchaseService {
	int buyProduct(ProductVO pdvo);
	List<ProductVO> getUsersPosterList(String email);
	List<ProductVO> getUsersFontColorList(String email);
	List<ProductVO> getUsersAllProduct(String email);
	int removeProduct(long sid);
	int removeAllProduct(String email);
 }
