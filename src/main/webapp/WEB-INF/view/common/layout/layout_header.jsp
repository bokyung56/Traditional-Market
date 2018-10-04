<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/layout.css"/>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>

</head>
<body>
	<!-- html5에서 새로나온 시멘트태그이고 div를 대체하기 위해서 나온 것 -->
	<div id="wrapper">
		<header>
			<div id="member_login">
				<c:if test="${not empty sessionScope._USER_}">
					${sessionScope._USER_.name}
					${sessionScope._USER_.point}
				</c:if>
			</div>
 			<div id="header_logo" style="text-align: center;">
				<img src="http://localhost:8080/Traditional-Market/img/logo.png" width="200" height="100">
			</div> 
			<div id="header_search" style="text-align: center;">
				<input type="text" id="search_main" name="search_main">
			</div>
			<div id="nevbar">
				<ul class="menu">
					<li class="menu">
						<a href="/Traditional-Market/notice">소식•알림</a>
					</li>
					<li class="menu">
						<a href="/Traditional-Market/trdtnmarket">재래시장</a>
					</li>
					<li class="menu">
						<a href="/Traditional-Market/board/list">게시판</a>
					</li>
					<li class="menu">
						<a href="/Traditional-Market/qna">QnA</a>
					</li>
				</ul>
			</div>
		</header>
		<section class="inline">
			<aside>
			
			</aside>
			<section>