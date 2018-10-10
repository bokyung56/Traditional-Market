package com.ktds.traditionalmarket.board.reply.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.board.reply.biz.BoardReplyBiz;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

@Service
public class BoardReplyServiceImpl implements BoardReplyService{
	
	@Autowired
	private BoardReplyBiz boardReplyBiz;

	// 댓글 작성
	@Override
	public boolean createOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return this.boardReplyBiz.createOneBoardReply(boardReplyVO);
	}

	// 댓글들 읽어오기
	@Override
	public List<BoardReplyVO> selectAllBoardReplies(String boardId) {

		return this.boardReplyBiz.selectAllBoardReplies(boardId);
	}

	// 하나의 댓글 삭제
	@Override
	public boolean deleteOneBoardReply(String boardReplyId) {
		
		return this.boardReplyBiz.deleteOneBoardReply(boardReplyId);
	}

	// 댓글 좋아요
	@Override
	public boolean insertOneBoardReplyGood(Map<String, String> goodVO) {
		
		return this.boardReplyBiz.insertOneBoardReplyGood(goodVO);
	}

	// 댓글 싫어요
	@Override
	public boolean insertOneBoardReplyBad(Map<String, String> badVO) {
		
		return this.boardReplyBiz.insertOneBoardReplyBad(badVO);
	}
	
	
	
}
