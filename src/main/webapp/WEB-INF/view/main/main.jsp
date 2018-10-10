<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<script type="text/javascript">
	$().ready(function(){
				
		
		$("#logoutBtn").click(function() {
			location.href="../member/logout";
		});
		$("#joinBtn").click(function(){
			location.href="../member/regist";
		});	

		
	});
</script>

	<form id="loginForm">
		<div id="lojo_button">
			     <jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
			<!-- <input type="button" id="loginBtn" name="loginBtn" value="로그인" /> -->
			<input type="button" id="logoutBtn" name="logoutBtn" value="로그아웃" />
		</div>
		<div>
			<input type="button" id="joinBtn" name="joinBtn" value="회원가입" />
		</div>
		
	</form>
	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>