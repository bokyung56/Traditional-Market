<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<title>전통시장: 로그인</title>
<script src="//developers.kakao.com/sdk/js/kakao.min.js"></script>
<script src="Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery/jquery-1.9.1.min.js" type="text/javascript" language="javascript"></script>

<script type="text/javascript">
	$(document).ready( function(){
	/* 	var loginBtn = $("<a/>", {"class":"kakao-login-btn", "text":"카카오로그인"});
		$("submit").append(loginBtn); */
		
		//<![CDATA[
	    
	    Kakao.init('32cdf8d5c7307247cda3f39acf0762a3');		// 사용할 앱의 JavaScript 키를 설정해 주세요.
	    
	    Kakao.Auth.createLoginButton({	// 카카오 로그인 버튼을 생성합니다.
	      container: '#kakao-login-btn',
	      success: function(authObj) {
	    	  Kakao.API.request({	// Kakao.API.request라는 함수를 호출해 사용자 정보 가져오기
	    		  url:'/v1/user/me',
	    		  success: function(res) {
	    			  alert(JSON.stringify(res));		// Kakao.api.request에서 불려온 결과값 json형태로 출력
	    			  alert(JSON.stringify(authObj));	// Kakao.Auth.createLoginButton에서 불러온 결과값 json형태로 출력
	    			  console.log(res.id);				// id정보출력(id는 res안에 있기 때문에 res.id를 불러온다.)
	    			  console.log(res.kaccount_email);	// email정보출력
	    			  // http://firstblog912.tistory.com/8
	    			  // 참고로, properties로는 프사이미지파일과 배경이미지파일, 닉제임정도 가져올 수 있음
	    			  console.log(res.properties['nickname']);	// 닉네임출력(properties에 있는 nickname접근)
	    			  console.log(res.properties.profile_image);
	    			  console.log(authObj.access_token);	// 토큰값
	    			  
	    			  $("#kakao-profile").append(res.properties['nickname']);
	    			  $("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"}));
					  //$("#kakao-profile").append($("<img/>",{"src":res.properties.profile_image,"alt":res.properties.nickname+"님의 프로필 사진"}));
	    		  }
	    	  })
	        //alert(JSON.stringify(authObj));
	      },
	      fail: function(err) {
	         alert(JSON.stringify(err));
	      }
	    });
	   //]]>
	    
	})

	

</script>

</head>


<body>

	<form>
		<div>
			<input type="id" name="id" placeholder="아이디"/>
		</div>
		<div>
			<input type="password" name="password" placeholder="비밀번호" />
		</div>
		<div id="submit">
			<input type="submit" value="로그인" />
			<a id="kakao-login-btn"></a>
		</div>

	</form>


</body>
</html>