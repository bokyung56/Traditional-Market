package com.ktds.traditionalmarket.board.reply.vo;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.member.vo.MemberVO;

//   -----------------------
//   l                     l
// Member --- Reply ---- Board

public class BoardReplyVO {

	// DB에 있는 columns 7개
	private String boardReplyId;
	private String reply;
	private String crtDate;
	private String memberId;
	private String boardId;
	private String parentReplyId;
	private String deleteReply;		// 댓글 삭제 여부
	
	private int goodCount;			// 좋아요, 싫어요
	private int badCount;
		
	private int level;				// 아마 Rownum 역할?
	
	private BoardVO boardVO;		// join 해줄 테이블들
	private MemberVO memberVO;
	
	

	
	// getter, setter
	public String getBoardReplyId() {
		return boardReplyId;
	}

	public void setBoardReplyId(String boardReplyId) {
		this.boardReplyId = boardReplyId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getParentReplyId() {
		return parentReplyId;
	}

	public void setParentReplyId(String parentReplyId) {
		this.parentReplyId = parentReplyId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public BoardVO getBoardVO() {
		return boardVO;
	}

	public void setBoardVO(BoardVO boardVO) {
		this.boardVO = boardVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public int getGoodCount() {
		return goodCount;
	}

	public void setGoodCount(int goodCount) {
		this.goodCount = goodCount;
	}

	public int getBadCount() {
		return badCount;
	}

	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}

	public String getDeleteReply() {
		return deleteReply;
	}

	public void setDeleteReply(String deleteReply) {
		this.deleteReply = deleteReply;
	}
}
