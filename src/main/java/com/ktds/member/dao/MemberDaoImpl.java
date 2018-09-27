package com.ktds.member.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.ktds.member.vo.MemberVO;

@Repository
public class MemberDaoImpl extends SqlSessionDaoSupport implements MemberDao {

	@Override
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {

		super.setSqlSessionTemplate(sqlSessionTemplate);
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
	public int updatePoint(Map<String, Object> memberVO) {
		
		return getSqlSession().update("MemberDao.updatePoint", memberVO);
	}

}
