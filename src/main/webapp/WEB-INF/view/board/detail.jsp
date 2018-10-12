<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${boardVO.title}</title>

<script src = "/Traditional-Market/js/Chart.bundle.js"></script>
<script src = "/Traditional-Market/js/Chart.bundle.min.js"></script>
<script src = "/Traditional-Market/js/Chart.js"></script>
<script src = "/Traditional-Market/js/Chart.min.js"></script>


<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">


	$().ready(function(){
		
		// 댓글 남녀 비율
		var ctx = document.getElementById('genderChart').getContext('2d');
		var myChart = new Chart(ctx, {
			type: 'bar',									// 막대모양으로 차트를 그리겠다
		    data: {
		        labels: ["여자 회원👩", "남자 회원🧑"],				// x축 데이터 
		        datasets: [{
		        	label: "회원 성별 비율",						// 그래프 제목
		            data: [$("#menCnt").val(), $("#womenCnt").val()], //y축 데이터, 컨트롤러에서 모델로 받아온다. 
		            backgroundColor: [
		                //'rgba(255, 99, 132, 0.2' red
		                //'rgba(54, 162, 235, 0.2)' blue
						//'rgba(255, 206, 86, 0.2)' yellow
		            	//'rgba(75, 192, 192, 0.2)' green
		            	//'rgba(153, 102, 255, 0.2)' purple
		            	//'rgba(255, 159, 64, 0.2)'orange
		            	'rgba(255, 159, 64, 0.2)',
		            	'rgba(153, 102, 255, 0.2)'
		            ],
		            borderColor: [
		            	'rgba(255, 159, 64, 1)',
		                'rgba(153, 102, 255, 1)'
		
		            ],
		            borderWidth: 1
		        }
		        ]
		    },
		    options: {
		        scales: {
		            yAxes: [{
		                ticks: {
		                    beginAtZero:true
		                }
		            }]
		        }
		    }
		});
		
		
		// <게시글 추천하기> ex) var countBtn = '<input type="button" id="countBtnnnn" value="'+count+'"/>';	
		//var recoCancelBtn = '<input type="button" id="recoCancelBtn" value="추천♥"/>';	
		var isRecommendBtnStatus = true;
 		$("#recommendBtn").click(function(){
			if( isRecommendBtnStatus ) {
				$.post("/Traditional-Market/board/recommend"		// URL
						, {
							"boardId": $("#bId").val()				// Request Parameter
							, "token":$("#csrfToken").val()	
						}
						, function(response) {						// Response Call back
							if( response.isSuccess ){				// true
								alert("추천되었습니다.");
								$("#recommendSpan").text(response.recommendCnt);
								isRecommendBtnStatus = false;
							}
							else {									// false
								alert("다시시도해주세요.");
							} 				
						});
			}
			else {
				$.post("/Traditional-Market/board/recommend/cancel"		// URL
						, {
							"boardId": $("#bId").val()					// Request Parameter
							, "token":$("#csrfToken").val()	
						}
						, function(response) {							// Response Call back
							if( response.isSuccess ){					// true
								alert("추천 취소되었습니다.");
								//#("#recoCount").find("#recommendSpan").before(recoCancelBtn);
								//#("#recoCount").find("#recommendBtn").remove();
								$("#recommendSpan").text(response.recommendCnt);
								isRecommendBtnStatus = true;
							}
							else {										// false
								alert("다시시도해주세요.");
							} 				
						});			
			}
 			
		});
 		
 		
 		
	 	// <댓글 지우기>
	 	$(".replyDeleteBtn").click(function(){
	 		var reId = $(this).closest(".replyDiv").find(".replyId").val();
	 			
   			$.post("/Traditional-Market/reply/delete"
  					, {
  						"boardReplyId": reId
  				      }
  				    , function(response) {
  						   if( response.reply_Id ){				// true
  								alert("삭제되었습니다.");
  								location.href="../board/detail?boardId="+$("#bId").val();
  							}
  							else {									// false
  								alert("삭제 실패야~");
  							}
  				         });  
	 		});
		

		// <대댓글 달기>
 	 	var inputReply = '<textarea name="reply" id="rereplyinput" placeholder="주제와 무관한 댓글, 악플은 삭제 될 수 있습니다." style="width: 650px; height: 30px"></textarea>';
		var buttonReply = '<input type="submit" id="rereplyBtn" value="등록"/>';
		
		var isStatus = true;
		$(".reReplyBtn").click(function() {
			if ( isStatus ) {
				$(this).closest(".replyDiv").find(".reReplyBtn").val("답글취소");				
				$(this).closest(".replyDiv").find("form").append(inputReply);
				$(this).closest(".replyDiv").find("form").append(buttonReply);
				isStatus = false;
			}
			else {						
					$(this).closest(".replyDiv").find("form").find("#rereplyinput").remove();					
					$(this).closest(".replyDiv").find("form").find("#rereplyBtn").remove();
					$(this).closest(".replyDiv").find(".reReplyBtn").val("답글");
					isStatus = true;
			 	 	
			}						  
		});
		
		
		// <댓글 좋아요>
		var isGoodBtnStatus = true;
		
 		$(".goodBtn").click(function(){
 			var goodValue = $(this).closest(".replyDiv").find(".replyId").val();
 			var goodSpan = $(this).closest(".replyDiv").find(".goodSpan");
 			
 			if ( isGoodBtnStatus ) {							// 좋아요 +1
					$.post("/Traditional-Market/reply/good"		// URL
							, {
								"boardReplyId": goodValue		// Request Parameter	
							}
							, function(response) {				// Response Call back
								if( response.good ){			// true
									alert("좋아요");
									goodSpan.text(response.goodCount);
									isGoodBtnStatus = false;
								}
								else {							// false
									alert("이미 싫어요를 누르셨습니다.");
								} 				
					});
 			}
 			else {												// 좋아요 -1
 				$.post("/Traditional-Market/reply/goodCancel"	// URL
						, {
							"boardReplyId": goodValue			// Request Parameter	
						}
						, function(response) {					// Response Call back
							if( response.good ){				// true
								alert("좋아요가 취소되었습니다.");
								goodSpan.text(response.goodCount);
								isGoodBtnStatus = true;
							}
							else {								// false
								alert("이미 좋아요를 누르셨습니다.");
							} 				
				});			
 			}
		});
 		
 		
		// <댓글 싫어요>
		var isBadBtnStatus = true;
		
 		$(".badBtn").click(function(){
 			var badValue = $(this).closest(".replyDiv").find(".replyId").val();
 			var badSpan = $(this).closest(".replyDiv").find(".badSpan");
 			
 			if ( isBadBtnStatus ) {								// 싫어요 +1
 				$.post("/Traditional-Market/reply/bad"			// URL
 						, {
 							"boardReplyId": badValue			// Request Parameter	
 						}
 						, function(response) {					// Response Call back
 							if( response.bad ){					// true
 								alert("싫어요");
 								badSpan.text(response.badCount);
 								isBadBtnStatus = false;
 							}
 							else {								// false
 								alert("이미 좋아요를 누르셨습니다.");
 							} 				
 				});
 			}
 			else {												// 싫어요 -1
 				$.post("/Traditional-Market/reply/badCancel"	// URL
 						, {
 							"boardReplyId": badValue			// Request Parameter	
 						}
 						, function(response) {					// Response Call back
 							if( response.bad ){					// true
 								alert("싫어요가 취소되었습니다.");
 								badSpan.text(response.badCount);
 								isBadBtnStatus = true;
 							}
 							else {								// false
 								alert("다시 시도해주세요");
 							} 				
 				});
 			}

		});
 		
 		
 		
 		
		
	}); 
		

