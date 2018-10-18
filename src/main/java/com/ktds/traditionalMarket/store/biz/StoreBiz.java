package com.ktds.traditionalmarket.store.biz;

import java.util.List;

import com.ktds.traditionalmarket.store.vo.StoreVO;

public interface StoreBiz {

	// 하나의 재래시장에서 하나의 Store 가져오기
	public StoreVO readOneStore(String storeId);
	
	// 하나의 재래시장에서 여러개 Store 가져오기
	public List<StoreVO> readAllStores(String marketId);
	
}
