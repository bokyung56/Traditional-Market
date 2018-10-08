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

	@Override
	public BoardVO readOneBoard(String boardId) {
		// 해당 게시글 조회수(viewCount) 증가시키기!
		BoardVO boardVO = this.readOneBoard(boardId);
		List<BoardReplyVO> replyList = this.boardReplyBiz.selectAllBoardReplies(boardId);
		if (replyList != null ) {			
			boardVO.setReplyList(replyList);
		}
		
		return boardVO;
	}

	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		
		return boardBiz.updateOneBoard(boardVO);
	}

	@Override
	public boolean deleteOneBoard(String boardId) {
		
		return boardBiz.deleteOneBoard(boardId);
	}

	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		
		return boardBiz.readAllBoards(boardSearchVO);
	}

	
	
}
