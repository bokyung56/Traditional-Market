package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;

@Repository
public class BoardReplyDaoImpl extends SqlSessionDaoSupport implements BoardReplyDao{
	// 이걸 가지고 sql CRUD를 사용할 수 있어짐(rootContext.xml에 보면 <bean id="sqlSessionTemplate">이거 있음)
	@Autowired
	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {	
		super.setSqlSessionTemplate(sqlSessionTemplate);
	}

	@Override
	public int insertOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return getSqlSession().insert("BoardReplyDao.insertOneBoardReply", boardReplyVO);
	}

	@Override
	public int deleteOneBoardReply(String boardReplyId) {
		
		return getSqlSession().delete("BoardReplyDao.deleteOneBoardReply", boardReplyId);
	}

	@Override
	public List<BoardReplyVO> selectAllBoardReplies(String boardId) {
		
		return getSqlSession().selectList("BoardReplyDao.selectAllBoardReplies", boardId);
	}

	@Override
	public BoardReplyVO selectOneMemberReplies(BoardReplyVO boardReplyVO) {
		// TODO Auto-generated method stub
		return null;
	}

}
