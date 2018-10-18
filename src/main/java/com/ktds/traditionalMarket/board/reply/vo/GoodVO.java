package com.ktds.traditionalmarket.board.reply.vo;

public class GoodVO {

	private String memberId;
	private String boardReplyId;
	private String crtDate;
			
	public String getMemberId() {
		return memberId;
	}
	
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	public String getBoardReplyId() {
		return boardReplyId;
	}
	
	public void setBoardReplyId(String boardId) {
		this.boardReplyId = boardReplyId;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}
}
