package com.ktds.traditionalmarket.board.reply.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.board.reply.biz.BoardReplyBiz;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{
	
	@Autowired
	private BoardReplyBiz boardReplyBiz;

	@Override
	public boolean createOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return boardReplyBiz.createOneBoardReply(boardReplyVO);
	}

	@Override
	public boolean deleteOneBoardReply(String boardReplyId) {
		
		return boardReplyBiz.deleteOneBoardReply(boardReplyId);
	}
	
}
