package com.ktds.traditionalmarket.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.product.biz.ProductBiz;
import com.ktds.traditionalmarket.product.vo.ProductVO;
import com.ktds.traditionalmarket.store.biz.StoreBiz;
import com.ktds.traditionalmarket.store.reply.biz.StoreReplyBiz;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;
import com.ktds.traditionalmarket.store.vo.StoreVO;

@Service
public class StoreServiceImpl implements StoreService{
	
	@Autowired
	private StoreBiz storeBiz;
	
	@Autowired
	private StoreReplyBiz storeReplyBiz;
	
	@Autowired
	private ProductBiz productBiz;
	
	
	// 하나의 재래시장에서 하나의 Store 가져오기
	@Override
	public StoreVO readOneStore(String storeId) {
		
		StoreVO storeVO = this.storeBiz.readOneStore(storeId);
		
		double rating = this.storeReplyBiz.readOneStoreRating(storeId);						// 한 상점에 대한 총점평균 
		storeVO.setStoreRating(rating);
		
		List<StoreReplyVO> replisList = this.storeReplyBiz.readAllStoreReplies(storeId);	// 한 상점에 대한 댓글들
		if ( replisList != null ) {
			storeVO.setStoreReplyList(replisList);
		}
		
		List<ProductVO> productList = this.productBiz.readAllProducts(storeId);				// 한 상점에 대한 상품들
		if ( productList != null ) {
			storeVO.setProductList(productList);
		}										
		
		for ( ProductVO product : productList ) {
			System.out.println("***********product.getName()= " + product.getName());
		}
		
		
		return storeVO;
	}


	// 하나의 재래시장에서 여러개 Store 가져오기
	@Override
	public List<StoreVO> readAllStores(String marketId) {
		
		List<StoreVO> storesList = this.storeBiz.readAllStores(marketId);	// 여러개 Store
		
		return storesList;
	}



}
