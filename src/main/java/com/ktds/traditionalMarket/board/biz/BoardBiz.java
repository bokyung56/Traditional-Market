package com.ktds.traditionalmarket.board.biz;

import com.ktds.traditionalmarket.board.vo.BoardVO;

public interface BoardBiz {

	public boolean createOneBoard(BoardVO boardVO);		// 글 쓰기
	public BoardVO readOneBoard(String boardId);		// 글 하나 읽어오기
	public boolean updateOneBoard(String boardId);		// 글 수정하기
	public boolean deleteOneBoard(String boardId);		// 글 지우기
	
}
