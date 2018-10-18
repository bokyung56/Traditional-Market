package com.ktds.traditionalmarket.store.reply.biz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.store.reply.dao.StoreReplyDao;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

@Component
public class StoreReplyBizImpl implements StoreReplyBiz{

	@Autowired
	private StoreReplyDao storeReplyDao;

	// Store 댓글 작성
	@Override
	public boolean createOneStoreReply(StoreReplyVO storeReplyVO) {
		
		return this.storeReplyDao.insertOneStoreReply(storeReplyVO) > 0;
	}

	// Store 하나의 댓글 삭제여부
	@Override
	public boolean updateDeleteStoreOneReply(String storeReplyId) {
		
		return this.storeReplyDao.updateDeleteStoreOneReply(storeReplyId) > 0;
	}

	// 하나의 Store당 전체댓글들 조회
	@Override
	public List<StoreReplyVO> readAllStoreReplies(String storeId) {
		
		return this.storeReplyDao.selectAllStoreReplies(storeId);
	}
	
	// 하나의 Store의 총점
	@Override
	public double readOneStoreRating(String storeId) {
		
		return this.storeReplyDao.selectOneStoreRating(storeId);
	}
	
	
}
