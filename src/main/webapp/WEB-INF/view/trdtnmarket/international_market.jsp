<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> -->
<title>전국재래시장</title>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/international.css" />
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"/>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=32cdf8d5c7307247cda3f39acf0762a3&libraries=services,clusterer,drawing"></script>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script async custom-element="amp-form" src="/Traditional-Market/js/amp-form-0.1.js"></script> 

<script type="text/javascript">
 
 	$().ready(function(){
 		
 	 	var container = document.getElementById('map'); 			//지도를 담을 영역의 DOM 레퍼런스
 		var options = { 											//지도를 생성할 때 필요한 기본 옵션
 			 center: new daum.maps.LatLng(35.10155, 129.0284),		//지도의 중심좌표.
 			 level: 3 												//지도의 레벨(확대, 축소 정도)
 		};
 			
 		var map = new daum.maps.Map(container, options); 			//지도 생성 및 객체 리턴 
 		
 		
 		var imageSrc = 'http://localhost:8080/Traditional-Market/img/store.png', // 마커이미지의 주소입니다    
 	    imageSize = new daum.maps.Size(80, 80), 				// 마커이미지의 크기입니다
 	    imageOption = {offset: new daum.maps.Point(27, 69)}; 	// 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
 	      
 		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
 		var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption) 
 	    
 		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
 		var zoomControl = new daum.maps.ZoomControl();
 		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
 		

		var iwRemoveable = true; 	// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
		

		var sw = new daum.maps.LatLng(35.102636, 129.027937), 	// 사각형 영역의 남서쪽 좌표
	    ne = new daum.maps.LatLng(35.099538, 129.028430); 		// 사각형 영역의 북동쪽 좌표

	    // <사각형>
		// 사각형을 구성하는 영역정보를 생성합니다
		// 사각형을 생성할 때 영역정보는 LatLngBounds 객체로 넘겨줘야 합니다
		var rectangleBounds = new daum.maps.LatLngBounds(sw, ne);
	
		// 지도에 표시할 사각형을 생성합니다
		var rectangle = new daum.maps.Rectangle({
		    bounds: rectangleBounds, 		// 그려질 사각형의 영역정보
		    strokeWeight: 4, 				// 선의 두께
		    strokeColor: '#33ceb3', 		// 선의 색깔
		    strokeOpacity: 1, 				// 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명
		    strokeStyle: 'shortdashdot', 	// 선의 스타일
		    fillColor: '#76decc', 			// 채우기 색깔
		    fillOpacity: 0.8 				// 채우기 불투명도 
		});
	
		
		rectangle.setMap(map);	// 지도에 사각형을 표시
        
        let marker;				// 마커	
        let infowindow;			// 인포윈도우
        var isCheck = true;		// 클릭상태
       
        $(".sName").click(function(){
        	var latitude = $(this).closest(".storeInfo").find(".latitude").text();
        	var longitude = $(this).closest(".storeInfo").find(".longitude").text();
        	var name = $(this).closest(".storeInfo").find(".sName").text();
        	var marketId = $(this).closest(".storeInfo").find(".marketId").text();
        	
        	if ( isCheck ) {
        		// 마커
        		marker = new daum.maps.Marker({				// 마커를 생성
    	 		    position: new daum.maps.LatLng(latitude, longitude),
    	 		   	image: markerImage, 					// 마커이미지 설정 
    	 		    clickable: true							// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설	정	    
    	 		});
        		
        		// 인포윈도우
        		infowindow = new daum.maps.InfoWindow({						// 인포윈도우를 생성
             		position : new daum.maps.LatLng(latitude, longitude),	// 인포윈도우 표시 위치
             	    content : '<div><b>'+name+'</b></div>', 				// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능
             	    removable : iwRemoveable
         		});
        		
        		
	        	$(this).closest(".storeInfo").find(".representative").slideDown(100);
	        	$(this).closest(".storeInfo").find(".phone").slideDown(100);
	        	$(this).closest(".storeInfo").find(".storeInformation").slideDown(100);
	        	$(this).closest(".storeInfo").find(".picture").slideDown(100);	  
	        	$(this).closest(".storeInfo").find(".storeRating").slideDown(100);
	        	        	
	        	marker.setMap(map);							// 마커가 지도 위에 표시되도록 설정		  
	        	infowindow.open(map, marker);  				// 마커 위에 인포윈도우를 표시
	        		        	       	
	        	//$("#inputStoreReply").show();
	        	//$("#storeReplyList").show();
	        	
	        	// 하나의 상점에 평점과 댓글 
	        	$.post(
         				"/Traditional-Market/oneTrdtnMarket/oneStore"
         				, {
         					"storeId": $(this).closest(".storeInfo").find(".storeId").text()
         				}
         				, function(response){
         					if ( response.storeVO ) {
       							var spanStart1 = $('<span class="rate"></span>');
     							var input5 = $('<input type="radio" id="star5" name="rating" value="5" />');
     							var label5 = $(' <label for="star5" title="text">5 stars</label>');
     							var input4 = $('<input type="radio" id="star4" name="rating" value="4" />');
     							var label4 = $('<label for="star4" title="text">4 stars</label>');
     							var input3 = $('<input type="radio" id="star3" name="rating" value="3" />');
     							var label3 = $('<label for="star3" title="text">3 stars</label>');
     							var input2 = $('<input type="radio" id="star2" name="rating" value="2" />');
     							var label2 = $('<label for="star2" title="text">2 stars</label>');
     							var input1 = $('<input type="radio" id="star1" name="rating" value="1" />');
     							var label1 = $('<label for="star1" title="text">1 star</label>');       							
       							var spanStart2 = $('<span style="margin-top: 100px"></span>');
     							var inputStoreId = $('<input type="hidden" name="storeId" value="'+response.storeVO.storeId+'" />');
     							var inputMarketId = $('<input type="hidden" name="marketId" value="'+response.storeVO.marketId+'" />');
     							var inputReply = $('<textarea name="reply" placeholder="건전한 댓글 문화를 위해 타인에게 불쾌감을 주는 비하단어들 사용을 자제합시다 :)" style="width:600px; height:50px; display: inline-block;"></textarea>');
     							var submit = $('<input type="submit" id="registBtn" value="등록" style="width:50px; height:50px";/>');	
       							
         						
       							spanStart1.addClass("rate");
       							
         						$("#replyDiv").prepend(spanStart1);
         						$(spanStart1).append(input5);
         						$(spanStart1).append(label5);
         						$(spanStart1).append(input4);
         						$(spanStart1).append(label4);
         						$(spanStart1).append(input3);
         						$(spanStart1).append(label3);
         						$(spanStart1).append(input2);
         						$(spanStart1).append(label2);
         						$(spanStart1).append(input1);
         						$(spanStart1).append(label1);
         						$("#replyDiv").append(spanStart2);
         						$(spanStart2).append(inputStoreId);
         						$(spanStart2).append(inputMarketId);
         						$(spanStart2).append(inputReply);
         						$(spanStart2).append(submit);

         						
         						      							
         						var list = response.storeVO.storeReplyList;
         						for ( var i in list ) { 	
         							var storeReplyId = list[i].storeReplyId;
         							var memberId = list[i].memberId;
         							var reply = list[i].reply;
         							var crtDate = list[i].crtDate;
         							
         							var startDiv = $('<div class="startDiv"></div>')
         							var oneReplyDiv = $('<div class="replyId" style="display: none">' + storeReplyId + '</div>')
             						var mDiv = $('<div class="member" style="font-weight: bold; color: #000080; font-size: 20px">'+ memberId +'</div>')
             						var rDiv = $('<div class="content">' + crtDate + '</div>')
             						var cDiv = $('<div class="crtdate" style="font-size: 20px">'+ reply +'</div>')
             						var cDivYes = $('<div class="deleteY" style="font-size: 20px">해당 댓글은 삭제되었습니다.</div>')
             						var delBtn = $('<input type="button" class="delBtn" value="삭제">')         				            						
             						
             						var session = "${sessionScope._USER_.memberId}";
             						
             						
             					    $("#storeReplyList").prepend(startDiv);
             						
             					    
             					   $(startDiv).append(oneReplyDiv);
             						//$(startDiv).append($(oneReplyDiv)); 저기 태그에 $()이거 안 감싸는대신에 이렇게 쓰는데 이거 적용 안됨!!!!!!!!!!!!!
             						//$("#storeReplyList").append(oneReplyDiv);		
             						
             						$(startDiv).append(mDiv);
             						//$(startDiv).append($(mDiv));
         							//$("#storeReplyList").append(mDiv);			// 회원ID
         							
         							$(startDiv).append(rDiv);
         							//$(startDiv).append($(rDiv));
         							//$("#storeReplyList").append(rDiv);			// 작성일        	
         							
         							if (list[i].deleteReply == 'N') {				// 해당 댓글 삭제 여부 = N
         								$(startDiv).append(cDiv);
         								//$(startDiv).append($(cDiv));
         								//$("#storeReplyList").append(cDiv);	
            							if( session == memberId ) { 				// 댓글 삭제하기	
             								$(startDiv).append(delBtn);
             								//$(startDiv).append($(delBtn));    								
             								//$("#storeReplyList").append(delBtn);	// 댓글 삭제버튼     								
             							} 
         							}
         							else {											// 해당 댓글 삭제 여부 = Y
         								$(startDiv).append(cDivYes);
         								$(".delBtn").remove();		// 댓글들 (회원ID와 일치시 버튼)
         								//$(startDiv).append($(cDivYes));
         								//$("#storeReplyList").append(cDivYes);       								
         							}
         							
						
         						}        						
         					}
         					else {
         						alert("다시 시도해주세요");
         					}
         		}); // post end

         	     $("#storeReplyList").on('click', '.delBtn', function() {	// shadow dom한테 event 거는 법!!!!!
	         	    	var replyId = $(this).closest(".startDiv").find(".replyId").text();
	         			$.post(
	         				       "/Traditional-Market/storeReply/delete"
	         				       , {
	         				    	   "storeReplyId": replyId
	         				       }
	         				       , function(response) {
	         				    	   if( response.isSuccessDeleteReply ) {
	         				    		    alert("작성자의 댓글이 삭제되었습니다.");
	         				    		   location.href="../trdtnmarket/oneMarket?marketId="+marketId;
	         				    	   }
	         				    	   else {
	         				    		  alert("다시 시도해주세요.");
	         				    	   }
	         				       }
	         					)
            		});
        
	        	isCheck = false;       	
        	}
        	
        	else {
	        	$(this).closest(".storeInfo").find(".representative").slideUp(100);
	        	$(this).closest(".storeInfo").find(".phone").slideUp(100);
	        	$(this).closest(".storeInfo").find(".storeInformation").slideUp(100);
	        	$(this).closest(".storeInfo").find(".picture").slideUp(100);
	        	$(this).closest(".storeInfo").find(".storeRating").slideUp(100);
				
				// 지도 위의 마커를 제거하는 코드
				marker.setMap(null);
				//marker.setVisible(false);
				
				infowindow.setMap(null);
	        	//infowindow.close();
	        	
	        		        	
	        	$("#replyDiv").empty();
	        	$(".replyId").remove();
	        	$(".member").remove();		// 댓글들 (회원ID)
	        	$(".crtdate").remove();		// 댓글들 (회원ID이 작성한 날짜)
	        	$(".content").remove();		// 댓글들 (회원ID이 작성한 내용)
	        	$(".deleteY").remove();		// 댓글들 (회원ID이 작성한 내용을 삭제)
	        	$(".delBtn").remove();		// 댓글들 (회원ID와 일치시 버튼)
	        	
	        	isCheck = true;
        	}
        })
     
        
 	});
 	
 	 
