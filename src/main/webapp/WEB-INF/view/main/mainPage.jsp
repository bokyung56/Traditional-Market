<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Traditional-Market</title>
<link rel="stylesheet" type="text/css" href="Traditional-Market/css/layout.css">
<script src="TraditionalMarket/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$().ready(function(){
		$("#login").click(function(){
			location.href="../member/login"
		});
		$("#join").click(function(){
			location.href="../member/regist"
		});
	})
</script>
</head>
<body>


	<div id="wrapper">
		<header>
			<c:if test=${not empty sessionScope._USER_}">
				${sessionScope._USER_.name}
			</c:if>
		</header>
	</div>
	<div>
		<input type="button" id="login" name="login" value="로그인">
	</div>
	<div>
		<input type="button" id="join" name="join" value="회원가입">
	</div>
	
</body>
</html>