package com.ktds.traditionalmarket.store.reply.dao;

import java.util.List;

import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

public interface StoreReplyDao {
	// Store 댓글 작성
	public int insertOneStoreReply(StoreReplyVO storeReplyVO);
	
	// Store 하나의 댓글 삭제여부
	public int updateDeleteStoreOneReply(String storeReplyId);
	
	// 하나의 Store당 전체댓글들 조회
	public List<StoreReplyVO> selectAllStoreReplies(String storeId);
	
	// 하나의 Store의 총점
	public double selectOneStoreRating(String storeId);
}
