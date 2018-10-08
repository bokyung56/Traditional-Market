package com.ktds.traditionalmarket.board.reply.service;

import java.util.List;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

public interface BoardReplyService {
	// 댓글 작성
	public boolean createOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 댓글 삭제
	public boolean deleteOneBoardReply( String boardReplyId );
	
	// 댓글 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies(String boardId);
}
