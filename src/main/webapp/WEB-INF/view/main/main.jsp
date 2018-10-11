<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/view/common/layout/layout_header.jsp"></jsp:include>

<script type="text/javascript">
</script>

	
	<div id="lojo_button">
		<c:if test="${empty sessionScope._USER_}">
			<jsp:include page="/WEB-INF/view/member/login.jsp"></jsp:include>
		</c:if> 
	</div>
	
<jsp:include page="/WEB-INF/view/common/layout/layout_footer.jsp"></jsp:include>