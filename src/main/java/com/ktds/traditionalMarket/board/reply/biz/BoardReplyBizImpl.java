package com.ktds.traditionalmarket.board.reply.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.reply.dao.BoardReplyDao;
import com.ktds.traditionalmarket.board.reply.vo.BadVO;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.board.reply.vo.GoodVO;

@Component
public class BoardReplyBizImpl implements BoardReplyBiz{

	@Autowired
	private BoardReplyDao boardReplyDao;
	
	@Override
	public boolean createOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return this.boardReplyDao.insertOneBoardReply(boardReplyVO) > 0;
	}

	// 그 게시글안에 있는 댓글 전체 삭제
	//@Override
	//public boolean deleteOneBoardReplies(String boardId) {
		
	//	return this.boardReplyDao.deleteOneBoardReplies(boardId) > 0;
	//}
	
	// 하나의 댓글 삭제
	//@Override
	//public boolean deleteOneBoardReply(String boardReplyId) {
		
	//	return this.boardReplyDao.deleteOneBoardReply(boardReplyId) > 0;
	//}
	
	
	// 전체 댓글 가져오기
	@Override
	public List<BoardReplyVO> readAllBoardReplies(String boardId) {
		List<BoardReplyVO> boardReplyList = this.boardReplyDao.selectAllBoardReplies(boardId);
		
		for (BoardReplyVO boardReplyVO : boardReplyList ) {
			int goodCount = this.boardReplyDao.selectOneBoardReplyGoodCount(boardReplyVO.getBoardReplyId());	// 좋아요 수
			int badCount = this.boardReplyDao.selectOneBoardReplyBadCount(boardReplyVO.getBoardReplyId());		// 싫어요 수
			boardReplyVO.setGoodCount(goodCount);	// boardReplyVO에 있는 변수 goodCount에 좋아요 수 넣어줌
			boardReplyVO.setBadCount(badCount);		// boardReplyVO에 있는 변수 badCount에 싫어요 수 넣어줌
		}
		
		return boardReplyList;
	}

	// 댓글 졸아요
	@Override
	public boolean createOneBoardReplyGood(String boardReplyId, String memberId) {
		Map<String, String> goodParam = new HashMap<>();
		goodParam.put("boardReplyId", boardReplyId);
		goodParam.put("memberId", memberId);
		
		return this.boardReplyDao.insertOneBoardReplyGood(goodParam) > 0;
	}

	// 댓글 싫어요
	@Override
	public boolean createOneBoardReplyBad( String boardReplyId, String memberId ) {
		Map<String, String> badParam = new HashMap<>();
		badParam.put("boardReplyId", boardReplyId);
		badParam.put("memberId", memberId);
		
		return this.boardReplyDao.insertOneBoardReplyBad(badParam) > 0;
	}

	// 댓글 졸아요 수
	@Override
	public int readOneBoardReplyGoodCount(String boardReplyId) {
		return this.boardReplyDao.selectOneBoardReplyGoodCount(boardReplyId);
	}

	// 댓글 싫어요 수
	@Override
	public int readOneBoardReplyBadCount(String boardReplyId) {
		return this.boardReplyDao.selectOneBoardReplyBadCount(boardReplyId);
	}

	// 해당 댓글에 좋아요를 한 회원이 다시 취소하기
	@Override
	public boolean deleteOneBoardReplyGood(String boardReplyId, String memberId) {
		Map<String, String> goodParam = new HashMap<>();
		goodParam.put("boardReplyId", boardReplyId);
		goodParam.put("memberId", memberId);
	
		return this.boardReplyDao.deleteOneBoardReplyGood(goodParam) > 0;
	}

	// 해당 댓글에 싫어요를 한 회원이 다시 취소하기
	@Override
	public boolean deleteOneBoardReplyBad(String boardReplyId, String memberId) {
		Map<String, String> badParam = new HashMap<String, String>();
		badParam.put("boardReplyId", boardReplyId);
		badParam.put("memberId", memberId);
		
		return this.boardReplyDao.deleteOneBoardReplyBad(badParam) > 0;
	}

	// 해당 댓글의 좋아요를 한 회원 검색
	@Override
	public boolean readOneBoardReplyGood(String boardReplyId, String memberId) {
		Map<String, String> goodParam = new HashMap<String, String>();
		goodParam.put("boardReplyId", boardReplyId);
		goodParam.put("memberId", memberId);
		
		GoodVO goodVO = this.boardReplyDao.selectOneBoardReplyGood(goodParam);
		if( goodVO != null ) {
			
			System.out.println("BizImpl 이미 회원님은 좋아요를 하셨습니다. goodVO=" + goodVO+ " , true");
			return true;	// 이미 회원은 해당 댓글에 좋아요를 했음
		}
		else {
			System.out.println("BizImpl 회원님은 좋아요를 누른적이 없습니다. goodVO= " + goodVO + " , false");
			return false;
		}				
	}

	// 해당 댓글의 싫어요 한 회원 검색
	@Override
	public boolean readOneBoardReplyBad(String boardReplyId, String memberId) {
		Map<String, String> badParam = new HashMap<String, String>();
		badParam.put("boardReplyId", boardReplyId);
		badParam.put("memberId", memberId);
		
		BadVO badVO = this.boardReplyDao.selectOneBoardReplyBad(badParam);
		if( badVO != null ) {
			System.out.println("BizImpl 이미 회원님은싫어요를 하셨습니다. badVO= " + badVO+ " , true");
			return true;	// 이미 회원은 해당 댓글에 싫어요를 했음
		}
		else {
			System.out.println("BizImpl 회원님은 싫어요를 누른적이 없습니다. badVO= " + badVO + " , false");
			return false;
		}

	}

	// 한 게시글의 댓글의 여자 수
	@Override
	public int oneBoardWomenCount(String boardReplyId) {
		
		return this.boardReplyDao.oneBoardWomenCount(boardReplyId);
	}

	// 한 게시글의 댓글의 남자 수
	@Override
	public int oneBoardMenCount(String boardReplyId) {
		
		return this.boardReplyDao.oneBoardMenCount(boardReplyId);
	}

	// 하나의 댓글 삭제 여부
	@Override
	public boolean updateDeleteOneReply(String boardReplyId) {
		
		return this.boardReplyDao.updateDeleteOneReply(boardReplyId) > 0;
	}

	
}
