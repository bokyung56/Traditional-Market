package com.ktds.traditionalmarket.common.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nhncorp.lucy.security.xss.XssFilter;

public class XssInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) 
	throws Exception {
		/*
		 * 모든 Parameter를 가져와 XSS Filtering을 수행
		 */
		Map<String, String[]> requestParams = request.getParameterMap();
		
		XssFilter filter = XssFilter.getInstance("lucy-xss-superset.xml");
		
		// 파라미터 키 : 파라미터 값 출력
		requestParams.entrySet().stream().forEach(entry -> {
			entry.getValue()[0] = filter.doFilter(entry.getValue()[0]);		// 모든 파라미터를 가져와서 필터링 하거라. 그러면 스크립트만 모두 제거됨.
			
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			System.out.println("================================");		
		});
		
		
		return true;
	}
}
