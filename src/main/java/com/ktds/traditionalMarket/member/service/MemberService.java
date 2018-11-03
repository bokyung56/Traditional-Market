package com.ktds.traditionalmarket.member.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.member.vo.MemberVO;
import com.ktds.traditionalmarket.trdtnmarket.vo.TrdtnmarketVO;

public interface MemberService {
	
	// 회원의 아이디 가져오기
	public boolean readOneId(String memberId);
	
	// 회원가입하기
	public boolean createNewMember(MemberVO memberVO);
	
	// 회원 한명정보 가져오기
	public boolean readOneMember(MemberVO memberVO, HttpSession session);
	
	// 회원정보 수정하기
	public boolean updateMyInformation(MemberVO memberVO);
	
	public boolean isBlockUser(String memberId);
	
	// 회원 정보가져오기(내 정보)
	public MemberVO readOneMemberInfo(MemberVO memberId);
}
