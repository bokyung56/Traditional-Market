package com.ktds.traditionalmarket.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.member.biz.MemberBiz;
import com.ktds.traditionalmarket.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberBiz memberBiz;

	// 입력한 아이디가 DB에 있는지 확인하기
	@Override
	public boolean readOneId(String memberId) {

		return this.memberBiz.readOneId(memberId);
	}
		
	// <회원가입>
	@Override
	public boolean createNewMember(MemberVO memberVO) {		
			
		return this.memberBiz.createNewMember(memberVO);
	}
	

	// <로그인>
	@Override
	public boolean readOneMember(MemberVO memberVO, HttpSession session) {		

		return this.memberBiz.readOneMember(memberVO, session);
	}
	
	@Override
	public boolean isBlockUser(String memberId) {
		return this.memberBiz.isBlockUser(memberId);
	}
}