</script>



</head>
<!-- <body> --> 

		<div style="margin-left:200px; margin-top:200px; display: inline-block;">
		    <c:forEach items="${trdtnmarket.storeList}" var="stores">
		    	<div class="storeInfo">
		    		<li><a class="sName" style="cursor: pointer; color: #000000;  text-decoration:none;"><b>${stores.storeName}</b></a></li>
		    		<div class="storeId" style="display: none;">${stores.storeId}</div>
					<div class="marketId" style="display: none;">${stores.marketId}</div>
			        <div class="representative" style="display: none;"> - 대표: ${stores.representative}</div>
			        <div class="storeInformation" style="display: none;"> - 소개: ${stores.storeInformation}</div>
			        <div class="phone" style="display: none;"> - 전화번호: ${stores.phone}</div>
			        <div class="latitude" style="display: none;">${stores.latitude}</div>
			        <div class="longitude" style="display: none;">${stores.longitude}</div>
			        <div class="storeRating" style="display: none;"> - 평점: ${stores.storeRating} / 5</div>
			        <br>
			        <div class="picture" style="display: none;">
			        	<img src="/Traditional-Market/img/international/${stores.picture}" width="300" height="200"> 
			        </div>			        
				</div>
		    </c:forEach>
		</div>
	
		<!-- 지도가 붙을 위치 -->	
		
	 	<div id="map" style="width:600px; height:600px; margin-left:300px; margin-top:200px; display: inline-block;">
	 		
	 	</div>



		<!-- 하나의 상점 평점과 댓글 "작성" -->
		<%-- <c:if test="${not empty sessionScope._USER_.memberId}"> --%>
		<div id="inputStoreReply" style="margin-left:300px; margin-top:100px;">
			<form id="replyForm" action="/Traditional-Market/store/reply/write" method="post">
				<div id="replyDiv">
				</div>
			</form>
			<br>
		</div>	

		<hr>
		
		<!-- 하나의 상점 평점과 댓글 작성 "후기"-->  <!-- 이것도 반복문 돌려야함. 댓글만 위에서 반복문이 돌음 -->
		<div>
			<div id="storeReplyList">
			</div>	
		</div>
		
		<%-- <c:forEach items="${storeVO.replyList}" var="OneReply">	
				<div class="replyDiv" style="margin-left: ${(OneReply.level-1) * 30}px" >
					<div>${OneReply.memberId}</div>	<!-- <div>${reply.memberId}</div> -->
					<div>${OneReply.crtDate}</div>				
					<div>${OneReply.reply}</div>
					<c:if test="${OneReply.memberId eq sessionScope._USER_.memberId}">
						<input type="button" class="replyDeleteBtn" value="삭제"/>
					</c:if>
				</div>
			</c:forEach> --%>
</body>
</html> 