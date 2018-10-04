package com.ktds.traditionalmarket.board.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int updateOneBoard(String boardId) {	
		return getSqlSession().update("BoardDao.updateOneBoard", boardId);
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
}
