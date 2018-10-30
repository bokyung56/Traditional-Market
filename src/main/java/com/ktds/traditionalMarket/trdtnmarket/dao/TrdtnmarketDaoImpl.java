package com.ktds.traditionalmarket.trdtnmarket.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Repository
public class TrdtnmarketDaoImpl extends SqlSessionDaoSupport implements TrdtnmarketDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	// 하나의 재래시장 안 여러상점들
	@Override
	public TrdtnmarketVO selectOneTrdtnMarket(String marketId) {
		
		return getSqlSession().selectOne("TrdtnmarketDao.selectOneTrdtnMarket", marketId);
	}

	// 전국에 있는 재래시장들 위치
	@Override
	public List<TrdtnmarketVO> selectAllTrdtnMarkets() {
		
		return getSqlSession().selectList("TrdtnmarketDao.selectAllTrdtnMarkets");
	}

	// 재래시장 조회수 
	@Override
	public int updateTrdtnViewCount(String marketId) {
	
		return getSqlSession().update("TrdtnmarketDao.updateTrdtnViewCount", marketId);
	}

	
	// 조회수순으로 인기 재래시장명 순위 
	/*@Override
	public List<String> selectTenTrdtnName() {
		
		return getSqlSession().selectList("TrdtnmarketDao.selectTenTrdtnName");
	}*/
	
}
