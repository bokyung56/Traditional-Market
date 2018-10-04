<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<script type="text/javascript">
	$().ready(function(){
		$("#loginBtn").click(function() {
			location.href="../member/login";
		});
		$("#logoutBtn").click(function() {
			location.href="../member/logout";
		});
		$("#joinBtn").click(function(){
			location.href="../member/regist";
		});	
		$("#writeBtn").click(function(){
			location.href="../board/write";
		});	
	});
</script>

	<div id="lojo_button">
		<input type="button" id="loginBtn" name="loginBtn" value="로그인" />
		<input type="button" id="logoutBtn" name="logoutBtn" value="로그아웃" />
		<input type="button" id="joinBtn" name="joinBtn" value="회원가입" />
	</div>
	<div>
		<input type="button" id="writeBtn" name="writeBtn" value="글쓰기" />
	</div>
	
	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>