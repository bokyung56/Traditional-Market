package com.ktds.traditionalmarket.board.biz;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.dao.BoardDao;
import com.ktds.traditionalmarket.board.reply.biz.BoardReplyBiz;
import com.ktds.traditionalmarket.board.vo.BoardSearchVO;
import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.member.biz.MemberBiz;

import io.github.seccoding.web.pager.Pager;
import io.github.seccoding.web.pager.PagerFactory;
import io.github.seccoding.web.pager.explorer.ClassicPageExplorer;
import io.github.seccoding.web.pager.explorer.PageExplorer;

@Component
public class BoardBizImpl implements BoardBiz{
	
	@Autowired
	private BoardDao boardDao;
	
	@Autowired
	private MemberBiz memberBiz;
	
	@Autowired
	private BoardReplyBiz boardReplyBiz;

	// 게시글 작성하기
	@Override
	public boolean createOneBoard(BoardVO boardVO) {
		boolean isSuccess = boardDao.insertOneBoard(boardVO) > 0;
		
		// 파일이름의 길이가 0이상이어야 true	
		boolean isUploadFile = boardVO.getPicture().length() > 0;	
		if ( isUploadFile ) {
			memberBiz.updatePoint(boardVO.getMemberId(), 500);
		}
		
		// member패키지에 있는 memberBizImpl에 정의 되어있는 메소드를 불러옴.
		memberBiz.updatePoint(boardVO.getMemberId(), 500);
		
		return  isSuccess;
	}

	// 게시글 하나 읽어오기
	@Override
	public BoardVO readOneBoard(String boardId) {
		// 1. 조회수 증가시키기
		boardDao.updateViewCount(boardId);
		
		// 2.하나의 게시글 조회
		return boardDao.selectOneBoard(boardId);
	}
	
	// 게시글 추천수 증가시키기
/*	@Override
	public boolean updateRecommendCount(String boardId) {
		
		return this.boardDao.updateRecommendCount(boardId) > 0;
	}*/
	
	// 게시글 수정하기 
	@Override
	public boolean updateOneBoard(BoardVO boardVO) {
		
		return boardDao.updateOneBoard(boardVO) > 0;
	}

	// 게시글 지우기
	//@Override
	//public boolean deleteOneBoard(String boardId) {
		
		// 그 게시글안에 있는 댓글 전체 삭제
		//boardReplyBiz.deleteOneBoardReplies(boardId);
		
		//return boardDao.deleteOneBoard(boardId) > 0;
	//}
	
	// 제일 중요한 코드!
	@Override
	public PageExplorer readAllBoards(BoardSearchVO boardSearchVO) {
		int totalCount = this.boardDao.selectAllBoardsCount(boardSearchVO);
		
		Pager pager = PagerFactory.getPager(Pager.ORACLE, boardSearchVO.getPageNo() + ""); // Pager.ORACLE: 오라클 전용 페이저가 만들어짐
		
		pager.setTotalArticleCount(totalCount);
		
		PageExplorer pageExplorer = pager.makePageExplorer(ClassicPageExplorer.class, boardSearchVO); // 페이지번호형식이 2가지인데, 우리는 이런형식을 쓰겠다(기본형식) ClassicPageExplorer.class가 이거다.
		
		pageExplorer.setList( this.boardDao.selectAllBoards(boardSearchVO) );
		
		return pageExplorer;
	}

	/*
	 * 51: 몇번부터 몇번까지 가져와라?
	 * 
	 * 53: 시작번호와 끝번호를 boardSearchVO한테 넣어준다.
	 */
	
	// 한 게시글당 추천 누른 회원정보 추가
	@Override
	public boolean createOneBoardRecommend(String boardId, String memberId) {
		Map<String, String> boardRecommendVO = new HashMap<>();
		boardRecommendVO.put("boardId", boardId);
		boardRecommendVO.put("memberId", memberId);
		
		return this.boardDao.insertOneBoardRecommend(boardRecommendVO) > 0;
	}

	// 한 게시글당 추천을 누른 회원정보 삭제
	@Override
	public boolean deleteOneBoardRecommend(String boardId, String memberId) {
		Map<String, String> boardRecommendVO = new HashMap<>();
		boardRecommendVO.put("boardId", boardId);
		boardRecommendVO.put("memberId", memberId);
		
		return this.boardDao.deleteOneBoardRecommend(boardRecommendVO) > 0;
	}

	// 하나의 게시글을 추천 수
	@Override
	public int readOneBoardRecommendCount(String boardId) {
		
		return this.boardDao.selectOneBoardRecommendCount(boardId);
	}

	// 게시글 지운 여부
	@Override
	public boolean updateDeleteOneBoard(String boardId) {
		
		return this.boardDao.updateDeleteOneBoard(boardId) > 0;
	}

	
	
	
	// main페이지에서 최시글 띄어줄 메소드
	@Override
	public List<BoardVO> readTenDateBoard() {
		
		return this.boardDao.selectTenDateBoard();
	}

	// main페이지에서 인기글 띄어줄 메소드
	@Override
	public List<BoardVO> readTenRecommendBoard() {
		
		return this.boardDao.selectTenRecommendBoard();
	}	
	
}
