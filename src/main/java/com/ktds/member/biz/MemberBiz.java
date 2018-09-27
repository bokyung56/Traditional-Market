package com.ktds.member.biz;

import com.ktds.member.vo.MemberVO;

public interface MemberBiz {

	public boolean createNewMember(MemberVO memberVO);
	
	public MemberVO readOneMember(MemberVO memberVO);
}
