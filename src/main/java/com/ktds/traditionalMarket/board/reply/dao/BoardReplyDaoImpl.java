package com.ktds.traditionalmarket.board.reply.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.board.reply.vo.BadVO;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.board.reply.vo.GoodVO;

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
	//@Override
	//public int deleteOneBoardReplies(String boardId) {
		
		//return getSqlSession().delete("BoardReplyDao.deleteOneBoardReplies", boardId);
	//}
	
	// 하나의 댓글 삭제
	//@Override
	//public int deleteOneBoardReply(String boardReplyId) {
		
		//return getSqlSession().delete("BoardReplyDao.deleteOneBoardReply", boardReplyId);
	//}
	
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
	
	// 댓글 졸아요 수
	@Override
	public int selectOneBoardReplyGoodCount(String boardReplyId) {
		return getSqlSession().selectOne("BoardReplyDao.selectOneBoardReplyGoodCount", boardReplyId);
	}
	
	// 댓글 싫어요 수
	@Override
	public int selectOneBoardReplyBadCount(String boardReplyId) {
		return getSqlSession().selectOne("BoardReplyDao.selectOneBoardReplyBadCount", boardReplyId);
	}

	// 해당 댓글에 좋아요를 한 회원이 다시 취소하기
	@Override
	public int deleteOneBoardReplyGood(Map<String, String> goodVO) {
		
		return getSqlSession().delete("BoardReplyDao.deleteOneBoardReplyGood", goodVO);
	}

	// 해당 댓글에 싫어요를 한 회원이 다시 취소하기
	@Override
	public int deleteOneBoardReplyBad(Map<String, String> badVO) {
		
		return getSqlSession().delete("BoardReplyDao.deleteOneBoardReplyBad", badVO);
	}

	// 해당 댓글의 좋아요를 한 회원 검색
	@Override
	public GoodVO selectOneBoardReplyGood(Map<String, String> goodVO) {
		
		return getSqlSession().selectOne("BoardReplyDao.selectOneBoardReplyGood", goodVO);
	}

	// 해당 댓글의 싫어요를 한 회원 검색
	@Override
	public BadVO selectOneBoardReplyBad(Map<String, String> badVO) {
		
		return getSqlSession().selectOne("BoardReplyDao.selectOneBoardReplyBad", badVO);
	}

	// 한 게시글의 댓글의 여자 수
	@Override
	public int oneBoardWomenCount(String boardId) {
		
		return getSqlSession().selectOne("BoardReplyDao.oneBoardWomenCount", boardId);
	}

	// 한 게시글의 댓글의 남자 수
	@Override
	public int oneBoardMenCount(String boardId) {
		
		return getSqlSession().selectOne("BoardReplyDao.oneBoardMenCount", boardId);
	}

	// 하나의 댓글 삭제 여부
	@Override
	public int updateDeleteOneReply(String boardReplyId) {
		
		return getSqlSession().update("BoardReplyDao.updateDeleteOneReply", boardReplyId);
	}

}
