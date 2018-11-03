<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/myInformation.css" />
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
$().ready(function(){
	$("#password").keyup(function(){	
		$.post("/Traditional-Market/member/check/password"	// URL
				, {
					"password":$(this).val()				// Request Parameter
				  }
				, function(response) {						// Response Call back
					if( !response.password ){				// true
						$("#password-error").slideDown(100);
					}
					else {									// false
						$("#password-error").slideUp(100);
					}
				})
	})
	
	$("#password_ckeck").keyup(function(){		
		$.post("/Traditional-Market/member/check/password_ckeck"	// URL
				, {
					"password":$("#password").val()					// Request Parameter
					, "password_ckeck":$(this).val()					
				  }
				, function(response) {								// Response Call back
					if( response.password_ckeck ){					// true
						$("#password_ckeck-error").slideDown(100);
					}
					else {											// false
						$("#password_ckeck-error").slideUp(100);
					}
				})
	})
	
	$("#email").keyup(function(){		
		$.post("/Traditional-Market/member/check/email"		// URL
				, {
					"email":$(this).val()					// Request Parameter
				  }
				, function(response) {						// Response Call back
					if( !response.email ){					// true
						$("#email-error").slideDown(100);
					}
					else {									// false
						$("#email-error").slideUp(100);
						
					}
				})
	})
	
	
	$("#modifyBtn").click( function(){
		if( $("#password").val() == "" ){			
			$("#password").focus()
			return;
		}
		else if( $("#password_ckeck").val() == "" ){	
			$("#password_ckeck").focus()
			return;
		}
		else if( $("#email").val() == "" ){	
			$("#email").focus()
			return;
		}
		$("#infoForm").attr({
			"method" : "post"
			, "action" : "/Traditional-Market/member/myInformation"
		})
		.submit();	 	
	})
})
</script>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> -->
<title>내 정보</title>
</head> 
<body>
	<div id="all">
		<div id="headerOne">
			> 내 정보
		</div>
		<div id="information">
			<div id="membership">
				<div class="inHeader">회원등급</div>
				<div id="membershipFont">${myInfo.membership} 등급입니다.</div>
			</div>
			<div id="coupon">
				<div class="inHeader">쿠폰</div>
			</div>
			<div id="point">
				<div class="inHeader">포인트</div>
				<div id="pointFont">${myInfo.point} Point</div>
			</div> 
		</div>	
		
		<div id="headerTwo">
			> 내 정보 수정하기
		</div>
		<div id="infoPart">
			<form id="infoForm">
				<div>
					<h2 class="infoHeader">아이디</h2>
					<span style="font-size: 22px; margin-left: 146px;">${myInfo.memberId}</span>
				</div>
				<div>
					<h2 class="infoHeader">비밀번호</h2>
					<input type="password" id="password" name="password" maxlength="20"/>
					<div id="password-error" style="display: none;">
						10~20자 영문 대 소문자, 숫자, 특수문자를 사용하세요.
					</div>
				</div>
				<div>
					<h2 class="infoHeader">비밀번호 재확인</h2>
					<input type="password" id="password_ckeck" name="password_ckeck" maxlength="20"/>
					<div id="password_ckeck-error" style="display: none;">
						비밀번호가 일치하지 않습니다.
					</div>
				</div>
				<div>
					<h2 class="infoHeader">이름</h2>  
					<span style="font-size: 22px; margin-left: 160px;">${myInfo.name}</span>
				</div>
				<div>
					<h2 class="infoHeader">이메일</h2>			
					<input type="email" id="email" name="email" value="${myInfo.email}"/>
					<div id="email-error" style="display: none;">
						이메일 주소를 다시 확인해주세요.
					</div>
				</div>
				<div>
					<input type="button" id="modifyBtn" value="수정" />
				</div>
			</form>
		</div>
		<div id="marker">
			<img src="http://localhost:8080/Traditional-Market/img/infomarker.png">
		</div>
	</div>
</body>
</html>
