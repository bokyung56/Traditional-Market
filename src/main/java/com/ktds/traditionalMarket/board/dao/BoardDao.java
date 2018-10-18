package com.ktds.traditionalmarket.board.dao;

import java.util.List;
import java.util.Map;

import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;

public interface BoardDao {
	
	public int insertOneBoard(BoardVO boardVO);				// 글 쓰기
	
	public BoardVO selectOneBoard(String boardId);			// 글 하나 읽어오기
	public int updateOneBoard(BoardVO boardVO);				// 글 수정하기
	//public int deleteOneBoard(String boardId);			// 글 지우기
	
	public int updateViewCount(String boardId);				// 조회수 증가시키기
	//public int updateRecommendCount(String boardId);		// 추천수 증가시키기
	
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO);
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO);
	
	public int insertOneBoardRecommend(Map<String, String> boardRecommendVO);	// 하나의 게시글을 추천한 회원 정보 추가
	public int deleteOneBoardRecommend(Map<String, String> boardRecommendVO);	// 하나의 게시글을 추천한 회원 정보 삭제
	public int selectOneBoardRecommendCount(String boardId);					// 하나의 게시글을 추천 수
	
	public int updateDeleteOneBoard(String boardId);		// 글 지운 여부
	
	
	public List<BoardVO> selectTenDateBoard();				// main페이지에서 최시글 띄어줄 메소드
	public List<BoardVO> selectTenRecommendBoard();			// main페이지에서 인기글 띄어줄 메소드
}
