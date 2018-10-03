package com.ktds.traditionalmarket.member.vo;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ktds.traditionalmarket.member.validator.MemberValidator;

public class MemberVO {

	@NotEmpty(message="아이디는 필수 입력 값입니다.", groups= {MemberValidator.Regist.class, MemberValidator.Login.class})
	private String memberId;
	
	@NotEmpty(message="이름은 필수 입력 값입니다.", groups= {MemberValidator.Regist.class})
	private String name;
	
	@NotEmpty(message="비밀번호는 필수 입력 값입니다.", groups= {MemberValidator.Regist.class, MemberValidator.Login.class})
	@Length(min=10, max=20, message="비밀번호는 10~20글자 사이로 입력해 주세요.", groups={MemberValidator.Regist.class})	
	private String password;
		
	private String salt;
	private int point;
	private String membership;
	
	@NotEmpty(message="이메일은 필수 입력 값입니다.", groups= {MemberValidator.Regist.class})
	@Email(message="이메일 형식으로 작성해주세요.", groups= {MemberValidator.Regist.class})
	private String email;
	
	@NotEmpty(message="생일은 필수 입력 값입니다.", groups= {MemberValidator.Regist.class})
	private String birth;
	
	private int loginFailCount;
	private String loginFailTime;

	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;
	}

	public int getLoginFailCount() {
		return loginFailCount;
	}

	public void setLoginFailCount(int loginFailCount) {
		this.loginFailCount = loginFailCount;
	}

	public String getLoginFailTime() {
		return loginFailTime;
	}

	public void setLoginFailTime(String loginFailTime) {
		this.loginFailTime = loginFailTime;
	}

}
