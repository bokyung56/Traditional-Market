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
	//public boolean deleteOneBoardReply( String boardReplyId );
	
	// 댓글 졸아요
	public boolean createOneBoardReplyGood( String boardReplyId, String memberId );
	
	// 댓글 싫어요
	public boolean createOneBoardReplyBad( String boardReplyId, String memberId );
	
	// 댓글 졸아요 수
	public int readOneBoardReplyGoodCount( String boardReplyId);
		
	// 댓글 싫어요 수
	public int readOneBoardReplyBadCount( String boardReplyId);
	
	// 해당 댓글에 좋아요를 한 회원이 다시 취소하기
	public boolean deleteOneBoardReplyGood( String boardReplyId, String memberId );
	
	// 해당 댓글에 싫어요를 한 회원이 다시 취소하기
	public boolean deleteOneBoardReplyBad( String boardReplyId, String memberId );
	
	// 해당 댓글의 좋아요를 한 회원 검색
	//public boolean readOneBoardReplyGood( String boardReplyId, String memberId );
		
	// 해당 댓글의 싫어요 한 회원 검색
	//public boolean readOneBoardReplyBad( String boardReplyId, String memberId );
	
	// 하나의 댓글 삭제 여부
	public boolean updateDeleteOneReply( String boardReplyId );
}
