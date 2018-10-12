package com.ktds.traditionalmarket.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;

@Repository
public class BoardDaoImpl extends SqlSessionDaoSupport implements BoardDao{
	
	// 이걸 가지고 sql CRUD를 사용할 수 있어짐(rootContext.xml에 보면 <bean id="sqlSessionTemplate">이거 있음)
	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {	
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	// 글 쓰기
	@Override
	public int insertOneBoard(BoardVO boardVO) {
		return getSqlSession().insert("BoardDao.insertOneBoard", boardVO);
	}

	// 글 하나 읽어오기
	@Override
	public BoardVO selectOneBoard(String boardId) {	
		return getSqlSession().selectOne("BoardDao.selectOneBoard", boardId);
	}

	// 글 수정하기
	@Override
	public int updateOneBoard(BoardVO boardVO) {	
		return getSqlSession().update("BoardDao.updateOneBoard", boardVO);
	}

	// 글 지우기
	@Override
	public int deleteOneBoard(String boardId) {		
		return getSqlSession().delete("BoardDao.deleteOneBoard", boardId);
	}

	// 조회수 증가시키기
	@Override
	public int updateViewCount(String boardId) {
		
		return getSqlSession().update("BoardDao.updateViewCount", boardId);
	}
	
	// 추천수 증가시키기
	@Override
	public int updateRecommendCount(String boardId) {
		
		return getSqlSession().update("BoardDao.updateRecommendCount", boardId);
	}

	// 전체글 개수
	@Override
	public int selectAllBoardsCount(BoardSearchVO boardSearchVO) {
		
		return getSqlSession().selectOne("BoardDao.selectAllBoardsCount", boardSearchVO);
	}
	
	// 전체 글 가져오기
	@Override
	public List<BoardVO> selectAllBoards(BoardSearchVO boardSearchVO) {
		
		return getSqlSession().selectList("BoardDao.selectAllBoards", boardSearchVO);
	}

	// 한 게시글당 추천을 누른 회원정보 추가
	@Override
	public int insertOneBoardRecommend(Map<String, String> boardRecommendVO) {
	
		return getSqlSession().insert("BoardDao.insertOneBoardRecommend", boardRecommendVO);
	}

	// 한 게시글당 추천을 누른 회원정보 삭제
	@Override
	public int deleteOneBoardRecommend(Map<String, String> boardRecommendVO) {
		
		return getSqlSession().delete("BoardDao.deleteOneBoardRecommend", boardRecommendVO);
	}

	// 하나의 게시글을 추천 수
	@Override
	public int selectOneBoardRecommendCount(String boardId) {
		
		return getSqlSession().selectOne("BoardDao.selectOneBoardRecommendCount", boardId);
	}	
}
