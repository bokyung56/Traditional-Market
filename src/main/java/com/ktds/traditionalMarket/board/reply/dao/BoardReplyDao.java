package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.board.reply.vo.BadVO;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.board.reply.vo.GoodVO;

public interface BoardReplyDao {
	
	// 댓글 작성
	public int insertOneBoardReply( BoardReplyVO boardReplyVO );
	
	// 해당 게시글에 로그인한 유저가 쓴 댓글이 있는지 확인하기
	public BoardReplyVO selectOneMemberReplies( BoardReplyVO boardReplyVO );
	
	// 그 게시글안에 있는 댓글 전체 삭제
	public int deleteOneBoardReplies( String boardId );
	
	// 하나의 댓글 삭제
	public int deleteOneBoardReply( String boardReplyId );
	
	// 댓글들 읽어오기
	public List<BoardReplyVO> selectAllBoardReplies( String boardId );
	
	
	// 댓글 좋아요
	public int insertOneBoardReplyGood( Map<String, String> goodVO );
	
	// 댓글 싫어요
	public int insertOneBoardReplyBad( Map<String, String> badVO );
	
	// 댓글 좋아요 수
	public int selectOneBoardReplyGoodCount( String boardReplyId );
	
	// 댓글 싫어요 수
	public int selectOneBoardReplyBadCount( String boardReplyId );
	
	// 해당 댓글에 좋아요를 한 회원이 다시 취소하기
	public int deleteOneBoardReplyGood( Map<String, String> goodVO );
	
	// 해당 댓글에 싫어요를 한 회원이 다시 취소하기
	public int deleteOneBoardReplyBad( Map<String, String> badVO );
	
	// 해당 댓글의 좋아요를 한 회원 검색
	public GoodVO selectOneBoardReplyGood( Map<String, String> goodVO );
		
	// 해당 댓글의 싫어요 한 회원 검색
	public BadVO selectOneBoardReplyBad( Map<String, String> badVO );
	
}
