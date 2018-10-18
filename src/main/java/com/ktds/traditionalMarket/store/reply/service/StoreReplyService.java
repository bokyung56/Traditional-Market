package com.ktds.traditionalmarket.store.reply.service;

import java.util.List;

import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

public interface StoreReplyService {

	// Store 댓글 작성
	public boolean createOneStoreReply(StoreReplyVO storeReplyVO);
	
	// Store 하나의 댓글 삭제
	public boolean updateDeleteStoreOneReply(String storeReplyId);
	
	// 하나의 Store당 전체댓글들 조회
	public List<StoreReplyVO> readAllStoreReplies(String storeId);
	
	// 하나의 Store의 총점
	//public int readOneStoreRating(String storeId);
	
}
