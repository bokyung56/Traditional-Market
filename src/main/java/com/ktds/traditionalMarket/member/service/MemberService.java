package com.ktds.traditionalmarket.member.service;

import com.ktds.traditionalmarket.member.vo.MemberVO;

public interface MemberService {
	
	// 회원의 아이디 가져오기
	public boolean readOneId(String memberId);
	
	// 회원가입하기
	public boolean createNewMember(MemberVO memberVO);
	
	// 회원 한명정보 가져오기
	public MemberVO readOneMember(MemberVO memberVO);
}
