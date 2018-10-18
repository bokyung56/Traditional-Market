package com.ktds.traditionalmarket.product.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.product.dao.ProductDao;
import com.ktds.traditionalmarket.product.vo.ProductVO;

@Component
public class ProductBizImpl implements ProductBiz{

	@Autowired
	private ProductDao productDao;

	// 하나의 Store에서 여러개의 Products가져오기
	@Override
	public List<ProductVO> readAllProducts(String storeId) {
		
		return this.productDao.selectAllProducts(storeId);
	}
	
	
}
