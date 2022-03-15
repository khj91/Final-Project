package com.springprj.www.repository.purchase;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.springprj.www.domain.shop.ProductVO;

@Repository
public interface PurchaseDAO {
	
	int insertProduct(ProductVO pdvo);
	List<ProductVO> selectListPoster(String email); // 구매한포스터 리스트
	List<ProductVO> selectListFontColor(String email);// 구매한 폰트컬러 리스트
	List<ProductVO> selectListAllProduct(String email); // 구매내역에 표현될 전체 구매 내역
	int deleteProduct(long sid);
	int deleteAllProduct(String email);
}

