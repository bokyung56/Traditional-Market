package com.ktds.traditionalmarket.board.reply.biz;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.reply.dao.BoardReplyDao;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

@Component
public class BoardReplyBizImpl implements BoardReplyBiz{

	@Autowired
	private BoardReplyDao boardReplyDao;
	
	@Override
	public boolean createOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return this.boardReplyDao.insertOneBoardReply(boardReplyVO) > 0;
	}

	// 그 게시글안에 있는 댓글 전체 삭제
	@Override
	public boolean deleteOneBoardReplies(String boardId) {
		
		return this.boardReplyDao.deleteOneBoardReplies(boardId) > 0;
	}
	
	// 하나의 댓글 삭제
	@Override
	public boolean deleteOneBoardReply(String boardReplyId) {
		
		return this.boardReplyDao.deleteOneBoardReply(boardReplyId) > 0;
	}

	@Override
	public List<BoardReplyVO> selectAllBoardReplies(String boardId) {
		
		return this.boardReplyDao.selectAllBoardReplies(boardId);
	}

	// 댓글 졸아요
	@Override
	public boolean insertOneBoardReplyGood(Map<String, String> goodVO) {

		return this.boardReplyDao.insertOneBoardReplyGood(goodVO) > 0;
	}

	// 댓글 싫어요
	@Override
	public boolean insertOneBoardReplyBad(Map<String, String> badVO) {
	
		return this.boardReplyDao.insertOneBoardReplyBad(badVO) > 0;
	}	
}
