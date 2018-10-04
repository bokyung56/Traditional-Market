package com.ktds.traditionalmarket.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ktds.traditionalmarket.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Autowired
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {

		super.setSqlSessionTemplate(sqlSessionTemplate);
	}
	
	@Override
	public int selectOneId(String memberId) {
		
		return getSqlSession().selectOne("MemberDao.selectOneId", memberId);
	}
	
	@Override 
	public int insertNewMember(MemberVO memberVO) {
		
		return getSqlSession().insert("MemberDao.insertNewMember", memberVO);
	}

	@Override
	public MemberVO selectOneMember(MemberVO memberVO) {
		
		return getSqlSession().selectOne("MemberDao.selectOneMember", memberVO);
	}
	
	@Override
	public String getSaltById(String memberId) {
		
		return getSqlSession().selectOne("MemberDao.getSaltById", memberId);
	}

	// 회원 포인트 업데이트하기(글 작성시)
	@Override
	public int updatePoint(Map<String, Object> memberVO) {
		
		return getSqlSession().update("MemberDao.updatePoint", memberVO);
	}

	
	// 시큐리티
	@Override
	public Integer isBlockUser(String memberId) {
		
		return getSqlSession().selectOne("MemberDao.isBlockUser", memberId);
	}

	@Override
	public int unBlockUser(String memberId) {

		return getSqlSession().update("MemberDao.unBlockUser", memberId);
	}

	@Override
	public int increaseLoginFailCount(String memberId) {
		
		return getSqlSession().update("MemberDao.increaseLoginFailCount", memberId);
	}	

}
