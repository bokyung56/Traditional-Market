package com.ktds.traditionalmarket.store.reply.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.store.reply.biz.StoreReplyBiz;
import com.ktds.traditionalmarket.store.reply.vo.StoreReplyVO;

@Service
public class StoreReplyServiceImpl implements StoreReplyService{
	
	@Autowired
	private StoreReplyBiz storeReplyBiz;

	// Store 댓글 작성
	@Override
	public boolean createOneStoreReply(StoreReplyVO storeReplyVO) {
		
		return this.storeReplyBiz.createOneStoreReply(storeReplyVO);
	}

	// Store 하나의 댓글 삭제
	@Override
	public boolean updateDeleteStoreOneReply(String storeReplyId) {
		
		return this.storeReplyBiz.updateDeleteStoreOneReply(storeReplyId);
	}

	// 하나의 Store당 전체댓글들 조회
	@Override
	public List<StoreReplyVO> readAllStoreReplies(String storeId) {
		
		return this.storeReplyBiz.readAllStoreReplies(storeId);
	}

}
