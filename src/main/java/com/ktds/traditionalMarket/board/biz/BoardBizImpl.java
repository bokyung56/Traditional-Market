package com.ktds.traditionalmarket.board.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.dao.BoardDao;
import com.ktds.traditionalmarket.board.vo.BoardVO;

@Component
public class BoardBizImpl implements BoardBiz{
	
	@Autowired
	private BoardDao boardDao;

	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		
		return boardDao.insertOneBoard(boardVO) > 0;
	}

	@Override
	public BoardVO readOneBoard(String boardId) {
		// 1. 조회수 증가시키기
		boardDao.updateViewCount(boardId);
		
		// 2.하나의 게시글 조회
		return boardDao.selectOneBoard(boardId);
	}

	@Override
	public boolean updateOneBoard(String boardId) {
		
		return boardDao.updateOneBoard(boardId) > 0;
	}

	@Override
	public boolean deleteOneBoard(String boardId) {
		
		return boardDao.deleteOneBoard(boardId) > 0;
	}
	
}
