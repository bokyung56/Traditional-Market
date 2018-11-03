<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>온누리 지도</title>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/header.css" />
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>


	<!-- html5에서 새로나온 시멘트태그이고 div를 대체하기 위해서 나온 것 -->
   <div id="wrapperHeader">
		<div id="member_login">
			<c:if test="${not empty sessionScope._USER_}">
				${sessionScope._USER_.memberId}&nbsp;님 
				&nbsp; &nbsp; ${sessionScope._USER_.point}&nbsp;point
				<input type="button" id="hLogoutBtn" value="로그아웃"
						onclick="location.href='../member/logout'" />
				<input type="button" id="hMyInfoBtn" value="내 정보"
						onclick="location.href='../member/myInformation'" />
			</c:if>
		</div>
		<div id="wrapperInner">
			<div id="header_logo">
				<a href="/Traditional-Market/main/main"> 
					<img src="http://localhost:8080/Traditional-Market/img/logo.png">
				</a>
			</div>
			<div id="header_search"">
				<div id="search_main">
					<input type="text" name="search_main">
				</div>
				<div id="search_button">
					<img src="http://localhost:8080/Traditional-Market/img/searchKeyword.png">
				</div>
			</div>  
		</div>

		<div class="menubar">
			<ul>
				<li><a href="/Traditional-Market/notice">소식•알림</a></li>
				<li><a href="/Traditional-Market/trdtnmarket/map">재래시장</a></li>
				<li><a href="/Traditional-Market/board/list">게시판</a></li>
				<li><a href="/Traditional-Market/qna">QnA</a></li>
				<div class="nav-underline"></div>
			</ul>
		</div> 
	</div>
</head>