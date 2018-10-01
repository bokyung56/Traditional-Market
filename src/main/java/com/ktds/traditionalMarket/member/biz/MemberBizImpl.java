package com.ktds.traditionalMarket.member.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalMarket.member.dao.MemberDao;
import com.ktds.traditionalMarket.member.vo.MemberVO;


@Component
public class MemberBizImpl implements MemberBiz{
	
	@Autowired
	private MemberDao memberDao;

	
	@Override
	public boolean createNewMember(MemberVO memberVO) {
		
		return memberDao.insertNewMember(memberVO) > 0;
	}

	@Override
	public MemberVO readOneMember(MemberVO memberVO) {
		
		return memberDao.selectOneMember(memberVO);
	}

	@Override
	public String getSaltById(String memberId) {
	
		return memberDao.getSaltById(memberId);
	}
	
	
	
}
