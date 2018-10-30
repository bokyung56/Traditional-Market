package com.ktds.traditionalmarket.main.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

@Repository
public class MainDaoImpl extends SqlSessionDaoSupport implements MainDao{
	
	// 이걸 가지고 sql CRUD를 사용할 수 있어짐(rootContext.xml에 보면 <bean id="sqlSessionTemplate">이거 있음)
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public List<BoardVO> selectTenDateBoard() {			// main페이지에서 최시글 띄어줄 메소드
		return getSqlSession().selectList("MainDao.selectTenDateBoard");
	}

	@Override
	public List<BoardVO> selectTenRecommendBoard() {	// main페이지에서 인기글 띄어줄 메소드
		return getSqlSession().selectList("MainDao.selectTenRecommendBoard");
	}

	@Override
	public List<TrdtnmarketVO> selectTenTrdtnName() {			// 조회수순으로 인기 재래시장명 순위 
		
		return getSqlSession().selectList("MainDao.selectTenTrdtnName");
	}
}
