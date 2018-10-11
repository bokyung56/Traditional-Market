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
/*		List<BoardReplyVO> boardReplyList = this.boardReplyBiz.selectAllBoardReplies(boardId);
		
		for (BoardReplyVO boardReplyVO : boardReplyList ) {
			int goodCount = this.boardReplyBiz.selectOneBoardReplyGoodCount(boardReplyVO.getBoardReplyId());
			int badCount = this.boardReplyBiz.selectOneBoardReplyBadCount(boardReplyVO.getBoardReplyId());
			System.out.println("출력 " + goodCount);
			boardReplyVO.setGoodCount(goodCount);
			boardReplyVO.setBadCount(badCount);			
		}*/
		//return boardReplyList;
		
		return this.boardReplyBiz.readAllBoardReplies(boardId);
	}

	// 하나의 댓글 삭제
	@Override
	public boolean deleteOneBoardReply(String boardReplyId) {
		
		return this.boardReplyBiz.deleteOneBoardReply(boardReplyId);
	}

	// 댓글 좋아요
	@Override
	public boolean createOneBoardReplyGood( String boardReplyId, String memberId ) {
		
		boolean isFail = this.boardReplyBiz.readOneBoardReplyBad(boardReplyId, memberId);	// 싫어요를 이미 했는지 회원검색. 했으면 true
	
		
		if ( isFail ) {		// 이미 회원이 싫어요를 눌렀다면 
			System.out.println("serviceImpl 이미 회원님은 싫어요를 누르셨습니다. BadisFail= " + isFail + " , false");
			return false;
		}
		else {				// 회원이 좋아요 or 싫어요 아무것도 안 누른 경우
			System.out.println("serviceImpl 회원님이 좋아요를 눌렀습니다. BadisFail= " + isFail + " , true");
			return this.boardReplyBiz.createOneBoardReplyGood(boardReplyId, memberId);
		}
	}

	// 댓글 싫어요
	@Override
	public boolean createOneBoardReplyBad( String boardReplyId, String memberId ) {
		
		boolean isFail = this.boardReplyBiz.readOneBoardReplyGood(boardReplyId, memberId); // 좋아요를 이미 했는지 회원검색. 했으면 true
		
		
		if ( isFail ) {		// 이미 회원이 좋아요를 눌렀다면 
			System.out.println("serviceImpl 이미 회원님은 좋아요를 누르셨습니다. GoodisFail= "+ isFail + " , false");
			return false;
		}
		else {				// 회원이 좋아요 or 싫어요 아무것도 안 누른 경우
			System.out.println("serviceImpl 회원님이 싫어요를 누르셨습니다. GoodisFail= " + isFail + " , true");
			return this.boardReplyBiz.createOneBoardReplyBad(boardReplyId, memberId);
		}
	}

	// 댓글 졸아요 수
	@Override
	public int readOneBoardReplyGoodCount(String boardReplyId) {
		
		return this.boardReplyBiz.readOneBoardReplyGoodCount(boardReplyId);
	}

	// 댓글 싫어요 수
	@Override
	public int readOneBoardReplyBadCount(String boardReplyId) {
		
		return this.boardReplyBiz.readOneBoardReplyBadCount(boardReplyId);
	}

	// 해당 댓글에 좋아요를 한 회원이 다시 취소하기
	@Override
	public boolean deleteOneBoardReplyGood( String boardReplyId, String memberId ) {
		
		return this.boardReplyBiz.deleteOneBoardReplyGood(boardReplyId, memberId);
	}

	// 해당 댓글에 싫어요를 한 회원이 다시 취소하기
	@Override
	public boolean deleteOneBoardReplyBad( String boardReplyId, String memberId ) {
		
		return this.boardReplyBiz.deleteOneBoardReplyBad(boardReplyId, memberId);
	}	
}