</script>

</head>
<body>
	<%-- <jsp:include page="/WEB-INF/view/common/header.jsp"/> --%>
	<c:choose>
		<c:when test="${boardVO.deleteBoard eq N}">
			<div>
				<c:if test="${boardVO.memberId eq sessionScope._USER_.memberId}">
					<a href="/Traditional-Market/board/modify/${boardVO.boardId}">수정</a>
					<a href="<c:url value='/board/delete/${boardVO.boardId}'/>">삭제</a>
				</c:if>	
			</div>
			<h1>
				${boardVO.title} 
				<span style="font-size: 12pt;">${boardVO.crtDate}</span>
			</h1>
			<h3>${boardVO.memberId}</h3>
			
			<div>
				<c:if test="${not empty boardVO.picture}">
						<img src="/Traditional-Market/board/download/${boardVO.boardId}" style="width: 400px; height: 200px" /> 
				</c:if>
			</div>
			<div style="width: 900px; height: 300px;">
				${boardVO.content}
			</div>
		
			<input type="hidden"  id="womenCnt" value="${boardVO.womenCnt}"/>
			<input type="hidden"  id="menCnt" value="${boardVO.menCnt}"/>
			
			<!-- 추천 -->	
			<div id="recoCount" style="text-align: center;">
				<%-- <a href="<c:url value='/board/recommend/${boardVO.boardId}?token=${sessionScope._CSRF_TOKEN_}'/>">추천</a> --%>
				<input type="hidden"  id="bId" value="${boardVO.boardId}"/>
				<input type="hidden"  id="csrfToken" value="${sessionScope._CSRF_TOKEN_}"/>
				<input type="hidden"  id="reCount" value="${boardVO.recommendCount}"/>
				<input type="button" id="recommendBtn" value="추천♡" />
				<span id="recommendSpan">${boardVO.recommendCount}</span>
			</div>
		</c:when>
		<c:otherwise>
		
		</c:otherwise>	
	
	댓글   조회수${boardVO.viewCount} 
	<hr/>
	
	<div style="width: 100%; text-align: right;">
		<a href="/Traditional-Market/board/list">목록</a>
		<a href="/Traditional-Market/board/write">글쓰기</a>
	</div>
	
	
	<div style="width: 400px; height: 200px">
		<canvas id="genderChart" ></canvas>
	</div>
	
	<hr/>
	<form action="/Traditional-Market/reply/write" method="post">
		<input type="hidden" name="boardId" value="${boardVO.boardId}" />
		<input type="hidden" name="parentReplyId" value="0" />
		<textarea name="reply" placeholder="건전한 댓글 문화를 위해 타인에게 불쾌감을 주는 비하단어들 사용을 자제합시다 :)" style="width: 650px; height: 50px"></textarea>
		<input type="submit" value="등록" />
	</form>
	<hr/>
	
	<div class="replyList">
		<c:forEach items="${boardVO.replyList}" var="reply">
			<div class="replyDiv" style="margin-left: ${(reply.level-1) * 30}px" >
				<input type="hidden" name="boardReplyId" class="replyId" value="${reply.boardReplyId}" />
				<input type="hidden" name="memberId" class="memberId" value="${reply.memberId}" />
				<div>${reply.memberVO.memberId}</div>	<!-- <div>${reply.memberId}</div> -->
				<div>${reply.crtDate}</div>				
				<div>${reply.reply}</div>
				<div>
					<input type="button" class="reReplyBtn" value="답글"/>
					<div id="reReplyDiv">
						<form action="/Traditional-Market/reply/write" method="post">
							<input type="hidden" name="boardId" value="${boardVO.boardId}" />
							<input type="hidden" name="parentReplyId" value="${reply.boardReplyId}" />
						</form>
					</div>
					<c:if test="${reply.memberId eq sessionScope._USER_.memberId}">
						<input type="button" class="replyDeleteBtn" value="삭제"/>
					</c:if>
					<div class="Reply_GoodBadDiv">
						<input type="button" class="goodBtn" value="좋아요😍"/><span class="goodSpan">${reply.goodCount}</span>
						<input type="button" class="badBtn" value="싫어요😡"/><span class="badSpan">${reply.badCount}</span>
					</div>			
				</div>
			</div> 
		</c:forEach>
	</div>
	
	<hr/>
	

	
</body>
</html>