package com.ktds.traditionalmarket.board.vo;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;
import com.ktds.traditionalmarket.board.reply.vo.BoardReplyVO;
import com.ktds.traditionalmarket.member.vo.MemberVO;

public class BoardVO {
	// PK
	private String boardId;

	@NotEmpty(message = "제목은 필수 입력 값입니다.")
	private String title;
	private int rowNum;
	private String writer;
	@NotEmpty(message = "내용은 필수 입력 값입니다.")
	private String content;
	private String crtDate;
	private String mdfyDate;
	private String viewCount;
	private int recommendCount;
	private String picture;
	private String memberId;				// 회원 Id

	private MemberVO memberVO;				// BOARD테이블과 MEMBE테이블을 조인하기 위해서
	
	private MultipartFile pictureFile;		// 이미지업로드 다운로드를 위한 변수
	//private List<MultipartFile> pictureFiles;
	
	private List<BoardReplyVO> replyList;	// 댓글
		
	private String token;					// CSRF
	
	private int womenCnt;					// 댓글 남녀 비율
	private int menCnt;
			
	private String deleteBoard;				// 게시글 삭제 여부
	
	
	
	// 원래 글을 등록할때, 파일을 올려놓지 않으면 에러 발생하는뎁!
	// 왜? Null이라서 그래서 초기값을 공백으로 줘버리니까 이제 파일 올리지 않아도 에러가 나지 않는다.
	public BoardVO() {
		this.picture = "";
	}

	public int getRowNum() {
		return rowNum;
	}

	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}

	public String getBoardId() {
		return boardId;
	}

	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCrtDate() {
		return crtDate;
	}

	public void setCrtDate(String crtDate) {
		this.crtDate = crtDate;
	}

	public String getMdfyDate() {
		return mdfyDate;
	}

	public void setMdfyDate(String mdfyDate) {
		this.mdfyDate = mdfyDate;
	}

	public String getViewCount() {
		return viewCount;
	}

	public void setViewCount(String viewCount) {
		this.viewCount = viewCount;
	}

	public int getRecommendCount() {
		return recommendCount;
	}

	public void setRecommendCount(int recommendCount) {
		this.recommendCount = recommendCount;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public MultipartFile getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(MultipartFile pictureFile) {
		this.pictureFile = pictureFile;
	}
	
	
/*	public List<MultipartFile> getPictureFiles() {
		return pictureFiles;
	}

	public void setPictureFiles(List<MultipartFile> pictureFile) {
		this.pictureFiles = pictureFiles;
	}*/
	
	

	public List<BoardReplyVO> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<BoardReplyVO> replyList) {
		this.replyList = replyList;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getWomenCnt() {
		return womenCnt;
	}

	public void setWomenCnt(int womenCnt) {
		this.womenCnt = womenCnt;
	}

	public int getMenCnt() {
		return menCnt;
	}

	public void setMenCnt(int menCnt) {
		this.menCnt = menCnt;
	}

	public String getDeleteBoard() {
		return deleteBoard;
	}

	public void setDeleteBoard(String deleteBoard) {
		this.deleteBoard = deleteBoard;
	}	

}
