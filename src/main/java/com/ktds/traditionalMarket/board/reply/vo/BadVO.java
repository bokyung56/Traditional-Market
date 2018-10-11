package com.ktds.traditionalmarket.board.reply.vo;

public class BadVO {
	
	private String memberId;
	private String boardReplyId;
	private int badCount;
	
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getBoardReplyId() {
		return boardReplyId;
	}

	public void setBoardReplyId(String boardReplyId) {
		this.boardReplyId = boardReplyId;
	}

	public int getBadCount() {
		return badCount;
	}

	public void setBadCount(int badCount) {
		this.badCount = badCount;
	}	
}
