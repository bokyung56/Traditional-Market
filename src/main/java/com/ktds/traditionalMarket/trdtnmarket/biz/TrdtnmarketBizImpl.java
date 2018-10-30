package com.ktds.traditionalmarket.trdtnmarket.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.trdtnmarket.dao.TrdtnmarketDao;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Component
public class TrdtnmarketBizImpl implements TrdtnmarketBiz{

	@Autowired
	private TrdtnmarketDao trdtnmarketDao;
	
	// 하나의 재래시장 위치
	@Override
	public TrdtnmarketVO readOneTrdtnMarket( String marketId ) {
		
		// 재래시장 조회수 
		this.trdtnmarketDao.updateTrdtnViewCount(marketId);
		
		return this.trdtnmarketDao.selectOneTrdtnMarket(marketId);
	}

	// 전국에 있는 재래시장들 위치
	@Override
	public List<TrdtnmarketVO> readAllTrdtnMarkets() {
		
		return this.trdtnmarketDao.selectAllTrdtnMarkets();
	}

	// 조회수순으로 인기 재래시장명 순위 
	/*@Override
	public List<String> readTenTrdtnName() {
		
		return this.trdtnmarketDao.selectTenTrdtnName();
	}*/

}
