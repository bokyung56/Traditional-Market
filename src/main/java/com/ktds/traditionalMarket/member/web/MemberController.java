package com.ktds.traditionalmarket.member.web;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ktds.traditionalmarket.common.session.Session;
import com.ktds.traditionalmarket.member.service.MemberService;
import com.ktds.traditionalmarket.member.validator.MemberValidator;
import com.ktds.traditionalmarket.member.vo.MemberVO;

import ch.qos.logback.core.net.SyslogOutputStream;

@Controller
public class MemberController {
	
	@Autowired 
	private MemberService memberService;
	
	// <로그아웃>
	@GetMapping("/member/logout")
	public String doMemberLogoutAction( HttpSession session ) {
		session.invalidate();	// Logout, 이 사용자에 대한 세션을 다 지워버리겠다.
		return "redirect:/main/main";	
	}

	// <회원가입>
	@GetMapping("/member/regist")
	public String viewRegistMemberPage() {	
		
		return "member/regist";
	} 
	
	
	// 회원가입시 아이디 중복확인
	@PostMapping("/member/check/duplicate")
	@ResponseBody
	public Map<String, Object> doCheckDuplicateId( @RequestParam String memberId ){
		
		boolean Id = this.memberService.readOneId(memberId);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("duplicated", Id);
		
		return result;
	}
	
	
	// 회원가입시 비밀번호 정책확인
	@PostMapping("/member/check/password")
	@ResponseBody
	public Map<String, Object> doCheckPassword( @RequestParam String password ){
		
		String passwordPolicy = "((?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*()]).{8,})";
		
		Pattern pattern = Pattern.compile(passwordPolicy);
		Matcher matcher = pattern.matcher(password);	// 리턴타입은 boolean
		//boolean check = pattern.matches(passwordPolicy, password);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("password", matcher.find());
		// result.put("password", check);
		return result;
	}
	
	
	// 회원가입시 비밀번호와 비밀번호재확인 일치여부
	@PostMapping("/member/check/password_ckeck")
	@ResponseBody
	public Map<String, Object> doCheckPasswordCheck( @RequestParam String password, @RequestParam String password_ckeck ){
				
		//Pattern pattern = Pattern.compile(password);
		//Matcher matcher = pattern.matcher(password_ckeck);	// 리턴타입은 boolean
				
		boolean isSuccess = true;
		
		if( password.equals(password_ckeck) ) {	// 비밀번호가 재확인비밀번호랑 일치하면
			isSuccess = false;
		}
		else {
			isSuccess = true;
		}
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("password_ckeck", isSuccess);
		
		return result;
	}
	
	
	// 이메일 유효성 검사
	@PostMapping("/member/check/email")
	@ResponseBody
	public Map<String, Object> doCheckEmail( @RequestParam String email ){
		
		//String emailPolicy = "/^[0-9a-zA-Z_-]+(\\\\.[0-9a-zA-Z_-]+)*@([0-9a-zA-Z_-]+)(\\\\.[0-9a-zA-Z_-]+){1,2}$/";
		//String emailPolicy = "/^([\\w-]+(?:\\.[\\w-]+)*)@((?:[\\w-]+\\.)*\\w[\\w-]{0,66})\\.([a-z]{2,6}(?:\\.[a-z]{2})?)$/";
		String emailPolicy = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		
		Pattern pattern = Pattern.compile(emailPolicy);
		Matcher matcher = pattern.matcher(email);	
		//boolean check = pattern.matches(passwordPolicy, password);
		
		Map<String, Object> result = new HashMap<>();
		result.put("status", "OK");
		result.put("email", matcher.find());	// 리턴타입은 boolean, 일치하지않으면 false			
		// result.put("password", check);
		return result;
	}
		
	
	@PostMapping("/member/regist")
	public ModelAndView doRegistMemberAction( @Validated({MemberValidator.Regist.class})
	                                    @ModelAttribute MemberVO memberVO, Errors errors ) {
		
		// 회원가입 성공시, 로그인페이지로!
		ModelAndView view = new ModelAndView("redirect:/member/login");
		
		// 만약, 회원가입하는데 에러가 있다면 다시 회원가입페이지로 가서 입력했던 값 살려두기.
		if( errors.hasErrors() ) {
			view.setViewName("member/regist");
			view.addObject("memberVO", memberVO);
			return view;	// 위에 2줄 값을 담고 return
		}
		
		boolean isRegist = this.memberService.createNewMember(memberVO);
		
		return view;	//return "redirect:/main/mainPage";
	}
	
	
	// <로그인>
	@GetMapping("/member/login")
	public String viewLoginMemberPage() {
		
		return "member/login";
	}
	
	
	// <일반회원-로그인>
	@PostMapping("/member/login")
	@ResponseBody
	public Map<String, Object> doLoginMemberAction(	@Validated({MemberValidator.Login.class})
													@ModelAttribute MemberVO memberVO
													, Errors errors
													, HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		
		boolean isBlockUser = this.memberService.isBlockUser(memberVO.getMemberId());		
		if ( isBlockUser ) {
			//로그인 실패 (계정 블럭된 유저 )
			result.put("isBlockUser", isBlockUser);
		}
		else {
			boolean isLoginSuccess = this.memberService.readOneMember(memberVO, session);
			
			//로그인 성공 - true(isLoginSuccess)
			if ( isLoginSuccess ) {	
				// CSRF 방어: Token값 생성 및 등록 코드 작성
				String token = UUID.randomUUID().toString();
				session.setAttribute(Session.CSRF_TOKEN, token);
			}
			//로그인 실패 (그냥 아이디 비밀번호 틀린사람) - false(isLoginSuccess)
			else {	
				
			}
			result.put("isLoginSuccess", isLoginSuccess);
		}
			
		// 로그인 성공시, 메인페이지로!
		// ModelAndView view = new ModelAndView("redirect:/main/main");
		
		
		// 만약, 로그인하는데 에러가 있다면 다시 로그인페이지로 가서 입력했던 값 살려두기.
		//if( errors.hasErrors() ) {
		//	view.setViewName("member/login");
		//	view.addObject("memberVO", memberVO);
		//	return view;
		//}
		
		//MemberVO param = this.memberService.readOneMember(memberVO);
		
		//if( param != null ) {
		//	session.setAttribute("_USER_", param);		// 1. session 등록 (session key == _USER_) -> 2. SessionInterceptor.java
		//}
		//else {
		//	view.setViewName("member/login");
		//}
		
		return result;	// return view;	// return "redirect:/main/mainPage";
	}
	
	// <카카오회원-로그인>
	
	
	
	@GetMapping("/main/main")
	public String viewMain() {	
		
		return "main/main";
	} 
	
	
}
