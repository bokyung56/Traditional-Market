package com.ktds.traditionalmarket.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.board.biz.BoardBiz;
import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.member.biz.MemberBiz;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardBiz boardBiz;
	
	@Autowired
	private MemberBiz memberBiz;

	// 회원이 글을 작성시, 회원의 point가 +500
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		
		boolean isSuccess = boardBiz.createOneBoard(boardVO);
		
		// member패키지에 있는 memberBizImpl에 정의 되어있는 메소드를 불러옴.
		memberBiz.updatePoint(boardVO.getMemberId(), 500);
		
		return isSuccess;
	}

	@Override
	public BoardVO readOneBoard(String boardId) {
		// 해당 게시글 조회수(viewCount) 증가시키기!
		
		
		return boardBiz.readOneBoard(boardId);
	}

	@Override
	public boolean updateOneBoard(String boardId) {
		
		return boardBiz.updateOneBoard(boardId);
	}

	@Override
	public boolean deleteOneBoard(String boardId) {
		
		return boardBiz.deleteOneBoard(boardId);
	}

	
	
}
