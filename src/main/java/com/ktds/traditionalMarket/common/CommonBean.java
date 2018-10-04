package com.ktds.traditionalmarket.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class CommonBean {

	@Bean("uploadPath")	// class에서도 bean을 만들 수 있다.
	public String uploadPath( ) {
		return "D:/uploadPicture";
	}
	
}
