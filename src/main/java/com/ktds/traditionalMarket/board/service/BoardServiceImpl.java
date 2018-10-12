package com.ktds.traditionalmarket.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.board.biz.BoardBiz;
import com.ktds.traditionalmarket.board.reply.biz.BoardReplyBiz;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardBiz boardBiz;
	
	@Autowired
	private BoardReplyBiz boardReplyBiz;
	


	// 회원이 글을 작성시, 회원의 point가 +500
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		
		return this.boardBiz.createOneBoard(boardVO);
	}

	// <게시글 하나 읽어오기>
	@Override
	public BoardVO readOneBoard(String boardId) {
		
		// 하나의 게시글 읽어오기
		BoardVO boardVO = this.boardBiz.readOneBoard(boardId);
		
		// 한 게시글안에 있는 여러 댓글들 읽어오기
		List<BoardReplyVO> replyList = this.boardReplyBiz.readAllBoardReplies(boardId);
		if (replyList != null ) {			
			boardVO.setReplyList(replyList);
		}
		
		// 한 게시글당 댓글의 남녀 비율
		int womenCnt = this.boardReplyBiz.oneBoardMenCount(boardId);
		int menCnt = this.boardReplyBiz.oneBoardWomenCount(boardId);
		boardVO.setWomenCnt(womenCnt);
		boardVO.setMenCnt(menCnt);
		
		// 한 게시글 추천수
		int recommendCnt = this.boardBiz.readOneBoardRecommendCount(boardId);
		boardVO.setRecommendCount(recommendCnt);
						
		return boardVO;
	}
	
/*	// 게시글 추천수 증가시키기
	@Override
	public boolean updateRecommendCount(String boardId) {
		
		return boardBiz.updateRecommendCount(boardId);
	}*/
	
	// 게시글 수정하기
	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		
		return this.boardBiz.updateOneBoard(boardVO);
	}

	// 게시글 지우기
	@Override
	public boolean deleteOneBoard(String boardId) {
		
		return this.boardBiz.deleteOneBoard(boardId);
	}

	// 여러 게시글들 읽어오기(게시판)
	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		
		return this.boardBiz.readAllBoards(boardSearchVO);
	}

	// 한 게시글당 추천 누른 회원정보 추가
	@Override
	public boolean createOneBoardRecommend(String boardId, String memberId) {
		
		return this.boardBiz.createOneBoardRecommend(boardId, memberId);
	}

	// 한 게시글당 추천 누른 회원정보 삭제
	@Override
	public boolean deleteOneBoardRecommend(String boardId, String memberId) {
		
		return this.boardBiz.deleteOneBoardRecommend(boardId, memberId);
	}

	// 하나의 게시글을 추천 수
	@Override
	public int readOneBoardRecommendCount(String boardId) {
		
		return this.boardBiz.readOneBoardRecommendCount(boardId);
	}	
}
