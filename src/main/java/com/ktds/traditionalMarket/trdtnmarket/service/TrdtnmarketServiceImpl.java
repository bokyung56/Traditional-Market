package com.ktds.traditionalmarket.trdtnmarket.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.store.biz.StoreBiz;
import com.ktds.traditionalmarket.store.reply.biz.StoreReplyBiz;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;
import com.ktds.traditionalmarket.store.vo.StoreVO;
import com.ktds.traditionalmarket.trdtnmarket.biz.TrdtnmarketBiz;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Service
public class TrdtnmarketServiceImpl implements TrdtnmarketService{
	
	@Autowired
	private TrdtnmarketBiz trdtnmarketBiz;	// 재래시장

	@Autowired
	private StoreBiz storeBiz;				// 상점
	
	@Autowired
	private StoreReplyBiz storeReplyBiz;	// 하나의 상점에 대한 댓글들
	
	
	// 하나의 재래시장안 여러 상점들
	@Override
	public TrdtnmarketVO readOneTrdtnMarket(String marketId) {
		
		List<StoreVO> storeList = this.storeBiz.readAllStores(marketId);					// 하나의 재래시장 속 여러상점
		
		for (StoreVO storeVO : storeList ) {
			double storeRating = this.storeReplyBiz.readOneStoreRating(storeVO.getStoreId());
			storeVO.setStoreRating(storeRating);
		}
		TrdtnmarketVO trdtnmarketVO = this.trdtnmarketBiz.readOneTrdtnMarket(marketId);		// 하나의 재래시장에 여러상점들을 담아줌
		trdtnmarketVO.setStoreList(storeList);
			
		return trdtnmarketVO;
	}
	
	
	// 전국에 있는 재래시장들 위치
	@Override
	public List<TrdtnmarketVO> readAllTrdtnMarkets() {
		
		return this.trdtnmarketBiz.readAllTrdtnMarkets();
	}
}
