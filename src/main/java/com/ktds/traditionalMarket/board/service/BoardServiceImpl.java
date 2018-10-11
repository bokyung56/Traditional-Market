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
		
		return boardBiz.createOneBoard(boardVO);
	}

	// 게시글 하나 읽어오기
	@Override
	public BoardVO readOneBoard(String boardId) {
		
		BoardVO boardVO = this.boardBiz.readOneBoard(boardId);
		
		List<BoardReplyVO> replyList = this.boardReplyBiz.readAllBoardReplies(boardId);
		if (replyList != null ) {			
			boardVO.setReplyList(replyList);
		}
		
		return boardVO;
	}
	
	// 게시글 추천수 증가시키기
	@Override
	public boolean updateRecommendCount(String boardId) {
		
		return boardBiz.updateRecommendCount(boardId);
	}
	
	// 게시글 수정하기
	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		
		return boardBiz.updateOneBoard(boardVO);
	}

	// 게시글 지우기
	@Override
	public boolean deleteOneBoard(String boardId) {
		
		return boardBiz.deleteOneBoard(boardId);
	}

	// 여러 게시글들 읽어오기(게시판)
	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		
		return boardBiz.readAllBoards(boardSearchVO);
	}	
}
