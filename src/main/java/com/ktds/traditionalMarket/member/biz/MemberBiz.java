package com.ktds.traditionalmarket.member.biz;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.ktds.traditionalmarket.member.vo.MemberVO;

public interface MemberBiz {

	// 회원의 id 가져오기
	public boolean readOneId(String memberId);
	
	public boolean createNewMember(MemberVO memberVO);
	
	public boolean readOneMember(MemberVO memberVO, HttpSession session);
	
	// 회원의 salt값 가져오기
	public String getSaltById(String memberId);
	
	// 회원 point 업데이트하기(글 작성시)
	public boolean updatePoint(String memberId, int point);
	
	// 회원 정보가져오기(내 정보)
	public MemberVO readOneMemberInfo(MemberVO memberId);
	
	// 회원정보 수정하기
	public boolean updateMyInformation(MemberVO memberVO);
	
	// 시큐리티
	public boolean isBlockUser(String memberId);

	public boolean unBlockUser(String memberId);

	public boolean increaseLoginFailCount(String memberId);
}
