package com.ktds.traditionalMarket.member.service;

import com.ktds.traditionalMarket.member.vo.MemberVO;

public interface MemberService {
	
	public boolean createNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
}
