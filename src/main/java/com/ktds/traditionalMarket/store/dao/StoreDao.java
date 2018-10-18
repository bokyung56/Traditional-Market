package com.ktds.traditionalmarket.store.dao;

import java.util.List;

import com.ktds.traditionalmarket.store.vo.StoreVO;

public interface StoreDao {
	// 하나의 재래시장에서 하나의 Store 가져오기
	public StoreVO selectOneStore(String storeId);
	
	// 하나의 재래시장에서 여러개 Store 가져오기
	public List<StoreVO> selectAllStores(String marketId);

}
