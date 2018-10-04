package com.ktds.traditionalmarket.board.dao;

import com.ktds.traditionalmarket.board.vo.BoardVO;

public interface BoardDao {
	
	public int insertOneBoard(BoardVO boardVO);		// 글 쓰기
	public BoardVO selectOneBoard(String boardId);	// 글 하나 읽어오기
	public int updateOneBoard(String boardId);		// 글 수정하기
	public int deleteOneBoard(String boardId);		// 글 지우기
	
	public int updateViewCount(String boardId);		// 조회수 증가시키기
}
