package com.ktds.traditionalmarket.member.biz;

import javax.servlet.http.HttpSession;

import com.ktds.traditionalmarket.member.vo.MemberVO;

public interface MemberBiz {

	// 회원의 아이디 가져오기
	public boolean readOneId(String memberId);
	
	public boolean createNewMember(MemberVO memberVO);
	
	public boolean readOneMember(MemberVO memberVO, HttpSession session);
	
	// 회원의 salt값 가져오기
	public String getSaltById(String memberId);
	
	// 시큐리티
	public boolean isBlockUser(String memberId);

	public boolean unBlockUser(String memberId);

	public boolean increaseLoginFailCount(String memberId);
}
