package com.ktds.traditionalmarket.trdtnmarket.service;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

public interface TrdtnmarketService {
	
	// 하나의 재래시장안 여러 상점들
	public TrdtnmarketVO readOneTrdtnMarket(String marketId);
	

	// 전국에 있는 재래시장들 위치
	public List<TrdtnmarketVO> readAllTrdtnMarkets();
}
