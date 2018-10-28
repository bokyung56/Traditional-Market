<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/boardList.css" />
<script type="text/javascript">
</script>

<body>
	<section class="inline">
		<!-- <aside class="aside_left"></aside> -->
		<section>
			<div id="allBackground">
				<div style="width:20%; height:100%; float:left">
				</div>
				<div style="float:left;">
					<!-- 배너 -->
					<span id="banner">
						<img src="http://localhost:8080/Traditional-Market/img/banner.png"/> 
					</span> 		
					<!-- 인기 게시글 -->	
					<div id="popularity">
						<h3><b style="font-size: 30px; font-weight: bold; color: #000000;">인기 게시글</b></h3>
						<div class="oneWrapper">
							<div class="headerWrapper">
								<div class="number header box">글 번호</div><!-- 
								 --><div class="subject header box">제목</div><!--
								 --><div class="writer header box">작성자</div><!--
								 --><div class="create-date header box">작성일</div><!--
								 --><div class="view header box">조회수</div><!--
								 --><div class="recommend header box">추천수</div>
							</div>
							<c:choose>
								<c:when test="${not empty boardRecommendList}">
									<c:forEach items="${boardRecommendList}" var="board">
										<div class="contentWrapper">
										<div class="number box cntr" >${board.rowNum}</div><!-- 
										 --><div class="subject box cntr">
											 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
											 		${board.title}
											 	</a>
										 </div><!--
										 	--><div class="writer box cntr">${board.memberId}</div><!--
											--><div class="create-date box">${board.crtDate}</div><!--
											--><div class="view box cntr">${board.viewCount}</div><!--
											--><div class="recommend box cntr">${board.recommendCount}</div>
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
					<div id="theNewest">
						<h3><b style="font-size: 30px; font-weight: bold; color: #000000;">최신 게시글</b></h3>
						<div class="oneWrapper">
							<div class="headerWrapper">
								<div class="number header box">글 번호</div><!-- 
								 --><div class="subject header box">제목</div><!--
								 --><div class="writer header box">작성자</div><!--
								 --><div class="create-date header box">작성일</div><!--
								 --><div class="view header box">조회수</div><!--
								 --><div class="recommend header box">추천수</div>
							</div>
							<c:choose>
								<c:when test="${not empty boardTenList}">
									<c:forEach items="${boardTenList}" var="board">
										<div class="contentWrapper">
										<div class="number box cntr">${board.rowNum}</div><!-- 
										 --><div class="subject box cntr">
											 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}">
											 		${board.title}
											 	</a>
										 </div><!--
										 	--><div class="writer box cntr">${board.memberId}</div><!--
											--><div class="create-date box">${board.crtDate}</div><!--
											--><div class="view box cntr">${board.viewCount}</div><!--
											--><div class="recommend box cntr">${board.recommendCount}</div>
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
				</div> 
				<!-- 로그인하는 곳 -->
				<div id="login_popTrd" style="float:left">
					<span id="lojo_button">
						<%-- <c:if test="${empty sessionScope._USER_}"> --%>
							<jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
						<%-- </c:if> --%> 
					</span>
					<!-- 인기재래시장 -->
					<div id="popularTrdtnName">
						<h3><b style="font-size: 30px; font-weight: bold; color: #000000;">인기 재래시장</b></h3>
					</div>
				</div>
			</div>	
</body>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>