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
						<h3>인기 게시글</h3>
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
									<div class="contentSize">
										<c:forEach items="${boardRecommendList}" var="board">
											<div class="contentWrapper">
												<div class="number box cntr" >${board.rowNum}</div><!-- 
											 --><div class="subject box cntr">
												 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}" style="color: #000000; text-decoration:none">
												 		${board.title}
												 	</a>
											 	</div><!--
											 	--><div class="writer box cntr">${board.memberId}</div><!--
												--><div class="create-date box">${board.crtDate}</div><!--
												--><div class="view box cntr">${board.viewCount}</div><!--
												--><div class="recommend box cntr">${board.recommendCount}</div>
											</div>
										</c:forEach>
									</div>
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
						<h3>최신 게시글</h3>
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
									<div class="contentSize">
										<c:forEach items="${boardTenList}" var="board">
											<div class="contentWrapper">
											<div class="number box cntr">${board.rowNum}</div><!-- 
											 --><div class="subject box cntr">
												 	<a href="/Traditional-Market/board/detail?boardId=${board.boardId}" style="color: #000000; text-decoration:none">
												 		${board.title}
												 	</a>
											 </div><!--
											 	--><div class="writer box cntr">${board.memberId}</div><!--
												--><div class="create-date box">${board.crtDate}</div><!--
												--><div class="view box cntr">${board.viewCount}</div><!--
												--><div class="recommend box cntr">${board.recommendCount}</div>
											</div>
										</c:forEach>
									</div>
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
				<div id="login_popTrd" style="float:left;">
					<span id="lojo_button">
						<%-- <c:if test="${empty sessionScope._USER_}"> --%>
							<jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
						<%-- </c:if> --%> 
					</span>
					<!-- 인기재래시장 -->
					<div id="popularTrdtnName">
						<h3>인기 재래시장</h3>
							<div id="num">
								<c:forEach var="i" begin="1" end="10">
									<div>
										${i}
									</div>
								</c:forEach>						
							</div>
							<div id="name">
								<c:forEach items="${trdtnName}" var="trdtn">
									 <div>
									 	<marquee direction=up scrollamount=1>
										<a href="/Traditional-Market/trdtnmarket/oneMarket?marketId=${trdtn.marketId}" style="color: #000000; text-decoration:none">
											 ${trdtn.name}
										</a>
										</marquee>
									</div>	
								</c:forEach>
							</div>
					</div>
					<div id="mainBanner2">
						<img src="http://localhost:8080/Traditional-Market/img/mainBanner2.png"/> 
					</div>
				</div>
			</div>	
</body>
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>