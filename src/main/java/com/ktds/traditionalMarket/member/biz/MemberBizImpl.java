package com.ktds.traditionalmarket.member.biz;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ktds.traditionalmarket.board.vo.BoardVO;
import com.ktds.traditionalmarket.common.utils.SHA256Util;
import com.ktds.traditionalmarket.member.dao.MemberDao;
import com.ktds.traditionalmarket.member.vo.MemberVO;


@Component
public class MemberBizImpl implements MemberBiz{
	
	@Autowired
	private MemberDao memberDao;

	// 입력한 아이디가 DB에 있는지 확인하기
	@Override
	public boolean readOneId(String memberId) {
		
		return memberDao.selectOneId(memberId) > 0;
	}
	
	// <회원가입>
	@Override
	public boolean createNewMember(MemberVO memberVO) {
		String salt = SHA256Util.generateSalt();			// 난수값을 이용해 5글자를 만들어냄
		String password = this.getHashedPassword(memberVO.getPassword(), salt);
		
		memberVO.setPassword(password);
		memberVO.setSalt(salt);
		
		return this.memberDao.insertNewMember(memberVO) > 0;
	}
	
	
	public String getHashedPassword(String password, String salt) {
		
		return SHA256Util.getEncrypt(password, salt);
	}

	// <로그인>
	@Override
	public boolean readOneMember(MemberVO memberVO, HttpSession session) {
		
		String salt = this.memberDao.getSaltById(memberVO.getMemberId());
		if ( salt != null ) {	// 사용자가 입력한 ID로 DB에 해당하는 ID의 SALT값이 null이 아니라면(즉, 해당 ID가 DB에  있다는 뜻)
			String password = this.getHashedPassword(memberVO.getPassword(), salt);
			memberVO.setPassword(password);
			
			MemberVO member = this.memberDao.selectOneMember(memberVO);
			// session.setAttribute("_USER_", member);
			
			if ( member == null ) {
				this.memberDao.increaseLoginFailCount(memberVO.getMemberId());
				return false;
			}
			else {
				this.memberDao.unBlockUser(member.getMemberId());
				session.setAttribute("_USER_", member);
				return true;
			}
		}
		else {					// 사용자가 입력한 ID로 DB에 해당하는 ID의 SALT값이 null이라면(즉, 해당 ID가 DB에 없다는 뜻)
			return false;
		}
	}
	
	
	@Override
	public String getSaltById(String memberId) {
	
		return memberDao.getSaltById(memberId);
	}
	
	
	// 회원 point 업데이트하기(글 작성시)
	@Override
	public boolean updatePoint(String memberId, int point) {
		
		Map<String, Object> memberVO = new HashMap<String, Object>();
		memberVO.put("memberId", memberId);
		memberVO.put("point", point);
		
		return memberDao.updatePoint(memberVO) > 0;
	}
	// +부연설명
	// Biz에 있는 updatePoint는 누가 부를까? 바로 board패키지 serviceImpl에 있는~!
	//
	// @Autowired
	// private MemberBiz memberBiz; -> 다른 패키지에 있는 Biz 불러오기
	//
	// public boolean createOneBoard(BoardVO boardVO)
	//	  boolean isSuccess = boardBiz.createOneBoard(boardVO); -> insert가 false될 일은 거의 없고(그래서 if문으로 체크 사용안함), update는 있음
	//    memberBiz.updatePoint(boardVO.getMemberId(), 500);
	//    return isSuccess;
	
	
	
	
	// 시큐리티
	@Override
	public boolean isBlockUser(String memberId) {
		Integer isBlockUser = memberDao.isBlockUser(memberId);	// isBlockUser = LOGIN_FAIL_COUNT;
		
		// 1번. WHERE MEMBER_ID = #{memberId} 2번. AND LOGIN_FAIL_TIME + 1/24 >= SYSDATE
		// 둘 중 하나라도 조건이 성립하지 않으면 null값을 가져온다. (memberDao.xml)
		if ( isBlockUser == null ) {	
			isBlockUser = 0;
		}
		
		// true: isBlockUser >= 3, false: isBlockUser < 3
		return isBlockUser >= 3;
	}

	@Override
	public boolean unBlockUser(String memberId) {
		
		return memberDao.unBlockUser(memberId) > 0;
	}

	@Override
	public boolean increaseLoginFailCount(String memberId) {
		
		return memberDao.increaseLoginFailCount(memberId) > 0;
	}

}
