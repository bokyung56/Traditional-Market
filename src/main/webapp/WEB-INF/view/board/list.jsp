<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">  -->
<!-- <title>게시판</title> -->

<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/boardList.css" />
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"/>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">

	$().ready(function(){

		/* var sTitle = "<c:out value='${boardSearchVO.searchTitle}' />";
		var sMemId = "<c:out value='${boardSearchVO.searchMemberId}' />";  */
			
		/* $('select[name=selectSearch]').change(function() { */
		$('select[name=selectSearch]').keyup(function(e){
			if(e.keyCode == 13){
				if( $(this).val()=="1" ) { 
					$("#search").attr("name","searchTitle");
					$("#search").attr("value", $("#searchPart").find("#search").val());
					console.log( $("#searchPart").find("#search").val() );
					/* var inputTwo = $('<input type="text" id="searchTitle" name="searchTitle" value='${boardSearchVO.searchTitle}'/>');
					$("#searchPart").closest("#searchBtn").find("#searchBtn").before(inputTwo); */ 
				}
				else if( $(this).val()=="2" ){ 
					$("#search").attr("name","searchMemberId");
					$("#search").attr("value", $("#searchPart").find("#search").val());
				
					/* var inputThree = $('<input type="text" id="searchMemberId" name="searchMemberId" value='${boardSearchVO.searchMemberId}'/>');
					$("#searchPart").closest("#searchBtn").find("#searchBtn").before(inputThree); */
				}
			}	
		});
		 
	})
	
</script>

<body>
	<div id="allBoard">
		<!-- <div id="boardpart"> -->
			<div id="menuBoard">
				> 게시판
			</div>
			<div id="wrapper">
				<div id="headerWrapper">
					<div class="number header box fontSizeOne">글 번호</div><!-- 
					 --><div class="subject header box fontSizeOne">제목</div><!--
					 --><div class="writer header box fontSizeOne">작성자</div><!--
					 --><div class="create-date header box fontSizeOne">작성일</div><!--
					 --><div class="view header box fontSizeOne">조회수</div><!--
					 --><div class="recommend header box fontSizeOne">추천수</div>
				</div>
				<c:choose>
					<c:when test="${not empty boardVOList}">
						<c:forEach items="${boardVOList}" var="board">
							<div class="contentWrapper">
								<div class="number box fontSizeTwo">${board.rowNum}</div><!-- 
								 --><div class="subject box fontSizeTwo">
									 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
									 		${board.title}
									 	</a>
								 </div><!--
								 	--><div class="writer box fontSizeTwo">${board.memberVO.memberId}</div><!--
									--><div class="create-date box fontSizeTwo">${board.crtDate}</div><!--
									--><div class="view box fontSizeTwo">${board.viewCount}</div><!--
									--><div class="recommend box fontSizeTwo">${board.recommendCount}</div>
							</div>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<div id="no-articles">
							등록된 게시글이 없습니다.
						</div>
					</c:otherwise>
				</c:choose>
			<!-- </div> -->
			
			<div id="writePart">	
				<!-- <a href="/Traditional-Market/board/write">글 작성</a> -->
				<input type="button" id="writeBtn" value="글 작성" onclick="location.href='/Traditional-Market/board/write'" />
			</div>
			
			<div id="searchPart">
				<form id="searchForm" onsubmit="javascript:movePage(0);">
					<div id="pagenation">
						${pagenation}
					</div>
					<div>
					    <select id="selectSearch" name="selectSearch"> 
					    	<option value="0">제목+내용</option>
							<option value="1">제목</option>
							<option value="2">작성자</option>
						</select> 
						<input type="text" id="search" name="searchKeyword" value='${boardSearchVO.searchKeyword}'/>
						<!-- <a href="/Traditional-Market/board/list/init">검색 초기화</a> --> <!-- 이렇게하면 사용자가 검색했던 칸에 검색어 지워서 엔터쳐서 전체목록페이지를 보게할 필요가 없음. -->
						<input type="button" id="searchBtn" value="검색 초기화" onclick="location.href='/Traditional-Market/board/list/init'" />
					</div>
				</form>
			</div>
			
		</div>
	</div>
</body>	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"/>