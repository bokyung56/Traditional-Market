package com.ktds.traditionalmarket.product.dao;

import java.util.List;

import com.ktds.traditionalmarket.product.vo.ProductVO;

public interface ProductDao {
	
	// 하나의 Store에서 여러개의 Products가져오기
	public List<ProductVO> selectAllProducts(String storeId);

}
