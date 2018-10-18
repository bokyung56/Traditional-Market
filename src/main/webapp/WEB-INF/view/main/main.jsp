<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<script type="text/javascript">
</script>

	<div>
		<!-- 배너 -->
		<span style="display: inline-block; margin-top:300px">
			<img src="http://localhost:8080/Traditional-Market/img/banner.png" width="1000" height="300"/> 
		</span> 
		
		<!-- 로그인하는 곳 -->
		<span id="lojo_button">
			<c:if test="${empty sessionScope._USER_}">
				<jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
			</c:if> 
		</span>
	</div>	
	
	<!-- 인기 게시글 -->	
	<div>
		<h3><b style="font-weight: bold; color: #000080;">인기 게시글</b></h3>
		<div id="oneWrapper" style="border:1px solid; padding:10px; width: 1000px">
			<div id="headerWrapper">
				<div class="number header box" style="display: inline-block; padding-right: 10px">글 번호</div><!-- 
				 --><div class="subject header box" style="display: inline-block; padding-right: 100px">제목</div><!--
				 --><div class="writer header box" style="display: inline-block; padding-right: 10px">작성자</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">작성일</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">조회수</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">추천수</div>
			</div>
			<c:choose>
				<c:when test="${not empty boardRecommendList}">
					<c:forEach items="${boardRecommendList}" var="board">
						<div class="contentWrapper">
						<div class="number box" style="display: inline-block; padding-right: 10px">${board.rowNum}</div><!-- 
						 --><div class="subject box" style="display: inline-block; padding-right: 100px">
							 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
							 		${board.title}
							 	</a>
						 </div><!--
						 	--><div class="writer box" style="display: inline-block; padding-right: 10px">${board.memberId}</div><!--
							--><div class="create-date box" style="display: inline-block; padding-right: 10px">${board.crtDate}</div><!--
							--><div class="view-count box" style="display: inline-block; padding-right: 10px">${board.viewCount}</div><!--
							--><div class="recommend-count box" style="display: inline-block; padding-right: 10px">${board.recommendCount}</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div id="no-articles">
						등록된 게시글이 없습니다.
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>
	
	
	
	
	
	
	<!-- 최신 게시글 -->
	<div>
		<h3><b style="font-weight: bold; color: #000080;">최신 게시글</b></h3>
		<div id="oneWrapper" style="border:1px solid; padding:10px; width: 1000px">
			<div id="headerWrapper">
				<div class="number header box" style="display: inline-block; padding-right: 10px">글 번호</div><!-- 
				 --><div class="subject header box" style="display: inline-block; padding-right: 100px">제목</div><!--
				 --><div class="writer header box" style="display: inline-block; padding-right: 10px">작성자</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">작성일</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">조회수</div><!--
				 --><div class="create-date header box" style="display: inline-block; padding-right: 10px">추천수</div>
			</div>
			<c:choose>
				<c:when test="${not empty boardTenList}">
					<c:forEach items="${boardTenList}" var="board">
						<div class="contentWrapper">
						<div class="number box" style="display: inline-block; padding-right: 10px">${board.rowNum}</div><!-- 
						 --><div class="subject box" style="display: inline-block; padding-right: 100px">
							 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
							 		${board.title}
							 	</a>
						 </div><!--
						 	--><div class="writer box" style="display: inline-block; padding-right: 10px">${board.memberId}</div><!--
							--><div class="create-date box" style="display: inline-block; padding-right: 10px">${board.crtDate}</div><!--
							--><div class="view-count box" style="display: inline-block; padding-right: 10px">${board.viewCount}</div><!--
							--><div class="recommend-count box" style="display: inline-block; padding-right: 10px">${board.recommendCount}</div>
						</div>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<div id="no-articles">
						등록된 게시글이 없습니다.
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div> 
	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>