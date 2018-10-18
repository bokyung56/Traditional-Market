package com.ktds.traditionalmarket.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.member.vo.MemberVO;

public interface MemberService {
	
	// 회원의 아이디 가져오기
	public boolean readOneId(String memberId);
	
	// 회원가입하기
	public boolean createNewMember(MemberVO memberVO);
	
	// 회원 한명정보 가져오기
	public boolean readOneMember(MemberVO memberVO, HttpSession session);
	
	public boolean isBlockUser(String memberId);
	
	
	
	// main 페이지에서 게시글 날짜순 10개 띄어줄려고
	public List<BoardVO> readTenDateBoard();
	
	// main 페이지에서 게시글 추천순10개 띄어줄려고
	public List<BoardVO> readTenRecommendBoard();
}
