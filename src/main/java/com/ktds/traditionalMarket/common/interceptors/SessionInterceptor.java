package com.ktds.traditionalmarket.common.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ktds.traditionalmarket.member.dao.MemberDao;
import com.ktds.traditionalmarket.member.vo.MemberVO;

public class SessionInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private MemberDao memberDao;
	
	
	// preHandle은 "Controller하기 전"이다.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		
		if ( session.getAttribute("_USER_") == null ) {
			// request.getContextPath()는 프로젝트의 Context path명을 반환 => /TraditionalMarket
			String contextPath = request.getContextPath();	
			response.sendRedirect(contextPath + "/member/login");
			
			return false;
		}
		
		return true;
	}
	
	
	// preHandle은 "Controller한 이후"이다.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		HttpSession session = request.getSession();
		
		// session이 있으면 계속 갱신되어라!
		MemberVO param = (MemberVO)session.getAttribute("_USER_");
		
		if ( param != null ) {
			MemberVO memberVO = memberDao.selectOneMember(param);
			session.setAttribute("_USER_", memberVO);
		}		
	}
	
	
}
