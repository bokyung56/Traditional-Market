<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<%--<title>전통시장: 로그인</title>--%>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.1.min.js" type="text/javascript" language="javascript"></script> 

<script type="text/javascript">
	$().ready( function(){		
		
		$("#logoutBtn").click(function() {
			location.href="../member/logout";
		});
		
		$("#joinBtn").click(function(){
			location.href="../member/regist";
		});	
		
		
	    $("#loginBtn").click( function(){
	    	
			if( $("#memberId").val() == "" ){						
				$("#memberId").focus();
				return;
			}
			else if( $("#password").val() == "" ){							
				$("#password").focus();
				return;
			}

			// Ajax 요쳥
			// $.post("URL", 요청 파라미터(항상 객체 리터럴 방식), function(response):응답파라미터 {})
			// short cut형식으로 쓴거임.
			$.post("/Traditional-Market/member/login"	// URL
					, /* {
						"memberId":$(this).val()				// Request Parameter
						, "password":$(this).val()
					  } */
					  $("#loginForm").serialize()
					, function(response) {						// Response Call back
						if( response.isBlockUser ){				// isBlockUser가 "true": LOGIN_FAIL_COUNT가 3회 이상시
							alert("잠긴 계정입니다.");
							location.href="../member/main";		//../member/login
						}
						else {									// isBlockUser가 "false": LOGIN_FAIL_COUNT가 3회 미만시
							if( response.isLoginSuccess ) {		// isLoginSuccess가 "true": 로그인 성공!
								location.href="../main/main";
							}
							else {								// isLoginSuccess가 "false": 로그인 실패!(LOGIN_FAIL_COUNT값 증가+1)				
								alert("로그인에 실패하였습니다.");
								location.href="../member/main";	//../member/login
							}
						}
						
					})
		});
	    	
	    	
 
/* 	 		$("#loginForm").attr({
				"method" : "post"
				, "action" : "/Traditional-Market/member/login"
			}).submit(); */	
	 	 
		
			
			//var loginBtn = $("<a/>", {"class":"kakao-login-btn", "text":"카카오로그인"});
			//$("submit").append(loginBtn); 
			
			//<![CDATA[
		    
		    Kakao.init('32cdf8d5c7307247cda3f39acf0762a3');		// 사용할 앱의 JavaScript 키를 설정해 주세요.
		    
		    Kakao.Auth.createLoginButton({	// 카카오 로그인 버튼을 생성합니다.
		      container: '#kakao-login-btn',
		      success: function(authObj) {
		    	  Kakao.API.request({	// Kakao.API.request라는 함수를 호출해 사용자 정보 가져오기
		    		  url:'/v1/user/me',
		    		  success: function(res) {
		    			  alert(JSON.stringify(res));		// Kakao.api.request에서 불려온 결과값 json형태로 출력
		    			  alert(JSON.stringify(authObj));	// Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
		    			  console.log(res.id);				// id정보출력(id는 res안에 있기 때문에 res.id를 불러온다.)
		    			  console.log(res.kaccount_email);	// email정보출력
		    			  // http://firstblog912.tistory.com/8
		    			  // 참고로, properties로는 프사이미지파일과 배경이미지파일, 닉제임정도 가져올 수 있음
		    			  console.log(res.properties['nickname']);	// 닉네임출력(properties에 있는 nickname접근)
		    			  console.log(res.properties.profile_image);
		    			  console.log(authObj.access_token);	// 토큰값
		    			  
		    			  $("#kakao-profile").append(res.properties['nickname']);
		    			  $("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"}));
						 // $("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"}));
		    		  }
		    	  })
		        alert(JSON.stringify(authObj));
		      },
		      fail: function(err) {
		         alert(JSON.stringify(err));
		      }
		    }); 
		   //]]>

	 		
	});
	 	
			
</script>
</head>

<!-- <body> -->	
		<c:choose>
			<c:when test="${not empty sessionScope._USER_.memberId}">
					<div style="width: 370px; height: 150px;">
						${sessionScope._USER_.memberId}님 환영합니다.
						<div>
							${sessionScope._USER_.membership}등급    
							${sessionScope._USER_.point}point
						</div>
						<br>
						<div style="width: 370px; height: 25px;">
							<input type="button" id="logoutBtn" value="로그아웃"/>
							<input type="button" id="myInfoBtn" value="내 정보" />
							<a id="kakao-login-btn"></a>
						</div>
					</div>
			</c:when>
			<c:otherwise>
				<form id="loginForm">
					<div>
						<input type="text" id="mId" name="memberId" placeholder="아이디"/>
					</div>
					<div>
						<input type="password" id="mPassword" name="password" placeholder="비밀번호" />
					</div>
					<div id="submit" style="width: 370px; height: 50px;">
						<input type="button" id="loginBtn" value="로그인" />
						<input type="button" id="joinBtn" name="joinBtn" value="회원가입" />
						<a id="kakao-login-btn"></a>			
					</div>
				</form>
			</c:otherwise>
		</c:choose>
	

<!-- </body>
</html> -->