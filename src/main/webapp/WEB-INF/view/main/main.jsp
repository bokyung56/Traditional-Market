<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<script type="text/javascript">
	$().ready(function(){
						
	});
</script>

	
	<div id="lojo_button">
		<c:if test="${empty sessionScope._USER_}">
			<jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
		</c:if> 
		<%-- <c:choose>
			<c:when test="${not empty sessionScope._USER_.memberId}">
					${sessionScope._USER_.memberId}님 환영합니다.
					<div>
						${sessionScope._USER_.membership}등급    
						${sessionScope._USER_.point}point
					</div>
					<div>
						<input type="button" id="logoutBtn" value="로그아웃" />
						<a id="kakao-login-btn"></a>
					</div>
			</c:when>
			<c:otherwise>
				<div>
					<input type="text" id="mId" name="memberId" placeholder="아이디"/>
				</div>
				<div>
					<input type="password" id="mPassword" name="password" placeholder="비밀번호" />
				</div>
				<div id="submit">
					<input type="button" id="loginBtn" value="로그인" />
					<a id="kakao-login-btn"></a>
					<input type="button" id="joinBtn" name="joinBtn" value="회원가입" />			
				</div>
			</c:otherwise>
		</c:choose> --%>
		</div>
	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>