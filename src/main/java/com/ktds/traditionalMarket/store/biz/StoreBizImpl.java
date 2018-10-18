package com.ktds.traditionalmarket.store.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.store.dao.StoreDao;
import com.ktds.traditionalmarket.store.vo.StoreVO;

@Component
public class StoreBizImpl implements StoreBiz{

	@Autowired
	private StoreDao storeDao;

	// 하나의 재래시장에서 하나의 Store 가져오기
	@Override
	public StoreVO readOneStore(String storeId) {
		
		return this.storeDao.selectOneStore(storeId);
	}

	// 하나의 재래시장에서 여러개 Store 가져오기
	@Override
	public List<StoreVO> readAllStores(String marketId) {
		
		return this.storeDao.selectAllStores(marketId);
	}
	


}
