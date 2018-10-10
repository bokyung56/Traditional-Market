package com.ktds.traditionalmarket.board.reply.service;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

public interface BoardReplyService {
	// 댓글 작성
	public boolean createOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 댓글들 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies(String boardId);
	
	// 하나의 댓글 삭제
	public boolean deleteOneBoardReply( String boardReplyId );
	
	// 댓글 졸아요
	public boolean insertOneBoardReplyGood( Map<String, String> goodVO);
	
	// 댓글 싫어요
	public boolean insertOneBoardReplyBad( Map<String, String> badVO);
}
