package com.ktds.traditionalmarket.board.biz;

import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;

import io.github.seccoding.web.pager.explorer.PageExplorer;

public interface BoardBiz {

	public boolean createOneBoard(BoardVO boardVO);		// 게시글 작성하기
	public BoardVO readOneBoard(String boardId);		// 게시글 하나 읽어오기
	
	public boolean updateRecommendCount(String boardId);	// 게시글 추천수 증가시키기
	
	public boolean updateOneBoard(BoardVO boardVO);		// 게시글 수정하기
	public boolean deleteOneBoard(String boardId);		// 게시글 지우기
	
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO);// public List<BoardVO> readAllBoards();
}
