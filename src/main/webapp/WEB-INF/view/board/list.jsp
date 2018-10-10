<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Traditional-Market</title>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/layout.css">
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"/>
<script type="text/javascript">
/* 	$().ready(function(){
		var selectSearch = $("#selectSearch").val();
		
		if ( selectSearch == 1 ){
			$.POST
			$("searchText").val();
		}
		else  {
			$("searchText").val();
		}	
	})  */
</script>


<div>

	<div id="oneWrapper">
		<div id="headerWrapper">
			<div class="number header box">글 번호</div><!-- 
			 --><div class="subject header box">제목</div><!--
			 --><div class="writer header box">작성자</div><!--
			 --><div class="create-date header box">작성일</div><!--
			 --><div class="create-date header box">조회수</div><!--
			 --><div class="create-date header box">추천수</div>
	</div>
	<c:choose>
		<c:when test="${not empty boardVOList}">
			<c:forEach items="${boardVOList}" var="board">
				<div class="contentWrapper">
					<div class="number box">${board.rowNum}</div><!-- 
					 --><div class="subject box">
						 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
						 		${board.title}
						 	</a>
					 </div><!--
						--><div class="writer box">${board.memberVO.memberId}</div><!--
						--><div class="create-date box">${board.crtDate}</div><!--
						--><div class="view-count box">${board.viewCount}</div><!--
						--><div class="recommend-count box">${board.recommendCount}</div>
				</div>
				<hr/>
			</c:forEach>
		</c:when>
		<c:otherwise>
			<div id="no-articles">
				등록된 게시글이 없습니다.
			</div>
		</c:otherwise>
		</c:choose>
		
		<div class="padded">
			<form id="searchForm" onsubmit="javascript:movePage(0);">
				${pagenation}
				<div>
				    <select name="selectSearch"> 
						<option value="0">제목</option>
						<option value="1">작성자</option>
					</select> 
					<input type="text" name="searchKeyword" value='${boardSearchVO.searchKeyword}'/>			
					<a href="/Traditional-Market/board/list/init">검색 초기화</a> <!-- 이렇게하면 사용자가 검색했던 칸에 검색어 지워서 엔터쳐서 전체목록페이지를 보게할 필요가 없음. -->
				</div>
			</form>
		</div>
		
		<div class="padded">	
			<a href="/Traditional-Market/board/write">글 작성</a>
		</div>
	</div>
	
</div>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"/>