package com.ktds.traditionalmarket.store.reply.vo;

import com.ktds.traditionalmarket.member.vo.MemberVO;
import com.ktds.traditionalmarket.store.vo.StoreVO;

// -----------------------
// l                     l
// Member --- Reply ---- Store

public class StoreReplyVO {
	// DB에 있는 columns 6개
	private String storeReplyId;
	private String storeId;
	private String memberId;
	private String reply;
	private String crtDate;
	private double rating;
	private String deleteReply; // 댓글 삭제 여부

	//private int level; 			// 아마 Rownum 역할?
	private String marketId;	// 댓글 작성때문에
	
	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	private StoreVO storeVO; // JOIN을 해줄 테이브들
	private MemberVO memberVO;

	// getter, setter
	public String getStoreReplyId() {
		return storeReplyId;
	}

	public void setStoreReplyId(String storeReplyId) {
		this.storeReplyId = storeReplyId;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getDeleteReply() {
		return deleteReply;
	}

	public void setDeleteReply(String deleteReply) {
		this.deleteReply = deleteReply;
	}

	public StoreVO getStoreVO() {
		return storeVO;
	}

	public void setStoreVO(StoreVO storeVO) {
		this.storeVO = storeVO;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

}
