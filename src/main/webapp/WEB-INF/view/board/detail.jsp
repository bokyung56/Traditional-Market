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
	<jsp:include page="/WEB-INF/view/common/header.jsp"/>
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
	
	<div>
		야~!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		${boardVO.replyList}
		<c:forEach items="${boardVO.replyList}" var="reply">
			야!!!!!!!!!!!!!!!!!!!!!
			<%-- <div style="margin-left: ${(reply.level-1) * 30}px">  --%>
				<div>${reply.memberVO.name} (${reply.memberVO.memberId})</div>
				<div>${reply.crtDate}</div>
				<div>${reply.reply}</div>
			<!-- </div> -->
		</c:forEach>
	</div>
	
	<hr/>
	
	<form action="/Traditional-Market/reply/write" method="post">
		<input type="hidden" name="boardReplyId" value="${boardReplyVO.boardReplyId}" />
		<input type="hidden" name="boardId" value="${boardVO.boardId}" />
		<input type="hidden" name="parentReplyId" value="0" />
		<textarea name="reply"></textarea>
		<input type="submit" value="등록" />
	</form>
	
</body>
</html>