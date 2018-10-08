package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

public interface BoardReplyDao {
	
	// 댓글 작성
	public int insertOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 해당 게시글에 로그인한 유저가 쓴 댓글이 있는지 확인하기
	public BoardReplyVO selectOneMemberReplies( BoardReplyVO boardReplyVO );
	
	// 댓글 삭제
	public int deleteOneBoardReply( String boardReplyId );
	
	// 댓글들 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies( String boardId );
	
	
}
