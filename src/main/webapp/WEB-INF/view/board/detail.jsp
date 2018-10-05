<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardVO.title}</title>
</head>
<body>
	<h1>
		${boardVO.title} 
		<span style="font-size: 12pt;">${boardVO.crtDate}</span>
	</h1>
	<h3>${boardVO.memberId}</h3>
	
	<div>
		<c:if test="${not empty boardVO.picture}">
			<img src="/Traditional-Market/board/download/${boardVO.boardId}" style="width: 50px;" /> 
		</c:if>
	</div>
	<div>
		${boardVO.content}
	</div>
	
	<div>
		<a href="/Traditional-Market/board/modify/${boardVO.boardId}">수정</a>
		<a href="/Traditional-Market/board/delete/${boardVO.boardId}">삭제</a>
		<a href="/Traditional-Market/board/list">목록</a>
	</div>
	
	<hr/>
	
	
</body>
</html>