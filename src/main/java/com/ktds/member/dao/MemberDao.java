package com.ktds.member.dao;

import java.util.Map;

import com.ktds.member.vo.MemberVO;

public interface MemberDao {

	// 회원가입하기
	public int insertNewMember(MemberVO memberVO);
	
	// 회원 한명정보 가져오기
	public MemberVO selectOneMember(MemberVO memberVO);
	
	// 회원 포인트 업데이트하기	
	public int updatePoint(Map<String, Object> memberVO);	// Dao에서 argument는 하나만 써줄 수 있음! String은 uploaderId, Object는 point를 

}
