package com.ktds.traditionalmarket.store.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

@Repository
public class StoreReplyDaoImpl extends SqlSessionDaoSupport implements StoreReplyDao{

	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	// Store 댓글 작성
	@Override
	public int insertOneStoreReply(StoreReplyVO storeReplyVO) {
		
		return getSqlSession().insert("StoreReplyDao.insertOneStoreReply", storeReplyVO);
	}

	// Store 하나의 댓글 삭제여부
	@Override
	public int updateDeleteStoreOneReply(String storeReplyId) {
		
		return getSqlSession().update("StoreReplyDao.updateDeleteStoreOneReply", storeReplyId);
	}

	// 하나의 Store당 전체댓글들 조회
	@Override
	public List<StoreReplyVO> selectAllStoreReplies(String storeId) {
		
		return getSqlSession().selectList("StoreReplyDao.selectAllStoreReplies", storeId);
	}

	// 하나의 Store의 총점
	@Override
	public double selectOneStoreRating(String storeId) {
		
		return getSqlSession().selectOne("StoreReplyDao.selectOneStoreRating", storeId);
	}
	
	
	
}
