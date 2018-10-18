package com.ktds.traditionalmarket.store.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.store.vo.StoreVO;

@Repository
public class StoreDaoImpl extends SqlSessionDaoSupport implements StoreDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	// 하나의 재래시장에서 하나의 Store 가져오기
	@Override
	public StoreVO selectOneStore(String storeId) {
		
		return getSqlSession().selectOne("StoreDao.selectOneStore", storeId);
	}
	
	// 하나의 재래시장에서 여러개 Store 가져오기
	@Override
	public List<StoreVO> selectAllStores(String marketId) {
		
		return getSqlSession().selectList("StoreDao.selectAllStores", marketId);
	}
	
}
