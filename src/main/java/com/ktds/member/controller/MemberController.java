package com.ktds.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ktds.member.service.MemberService;
import com.ktds.member.vo.MemberVO;

@Controller
public class MemberController {
	
	@Autowired 
	private MemberService memberService;
	
	// <회원가입>
	@GetMapping("/member/regist")
	public String viewRegistNewUploader() {	
		
		return "/member/regist";
	}
	
	
	// <로그인>
	@GetMapping("/member/login")
	public String showLoginPage() {
		
		return "/member/login";
	}
	
	@PostMapping("/member/login")
	public String doLoginAction( @ModelAttribute MemberVO memberVO
									, HttpSession session) {
		
		MemberVO param = this.memberService.readOneMember(memberVO);
		
		// session 등록 (session key == _USER_)
		session.setA
		
		return ;
	}
	
	
	

	
}
