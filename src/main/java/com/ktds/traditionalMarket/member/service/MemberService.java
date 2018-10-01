package com.ktds.traditionalmarket.member.service;

import com.ktds.traditionalmarket.member.vo.MemberVO;

public interface MemberService {
	
	public boolean createNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
}
