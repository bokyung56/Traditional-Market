<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- <!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"> -->
<title>전국재래시장</title>
<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"/>
<link rel="stylesheet" type="text/css" href="/Traditional-Market/css/map.css" />
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=32cdf8d5c7307247cda3f39acf0762a3&libraries=services,clusterer,drawing"></script>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
 <script type="text/javascript">
 
 	$().ready(function(){
 	 	var container = document.getElementById('map'); 			//지도를 담을 영역의 DOM 레퍼런스
 		var options = { 											//지도를 생성할 때 필요한 기본 옵션
 			 center: new daum.maps.LatLng(36.471026, 127.865800),	//지도의 중심좌표.
 			 level: 13 												//지도의 레벨(확대, 축소 정도)
 		};
 			
 		var map = new daum.maps.Map(container, options); 			//지도 생성 및 객체 리턴 
 		
 		
 		var imageSrc = 'http://localhost:8080/Traditional-Market/img/marker.png', // 마커이미지의 주소입니다    
 	    imageSize = new daum.maps.Size(40, 45), 				// 마커이미지의 크기입니다
 	    imageOption = {offset: new daum.maps.Point(27, 69)}; 	// 마커이미지의 옵션입니다. 마커의 좌표와 일치시킬 이미지 안에서의 좌표를 설정합니다.
 	      
 		// 마커의 이미지정보를 가지고 있는 마커이미지를 생성합니다
 		var markerImage = new daum.maps.MarkerImage(imageSrc, imageSize, imageOption) 
 		
 		
 		// marker.setMap(null);		// 아래 코드는 지도 위의 마커를 제거하는 코드입니다
 	    
 		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
 		var zoomControl = new daum.maps.ZoomControl();
 		map.addControl(zoomControl, daum.maps.ControlPosition.RIGHT);
 		

		var iwRemoveable = true; 	// removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다
  		
        var marketList = [];       
		 <c:forEach items="${location}" var="oneLocation">
        	var object = {};
        	object.marketId = "${oneLocation.marketId}";
        	object.name = "${oneLocation.name}";
        	object.latitude = "${oneLocation.latitude}";
        	object.longitude = "${oneLocation.longitude}";
        	object.address = "${oneLocation.address}";
        	marketList.push(object);
        </c:forEach>
        
        for (let i in marketList) {
        	let marker = new daum.maps.Marker({			// 마커를 생성
	 		    position: new daum.maps.LatLng(marketList[i].latitude, marketList[i].longitude),
	 		   	image: markerImage, 					// 마커이미지 설정 
	 		    clickable: true 						// 마커를 클릭했을 때 지도의 클릭 이벤트가 발생하지 않도록 설정
	 		});
        	     	
        	let infowindow = new daum.maps.InfoWindow({	// 인포윈도우를 생성합니다
         		position : new daum.maps.LatLng(marketList[i].latitude, marketList[i].longitude), // 인포윈도우 표시 위치
         	    content : '<div style="padding:5px; color:orange;"><b><a style="color:orange" href="http://localhost:8080/Traditional-Market/trdtnmarket/oneMarket?marketId=' + marketList[i].marketId + '">' + marketList[i].name  + '</a></b><a style="color:blue; padding-left:80px; text-align: right;" href="http://map.daum.net/link/to/'+ marketList[i].name+',' + marketList[i].latitude + ',' + marketList[i].longitude+'">길찾기</a></div><div style="padding:5px">' + marketList[i].address + '</div>', 			// 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능
         	    removable : iwRemoveable
         	});


	 		marker.setMap(map);	// 마커가 지도 위에 표시되도록 설정합니다
	 		//infowindow.open(map, marker); 
	 		
	        daum.maps.event.addListener(marker, 'click', function() {   // 마커에 클릭이벤트를 등록합니다	        	
	            infowindow.open(map, marker);  // 마커 위에 인포윈도우를 표시합니다 
	        });
        }
         
        
        //daum.maps.event.addListener(marker, 'click', function() {   // 마커에 클릭이벤트를 등록합니다
        //    infowindow.open(map, marker);  // 마커 위에 인포윈도우를 표시합니다 
        //}); 
        //<br><a href="http://map.daum.net/link/map/Hello World!,33.450701,126.570667" style="color:blue" target="_blank">큰지도보기</a> 
       // <a href="http://map.daum.net/link/to/'+ marketList[i].name + ',' + marketList[i].latitude + ',' + marketList[i].longitude" style="color:green" target="_blank">'길찾기'</a>'
 	});
 	
 	 
</script>
</head>
<!-- <body> -->
	<div id="all">
		<div id="title1">
			> 전국 재래시장 온누리 지도
		</div>
		<div id="title2">
			아래 지도에서 원하는 재래시장을 선택해주세요 :D
		</div>
		<!-- 지도가 붙을 위치 -->	
	 	<div id="map">
	 	</div>
 	</div>

<!-- </body>
</html> -->