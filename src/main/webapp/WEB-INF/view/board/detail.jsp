<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardVO.title}</title>

<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
	$().ready(function(){
		var btn = '<input type="button" value="삭제"/>';  			// Shadow Dom
		
		
		
		$.post("/Traditional-Market/reply/delete"					// URL
					, {
						"boardReplyId":$("#replyId").val();			// Request Parameter
						"memberId":$("#memberId").val();
					}
					, function(response) {							// Response Call back
						if( response.replyId ){						// true
							alert("삭제되었습니다.");
							$("#replyList").after(btn);
						}
						else {										// false
							alert("삭제 실패야~");
						} 
					});	  */
		
/* 	 	var inputReply = '<input type="text" placeholder="주제와 무관한 댓글, 악플은 삭제 될 수 있습니다."/>';
		var buttonReply = '<input type="button" value="등록"/>';
		
		$(".replyList").find(".reReplyBtn").click(function() {
		 	$(".reReplyBtn").after(inputReply);
			$(".reReplyBtn").after(buttonReply);  
			
			alert("과연 되나요?");
		}); */
		
	});
</script>

</head>
<body>
	<%-- <jsp:include page="/WEB-INF/view/common/header.jsp"/> --%>
	<div>
		<a href="/Traditional-Market/board/modify/${boardVO.boardId}">수정</a>
		<a href="/Traditional-Market/board/delete/${boardVO.boardId}">삭제</a>
	</div>
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
		<a href="/Traditional-Market/board/list">목록</a>
	</div>
	
	댓글   조회수${boardVO.viewCount}
	<hr/>
	<form action="/Traditional-Market/reply/write" method="post">
		<input type="hidden" name="boardId" value="${boardVO.boardId}" />
		<input type="hidden" name="parentReplyId" value="0" />
		<textarea name="reply" placeholder="건전한 댓글 문화를 위해 타인에게 불쾌감을 주는 비하단어들 사용을 자제합시다 :)"></textarea>
		<input type="submit" value="등록" />
	</form>
	<hr/>
	
	<div class="replyList">
		<c:forEach items="${boardVO.replyList}" var="reply">
			<div style="margin-left: ${(reply.level-1) * 30}px" >
				<input type="hidden" name="boardReplyId" id="replyId" value="${reply.boardReplyId}" />
				<input type="hidden" name="memberId" id="memberId" value="${reply.memberId}" />
				<div>${reply.memberVO.memberId}</div>	<!-- <div>${reply.memberId}</div> -->
				<div>${reply.crtDate}</div>				
				<div>${reply.reply}</div>
				<div>
					<input type="button" class="reReplyBtn" value="답글"/>
					<!-- <input type="button" id="deleteBtn" value="삭제"/> -->
					<input type="button" value="좋아요"/>
					<input type="button" value="싫어요"/>				
				</div>
			</div> 
		</c:forEach>
	</div>
	
	<hr/>
	

	
</body>
</html>