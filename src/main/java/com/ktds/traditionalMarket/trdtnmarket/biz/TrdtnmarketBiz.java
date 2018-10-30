package com.ktds.traditionalmarket.trdtnmarket.biz;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

public interface TrdtnmarketBiz {
	
	// 하나의 재래시장안 여러 상점들
	public TrdtnmarketVO readOneTrdtnMarket(String marketId);
	
	// 전국에 있는 재래시장들 위치
	public List<TrdtnmarketVO> readAllTrdtnMarkets();
	
	// 조회수순으로 인기 재래시장명 순위 
	/*public List<String> readTenTrdtnName();*/

}
