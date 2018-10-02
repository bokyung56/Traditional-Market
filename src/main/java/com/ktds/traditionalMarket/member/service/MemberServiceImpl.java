package com.ktds.traditionalmarket.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalmarket.common.utils.SHA256Util;
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
		String salt = SHA256Util.generateSalt();			// 난수값을 이용해 5글자를 만들어냄
		String password = this.getHashedPassword(memberVO.getPassword(), salt);
		
		memberVO.setPassword(password);
		memberVO.setSalt(salt);
		
		return this.memberBiz.createNewMember(memberVO);
	}
	
	
	public String getHashedPassword(String password, String salt) {
		
		return SHA256Util.getEncrypt(password, salt);
	}
	

	// <로그인>
	@Override
	public MemberVO readOneMember(MemberVO memberVO) {		
		String salt = memberBiz.getSaltById(memberVO.getMemberId());
		System.out.println(salt);
		String password = this.getHashedPassword(memberVO.getPassword(),salt);
		
		memberVO.setPassword(password);
		MemberVO member = memberBiz.readOneMember(memberVO);
		
			
		return member;
	}
	
}
