package com.ktds.traditionalmarket.board.reply.biz;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

public interface BoardReplyBiz {
	
	// 댓글 작성
	public boolean createOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 그 게시글안에 있는 댓글 전체 삭제
	public boolean deleteOneBoardReplies( String boardId );
	
	// 하나의 댓글 삭제
	public boolean deleteOneBoardReply( String boardReplyId );
	
	// 댓글 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies(String boardId);
	
	// 댓글 졸아요
	public boolean insertOneBoardReplyGood( Map<String, String> goodVO);
	
	// 댓글 싫어요
	public boolean insertOneBoardReplyBad( Map<String, String> badVO);
	
	// 댓글 졸아요 수
	public int selectOneBoardReplyGoodCount( String boardReplyId);
	
	// 댓글 싫어요 수
	public int selectOneBoardReplyBadCount( String boardReplyId);
	
	
}
