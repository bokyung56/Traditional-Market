<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/Traditional-Market/js/jquery-3.3.1.min.js" type="text/javascript"></script>
<script type="text/javascript">
/* <!-- 1. 똑같은 form이여도 전송하는 위치가 다를 수 있으니
     2. 벨리데이션 체크: 공백이면 종료(return)하고 만약 이 모든것들이 이상이 없으면 submit을 해라. 
                     다시 말하면. submit하기전에 체크하는건 다 벨리데이션 체크 --> */
	$().ready(function(){
		
		$("#writeBtn").click(function() {
			if( $("#title").val() == "" ) {				
				$("#title").focus();
				return;
			}
			else if ( $("#content").val() == "" ) {		
				$("#content").focus();
				return;
			}			
/* 			$("#writeForm").attr({
				method : "post"
				, action : "/Traditional-Market/board/write"
				, enctype : "multipart/form-data"
			})
			.submit(); */	
			$("#writeForm").submit();
		});
		
	});
</script>
</head>

<body>
	<form id="writeForm" method="post" action="/Traditional-Market/board/write"  enctype="multipart/form-data">
		<input type="hidden" name="token" value="${sessionScope._CSRF_TOKEN_}"/>
		<div>
			<input type="text" id="title" name="title" placeholder="제목" value="${boardVO.title}"/>
		</div>
		<div>
			<textarea type="text" id="content" name="content" placeholder="내용">${boardVO.content}</textarea>
		</div>
		<div>
			<input type="file" id="pictureFile" name="pictureFile" placeholder="파일선택" accept=".jpg, .img, .png"/> 
			<!-- <input type="file" multiple="multiple" id="pictureFiles" name="pictureFiles" placeholder="파일선택" accept=".jpg, .img, .png"/> -->
		</div>
		<div>
			<!-- <input type="submit" id="wirte" value="글쓰기"/> -->
			<input type="button" id="writeBtn" name="writeBtn" value="글쓰기"/>
		</div>
	</form>

</body>
</html>