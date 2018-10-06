package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

public interface BoardReplyDao {
	
	// 댓글 작성
	public int insertOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 댓글 삭제
	public int deleteOneBoardReply( String boardReplyId );
	
	// 댓글들 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies( String boardReplyId );
	
}
