<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전통시장: 회원가입</title>
<script src="/TraditionalMarket/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
/* <!-- 1. 똑같은 form이여도 전송하는 위치가 다를 수 있으니
     2. 벨리데이션 체크: 공백이면 종료(return)하고 만약 이 모든것들이 이상이 없으면 submit을 해라. 
                     다시 말하면. submit하기전에 체크하는건 다 벨리데이션 체크 --> */
	$(document).ready( function(){
		$("#regist").click( function(){
			if( $("#memberId").val() == "" ){
				
				$("#memberId").focus()
				return;
			}
			else if( $("#password").val() == "" ){
				
				$("#password").focus()
				return;
			}
			else if( $("#password_ckeck").val() == "" ){
				
				$("#password_ckeck").focus()
				return;
			}
			else if( $("#name").val() == "" ){
				
				$("#name").focus()
				return;
			}
			else if( $("#yy").val() == "" ){
				
				$("#yy").focus()
				return;
			}
			else if( $("#mm").val() == "" ){
				
				$("#mm").focus()
				return;
			}
			else if( $("#dd").val() == "" ){
				
				$("#dd").focus()
				return;
			}
			else if( $("#email").val() == "" ){
				
			}
			$("#registForm").attr({
				"method" : "post"
				, "action" : "/TraditionalMarket/member/regist"
			})
			.submit()
			
		})
	})

</script>
</head>
<body>

<form id="registForm">
	<div>
		<h3 class="join_title">아이디</h3>
		<input type="text" id="memberId" name="memberId" placeholder="아이디">
	</div>
	<div>
		<h3 class="join_title">비밀번호</h3>
		<input type="password" id="password" name="password" placeholder="비밀번호">
		<h3 class="join_title">비밀번호 재확인</h3>
		<input type="password" id="password_ckeck" name="password_ckeck" placeholder="비밀번호 재확인">
	</div>
	<div>
		<h3 class="join_title">이름</h3>
		<input type="text" id="name" name="name" placeholder="이름">
	</div>
	<div class="bir_wrap">
		<h3 class="join_title">생년월일</h3>
		<input type="date" name="birth" min="1930-01-01">
		<!-- <span>
			<input type="text" id="yy" placeholder="년(4자)" maxlength="4">
		</span>
		<span>
			<select id="mm" title="월" class="sel">
				<option value="01">1</option>
				<option value="02">2</option>
				<option value="03">3</option>
				<option value="04">4</option>
				<option value="05">5</option>
				<option value="06">6</option>
				<option value="07">7</option>
				<option value="08">8</option>
				<option value="09">9</option>
				<option value="10">10</option>
				<option value="11">11</option>
				<option value="12">12</option>
			</select>
		</span>
		<span>
			<input type="text" id="dd" placeholder="일" maxlength="2">
		</span> -->
	</div>
	<div>
		<h3 class="join_title">이메일</h3>
		<input type="text" id="email" name="email" placeholder="이메일">
	</div>
	
	<div>
		<input type="button" id="regist" value="회원가입">
	</div>
</form>

</body>
</html>