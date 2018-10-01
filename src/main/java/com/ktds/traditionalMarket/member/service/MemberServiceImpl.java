package com.ktds.traditionalMarket.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ktds.traditionalMarket.common.utils.SHA256Util;
import com.ktds.traditionalMarket.member.biz.MemberBiz;
import com.ktds.traditionalMarket.member.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberBiz memberBiz;

	
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
		String password = this.getHashedPassword(salt, memberVO.getPassword());
		
		MemberVO member = memberBiz.readOneMember(memberVO);
		
		memberVO.setPassword(password);
			
		return this.memberBiz.readOneMember(member);
	}
	
	
}
