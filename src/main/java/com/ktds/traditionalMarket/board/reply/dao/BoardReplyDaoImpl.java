package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;
import java.util.Map;

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

	// 댓글 작성
	@Override
	public int insertOneBoardReply(BoardReplyVO boardReplyVO) {
		
		return getSqlSession().insert("BoardReplyDao.insertOneBoardReply", boardReplyVO);
	}

	// 그 게시글안에 있는 댓글 전체 삭제
	@Override
	public int deleteOneBoardReplies(String boardId) {
		
		return getSqlSession().delete("BoardReplyDao.deleteOneBoardReplies", boardId);
	}
	
	// 하나의 댓글 삭제
	@Override
	public int deleteOneBoardReply(String boardReplyId) {
		
		return getSqlSession().delete("BoardReplyDao.deleteOneBoardReply", boardReplyId);
	}
	
	// 댓글들 읽어오기
	@Override
	public List<BoardReplyVO> selectAllBoardReplies(String boardId) {
		
		return getSqlSession().selectList("BoardReplyDao.selectAllBoardReplies", boardId);
	}

	@Override
	public BoardReplyVO selectOneMemberReplies(BoardReplyVO boardReplyVO) {
		
		return null;
	}

	// 댓글 졸아요
	@Override
	public int insertOneBoardReplyGood(Map<String, String> goodVO) {
		
		return getSqlSession().insert("BoardReplyDao.insertOneBoardReplyGood", goodVO);
	}

	// 댓글 싫어요
	@Override
	public int insertOneBoardReplyBad(Map<String, String> badVO) {
		
		return getSqlSession().insert("BoardReplyDao.insertOneBoardReplyBad", badVO);
	}
}
